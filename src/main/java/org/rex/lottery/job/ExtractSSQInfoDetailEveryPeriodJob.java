package org.rex.lottery.job;

import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.rex.lottery.bean.SSQInfoDetail;
import org.rex.lottery.dao.ILotterySSQInfoDetailDAO;
import org.rex.lottery.service.DocumentParseService;
import org.rex.lottery.service.MailService;
import org.rex.lottery.util.Constants;
import org.rex.lottery.util.Lottery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * Created by RexSong on 2017/8/13.
 */

@Component
public class ExtractSSQInfoDetailEveryPeriodJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(ExtractSSQInfoDetailEveryPeriodJob.class);

    @Autowired
    private DocumentParseService documentParseService;

    @Autowired
    private MailService mailService;

    @Value("${mail.toSSX.addr}")
    private String songshengxiongMailAddr;

    @Autowired
    private ILotterySSQInfoDetailDAO lotterySSQInfoDetailDAO;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 每周2 4 7出奖 所以每周3 5 1去抓前一天数据
        DateTime latestOpenDay = DateTime.now().minusDays(1);

        SSQInfoDetail latestSSQInfoDetail = _getLatestSSQInfoDetail(latestOpenDay);
        if (latestSSQInfoDetail != null) {
            logger.info("Already Exists - {}", latestSSQInfoDetail);
            return;
        }

        String currentUrl = _getCurrentURL(latestOpenDay);

        _sendMail("SSQ Detail " + latestOpenDay.toString("yyyy-MM-dd"), currentUrl);
        documentParseService.parseAndSave(currentUrl, Lottery.SSQ_INFO_DETAIL);
    }

    private SSQInfoDetail _getLatestSSQInfoDetail(DateTime latestOpenDay) {
        String yyyyMMddOfLatest = latestOpenDay.toString("yyyy-MM-dd");
        logger.info("Extract ssqInfoDetail - {}", latestOpenDay);
        return lotterySSQInfoDetailDAO.getOne(yyyyMMddOfLatest);
    }

    private String _getCurrentURL(DateTime latestOpenDay) {
        // 17092变成17093 / 18001
        int weekOfLastOpenDay = latestOpenDay.dayOfWeek().get();
        // 周4开奖要隔3天 才到周日 其余周2 7开奖都是过两天就下一期了
        int interval = weekOfLastOpenDay == 7 ? 3 : 2;
        String yyyyMMddOfLastOpen = latestOpenDay.minusDays(interval).toString("yyyy-MM-dd");
        if (lotterySSQInfoDetailDAO.exists(yyyyMMddOfLastOpen)) {
            String msg = "can not find last open lottery - " + yyyyMMddOfLastOpen;
            logger.error(msg);
            _sendMail("Lottery ERROR", msg);
            throw new RuntimeException(msg);
        }

        SSQInfoDetail detailOfLastOpen = lotterySSQInfoDetailDAO.getOne(yyyyMMddOfLastOpen);

        // 上一期开奖编号
        String lastOpenPeriod = detailOfLastOpen.getPeriod();
        // 17093 - yy 17 - num 093
        String lastYY = lastOpenPeriod.substring(0, 2);
        int currentOpenNum = Integer.parseInt(lastOpenPeriod.substring(2, 5)) + 1;

        // latest, current 当前需要抓取的这一期年份
        int currentYear = latestOpenDay.year().get();
        String currentYY = String.valueOf(currentYear).substring(2, 4);
        if (!lastYY.equals(currentYY)) {
            //如果年份不同 期号要从1开始 否则继续在原来的期号+1
            currentOpenNum = 1;
        }
        String currentOpenNumFormatted = String.format("%03d", currentOpenNum);
        String currentPeriod = currentYY + currentOpenNumFormatted;
        return MessageFormat.format(Constants.SSQ_DETAIL_LATEST_URL, currentPeriod, currentPeriod);

    }

    private void _sendMail(String subject, String content) {
        try {
            mailService.sendSimpleMail(songshengxiongMailAddr, subject, content);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}

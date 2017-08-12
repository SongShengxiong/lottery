package org.rex.lottery.task;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.rex.lottery.bean.SSQInfoDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RexSong on 2017/8/8.
 */
public class SSQInfoDetailDataTask implements DataTask<List<SSQInfoDetail>, Document> {
    private static final Logger logger = LoggerFactory.getLogger(SSQInfoDetailDataTask.class);

    @Override
    public List<SSQInfoDetail> extract(Document document) {
        checkNotNull(document);
        Elements details = document.select("table#tablelist").select("tr.t_tr1");
        checkNotNull(details);

        List<SSQInfoDetail> result = new ArrayList<>();

        logger.info("found details size {}", details.size());
        Iterator<Element> iterator = details.iterator();
        while (iterator.hasNext()) {
            Element detail = iterator.next();
            if (detail == null) {
                logger.warn("no detail data");
                continue;
            }
            Elements data = detail.children();
            if (data == null) {
                continue;
            }

            SSQInfoDetail infoDetail = new SSQInfoDetail();
            try {
                infoDetail.period(data.get(0).text())
                        .firstRed(Integer.parseInt(data.get(1).text()))
                        .secondRed(Integer.parseInt(data.get(2).text()))
                        .thirdRed(Integer.parseInt(data.get(3).text()))
                        .forthRed(Integer.parseInt(data.get(4).text()))
                        .fifthRed(Integer.parseInt(data.get(5).text()))
                        .sixthRed(Integer.parseInt(data.get(6).text()))
                        .blue(Integer.parseInt(data.get(7).text()))
                        .totalRemainAward(Long.parseLong(data.get(9).text().replaceAll(",","")))
                        .firstLevelCount(Integer.parseInt(data.get(10).text()))
                        .firstLevelAward(Long.parseLong(data.get(11).text().replaceAll(",","")))
                        .secondLevelCount(Integer.parseInt(data.get(12).text()))
                        .secondLevelAward(Long.parseLong(data.get(13).text().replaceAll(",","")))
                        .currentPeriodSold(Long.parseLong(data.get(14).text().replaceAll(",","")))
                        .date(data.get(15).text());
            } catch (Exception e) {
                logger.error(" ERROR while parsing {}", data.text());
                continue;
            }

            result.add(infoDetail);
        }
        return result;
    }
}

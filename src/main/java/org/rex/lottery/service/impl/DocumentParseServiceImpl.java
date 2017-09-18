package org.rex.lottery.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.rex.lottery.bean.SSQInfoDetail;
import org.rex.lottery.dao.ILotterySSQInfoDetailDAO;
import org.rex.lottery.exception.LotteryException;
import org.rex.lottery.service.DocumentParseService;
import org.rex.lottery.task.DataTaskExecutor;
import org.rex.lottery.util.Lottery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by RexSong on 2017/8/1.
 */
@Service
public class DocumentParseServiceImpl implements DocumentParseService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentParseServiceImpl.class);


    @Autowired
    private ILotterySSQInfoDetailDAO lotterySSQInfoDetailDAO;

    @Override
    public boolean parseAndSave(String url, Lottery lottery) {
        Document document = _getDocument(url);

        switch (lottery) {
            case SSQ_INFO_DETAIL:
                List<SSQInfoDetail> details = DataTaskExecutor.parseLotteryDocument(document, lottery);
                lotterySSQInfoDetailDAO.addList(details);
                break;
            default:
                break;
        }

        return false;
    }

    private Document _getDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new LotteryException(e.getMessage(), url);
        }
        return document;
    }

}

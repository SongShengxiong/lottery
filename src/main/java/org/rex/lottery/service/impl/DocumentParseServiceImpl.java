package org.rex.lottery.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.rex.lottery.bean.SSQInfoDetail;
import org.rex.lottery.exception.LotteryException;
import org.rex.lottery.service.DocumentParseService;
import org.rex.lottery.task.DataTaskExecutor;
import org.rex.lottery.task.SSQInfoDetailDataTask;
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
    private CassandraTemplate cassandraTemplate;

    @Override
    public boolean parseAndSave(String url, Lottery lottery) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new LotteryException(e.getMessage(), lottery.name());
        }

        switch (lottery) {
            case SSQ_INFO_DETAIL:
                List<SSQInfoDetail> details = DataTaskExecutor.parse(document, new SSQInfoDetailDataTask());
                cassandraTemplate.insert(details);
                break;
            default:
                break;
        }

        return false;
    }

}

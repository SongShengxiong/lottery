package org.rex.lottery.job;

import org.rex.lottery.service.DocumentParseService;
import org.rex.lottery.util.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by RexSong on 2017/8/8.
 */
@Component
public class SSQInfoDetailJob {
    @Autowired
    private DocumentParseService documentParseService;

    @PostConstruct
    public void init() {
        documentParseService.parseAndSave("http://datachart.500.com/ssq/history/newinc/history.php?start=03001&end=17092", Lottery.SSQ_INFO_DETAIL);
    }
}

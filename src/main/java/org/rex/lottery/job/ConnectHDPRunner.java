package org.rex.lottery.job;

import org.apache.commons.collections.CollectionUtils;
import org.rex.lottery.bean.SSQInfoDetail;
import org.rex.lottery.dao.ILotterySSQInfoDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by RexSong on 2017/9/17.
 */
//@Component
public class ConnectHDPRunner implements CommandLineRunner {
//    @Autowired
//    private FsShell fsShell;
//
//    @Autowired
//    private org.apache.hadoop.conf.Configuration configuration;

    @Autowired
    private CassandraTemplate cassandraTemplate;

    @Autowired
    private ILotterySSQInfoDetailDAO lotterySSQInfoDetailDAO;

    @Value("${lottery.hdfs.basepath}")
    private String basePath;

    @Override
    public void run(String... args) throws Exception {
        List<SSQInfoDetail> ssqInfoDetailListInCassandra =
                cassandraTemplate.select("SELECT * FROM ssq_info_detail"
                        , SSQInfoDetail.class);
        if (CollectionUtils.isEmpty(ssqInfoDetailListInCassandra)) {
            return;
        }

        lotterySSQInfoDetailDAO.addList(ssqInfoDetailListInCassandra);
    }
}

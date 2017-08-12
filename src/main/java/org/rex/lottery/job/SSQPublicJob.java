package org.rex.lottery.job;

import org.rex.lottery.config.SSQConfig;
import org.rex.lottery.service.DocumentParseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by RexSong on 2017/8/1.
 */
@Configuration
@EnableConfigurationProperties(SSQConfig.class)
public class SSQPublicJob {
    private static final Logger logger = LoggerFactory.getLogger(SSQPublicJob.class);

    @Autowired
    private SSQConfig ssqConfig;

    @Autowired
    private DocumentParseService documentParseService;

//    @PostConstruct
    public void init() {
        List<String> fullUrls = _generateFullUrls(ssqConfig.getBaseUrls(), ssqConfig.getTypeUri());

        logger.info("generated full urls {}", fullUrls.size());
        if (fullUrls != null) {
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
            for (String fullUrl : fullUrls) {
                executorService.submit(new CrawJob(fullUrl));
            }
            executorService.shutdown();
        }
    }

    private class CrawJob implements Runnable {
        private String url;

        public CrawJob(String url) {
            this.url = url;
        }

        @Override
        public void run() {
//            documentParseService.parseAndSave(url, null);
        }
    }

    private List<String> _generateFullUrls(String[] baseUrls, String typeUri) {
        int yyBegin = ssqConfig.getYybegin();
        int yyEnd = ssqConfig.getYyend();
        int nnnBegin = ssqConfig.getNnnbegin();
        int nnnEnd = ssqConfig.getNnnend();
        List<String> fullUrls = new ArrayList<>(baseUrls.length * yyEnd * nnnEnd);
        for (String baseUrl : baseUrls) {
            for (int i = yyBegin; i <= yyEnd ; i++) {
                for (int j = nnnBegin; j <= nnnEnd ; j++) {
                    fullUrls.add(baseUrl + MessageFormat.format(typeUri,
                            String.format("%02d", i), String.format("%03d", j)));
                }
            }
        }

        return fullUrls;
    }
}

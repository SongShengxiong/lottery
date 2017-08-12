package org.rex.lottery.util;

import org.jsoup.nodes.Document;
import org.rex.lottery.bean.SSQInfoDetail;
import org.rex.lottery.task.DataTask;
import org.rex.lottery.task.SSQInfoDetailDataTask;

import java.util.List;

/**
 * Created by RexSong on 2017/8/9.
 */
public enum Lottery {
    SSQ_INFO_DETAIL("ssqInfoDetail") {
        @Override
        public DataTask<List<SSQInfoDetail>, Document> taskExecutor() {
            return new SSQInfoDetailDataTask();
        }
    };

    private String type;

    Lottery(String type) {
        this.type = type;
    }

    public static Lottery typeOf(String type) {
        for (Lottery lottery : values()) {
            if (lottery.type.equals(type)) {
                return lottery;
            }
        }

        return null;
    }

    public abstract <T, D> DataTask<T, D> taskExecutor();
}

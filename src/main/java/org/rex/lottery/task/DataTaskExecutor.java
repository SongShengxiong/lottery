package org.rex.lottery.task;

import org.jsoup.nodes.Document;
import org.rex.lottery.util.Lottery;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by RexSong on 2017/8/9.
 */
public class DataTaskExecutor {
    public static <T, D> T parse(D data, DataTask<T, D> task) {
        checkNotNull(data);
        checkNotNull(task);
        T result = task.extract(data);
        return result;
    }

    public static <T> T parseLotteryDocument(Document document, Lottery lottery) {
        return parse(document, lottery.taskExecutor());
    }
}

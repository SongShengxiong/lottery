package org.rex.lottery.task;

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
}

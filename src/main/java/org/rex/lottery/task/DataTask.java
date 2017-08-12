package org.rex.lottery.task;

/**
 * Created by RexSong on 2017/8/8.
 */
public interface DataTask<T, D> {
    T extract(D d);
}

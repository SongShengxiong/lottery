package org.rex.lottery.service;

import org.rex.lottery.util.Lottery;

/**
 * Created by RexSong on 2017/8/1.
 */
public interface DocumentParseService {
    boolean parseAndSave(String url, Lottery lottery);
}

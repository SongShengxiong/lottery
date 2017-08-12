package org.rex.lottery.util;

/**
 * Created by RexSong on 2017/8/9.
 */
public enum Lottery {
    SSQ_INFO_DETAIL("ssqInfoDetail");

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
}

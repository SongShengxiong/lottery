package org.rex.lottery.exception;

/**
 * Created by RexSong on 2017/8/9.
 */
public class LotteryException extends RuntimeException {

    private String lotteryType;

    public LotteryException(String message, String lotteryType) {
        super(message + " " + lotteryType);
        this.lotteryType = lotteryType;
    }

    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }
}

package org.rex.lottery.dao;

import org.rex.lottery.bean.SSQInfoDetail;
import org.springframework.stereotype.Service;

/**
 * Created by RexSong on 2017/9/18.
 */
@Service("lotterySSQInfoDetailDAO")
public class LotterySSQInfoDetailDAO implements ILotterySSQInfoDetailDAO {
    @Override
    public boolean exists(String dayOfYYYYMMDD) {
        return false;
    }

    @Override
    public SSQInfoDetail getOne(String dayOfYYYYMMDD) {
        return null;
    }

    @Override
    public boolean addOne(SSQInfoDetail ssqInfoDetail) {
        return false;
    }
}

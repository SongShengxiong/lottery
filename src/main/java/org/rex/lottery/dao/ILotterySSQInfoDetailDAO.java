package org.rex.lottery.dao;

import org.rex.lottery.bean.SSQInfoDetail;

import java.util.List;

/**
 * Created by RexSong on 2017/9/18.
 */
public interface ILotterySSQInfoDetailDAO {
    boolean exists(String dayOfYYYYMMDD);

    SSQInfoDetail getOne(String dayOfYYYYMMDD);

    boolean addOne(SSQInfoDetail ssqInfoDetail);

    boolean addList(List<SSQInfoDetail> ssqInfoDetailList);
}

package org.rex.lottery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rex.lottery.bean.SSQInfoDetail;
import org.rex.lottery.dao.ILotterySSQInfoDetailDAO;
import org.rex.lottery.dao.LotterySSQInfoDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryApplicationTests {

	@Autowired
    ILotterySSQInfoDetailDAO lotterySSQInfoDetailDAO;

	@Test
	public void getOne() {
        Assert.assertNotNull(lotterySSQInfoDetailDAO.getOne("2017-09-20"));
	}

	@Test
    public void addOne() {
        Assert.assertTrue(lotterySSQInfoDetailDAO.addOne(_mockSSQInfoDetail()));
    }

    private SSQInfoDetail _mockSSQInfoDetail() {
        SSQInfoDetail infoDetail = new SSQInfoDetail();
        infoDetail.period("17091")
                .firstRed(1)
                .secondRed(2)
                .thirdRed(3)
                .forthRed(4)
                .fifthRed(5)
                .sixthRed(6)
                .blue(7)
                .totalRemainAward(100l)
                .firstLevelCount(1)
                .firstLevelAward(100)
                .secondLevelCount(2)
                .secondLevelAward(200l)
                .currentPeriodSold(300)
                .date(LocalDate.now().toString());

        return infoDetail;
    }
}

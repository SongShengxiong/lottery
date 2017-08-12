package org.rex.lottery.bean;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by RexSong on 2017/8/8.
 */
@Table("ssq_info_detail")
public class SSQInfoDetail {

    @PrimaryKey
    private String period;
    @Column("first_red")
    private int firstRed;
    @Column("second_red")
    private int secondRed;
    @Column("third_red")
    private int thirdRed;
    @Column("forth_red")
    private int forthRed;
    @Column("fifth_red")
    private int fifthRed;
    @Column("sixth_red")
    private int sixthRed;
    @Column("blue")
    private int blue;

    // 奖池总奖金
    @Column("total_remain_award")
    private long totalRemainAward;

    // 一等奖注数
    @Column("first_level_count")
    private int firstLevelCount;

    // 一等奖奖金
    @Column("first_level_award")
    private long firstLevelAward;

    // 2等奖注数
    @Column("second_level_count")
    private int secondLevelCount;

    // 2等奖奖金
    @Column("second_level_award")
    private long secondLevelAward;

    // 当期销售额
    @Column("current_period_sold")
    private long currentPeriodSold;

    // date
    @Column("date")
    private String date;


    public SSQInfoDetail date(String date) {
        this.setDate(date);
        return this;
    }

    public SSQInfoDetail currentPeriodSold(long currentPeriodSold) {
        this.setCurrentPeriodSold(currentPeriodSold);
        return this;
    }

    public SSQInfoDetail secondLevelAward(long secondLevelAward) {
        this.setSecondLevelAward(secondLevelAward);
        return this;
    }

    public SSQInfoDetail secondLevelCount(int secondLevelCount) {
        this.setSecondLevelCount(secondLevelCount);
        return this;
    }

    public SSQInfoDetail firstLevelAward(long firstLevelAward) {
        this.setFirstLevelAward(firstLevelAward);
        return this;
    }

    public SSQInfoDetail firstLevelCount(int firstLevelCount) {
        this.setFirstLevelCount(firstLevelCount);
        return this;
    }

    public SSQInfoDetail totalRemainAward(long totalRemainAward) {
        this.setTotalRemainAward(totalRemainAward);
        return this;
    }

    public SSQInfoDetail period(String period) {
        this.setPeriod(period);
        return this;
    }

    public SSQInfoDetail firstRed(int firstRed) {
        this.setFirstRed(firstRed);
        return this;
    }

    public SSQInfoDetail secondRed(int secondRed) {
        this.setSecondRed(secondRed);
        return this;
    }

    public SSQInfoDetail thirdRed(int thirdRed) {
        this.setThirdRed(thirdRed);
        return this;
    }

    public SSQInfoDetail forthRed(int forthRed) {
        this.setForthRed(forthRed);
        return this;
    }

    public SSQInfoDetail fifthRed(int fifthRed) {
        this.setFifthRed(fifthRed);
        return this;
    }

    public SSQInfoDetail sixthRed(int sixthRed) {
        this.setSixthRed(sixthRed);
        return this;
    }


    public SSQInfoDetail blue(int blue) {
        this.setBlue(blue);
        return this;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getFirstRed() {
        return firstRed;
    }

    public void setFirstRed(int firstRed) {
        this.firstRed = firstRed;
    }

    public int getSecondRed() {
        return secondRed;
    }

    public void setSecondRed(int secondRed) {
        this.secondRed = secondRed;
    }

    public int getThirdRed() {
        return thirdRed;
    }

    public void setThirdRed(int thirdRed) {
        this.thirdRed = thirdRed;
    }

    public int getForthRed() {
        return forthRed;
    }

    public void setForthRed(int forthRed) {
        this.forthRed = forthRed;
    }

    public int getFifthRed() {
        return fifthRed;
    }

    public void setFifthRed(int fifthRed) {
        this.fifthRed = fifthRed;
    }

    public int getSixthRed() {
        return sixthRed;
    }

    public void setSixthRed(int sixthRed) {
        this.sixthRed = sixthRed;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public long getTotalRemainAward() {
        return totalRemainAward;
    }

    public void setTotalRemainAward(long totalRemainAward) {
        this.totalRemainAward = totalRemainAward;
    }

    public int getFirstLevelCount() {
        return firstLevelCount;
    }

    public void setFirstLevelCount(int firstLevelCount) {
        this.firstLevelCount = firstLevelCount;
    }

    public long getFirstLevelAward() {
        return firstLevelAward;
    }

    public void setFirstLevelAward(long firstLevelAward) {
        this.firstLevelAward = firstLevelAward;
    }

    public int getSecondLevelCount() {
        return secondLevelCount;
    }

    public void setSecondLevelCount(int secondLevelCount) {
        this.secondLevelCount = secondLevelCount;
    }

    public long getSecondLevelAward() {
        return secondLevelAward;
    }

    public void setSecondLevelAward(long secondLevelAward) {
        this.secondLevelAward = secondLevelAward;
    }

    public long getCurrentPeriodSold() {
        return currentPeriodSold;
    }

    public void setCurrentPeriodSold(long currentPeriodSold) {
        this.currentPeriodSold = currentPeriodSold;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SSQInfoDetail{" +
                "period='" + period + '\'' +
                ", firstRed=" + firstRed +
                ", secondRed=" + secondRed +
                ", thirdRed=" + thirdRed +
                ", forthRed=" + forthRed +
                ", fifthRed=" + fifthRed +
                ", sixthRed=" + sixthRed +
                ", blue=" + blue +
                ", totalRemainAward=" + totalRemainAward +
                ", firstLevelCount=" + firstLevelCount +
                ", firstLevelAward=" + firstLevelAward +
                ", secondLevelCount=" + secondLevelCount +
                ", secondLevelAward=" + secondLevelAward +
                ", currentPeriodSold=" + currentPeriodSold +
                ", date='" + date + '\'' +
                '}';
    }
}

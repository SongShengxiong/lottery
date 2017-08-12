package org.rex.lottery.bean;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;

/**
 * Created by RexSong on 2017/8/2.
 */
@Table("ssq_full")
public class SSQFull implements Serializable {

    @PrimaryKey
    private String period;
    private String balls;

    public SSQFull(String period, String balls) {
        this.period = period;
        this.balls = balls;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }
}

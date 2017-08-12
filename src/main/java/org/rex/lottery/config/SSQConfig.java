package org.rex.lottery.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by RexSong on 2017/8/1.
 */
@ConfigurationProperties(prefix = "rex.lottery.craw.ssq")
public class SSQConfig {
    private String[] baseUrls;
    private String typeUri;
    private int yybegin;
    private int yyend;
    private int nnnbegin;
    private int nnnend;

    public String[] getBaseUrls() {
        return baseUrls;
    }

    public void setBaseUrls(String[] baseUrls) {
        this.baseUrls = baseUrls;
    }

    public String getTypeUri() {
        return typeUri;
    }

    public void setTypeUri(String typeUri) {
        this.typeUri = typeUri;
    }

    public int getYybegin() {
        return yybegin;
    }

    public void setYybegin(int yybegin) {
        this.yybegin = yybegin;
    }

    public int getYyend() {
        return yyend;
    }

    public void setYyend(int yyend) {
        this.yyend = yyend;
    }

    public int getNnnbegin() {
        return nnnbegin;
    }

    public void setNnnbegin(int nnnbegin) {
        this.nnnbegin = nnnbegin;
    }

    public int getNnnend() {
        return nnnend;
    }

    public void setNnnend(int nnnend) {
        this.nnnend = nnnend;
    }
}

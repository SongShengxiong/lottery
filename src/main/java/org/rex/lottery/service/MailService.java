package org.rex.lottery.service;

/**
 * Created by RexSong on 2017/8/13.
 */
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}

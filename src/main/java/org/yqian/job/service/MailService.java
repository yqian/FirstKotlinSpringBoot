package org.yqian.job.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {
    public void sendEmail(String email) {
        log.info("Send Email To Lowest Bid");
    }
}

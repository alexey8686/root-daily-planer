package com.bae.spb.mailsender;

import com.bae.spb.daily.planner.dto.MailSendingRequest;

import javax.mail.MessagingException;


public interface EmailService {

     void sendEmail(MailSendingRequest request) throws MessagingException;
}

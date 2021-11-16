package com.bae.spb.mailsender;

import com.bae.spb.daily.planner.dto.MailSendingRequest;
import com.bae.spb.daily.planner.dto.TaskDto;
import com.bae.spb.daily.planner.dto.TaskTypeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("DailyPlanner")
    private String from;

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public void sendEmail(MailSendingRequest request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(request.getSendTo());
        helper.setSubject(request.getTaskDto().getTaskName());
        helper.setText(constructHtmlMessage(request.getTaskDto()), true);

        mailSender.send(message);
    }

    private String constructHtmlMessage(TaskDto taskDto) {
        String htmlMessage = "<html lang='en'>\n" +
                "<body>\n" +
                "<h1>" + taskDto.getTaskName() + "</h1>\n" +
                "<h3>" + taskDto.getTaskLevel() + "</h3>\n";
        if (!taskDto.getTaskTypes().isEmpty()) {
            htmlMessage = htmlMessage + "<ul>\n";
            for (String taskType : taskDto.getTaskTypes().stream().map(TaskTypeDto::getName).collect(Collectors.toList())) {
                htmlMessage = htmlMessage + "<li>" + taskType + "</li>\n";
            }
            htmlMessage = htmlMessage + "</ul>\n";
        }
        htmlMessage = htmlMessage + "<p>" + taskDto.getDescription() + "</p>\n" +
                "</body>\n" +
                "</html>";
        return htmlMessage;
    }
}

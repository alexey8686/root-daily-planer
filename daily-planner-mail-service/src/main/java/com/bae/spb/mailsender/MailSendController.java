package com.bae.spb.mailsender;

import com.bae.spb.daily.planner.dto.MailSendingRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MailSendController {

    private final EmailService mailService;

    public MailSendController(EmailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/sendTask")
    public ResponseEntity<String> sendEmail(@RequestBody MailSendingRequest request, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
        try {
            mailService.sendEmail(request);
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.of(Optional.of(e.getMessage()));
        }
    }
}

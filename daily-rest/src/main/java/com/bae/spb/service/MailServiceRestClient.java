package com.bae.spb.service;

import com.bae.spb.daily.planner.dto.MailSendingRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "daily-planner-mail-service")
public interface MailServiceRestClient {

    @PostMapping("/sendTask")
    ResponseEntity<String> sendEmail(@RequestBody MailSendingRequest request);

}

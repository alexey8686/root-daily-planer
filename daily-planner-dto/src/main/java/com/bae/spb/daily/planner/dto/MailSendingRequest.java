package com.bae.spb.daily.planner.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class MailSendingRequest implements Serializable {
    public static final long serialVersionUID = 1l;
    private TaskDto taskDto;

    @NotBlank(message = "SendTo have to be not empty")
    private String sendTo;
}

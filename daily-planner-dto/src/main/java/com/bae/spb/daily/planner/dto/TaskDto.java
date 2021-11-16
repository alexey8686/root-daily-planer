package com.bae.spb.daily.planner.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 *  Dto for task
 */
@Setter
@Getter
public class TaskDto implements Serializable {

    public static final long serialVersionUID = 1l;

    private String id;

    @NotBlank(message = "Task name is mandatory")
    private String taskName;

    private String description;

    private LocalDateTime deadLine;

    private boolean isClosed;

    @NotBlank(message = "Task level is mandatory")
    private String taskLevel;

    private Set<TaskTypeDto> taskTypes;

    private String userId;

}

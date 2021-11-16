package com.bae.spb.daily.planner.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TaskRequest implements Serializable {
    public static final long serialVersionUID = 1l;

    private String taskLevel;

    private List<String> taskTypeCode;

    private String name;

    private String userId;

    private String taskId;

}

package com.bae.spb.daily.planner.service;

import com.bae.spb.daily.planner.dto.TaskTypeDto;
import com.bae.spb.daily.planner.model.TaskType;

import java.util.List;

public interface TaskTypeService {

    List<TaskTypeDto> findAllTaskType();

    void saveOrUpdateTaskType (TaskTypeDto taskType);

    void deleteTaskType (String id);

    TaskTypeDto findTaskTypeByCode (String code);
}

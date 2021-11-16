package com.bae.spb.daily.planner.repository;

import com.bae.spb.daily.planner.model.TaskType;

import java.util.List;

public interface TaskTypeRepositoryDa {

    List<TaskType> findAllByCodeCollection(List<String> codeList);

    void saveOrUpdateTaskType(TaskType taskType);

    void deleteTaskType (String id);

    TaskType findOneByCode(String code);

    List<TaskType> findAll();
}

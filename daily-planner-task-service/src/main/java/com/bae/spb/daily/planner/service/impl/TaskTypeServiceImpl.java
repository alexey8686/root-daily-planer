package com.bae.spb.daily.planner.service.impl;

import com.bae.spb.daily.planner.dto.TaskTypeDto;
import com.bae.spb.daily.planner.repository.TaskTypeRepositoryDa;
import com.bae.spb.daily.planner.service.TaskTypeService;
import com.bae.spb.daily.planner.service.mapper.TaskTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeServiceImpl implements TaskTypeService {

    private final TaskTypeRepositoryDa taskTypeRepositoryDa;

    private final TaskTypeMapper mapper;

    @Autowired
    public TaskTypeServiceImpl(TaskTypeRepositoryDa taskTypeRepositoryDa, TaskTypeMapper mapper) {
        this.taskTypeRepositoryDa = taskTypeRepositoryDa;
        this.mapper = mapper;
    }

    @Override
    public List<TaskTypeDto> findAllTaskType() {
        return mapper.convertListToDto(taskTypeRepositoryDa.findAll());
    }

    @Override
    public void saveOrUpdateTaskType(TaskTypeDto taskType) {
        taskTypeRepositoryDa.saveOrUpdateTaskType(mapper.convertToTaskType(taskType));
    }

    @Override
    public void deleteTaskType(String id) {
        taskTypeRepositoryDa.deleteTaskType(id);
    }

    @Override
    public TaskTypeDto findTaskTypeByCode(String code) {
        return mapper.convertToDto(taskTypeRepositoryDa.findOneByCode(code));
    }
}

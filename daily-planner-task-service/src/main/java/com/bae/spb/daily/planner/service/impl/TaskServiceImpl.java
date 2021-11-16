package com.bae.spb.daily.planner.service.impl;

import com.bae.spb.daily.planner.dto.TaskDto;
import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.dto.TaskResponse;
import com.bae.spb.daily.planner.repository.TaskRepositoryDa;
import com.bae.spb.daily.planner.service.TaskService;
import com.bae.spb.daily.planner.service.mapper.TaskMapper;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

  private final TaskRepositoryDa taskRepositoryDa;

  private final TaskMapper mapper;

  @Autowired
  public TaskServiceImpl(TaskRepositoryDa taskRepositoryDa, TaskMapper mapper) {
    this.taskRepositoryDa = taskRepositoryDa;
    this.mapper = mapper;
  }

  @Override
  public TaskResponse getAllTaskFiltered(TaskRequest request) {
    TaskResponse taskResponse = new TaskResponse();
    taskResponse.setDtoList(mapper.convertToDtoList(taskRepositoryDa.getAllTaskFiltered(request)));
    return taskResponse;
  }

  @Override
  public TaskResponse saveOrUpdateTask(TaskDto dto) {
    TaskResponse response = new TaskResponse();

    TaskDto taskDto = mapper.convertToDto(
        taskRepositoryDa.saveOrUpdateTask(mapper.convertToTask(dto)));
    response.setDtoList(Collections.singletonList(taskDto));
    return response;
  }

  @Override
  public TaskResponse getTaskByUserId(String id, String userId) {
    TaskResponse taskResponse = new TaskResponse();
    TaskDto taskDto = mapper.convertToDto(taskRepositoryDa.getTaskById(id, userId));
    taskResponse.setDtoList(Collections.singletonList(taskDto));
    return taskResponse;
  }

  @Override
  public void deleteTask(TaskRequest request) {
    taskRepositoryDa.deleteTask(request);
  }



  @Override
  public void deleteAllTasks(String userId) {
    taskRepositoryDa.deleteAllTasks(userId);
  }



}

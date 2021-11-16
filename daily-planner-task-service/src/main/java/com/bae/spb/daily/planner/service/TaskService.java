package com.bae.spb.daily.planner.service;

import com.bae.spb.daily.planner.dto.TaskDto;
import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.dto.TaskResponse;

public interface TaskService {

  TaskResponse getAllTaskFiltered(TaskRequest request);

  TaskResponse saveOrUpdateTask(TaskDto dto);

  TaskResponse getTaskByUserId(String id, String userId);

  void deleteTask(TaskRequest request);

  void deleteAllTasks(String userId);

}

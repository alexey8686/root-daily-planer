package com.bae.spb.daily.planner.repository;

import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.model.Task;
import java.util.List;

public interface TaskRepositoryDa {

  List<Task> getAllTaskFiltered(TaskRequest request);

  Task saveOrUpdateTask(Task dto);

  Task getTaskById(String id, String userId);

  void deleteTask(TaskRequest request);

  void deleteAllTasks(String userId);

}

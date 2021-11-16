package com.bae.spb.daily.planner.repository.impl;

import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.helper.RequestHelper;
import com.bae.spb.daily.planner.model.Task;
import com.bae.spb.daily.planner.repository.TaskRepository;
import com.bae.spb.daily.planner.repository.TaskRepositoryDa;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TaskRepositoryDaImpl implements TaskRepositoryDa {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private RequestHelper requestHelper;


  @Override
  public List<Task> getAllTaskFiltered(TaskRequest request) {
    List<Task> tasks = taskRepository.findAll(requestHelper.getExample(request));
    return tasks;
  }

  @Override
  public Task saveOrUpdateTask(Task task) {
    task.setDateOfCreation(LocalDateTime.now());
    return taskRepository.save(task);
  }

  @Override
  public Task getTaskById(String id, String userId) {
    return null;
  }


  @Transactional
  @Override
  public void deleteTask(TaskRequest request) {
    List<Task> tasks = taskRepository.findAll(requestHelper.getExample(request));
    if (!tasks.isEmpty()) {
      taskRepository.delete(tasks.get(0));
    }
  }

  @Transactional
  @Override
  public void deleteAllTasks(String userId) {

    if (userId == null) {
      taskRepository.deleteAll();
    } else {
      List<Task> allByUserId = taskRepository.findAllByUserId(userId);
      if (!allByUserId.isEmpty()) {
        taskRepository.deleteAll(allByUserId);
      }

    }
  }

}

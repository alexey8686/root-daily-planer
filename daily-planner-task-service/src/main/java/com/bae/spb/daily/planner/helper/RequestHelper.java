package com.bae.spb.daily.planner.helper;

import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.model.Task;
import com.bae.spb.daily.planner.repository.TaskTypeRepositoryDa;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Component
public class RequestHelper {

  @Autowired
  private TaskTypeRepositoryDa taskTypeRepositoryDa;

  public Example<Task> getExample(TaskRequest request) {
    Task task = new Task();
    ExampleMatcher matcher = ExampleMatcher.matchingAll();
    if (Strings.isNotEmpty(request.getName())) {
      matcher.withIgnoreCase("taskName").withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
      task.setTaskName(request.getName());
    }
    if (Strings.isNotEmpty(request.getTaskLevel())) {
      matcher.withMatcher("taskLevel", ExampleMatcher.GenericPropertyMatchers.exact());
      task.setTaskLevel(Task.TaskLevel.getTaskLevelByName(request.getTaskLevel()));
    }

    if (request.getTaskTypeCode() != null && !request.getTaskTypeCode().isEmpty()) {
      task.setTaskTypes(taskTypeRepositoryDa.findAllByCodeCollection(request.getTaskTypeCode()));
      matcher.withIgnoreNullValues()
          .withMatcher("taskTypes", ExampleMatcher.GenericPropertyMatchers.exact());
    }
    if (request.getUserId() != null && !request.getUserId().isEmpty()) {
      matcher.withMatcher("userId", ExampleMatcher.GenericPropertyMatchers.exact());
      task.setUserId(request.getUserId());
    }
    if (request.getTaskId() != null && !request.getTaskId().isEmpty()) {
      matcher.withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());
      task.setUserId(request.getTaskId());
    }
    return Example.of(task, matcher);
  }
}
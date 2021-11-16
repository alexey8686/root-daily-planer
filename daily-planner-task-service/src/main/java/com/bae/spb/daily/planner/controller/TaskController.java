package com.bae.spb.daily.planner.controller;

import com.bae.spb.daily.planner.dto.TaskDto;
import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.dto.TaskResponse;
import com.bae.spb.daily.planner.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

  private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/{id}")
  public TaskResponse getTaskById(@PathVariable String id, @RequestParam String userId) {
    LOG.info("getTaskById");
    return taskService.getTaskByUserId(id, userId);
  }

  @PostMapping(path = "/getFiltered", consumes = MediaType.APPLICATION_JSON_VALUE)
  public TaskResponse getAllTasksFilteredAndSorted(@RequestBody TaskRequest request) {
    LOG.info("getFiltered");
    return taskService.getAllTaskFiltered(request);
  }


  @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
  public TaskResponse saveTask(@RequestBody TaskDto task) {
    LOG.info("saveTask");
    return taskService.saveOrUpdateTask(task);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping()
  public void deleteTaskById(@RequestBody TaskRequest request) {
    LOG.info("deleteTaskById {}", request);
    taskService.deleteTask(request);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/deleteAll")
  public void deleteAllTasks(@RequestParam(required = false) String userId) {
    LOG.info("deleteAllTasks");
    taskService.deleteAllTasks(userId);
  }

}

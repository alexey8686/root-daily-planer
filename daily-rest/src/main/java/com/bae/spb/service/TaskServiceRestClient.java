package com.bae.spb.service;

import com.bae.spb.daily.planner.dto.CommonResponse;
import com.bae.spb.daily.planner.dto.TaskDto;
import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.dto.TaskResponse;
import com.bae.spb.daily.planner.dto.TaskTypeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "daily-planner-task-service")
public interface TaskServiceRestClient {

  @PostMapping(value = "/task/getFiltered", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  TaskResponse getAllTasks(@RequestBody TaskRequest request);


  @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  TaskResponse getTaskById(@PathVariable String id, @RequestParam String userId);

  @PostMapping(value = "/task/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  TaskResponse saveTask(@RequestBody TaskDto taskDto);

  @DeleteMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
  void deleteMyTaskById(@RequestBody TaskRequest request);

  @DeleteMapping("/task/deleteAll")
  void deleteAll(@RequestParam(required = false) String userId);

  @GetMapping(value = "/taskType", produces = MediaType.APPLICATION_JSON_VALUE)
  CommonResponse getAllTaskType();

  @GetMapping(value = "/taskType/byCode", produces = MediaType.APPLICATION_JSON_VALUE)
  CommonResponse getTaskTypeByCode(@RequestParam String code);

  @DeleteMapping("/taskType/{id}")
  void deleteTaskTypeById(@PathVariable String id);

  @PostMapping(value = "/taskType", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  CommonResponse saveTaskType(@RequestBody TaskTypeDto dto);

}

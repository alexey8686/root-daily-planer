package com.bae.spb.daily.planner.controller;

import com.bae.spb.daily.planner.dto.TaskTypeDto;
import com.bae.spb.daily.planner.service.TaskTypeService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
@RequestMapping("/taskType")
public class TaskTypeController {

  private static final Logger LOG = LoggerFactory.getLogger(TaskTypeController.class);

  private final TaskTypeService service;

  @Autowired
  public TaskTypeController(TaskTypeService service) {
    this.service = service;
  }

  @GetMapping
  public List<TaskTypeDto> getAllTaskType() {
    LOG.info("getAllTaskType");
    return service.findAllTaskType();
  }

  @GetMapping("/byCode")
  public TaskTypeDto getTaskTypeByCode(@RequestParam String code) {
    LOG.info("getTaskTypeByCode {}", code);
    return service.findTaskTypeByCode(code);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteTaskTypeById(@PathVariable String id) {
    LOG.info("deleteTaskTypeById {}", id);
    service.deleteTaskType(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<String> saveTaskType(@Valid @RequestBody TaskTypeDto dto, Errors errors) {
    LOG.info("saveTaskType {}", dto);
    List<ObjectError> allErrors = errors.getAllErrors();
    if (allErrors.isEmpty()) {
      service.saveOrUpdateTaskType(dto);
      return ResponseEntity.ok("saved");
    } else {
      return ResponseEntity.badRequest().body(
          allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
              .collect(Collectors.joining(",")));
    }
  }

}
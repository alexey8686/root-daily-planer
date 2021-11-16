package com.bae.spb.controller;

import com.bae.spb.daily.planner.dto.CommonResponse;
import com.bae.spb.daily.planner.dto.DataNotFoundException;
import com.bae.spb.daily.planner.dto.RemindProcessException;
import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.dto.TaskResponse;
import com.bae.spb.service.RemindService;
import com.bae.spb.service.TaskServiceRestClient;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/admin/rest-client", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminTaskController extends AbstractTaskController {

  private static final Logger LOG = LoggerFactory.getLogger(AdminTaskController.class);

  @Autowired
  protected AdminTaskController(RemindService remindService,
      TaskServiceRestClient taskServiceRestClient) {
    super(remindService, taskServiceRestClient);
  }


  @GetMapping(value = "/task/getAll")
  public ResponseEntity<TaskResponse> getAllTask(@RequestParam(required = false) String userId) {
    TaskResponse taskResponse;
    try {
      TaskRequest taskRequest = new TaskRequest();
      Optional.ofNullable(userId).filter(s -> !s.isEmpty()).ifPresent(taskRequest::setUserId);
      taskResponse = getTaskServiceRestClient().getAllTasks(taskRequest);
      return ResponseEntity.ok(taskResponse);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      taskResponse = new TaskResponse();
      taskResponse.setStatus(UNKNOWN_ERROR);
      taskResponse.setExtendedMessage(e.getMessage());
      return ResponseEntity.internalServerError().body(taskResponse);
    }

  }


  @ResponseStatus(value = HttpStatus.OK)
  @DeleteMapping(value = "/task/deleteAll")
  public void deleteAll(@RequestParam(required = false) String userId) {
    getTaskServiceRestClient().deleteAll(userId);
  }

}

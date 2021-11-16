package com.bae.spb.controller;

import com.bae.spb.daily.planner.dto.CommonResponse;
import com.bae.spb.daily.planner.dto.DataNotFoundException;
import com.bae.spb.daily.planner.dto.RemindProcessException;
import com.bae.spb.daily.planner.dto.TaskDto;
import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.dto.TaskResponse;
import com.bae.spb.model.User;
import com.bae.spb.service.RemindService;
import com.bae.spb.service.TaskServiceRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping(path = "/rest-client", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskMailRestClientController extends AbstractTaskController {

  private static final Logger LOG = LoggerFactory.getLogger(TaskMailRestClientController.class);

  @Autowired
  protected TaskMailRestClientController(RemindService remindService,
      TaskServiceRestClient taskServiceRestClient) {
    super(remindService, taskServiceRestClient);
  }


  @GetMapping("/sendMeRemind")
  public ResponseEntity<CommonResponse> sendRemindToEmail(@RequestParam String id,
      @AuthenticationPrincipal User user) {
    CommonResponse commonResponse = new CommonResponse();
    try {
      getRemindService().getTaskEndSendToEmail(id, user.getEmail(), user.getId());
      commonResponse.setExtendedMessage("MAIL WAS SENT");
      return ResponseEntity.ok().body(commonResponse);
    } catch (DataNotFoundException e) {
      LOG.error(e.getMessage(), e);
      commonResponse.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
      commonResponse.setExtendedMessage(e.getMessage());
    } catch (RemindProcessException rpe) {
      LOG.error(rpe.getMessage(), rpe);
      commonResponse.setStatus(UNKNOWN_ERROR);
      commonResponse.setExtendedMessage(rpe.getMessage());
    }
    return ResponseEntity.internalServerError().body(commonResponse);
  }

  @PostMapping(value = "/task/getAll")
  public ResponseEntity<TaskResponse> getFilteredTask(@RequestBody TaskRequest request,
      @AuthenticationPrincipal User user) {
    TaskResponse taskResponse;
    try {
      request.setUserId(user.getId());
      taskResponse = getTaskServiceRestClient().getAllTasks(request);
      return ResponseEntity.ok(taskResponse);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      taskResponse = new TaskResponse();
      taskResponse.setStatus(UNKNOWN_ERROR);
      taskResponse.setExtendedMessage(e.getMessage());
      return ResponseEntity.internalServerError().body(taskResponse);
    }
  }



  @GetMapping(value = "/task/{id}")
  public ResponseEntity<TaskResponse> getAllTask(@PathVariable String id, @AuthenticationPrincipal User user) {
    TaskResponse taskResponse;
    try {
      TaskRequest taskRequest = new TaskRequest();
      taskRequest.setUserId(user.getId());
      taskRequest.setName(id);
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


  @PostMapping(value = "/task/save", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TaskResponse> saveOrUpdateTask(@RequestBody TaskDto taskDto,
      @AuthenticationPrincipal User user) {
    TaskResponse commonResponse;
    try {
      taskDto.setId(user.getId());
      commonResponse = getTaskServiceRestClient().saveTask(taskDto);
      return ResponseEntity.ok(commonResponse);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      commonResponse = new TaskResponse();
      commonResponse.setStatus(UNKNOWN_ERROR);
      commonResponse.setExtendedMessage(e.getMessage());
      return ResponseEntity.internalServerError().body(commonResponse);

    }
  }

  @ResponseStatus(value = HttpStatus.OK)
  @DeleteMapping(value = "/task/{id}")
  public void deleteMyTaskById(@PathVariable String id,
      @AuthenticationPrincipal User user) {

    TaskRequest request = new TaskRequest();
    request.setTaskId(id);
    request.setUserId(user.getId());
    getTaskServiceRestClient().deleteMyTaskById(request);
  }

}

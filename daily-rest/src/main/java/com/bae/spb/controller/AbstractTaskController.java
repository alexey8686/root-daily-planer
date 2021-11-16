package com.bae.spb.controller;

import com.bae.spb.service.RemindService;
import com.bae.spb.service.TaskServiceRestClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public abstract class AbstractTaskController {

  static final String UNKNOWN_ERROR = "UnknownError";

  private final RemindService remindService;

  private final TaskServiceRestClient taskServiceRestClient;

  @Autowired
  protected AbstractTaskController(RemindService remindService,
      TaskServiceRestClient taskServiceRestClient) {
    this.remindService = remindService;
    this.taskServiceRestClient = taskServiceRestClient;
  }
}

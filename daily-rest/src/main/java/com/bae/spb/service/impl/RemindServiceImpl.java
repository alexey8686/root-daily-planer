package com.bae.spb.service.impl;

import com.bae.spb.daily.planner.dto.DataNotFoundException;
import com.bae.spb.daily.planner.dto.MailSendingRequest;
import com.bae.spb.daily.planner.dto.RemindProcessException;
import com.bae.spb.daily.planner.dto.TaskRequest;
import com.bae.spb.daily.planner.dto.TaskResponse;
import com.bae.spb.service.MailServiceRestClient;
import com.bae.spb.service.RemindService;
import com.bae.spb.service.TaskServiceRestClient;
import org.springframework.stereotype.Service;

@Service
public class RemindServiceImpl implements RemindService {

  public static final String TASK_NOT_FOUND = "No such task was found";

  private final TaskServiceRestClient taskClient;

  private final MailServiceRestClient mailClient;

  public RemindServiceImpl(TaskServiceRestClient taskClient, MailServiceRestClient mailClient) {
    this.taskClient = taskClient;
    this.mailClient = mailClient;
  }

  @Override
  public void getTaskEndSendToEmail(String id, String sendTo, String userId) {
    try {
      TaskRequest taskRequest = new TaskRequest();
      taskRequest.setUserId(userId);
      taskRequest.setTaskId(id);
      TaskResponse taskResponse = taskClient.getAllTasks(taskRequest);
      if (taskResponse != null && taskResponse.getDtoList().isEmpty()) {
        throw new DataNotFoundException(TASK_NOT_FOUND);
      }
      MailSendingRequest request = new MailSendingRequest();
      request.setSendTo(sendTo);
      request.setTaskDto(taskResponse.getDtoList().get(0));
      mailClient.sendEmail(request);
    } catch (DataNotFoundException ex) {
      throw ex;
    } catch (Exception e) {
      throw new RemindProcessException(e.getMessage(), e);
    }
  }
}

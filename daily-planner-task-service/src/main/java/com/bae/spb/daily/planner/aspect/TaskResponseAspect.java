package com.bae.spb.daily.planner.aspect;

import com.bae.spb.daily.planner.dto.StatusEnum;
import com.bae.spb.daily.planner.dto.TaskResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskResponseAspect {

  @Around("execution(public com.bae.spb.daily.planner.dto.TaskResponse com.bae.spb.daily.planner.service.impl.TaskServiceImpl.*(..))")
  public Object populateTaskResponse(ProceedingJoinPoint proceedingJoinPoint) {
    TaskResponse response;

    try {
      response = (TaskResponse) proceedingJoinPoint.proceed();
      response.setStatus(StatusEnum.SUCCESS.name());

    } catch (Throwable throwable) {
      response = new TaskResponse();
      response.setStatus(StatusEnum.ERROR.name());
      response.setExtendedMessage(throwable.getMessage());

    }
    return response;
  }

}

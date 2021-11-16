package com.bae.spb.daily.planner.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1l;

  private List<TaskDto> dtoList;

  private String status;

  private String extendedMessage;
}

package com.bae.spb.daily.planner.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1l;

  private Object data;

  private String status;

  private String extendedMessage;

}

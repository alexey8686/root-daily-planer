package com.bae.spb.daily.planner.service.mapper;

import com.bae.spb.daily.planner.dto.TaskTypeDto;
import com.bae.spb.daily.planner.model.TaskType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskTypeMapper {

    List<TaskTypeDto> convertListToDto(List<TaskType> taskTypeList);

    TaskTypeDto convertToDto(TaskType taskType);


    TaskType convertToTaskType(TaskTypeDto dto);
}

package com.bae.spb.daily.planner.service.mapper;

import com.bae.spb.daily.planner.dto.TaskDto;
import com.bae.spb.daily.planner.dto.TaskTypeDto;
import com.bae.spb.daily.planner.model.Task;
import com.bae.spb.daily.planner.model.TaskType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    List<TaskDto> convertToDtoList (List<Task> list);

    TaskDto convertToDto(Task task);


    @Mapping(source = "taskLevel", target = "taskLevel", qualifiedByName = "toTaskLevel")
    Task convertToTask(TaskDto dto);

    TaskTypeDto convertToDto(TaskType taskType);


    TaskType convertToTaskType(TaskTypeDto dto);

    @Named("toTaskLevel")
    default Task.TaskLevel convertEnum(String name) {
        return Task.TaskLevel.getTaskLevelByName(name);
    }


}

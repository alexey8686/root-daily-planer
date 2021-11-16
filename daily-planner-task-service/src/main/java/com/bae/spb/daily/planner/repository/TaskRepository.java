package com.bae.spb.daily.planner.repository;

import com.bae.spb.daily.planner.model.Task;
import com.bae.spb.daily.planner.model.TaskType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

  List<Task> findAllByUserId(String userId);

}

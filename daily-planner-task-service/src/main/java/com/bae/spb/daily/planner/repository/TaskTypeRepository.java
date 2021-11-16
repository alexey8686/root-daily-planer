package com.bae.spb.daily.planner.repository;

import com.bae.spb.daily.planner.model.TaskType;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TaskTypeRepository extends MongoRepository<TaskType, String> {

    TaskType findByCode(String code);

}

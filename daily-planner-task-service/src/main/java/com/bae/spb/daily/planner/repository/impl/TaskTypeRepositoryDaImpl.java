package com.bae.spb.daily.planner.repository.impl;

import com.bae.spb.daily.planner.model.TaskType;
import com.bae.spb.daily.planner.repository.TaskTypeRepository;
import com.bae.spb.daily.planner.repository.TaskTypeRepositoryDa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TaskTypeRepositoryDaImpl implements TaskTypeRepositoryDa {

  private final TaskTypeRepository taskTypeRepository;

  private final MongoTemplate mongoTemplate;

  @Autowired
  public TaskTypeRepositoryDaImpl(TaskTypeRepository taskTypeRepository,
      MongoTemplate mongoTemplate) {
    this.taskTypeRepository = taskTypeRepository;
    this.mongoTemplate = mongoTemplate;
  }


  @Override
  public List<TaskType> findAllByCodeCollection(List<String> codeList) {
    Query query = new Query();
    query.addCriteria(Criteria.where("code").in(codeList));
    List<TaskType> taskTypeList = mongoTemplate.find(query, TaskType.class);
    return taskTypeList;
  }

  @Override
  public void saveOrUpdateTaskType(TaskType taskType) {
    taskTypeRepository.save(taskType);
  }

  @Override
  public void deleteTaskType(String id) {
    taskTypeRepository.deleteById(id);
  }

  @Override
  public TaskType findOneByCode(String code) {
    return taskTypeRepository.findByCode(code);
  }

  @Override
  public List<TaskType> findAll() {
    return taskTypeRepository.findAll();
  }
}

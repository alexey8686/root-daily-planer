package com.bae.spb.daily.planner;

import com.bae.spb.daily.planner.model.Task;
import com.bae.spb.daily.planner.model.TaskType;
import com.bae.spb.daily.planner.repository.TaskRepository;
import com.bae.spb.daily.planner.repository.TaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@SpringBootApplication
@EnableEurekaClient
public class DailyPlannerApplication {
	@Autowired
	private TaskTypeRepository taskTypeRepository;

	@Autowired
	private TaskRepository taskRepository;

@PostConstruct
	private void populateDB(){
		taskTypeRepository.saveAll(Arrays.asList(new TaskType().builder().name("At Home").code("HOME").build(),new TaskType().builder().name("At work").code("WORK").build(),
				new TaskType().builder().name("At PC").code("PC").build(),new TaskType().builder().name("In store").code("STORE").build()));
		Task testTask = new Task();
		testTask.setUserId("617b661e4955bf4b194943a4");
		testTask.setTaskName("Write code");
		testTask.setDescription("Need to write realization for RabbitMQ");
		testTask.setDateOfCreation(LocalDateTime.now());
		testTask.setTaskLevel(Task.TaskLevel.NOT_URGENT_IMPORTANT);
		testTask.setTaskTypes(Collections.singletonList(taskTypeRepository.findByCode("PC")));
		Task task = taskRepository.save(testTask);
		System.out.printf("Task was saved with id = %s%n", task.getId());
		Task testTask2 = new Task();
		testTask2.setUserId("617b661e4955bf4b194943a4");
		testTask2.setTaskName("Have a breakfast");
		testTask2.setDescription("Eat some thing");
		testTask2.setDateOfCreation(LocalDateTime.now());
		testTask2.setTaskLevel(Task.TaskLevel.URGENT_IMPORTANT);
		testTask2.setTaskTypes(Collections.singletonList(taskTypeRepository.findByCode("HOME")));

		System.out.printf("Task was saved with id = %s%n", taskRepository.save(testTask2).getId());

	}

	public static void main(String[] args) {
		SpringApplication.run(DailyPlannerApplication.class, args);
	}

}

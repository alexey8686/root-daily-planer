package com.bae.spb.daily.planner.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Document(collection = "tasks")
@Data
public class Task {

    @Id
    private String id;

    private String taskName;

    private String description;

    private LocalDateTime dateOfCreation;

    private LocalDateTime dateOfClosing;

    private LocalDateTime deadLine;

    private boolean isClosed;

    private TaskLevel taskLevel;

    private List<TaskType> taskTypes;

    private String userId;


    public Task() {
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Task;
    }

    public enum TaskLevel {
        URGENT_IMPORTANT("срочные и важные"),
        URGENT_NOT_IMPORTANT("срочные, но не важные"),
        NOT_URGENT_IMPORTANT("не срочные, но важные"),
        NOT_URGENT_NOT_IMPORTANT("не срочные и не важные");

        private String description;

        TaskLevel(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static TaskLevel getTaskLevelByName(String name) {
            return Arrays.stream(TaskLevel.values()).filter(t -> t.name().equalsIgnoreCase(name)).findFirst().orElse(null);
        }

    }
}

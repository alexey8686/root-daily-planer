package com.bae.spb.daily.planner.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class TaskType {

    @Id
    private String id;

    private String name;

    private String code;

}

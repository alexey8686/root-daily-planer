package com.bae.spb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AbstractBaseEntity {

    @Id
    private String id;
}

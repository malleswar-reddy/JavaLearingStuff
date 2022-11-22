package com.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Sloat {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";


    @Id
    private Long id;
    private String sloatName;

}

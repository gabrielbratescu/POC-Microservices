package com.example.model.beans;


import io.micronaut.data.annotation.MappedEntity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@MappedEntity
public class TestTable {

    @Id
    private Long id;

    private String mesaj;

}

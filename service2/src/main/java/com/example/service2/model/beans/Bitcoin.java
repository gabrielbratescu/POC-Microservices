package com.example.service2.model.beans;


import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
@Entity
public class Bitcoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}

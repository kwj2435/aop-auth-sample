package com.uijin.study.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "API_INFO")
@Getter
public class ApiInfoEntity {
    @Id
    private String apiId;
}

package com.myt1.department.entity;


import lombok.Data;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Valid
@Table(name = "Department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEP_ID")
    private Long depId;

    @NotNull
    @Column(name = "DEP_NAME", length = 40)
    @Size(min = 2, max = 40 , message = "Name should be greater then 1 and less than 40 characters")
    private String depName;

    @Column(name = "DEP_LOCATION", length = 40, nullable = false)
    private String depLocation;

    @Column(name = "DEP_HEAD",nullable = false)
    private String depHead;
}

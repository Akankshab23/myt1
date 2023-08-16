package com.myt1.department.service;

import com.myt1.department.entity.DepartmentEntity;
import com.myt1.department.exception.DepartmentException;
import com.myt1.department.model.Department;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department)  throws DepartmentException;
    Department updateDepartment(Department department)throws DepartmentException;
    Department getDepartmentById(Long depId)throws DepartmentException;
    void deleteDepartmentById(Long depId)throws DepartmentException;
    List<Department> getAllDepartment()throws DepartmentException;
    Page<DepartmentEntity> getDepartmentPagination(Integer pageNumber, Integer pageSize);
}

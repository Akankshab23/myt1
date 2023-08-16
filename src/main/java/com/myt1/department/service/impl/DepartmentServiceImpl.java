package com.myt1.department.service.impl;

import com.myt1.department.controller.DepartmentController;
import com.myt1.department.entity.DepartmentEntity;
import com.myt1.department.exception.DepartmentException;
import com.myt1.department.model.Department;
import com.myt1.department.repository.DepartmentRepository;
import com.myt1.department.service.DepartmentService;
import com.myt1.department.validation.ValidateData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    public static final Logger logger = LogManager.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department department) throws DepartmentException {
        // Create a new DepartmentEntity and set its properties using the data from the provided 'department' parameter
        Department savedDepartment = new Department();
        boolean isValid = ValidateData.validateDepartmentData(department);
        if (isValid) {
            DepartmentEntity newDepartmentEntity = new DepartmentEntity();
            newDepartmentEntity.setDepId(department.getDepId());
            newDepartmentEntity.setDepName(department.getDepName());
            newDepartmentEntity.setDepLocation(department.getDepLocation());
            newDepartmentEntity.setDepHead(department.getDepHead());
            // Set other properties if needed

            // Save the new DepartmentEntity to the database
            DepartmentEntity savedDepartmentEntity = departmentRepository.save(newDepartmentEntity);

            // Convert the saved DepartmentEntity back to an department object and return it
            // Department savedDepartment = new Department();
            savedDepartment.setDepId(savedDepartmentEntity.getDepId());
            savedDepartment.setDepName(savedDepartmentEntity.getDepName());
            savedDepartment.setDepLocation(savedDepartmentEntity.getDepLocation());
            savedDepartment.setDepHead(savedDepartmentEntity.getDepHead());

            // Set other properties if needed
        }

        return savedDepartment;
    }

    @Override
    public Department updateDepartment(Department department) throws DepartmentException {
        Long depId = department.getDepId();
        Optional<DepartmentEntity> depOptional = departmentRepository.findById(depId);
        if (depOptional.isPresent()) {
            // If the department exists, update its properties with the data from the provided 'department' parameter
            DepartmentEntity existingDepartmentEntity = depOptional.get();
            existingDepartmentEntity.setDepId(department.getDepId());
            existingDepartmentEntity.setDepName(department.getDepName());
            existingDepartmentEntity.setDepLocation(department.getDepLocation());
            existingDepartmentEntity.setDepHead(department.getDepHead());
            // Set other properties if needed

            // Save the updated department back to the database
            DepartmentEntity updatedDepartmentEntity = departmentRepository.save(existingDepartmentEntity);

            // Convert the updated DepartmentEntity back to an Department object and return it
            Department updatedDepartment = new Department();
            updatedDepartment.setDepId(updatedDepartmentEntity.getDepId());
            updatedDepartment.setDepName(updatedDepartmentEntity.getDepName());
            updatedDepartment.setDepLocation(updatedDepartmentEntity.getDepLocation());
            updatedDepartment.setDepHead(updatedDepartmentEntity.getDepHead());
            return updatedDepartment;
        } else {
            throw new DepartmentException("Department with ID " + depId + " not found");
        }
    }

    @Override
    public Department getDepartmentById(Long depId) throws DepartmentException {
        Optional<DepartmentEntity> depById = departmentRepository.findById(depId);
        if (depById.isPresent()) {
            DepartmentEntity departmentEntity = depById.get();
            Department department = new Department();
            department.setDepId(departmentEntity.getDepId());
            department.setDepName(departmentEntity.getDepName());
            department.setDepLocation(departmentEntity.getDepLocation());
            department.setDepHead(departmentEntity.getDepHead());

            // Set other properties if needed

            return department;
        } else {
            throw new DepartmentException("Department with ID " + depId + " not found");
        }
    }

    @Override
    public void deleteDepartmentById(Long depId) throws DepartmentException {
        if (depId != 0) {
            departmentRepository.deleteById(depId);
        } else {
            throw new DepartmentException("Employee ID" + depId + "cannot be null");
        }
    }

    @Override
    public List<Department> getAllDepartment() throws DepartmentException {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        List<Department> departments = new ArrayList<>();
        for (DepartmentEntity depEntity : departmentEntities) {
            Department department = new Department();
            department.setDepId(depEntity.getDepId());
            department.setDepName(depEntity.getDepName());
            department.setDepLocation(depEntity.getDepLocation());
            department.setDepHead(depEntity.getDepHead());
            departments.add(department);

        }
        return departments;
    }

    @Override
    public Page<DepartmentEntity> getDepartmentPagination(Integer pageNumber, Integer pageSize) {
//        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Sort sort = Sort.by(Sort.Direction.ASC, "depId");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
//        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<DepartmentEntity> entityPage = departmentRepository.findAll(pageable);
        List<Department> departments = new ArrayList<>();
        for (DepartmentEntity page : entityPage) {
            Department department = new Department();
            department.setDepId(page.getDepId());
            department.setDepName(page.getDepName());
            department.setDepLocation(page.getDepLocation());
            department.setDepHead(page.getDepHead());
            departments.add(department);

        }
        return entityPage;
    }
}
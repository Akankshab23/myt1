package com.myt1.department.controller;

import com.myt1.department.entity.DepartmentEntity;
import com.myt1.department.exception.DepartmentException;
import com.myt1.department.model.Department;
import com.myt1.department.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    public static final Logger logger = LogManager.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/adddepartment")
    public ResponseEntity addDepartment(@RequestBody Department department) {
        logger.info("inside controller addDepartment method");
        logger.debug("Department Info :" +department.toString());

        try{
            Department depResponse = departmentService.addDepartment(department);
            logger.debug("DepResponse Info :"+depResponse.toString());
            return new ResponseEntity(depResponse, HttpStatus.OK);
        } catch (DepartmentException ex) {
            logger.error("Error Message : ",ex.getMessage());
            logger.info("inside DepartmentException");
            return new ResponseEntity (getErrorResponse("404", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("Error Message : ", ex.getMessage());
            return new ResponseEntity (getErrorResponse("500", ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{depId}")
    public Department updateDepartmentById(@RequestBody Department department) {
        logger.info("inside controller updateDepartmentById method");
        return departmentService.updateDepartment(department);
    }

    @GetMapping("/getdepartmentbyid/{id}")
    public Department getDepartmentById(@PathVariable("id") long id) {
        logger.info("inside controller getDepartmentById method");
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/getalldepartment")
    public List<Department> getAllDepartment() {
        logger.info("inside controller getAllDepartment method");
        return departmentService.getAllDepartment();
    }

    @DeleteMapping("/deleteDepartmentById/{id}")
    void deleteDepartmentById(@PathVariable("id") long id) {
        logger.info("inside controller deleteDepartmentById  method");
        departmentService.deleteDepartmentById(id);
    }
    @GetMapping("/pagingAndSortingDepartment/{pageNumber}/{pageSize}")
    public Page<DepartmentEntity> departmentPagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
        return departmentService.getDepartmentPagination(pageNumber,pageSize);
    }
    public HashMap<String, String> getErrorResponse(String errorcode, String error) {
        HashMap<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("code", errorcode);
        errorMap.put("msg", error);
        return errorMap;
    }

}
package com.myt1.department.validation;

import com.myt1.department.constant.ErrorMessage;
import com.myt1.department.exception.DepartmentException;
import com.myt1.department.model.Department;

public class ValidateData {
    public static boolean validateDepartmentData(Department department) {
        boolean isValid = true;

        if(department.getDepName().isEmpty()){
            isValid =false;
            throw new DepartmentException(ErrorMessage.DEPARTMENT_NAME);
        }
        if(department.getDepName().length()<2 && department.getDepName().length()<=40){
            isValid =false;
            throw new DepartmentException(ErrorMessage.DEPARTMENT_NAME_LENGTH);
        }
        return isValid;
    }
}


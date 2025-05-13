package com.example.k22411csampleproject.connectors;

import com.example.k22411csampleproject.models.Employee;
import com.example.k22411csampleproject.models.ListEmployee;

public class EmployeeConnector {
    public Employee login(String usr, String pwd) {
        ListEmployee le = new ListEmployee();
        le.gen_database();
        for (Employee emp : le.getEmployees()) {
            if (emp.getUsername().equalsIgnoreCase(usr) && emp.getPassword().equals(pwd))
            {
                return emp;
            }
        }
        return null;
    }
}

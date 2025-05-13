package com.example.k22411csampleproject.models;


import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees=new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
        // hàm truy xuất getter
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
        // hàm truy xất getter setter
    }
    public void gen_database(){
        Employee e1=new Employee();
        e1.setId(1);
        e1.setName("John");
        e1.setEmail("john@gmail.com");
        e1.setPhone("0123456789");
        e1.setUsername("john123");
        e1.setPassword("123456");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setId(1);
        e2.setName("Linda");
        e2.setEmail("linda@gmail.com");
        e2.setPhone("0987654321");
        e2.setUsername("linda123");
        e2.setPassword("123456");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(1);
        e3.setName("Jin");
        e3.setEmail("jin@gmail.com");
        e3.setPhone("0912345678");
        e3.setUsername("jin123");
        e3.setPassword("123456");
        employees.add(e3);

    }
}



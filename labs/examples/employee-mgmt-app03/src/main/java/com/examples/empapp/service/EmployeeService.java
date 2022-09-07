package com.examples.empapp.service;

import com.examples.empapp.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    public boolean add(Employee employee);

    public Employee get(int id);

    public Collection<Employee> getAll();

    public boolean update(Employee employee);

    public boolean delete(Employee employee);
}

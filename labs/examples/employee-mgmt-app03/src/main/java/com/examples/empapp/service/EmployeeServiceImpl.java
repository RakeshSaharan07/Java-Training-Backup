package com.examples.empapp.service;

import com.examples.empapp.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

    public EmployeeServiceImpl() {

    }

    public boolean add(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.put(employee.getId(), employee);
        return true;
    }

    public Employee get(int id) {
        return employees.get(id);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public boolean update(Employee employee) {
        employees.put(employee.getId(), employee);
        return true;
    }

    public boolean delete(Employee employee) {
        employees.remove(employee.getId());
        return true;
    }

}

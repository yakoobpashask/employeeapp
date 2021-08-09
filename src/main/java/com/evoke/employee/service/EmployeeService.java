package com.evoke.employee.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.evoke.employee.domain.Event;
import com.evoke.employee.domain.Operation;
import com.evoke.employee.entities.Employee;
import com.evoke.employee.entities.Role;
import com.evoke.employee.repository.EmployeeRepository;
import com.evoke.employee.repository.RoleRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    HrmsHistoryService historyService;

    public List<Employee> getAllEmployees() {

        List<Employee> listEmp = new ArrayList<>();
        empRepository.findAll()
                .forEach(listEmp::add);
        return listEmp;

    }

    public Employee findById(Integer id) {

        Optional<Employee> e = empRepository.findById(id);
        Employee emp = null;
        if (e.isPresent()) {
            emp = e.get();
        }
        return emp;
    }

    @Transactional
    public Employee addEmployee(Employee employee) {


        Employee persistedEmployee = empRepository.save(employee);
        historyService.insertHistory(Event.valueOf("EMPLOYEE"), Operation.valueOf("INSERT"), employee.getId());
        return persistedEmployee;
    }

    @Transactional
    public void updateEmployee(Employee employee) {

        Employee persistedEmployee = empRepository.save(employee);
        historyService.insertHistory(Event.valueOf("EMPLOYEE"), Operation.valueOf("UPDATE"), employee.getId());

    }

    @Transactional
    public ResponseEntity<Object> deleteEmployee(Integer id) {

        Optional<Employee> ee = empRepository.findById(id);

        Employee newEmployee = null;
        if (ee.isPresent()) {
            newEmployee = ee.get();

            newEmployee.getRoles()
                    .removeAll(newEmployee.getRoles());
            empRepository.deleteById(id);

            if (empRepository.findById(id)
                    .isPresent()) {
                return ResponseEntity.unprocessableEntity()
                        .body("Failed to delete the specified employee");
            } else {
                historyService.insertHistory(Event.valueOf("EMPLOYEE"), Operation.valueOf("DELETE"), id);
                return ResponseEntity.ok()
                        .body("Successfully deleted specified employee");
            }
        } else {
            return ResponseEntity.unprocessableEntity()
                    .body("No Records Found for this Id.");
        }


    }


    public List<Employee> findByName(String name) {

        return empRepository.findByName(name);

    }

    public Employee findByEmail(String email) {
        return empRepository.findByEmail(email);
    }

    public ResponseEntity<Object> addEmployeRole(Employee inputEmployee) {

        Optional<Employee> dbEmployee = empRepository.findById(inputEmployee.getId());
        Employee newEmployee = new Employee();
        int dbRoleSIze = 0;

        if (dbEmployee.isPresent()) {
            newEmployee = dbEmployee.get();
            dbRoleSIze = newEmployee.getRoles()
                    .size();

            Set<Role> roleSet = inputEmployee.getRoles();
            Set<Role> newRoleSet = new HashSet<>();
            for (Role role : roleSet) {

                Optional<Role> dbRole = roleRepository.findById(role.getId());

                Role newRole = new Role();
                if (dbRole.isPresent()) {
                    newRole = dbRole.get();
                    newRoleSet.add(newRole);
                }

            }
            newEmployee.setRoles(newRoleSet);
            empRepository.save(newEmployee);

            Optional<Employee> employee1 = empRepository.findById(newEmployee.getId());

            if (employee1.isPresent() && employee1.get()
                    .getRoles()
                    .size() > dbRoleSIze) {

                return ResponseEntity.ok()
                        .body("Successfully Saved the Role to an Employee..");
            } else
                return ResponseEntity.unprocessableEntity()
                        .body("Unable to add  specified role to an employee");
        } else {
            return ResponseEntity.unprocessableEntity()
                    .body("No Records Found for this Id.");
        }
    }


}

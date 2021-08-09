package com.evoke.employee.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.evoke.employee.entities.Employee;
import com.evoke.employee.exeception.ResourceNotFoundException;
import com.evoke.employee.service.EmployeeService;
import com.evoke.employee.service.RoleService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @Autowired
    RoleService roleService;

    @RequestMapping("/getAll")
    public List<Employee> getAllEmployeeDetails() throws ResourceNotFoundException {
        List<Employee> empList = new ArrayList<>();
        empService.getAllEmployees()
                .forEach(empList::add);
        if (empList.size() == 0) {
            throw new ResourceNotFoundException("Employee Record is Not Found..");
        }

        return empList;
    }

    @GetMapping("/get/{id}")
    public Employee findById(@PathVariable Integer id) throws ResourceNotFoundException {

        Employee employee = empService.findById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee Not Found..");
        }
        return employee;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {

        Employee ee = empService.findByEmail(employee.getEmail());

        if (ee == null) {

            if (empService.addEmployee(employee) == null) {
                return new ResponseEntity<>("Employee Not Added", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>("Employee Added", HttpStatus.OK);
            }
        }

        else {
            return new ResponseEntity<>("Employee already exists...", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/addRole")
    public ResponseEntity<Object> addEmployeeRole(@RequestBody Employee employee) {

        return empService.addEmployeRole(employee);

    }

    @PutMapping("/update")
    public void updateEmployee(@RequestBody Employee employee) {
        empService.updateEmployee(employee);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) {

        return empService.deleteEmployee(id);

    }

}

package com.evoke.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.evoke.employee.entities.Role;
import com.evoke.employee.exeception.ResourceNotFoundException;
import com.evoke.employee.exeception.RoleAssignedException;
import com.evoke.employee.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/add")
    public String addRole(@RequestBody Role role) {
        return roleService.addRole(role);

    }

    @PostMapping("/addPermission")
    public String addPermission(@RequestBody Role role) {
        return roleService.addPermission(role);

    }

    @PutMapping("/update")
    public String updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);

    }

    @DeleteMapping("/delete")
    public String deleteRole(@RequestBody Role role) {
        return roleService.deleteRole(role);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable("id") Integer id) throws ResourceNotFoundException, RoleAssignedException {
        return roleService.deleteRoleById(id);

    }

    @GetMapping("/get/{id}")
    public Role getRole(@PathVariable("id") Integer id) {
        return roleService.getRole(id);

    }

    @GetMapping("/getAll")
    public List<Role> getRole() {
        return roleService.getAllRoles();

    }

}

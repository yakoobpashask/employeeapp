package com.evoke.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.evoke.employee.entities.Permission;
import com.evoke.employee.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    PermissionService permissionService;

    @PostMapping("/add")
    public String addPermission(@RequestBody Permission permission) {
        return permissionService.addPermission(permission);

    }

    @PutMapping("/update")
    public String updatePermission(@RequestBody Permission permission) {
        return permissionService.updatePermission(permission);

    }

    @DeleteMapping("/delete")
    public String deletePermission(@RequestBody Permission permission) {
        return permissionService.deletePermission(permission);

    }

    @GetMapping("/get/{id}")
    public Permission getPermission(@PathVariable("id") Integer id) {
        return permissionService.getPermission(id);


    }

    @GetMapping("/getAll")
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();


    }
}

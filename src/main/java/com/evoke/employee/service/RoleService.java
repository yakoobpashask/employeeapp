package com.evoke.employee.service;

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
import com.evoke.employee.entities.Permission;
import com.evoke.employee.entities.Role;
import com.evoke.employee.exeception.ResourceNotFoundException;
import com.evoke.employee.exeception.RoleAssignedException;
import com.evoke.employee.repository.EmployeeRepository;
import com.evoke.employee.repository.PermissionRepository;
import com.evoke.employee.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    EmployeeRepository empRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permRepository;

    @Autowired
    HrmsHistoryService historyService;

    @Transactional
    public String addRole(Role role) {

        Role role1 = roleRepository.save(role);
        historyService.insertHistory(Event.valueOf("ROLE"), Operation.valueOf("INSERT"), role.getId());

        return "Role Added Successfully...";
    }

    public Role getRole(Integer id) {

        Optional<Role> r = roleRepository.findById(id);

        Role role = null;
        if (r.isPresent()) {
            role = r.get();
        }
        return role;

    }

    @Transactional
    public String updateRole(Role role) {

        Role role1 = roleRepository.save(role);
        historyService.insertHistory(Event.valueOf("ROLE"), Operation.valueOf("UPDATE"), role.getId());
        return "Role Updated Successfully...";

    }

    @Transactional
    public String deleteRole(Role role) {

        roleRepository.delete(role);
        historyService.insertHistory(Event.valueOf("ROLE"), Operation.valueOf("DELETE"), role.getId());
        return "Role Deleted Successfully...";

    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public String addPermission(Role r) {
        Role role = roleRepository.getById(r.getId());
        Set<Permission> permSet = r.getPermissions();
        Set<Permission> newPermSet = new HashSet<>();
        for (Permission perm : permSet) {

            Permission newPermission = permRepository.getById(perm.getId());
            newPermSet.add(newPermission);
        }
        role.setPermissions(newPermSet);
        roleRepository.save(role);

        return "Permission Added to Role Successfully..";
    }

    @Transactional
    public ResponseEntity<Object> deleteRoleById(Integer id) throws ResourceNotFoundException, RoleAssignedException {
        Optional<Role> role = roleRepository.findById(id);
        List<Employee> employeeList = empRepository.findByRoles_Id(id);


        if (employeeList != null && employeeList.size() > 1) {

            throw new RoleAssignedException("Role is already Assigned and cannot be deleted...");
        } else {
            Role newRole = null;
            if (role.isPresent()) {
                newRole = role.get();

                newRole.getPermissions()
                        .removeAll(newRole.getPermissions());
                roleRepository.delete(newRole);

                if (roleRepository.findById(id)
                        .isPresent()) {
                    return ResponseEntity.unprocessableEntity()
                            .body("Failed to delete the specified Role");
                } else
                    return ResponseEntity.ok()
                            .body("Successfully deleted specified Role");
            } else {
                throw new ResourceNotFoundException("Role is not found");
            }
        }

    }
}

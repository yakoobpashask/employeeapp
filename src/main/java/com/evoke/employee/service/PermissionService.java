package com.evoke.employee.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.evoke.employee.domain.Event;
import com.evoke.employee.domain.Operation;
import com.evoke.employee.entities.Permission;
import com.evoke.employee.repository.HrmsHistoryRepository;
import com.evoke.employee.repository.PermissionRepository;

@Service
public class PermissionService {


    @Autowired
    PermissionRepository permissionRepo;

    @Autowired
    HrmsHistoryRepository historyRepo;

    @Autowired
    HrmsHistoryService historyService;

    @Transactional
    public String addPermission(Permission permission) {

        Permission permission1 = permissionRepo.save(permission);
        historyService.insertHistory(Event.valueOf("PERMISSION"), Operation.valueOf("INSERT"), permission1.getId());
        return "Permission Added Successfully...";
    }

    public Permission getPermission(Integer id) {

        Optional<Permission> prm = permissionRepo.findById(id);

        Permission perm = null;
        if (prm.isPresent()) {
            perm = prm.get();
        }
        return perm;

    }

    @Transactional
    public String updatePermission(Permission permission) {

        Permission permission1 = permissionRepo.save(permission);
        historyService.insertHistory(Event.valueOf("PERMISSION"), Operation.valueOf("UPDATE"), permission1.getId());

        return "Permission Updated Successfully...";

    }

    @Transactional
    public String deletePermission(Permission permission) {

        permissionRepo.delete(permission);
        historyService.insertHistory(Event.valueOf("PERMISSION"), Operation.valueOf("DELETE"), permission.getId());
        return "Permission Deleted Successfully...";

    }

    public List<Permission> getAllPermissions() {
        return permissionRepo.findAll();

    }
}

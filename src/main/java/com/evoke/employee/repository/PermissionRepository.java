package com.evoke.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.evoke.employee.entities.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    // @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value =
    // "true"))

}



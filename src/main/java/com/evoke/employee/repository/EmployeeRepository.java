package com.evoke.employee.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.evoke.employee.entities.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    public Employee getEmployeeByname(@Param("name") String name);

    public Employee findByEmail(String email);

    public List<Employee> findByName(String email);

    /*
     * @Query("SELECT e FROM Employee e WHERE e.roles.id = :id") public Employee
     * getEmployeeByRoleId(@Param("id") Integer id);
     */

    public List<Employee> findByRoles_Id(Integer id);



}



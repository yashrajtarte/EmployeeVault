package com.empData.repository;

import com.empData.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for Employee entity.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Finds a list of employees whose names contain the given string.
     *
     * @param name the name fragment to search for
     * @return a list of matching employees
     */
    List<Employee> findByEmpNameContaining(String name);

    /**
     * Finds a list of employees based on the specified city.
     *
     * @param city the city to filter employees by
     * @return a list of employees in the given city
     */
    List<Employee> findByEmpCity(String city);

    /**
     * Finds a list of employees based on the specified designation.
     *
     * @param designation the designation to filter employees by
     * @return a list of employees with the given designation
     */
    List<Employee> findByEmpDesignation(String designation);

    /**
     * Finds a list of employees based on both city and designation.
     *
     * @param city the city to filter employees by
     * @param designation the designation to filter employees by
     * @return a list of employees matching both criteria
     */
    List<Employee> findByEmpCityAndEmpDesignation(String city, String designation);


}

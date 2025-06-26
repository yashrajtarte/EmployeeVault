package com.empData.controller;

import com.empData.model.Employee;
import com.empData.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST controller for managing Employee-related operations.
 * Handles HTTP requests for creating, retrieving, updating, and deleting employee data.
 *
 * This controller is accessible from the frontend running at http://localhost:8080.
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;


    /**
     * Constructor-based dependency injection for EmployeeRepository.
     *
     * @param employeeRepository the EmployeeRepository instance to interact with the database
     */
    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieves all employees or filters employees by name if a query parameter is provided.
     *
     * @param name optional query parameter to filter employees whose names contain the given string
     * @return a list of employees or a 204 No Content if none found
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(required = false) String name){
        try{
            List<Employee> employees = new ArrayList<>();
            if(name == null) employeeRepository.findAll().forEach(employees::add);
            else employeeRepository.findByEmpNameContaining(name).forEach(employees::add);
            if(employees.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a single employee by their ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the employee data or 404 Not Found if no such employee exists
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
        try{
            Optional<Employee> employeeData = employeeRepository.findById(id);
            return employeeData.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new employee record.
     *
     * @param employee the employee data to be created
     * @return the created employee with status 201 Created
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        try{
            Employee _employee = employeeRepository.save(new Employee(employee.getEmpName(), employee.getEmpDesignation(), employee.getEmpCity()));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates multiple employee records in a single request.
     *
     * @param employees list of employee data to be saved
     * @return list of saved employees with status 201 Created
     */
    @PostMapping("/bulk")
    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> employees) {
        try {
            List<Employee> savedEmployees = employeeRepository.saveAll(employees);
            return new ResponseEntity<>(savedEmployees, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Updates an existing employee's details by ID.
     *
     * @param id       the ID of the employee to update
     * @param employee the updated employee data
     * @return the updated employee data or 404 Not Found if the employee doesn't exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
        Optional<Employee> employeeData = employeeRepository.findById(id);
        return employeeData.map(emp -> {
            emp.setEmpName(employee.getEmpName());
            emp.setEmpDesignation(employee.getEmpDesignation());
            emp.setEmpCity(employee.getEmpCity());
            return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes all employee records from the database.
     *
     * @return HTTP 204 No Content if successful, or HTTP 500 Internal Server Error on failure
     */
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            employeeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a specific employee by their ID.
     *
     * @param id the ID of the employee to delete
     * @return HTTP 204 No Content if successful, 404 Not Found if the employee doesn't exist,
     *         or 500 Internal Server Error on failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeesById(@PathVariable("id") long id) {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Searches employees based on optional city and/or designation query parameters.
     * Returns all employees if no filter is provided.
     *
     * @param city optional city to filter employees by
     * @param designation optional designation to filter employees by
     * @return list of matching employees or HTTP 204 No Content if none found
     */
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String designation) {

        List<Employee> employees;

        if (city != null && designation != null)
            employees = employeeRepository.findByEmpCityAndEmpDesignation(city, designation);
        else if (city != null)
            employees = employeeRepository.findByEmpCity(city);
        else if (designation != null)
            employees = employeeRepository.findByEmpDesignation(designation);
        else
            employees = employeeRepository.findAll();

        return employees.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(employees, HttpStatus.OK);
    }


}

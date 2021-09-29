package com.pep.restaurant.web.rest;

import com.pep.restaurant.service.EmployeeService;
import com.pep.restaurant.service.mapper.EmployeeManualMapper;
import com.pep.restaurant.service.mapper.EmployeeMapper;
import com.pep.restaurant.service.model.EmployeeDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
public class EmployeeController {

    public static final int OK = 200;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String EMPLOYEE_EMPLOYEE_ID = "/employee/{employeeId}";
    public static final String EMPLOYEE = "/employee";
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeManualMapper employeeManualMapper;

    /**
     * Constructor for Employee Controller.
     * @param employeeService Employee Service.
     * @param employeeMapper  Employee mapper.
     * @param employeeManualMapper Employee manual mapper.
     */
    public EmployeeController(final EmployeeService employeeService, final EmployeeMapper employeeMapper,
                              final EmployeeManualMapper employeeManualMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
        this.employeeManualMapper = employeeManualMapper;
    }

    /**
     * Controller to get a employee by id.
     *
     * @param employeeId id of employee to get.
     * @return EmployeeDTO with the provided id.
     */
    @ApiOperation(
            value = "Get Employee by id",
            notes = "This method allows us to get employee by id")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = "Return employee got",
                    response = EmployeeDTO.class, responseContainer = "Employee"),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Employee not exists",
                    response = EmployeeDTO.class, responseContainer = "Employee")
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = EMPLOYEE_EMPLOYEE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable final long employeeId) {
        return ResponseEntity.ok(employeeManualMapper.mapEmployeeToEmployeeDTO(
                employeeService.getEmployee(employeeId)));
    }

    /**
     * Controller to create a employee.
     *
     * @param employeeDTO employeeDTO to create.
     * @return EmployeeDTO created.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = EMPLOYEE,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody final EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.createEmployee(employeeMapper.mapEmployeeDTOToEmployee(employeeDTO))));
    }

    /**
     * Controller to edit a employee by id.
     *
     * @param employeeId     employee id to edit.
     * @param employeeToEdit employee update.
     * @return EmployeeDTO edited.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping(value = EMPLOYEE_EMPLOYEE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> editEmployee(@PathVariable final long employeeId,
                                                        @RequestBody final EmployeeDTO employeeToEdit) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.editEmployee(employeeId,
                        employeeMapper.mapEmployeeDTOToEmployee(employeeToEdit))));
    }

    /**
     * Controller to delete a employee by id.
     *
     * @param employeeId employee id to be deleted.
     * @return EmployeeDTO deleted.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping(value = EMPLOYEE_EMPLOYEE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable final long employeeId) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.deleteEmployee(employeeId)));
    }

    /**
     * Controller to get a list with all employees.
     *
     * @return EmployeesDTO list.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = EMPLOYEE,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeManualMapper.mapEmployeeListToEmployeeDTOList(
                employeeService.getAllEmployees()));
    }

}
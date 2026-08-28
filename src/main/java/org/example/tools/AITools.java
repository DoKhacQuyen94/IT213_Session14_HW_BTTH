package org.example.tools;

import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.entity.LeaveRequest;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.example.repository.LeaveRequestRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AITools {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    public AITools(EmployeeRepository employeeRepository, 
                   DepartmentRepository departmentRepository, 
                   LeaveRequestRepository leaveRequestRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Tool(description = "Tìm thông tin nhân viên theo họ tên. Chỉ sử dụng khi người dùng hỏi thông tin nhân viên.")
    public List<Employee> searchEmployee(String fullName) {
        return employeeRepository.findByFullNameContainingIgnoreCase(fullName);
    }

    @Tool(description = "Tìm thông tin phòng ban theo tên phòng ban.")
    public Department searchDepartment(String name) {
        return departmentRepository.findByNameIgnoreCase(name).orElse(null);
    }

    @Tool(description = "Đếm số lượng nhân viên trong một phòng ban.")
    public long countEmployeesByDepartment(String departmentName) {
        return employeeRepository.countByDepartmentNameIgnoreCase(departmentName);
    }

    @Tool(description = "Tìm các đơn nghỉ phép của một nhân viên.")
    public List<LeaveRequest> findLeaveRequests(String employeeName) {
        return leaveRequestRepository.findByEmployeeFullNameContainingIgnoreCase(employeeName);
    }

    @Tool(description = "Tìm danh sách nhân viên thuộc một phòng ban.")
    public List<Employee> findEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}

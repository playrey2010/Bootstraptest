package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompanyController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("departments",departmentRepository.findAll());
        return "login";
    }



    @RequestMapping("/departmentList")
    public String allDepartments(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentList";
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "index";
    }

    @RequestMapping("/department/{id}")
    public String viewDepartment(Model model, @PathVariable long id){
        Department department = departmentRepository.findById(id).get();
        model.addAttribute("department", department);
        return "viewDepartment";
    }
}

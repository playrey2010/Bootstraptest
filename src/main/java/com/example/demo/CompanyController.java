package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class CompanyController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CloudinaryConfig cloudc;

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
        model.addAttribute("departments",departmentRepository.findAll());
        return "department";
    }




}

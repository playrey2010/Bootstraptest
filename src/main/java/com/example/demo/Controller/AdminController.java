package com.example.demo.Controller;

import com.cloudinary.utils.ObjectUtils;
import com.example.demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/admin")
    public String admin (Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("byName", Comparator.comparing(Employee::getName));
        return "admin";
    }

    @RequestMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentForm";
    }

    @RequestMapping("/addDepartment")
    public String addDepartment(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", new Department());
        return "departmentForm";
    }

    @PostMapping("/processDepartment")
    public String processDepartment(@ModelAttribute Department department, @RequestParam("file") MultipartFile file,
                                    Model model){
        if (file.isEmpty() && department.getBackgroundPhoto() != null){
            departmentRepository.save(department);
        } else if (!file.isEmpty()) {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                department.setBackgroundPhoto(uploadResult.get("url").toString());
                departmentRepository.save(department);
            } catch (IOException e){
                e.printStackTrace();
                model.addAttribute("department", department);
                model.addAttribute("departments", departmentRepository.findAll());
//                change this to create department if necessary
                return "departmentForm";
            }
        }
        return "redirect:/admin";
    }

    @RequestMapping("/changeEmployeeStatus/{id}")
    public String changeStatus(@PathVariable("id")long id){
        Employee employee = employeeRepository.findById(id).get();
        if (employee.isEmployed()) {
            employee.setEmployed(false);
        } else {
            employee.setEmployed(true);
        }
        employeeRepository.save(employee);
        return "redirect:/admin";
    }

    @RequestMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model) {
        model.addAttribute("employee", employeeRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employeeForm";
    }

    @PostMapping("/processEmployee")
    public String processEmployee(@ModelAttribute Employee employee,
                                  @RequestParam("file")MultipartFile file,
                                  Model model){
        if (file.isEmpty() && employee.getPhoto() != null) {
            employeeRepository.save(employee);
        } else if (!file.isEmpty()) {
            try{
                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                employee.setPhoto(uploadResult.get("url").toString());
                employeeRepository.save(employee);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("employee", employee);
                model.addAttribute("departments", departmentRepository.findAll());
                return "employeeForm";
            }
        }
        return "redirect:/admin";
    }

    @RequestMapping("/addEmployee")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employeeForm";
    }

}

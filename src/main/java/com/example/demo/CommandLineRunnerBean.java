package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public void run(String... args){

        // Preloading users and roles
        User user = new User("bart", "bart@domain.com", "bart", "Bart", "Simpson",
                true);
        Role userRole = new Role("bart", "ROLE_USER");
        userRepository.save(user);
        roleRepository.save(userRole);

        User admin = new User("super", "super@domain.com", "super",
                "Super", "Hero", true);
        Role adminRole1 = new Role("super", "ROLE_ADMIN");
        Role adminRole2 = new Role("super", "ROLE_USER");
        userRepository.save(admin);
        roleRepository.save(adminRole1);
        roleRepository.save(adminRole2);

        // preloading departments
        Department mathDepartment = new Department();
        mathDepartment.setName("Math");
        mathDepartment.setBackgroundPhoto("/images/badAtMath.jpg");
        Department histSsDpt = new Department();
        histSsDpt.setName("History & Social Sciences");
        histSsDpt.setBackgroundPhoto("/images/historybg.jpg");
        Department csDpt = new Department();
        csDpt.setName("Computer Science");
        csDpt.setBackgroundPhoto("/images/csbg.jpg");
        Department artDpt = new Department();
        artDpt.setName("Art");
        artDpt.setBackgroundPhoto("/images/artbg.jpg");

        departmentRepository.save(mathDepartment);
        departmentRepository.save(histSsDpt);
        departmentRepository.save(csDpt);
        departmentRepository.save(artDpt);



        // adding employees
        Employee employee1 = new Employee();
        employee1.setJobTitle("Geometry Instructor");
        employee1.setName("Michael Murray");
        employee1.setDepartment(mathDepartment);

        Employee employee2 = new Employee();
        employee2.setName("Katsuhiro Harada");
        employee2.setJobTitle("Algebra Instructor");
        employee2.setDepartment(mathDepartment);

        Employee employee3 = new Employee();
        employee3.setName("Dwayne Johnson");
        employee3.setJobTitle("Calculus Instructor");
        employee3.setDepartment(mathDepartment);

        // saving employee objects
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
    }

}

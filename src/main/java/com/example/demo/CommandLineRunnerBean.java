package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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

//
//        User admin2 = new User("bart", "anotherBart@gmail.com", "otherBart",
//                "Bart", "Williams", true);
//        Role adminRole3 = new Role("bart", "ROLE_ADMIN");
//        userRepository.save(admin2);
//        roleRepository.save(adminRole3);
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
        employee1.setPhoto("/images/MichaelMurray.png");
        employee1.setDepartment(mathDepartment);

        Employee employee2 = new Employee();
        employee2.setName("Katsuhiro Harada");
        employee2.setJobTitle("Algebra Instructor");
        employee2.setPhoto("/images/Harada.jpg");
        employee2.setDepartment(mathDepartment);

        Employee employee3 = new Employee();
        employee3.setName("Dwayne Johnson");
        employee3.setJobTitle("Calculus Instructor");
        employee3.setPhoto("/images/dwayneJohnson.jpg");
        employee3.setDepartment(mathDepartment);


        Employee employee4 = new Employee();
        employee4.setName("Elizabeth Jennings");
        employee4.setJobTitle("US History Instructor");
        employee4.setPhoto("/images/EJenningsphoto.jpg");
        employee4.setDepartment(histSsDpt);

        Employee employee5 = new Employee();
        employee5.setName("Philip Jennings");
        employee5.setJobTitle("World History Instructor");
        employee5.setPhoto("/images/philJenningspic.jpg");
        employee5.setDepartment(histSsDpt);

        Employee employee6 = new Employee();
        employee6.setName("Jack Bauer");
        employee6.setJobTitle("Java Instructor");
        employee6.setPhoto("/images/jackBauer1.jpg");
        employee6.setDepartment(csDpt);

        Employee employee7 = new Employee();
        employee7.setName("Nina Myers");
        employee7.setJobTitle("CyberSecurity Instructor");
        employee7.setPhoto("/images/NinaMyers1.jpg");
        employee7.setDepartment(csDpt);

        Employee employee8 = new Employee();
        employee8.setName("Bob Ross");
        employee8.setJobTitle("Painting Instructor");
        employee8.setPhoto("/images/bobross1.jpg");
        employee8.setDepartment(artDpt);

        Employee employee9 = new Employee();
        employee9.setName("Crystal Latimer");
        employee9.setJobTitle("Art History Instructor");
        employee9.setPhoto("/images/crystalLatimer1.jpg");
        employee9.setDepartment(artDpt);




        // saving employee objects
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);
        employeeRepository.save(employee6);
        employeeRepository.save(employee7);
        employeeRepository.save(employee8);
        employeeRepository.save(employee9);
    }

}

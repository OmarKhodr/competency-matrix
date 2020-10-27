package com.example.postgresdemo;

import com.example.postgresdemo.model.*;
import com.example.postgresdemo.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository,
                                   BranchRepository branchRepository,
                                   SkillRepository skillRepository,
                                   ProjectRepository projectRepository,
                                   WorkingOnRepository workingOnRepository) {
        return args -> {
            log.info("Testing Database..");
//
//            //uncomment to test Database..
//
            //Cupertino branch
            Branch cupertino = new Branch();
            cupertino.setAddress("One Infinite Loop, San Jose");
            cupertino.setCountry("USA");
            //First Employee
            Employee ayman = new Employee();
            ayman.setFirst_name("Ayman");
            ayman.setLast_name("El Hajj");
            ayman.setPosition("Backend Engineer");
            ayman.setDepartment("Web");
            ayman.setGender("M");
            ayman.setHire_date(LocalDate.of(2020, 1, 1));
            ayman.setBranch_id(cupertino);
            cupertino.setBranch_manager(ayman);
            ayman.setReports_to(null);
            ayman.setPhone_num("11 111111");
            ayman.setEfficiency(2);
            ayman.setEffectiveness(3);
            ayman.setNationality("Lebanese");

//            employeeRepository.save(ayman);
//
            branchRepository.save(cupertino);
//            log.info("Loaded Ayman + Branch he manages.");
//
//            //Second Employee
            Employee bahaa = new Employee();
            bahaa.setFirst_name("Bahaa");
            bahaa.setLast_name("El Masri");
            bahaa.setPosition("Frontend/UX Designer");
            bahaa.setDepartment("UI/UX");
            bahaa.setGender("M");
            bahaa.setHire_date(LocalDate.of(2019, 10, 27));
            bahaa.setBranch_id(cupertino);
            bahaa.setReports_to(null);
            bahaa.setPhone_num("22 222222");
            bahaa.setEfficiency(2);
            bahaa.setEffectiveness(3);
            bahaa.setNationality("Lebanese");
//
            employeeRepository.save(bahaa);
//            log.info("Loaded Bahaa.");

            //            //Second Employee
            Employee rayan = new Employee();
            rayan.setFirst_name("Rayan");
            rayan.setLast_name("Fakher");
            rayan.setPosition("Frontend Engineer");
            rayan.setDepartment("Web");
            rayan.setGender("M");
            rayan.setHire_date(LocalDate.of(2019, 12, 11));
            rayan.setBranch_id(cupertino);
            rayan.setReports_to(null);
            rayan.setPhone_num("33 333333");
            rayan.setEfficiency(2);
            rayan.setEffectiveness(3);
            rayan.setNationality("Lebanese");
//
            employeeRepository.save(rayan);
//            log.info("Loaded Bahaa.");
//
            Skill aymanJava = new Skill();
            aymanJava.setEmp_id(ayman);
            aymanJava.setSkill("Java");
            aymanJava.setCategory("Backend");
            aymanJava.setCompetency(3);

            skillRepository.save(aymanJava);

            Skill aymanSwift = new Skill();
            aymanSwift.setEmp_id(ayman);
            aymanSwift.setSkill("Swift");
            aymanSwift.setCompetency(2);

            skillRepository.save(aymanSwift);

            Skill aymanPython = new Skill();
            aymanPython.setEmp_id(ayman);
            aymanPython.setSkill("Python");
            aymanPython.setCategory("Data Science");
            aymanPython.setCompetency(1);

            skillRepository.save(aymanPython);

            //Now for bahaa's fire skillz

            Skill bahaaJava = new Skill();
            bahaaJava.setEmp_id(bahaa);
            bahaaJava.setSkill("Java");
            bahaaJava.setCategory("Backend");
            bahaaJava.setCompetency(3);

            skillRepository.save(bahaaJava);

            Skill bahaaVue = new Skill();
            bahaaVue.setEmp_id(bahaa);
            bahaaVue.setSkill("VueJS");
            bahaaVue.setCategory("Frontend");
            bahaaVue.setCompetency(3);

            skillRepository.save(bahaaVue);

            Skill rayanHTML = new Skill();
            rayanHTML.setEmp_id(rayan);
            rayanHTML.setSkill("HTML/CSS");
            rayanHTML.setCategory("Frontend");
            rayanHTML.setCompetency(3);

            skillRepository.save(rayanHTML);

            Skill rayanVue = new Skill();
            rayanVue.setEmp_id(rayan);
            rayanVue.setSkill("VueJS");
            rayanVue.setCategory("Frontend");
            rayanVue.setCompetency(3);

            skillRepository.save(rayanVue);

            //Now to finding a team:

            CompetencyAlgorithm algorithm = new CompetencyAlgorithm(employeeRepository,
                    projectRepository,
                    skillRepository,
                    workingOnRepository);

            //blueprint
            ProjectBlueprint blueprint = new ProjectBlueprint();
            blueprint.setStart_date(LocalDate.of(2020, 6, 23));
            blueprint.setDeadline(LocalDate.of(2020, 12, 30));

            Demand d1 = new Demand();
            d1.setNumOfEmployees(2);
            d1.setSkillCategory("Frontend");
            d1.setSkillName("HTML/CSS");

            Demand d2 = new Demand();
            d2.setNumOfEmployees(2);
            d2.setSkillCategory("Frontend");
            d2.setSkillName("VueJS");

            Demand d3 = new Demand();
            d3.setNumOfEmployees(2);
            d3.setSkillCategory("Backend");
            d3.setSkillName("Java");

//
//            Demand[] demands = {d1, d2, d3};

//            List<List<Employee>> employees = algorithm.findTeams(demands, blueprint);
//
//            System.out.println(employees);



//
//            log.info("Loaded Ayman's fire Java skillz");
//
//            Project competency = new Project();
////            competency.setProject_name("Competency Matrix");
////            competency.setProject_leader(ayman);
////            competency.setCompleted((short) 0.1);
////            competency.setDeadline(LocalDate.of(2020, 5, 1));
////            competency.setStart_date(LocalDate.of(2019, 8, 27));
////            competency.setEnd_date(null);
////            competency.setEffectiveness(3);
////            competency.setEfficiency(1);
////            competency.setSeverity(3);
////
////            projectRepository.save(competency);
//
//            log.info("Saved project.");
        };
    }

}

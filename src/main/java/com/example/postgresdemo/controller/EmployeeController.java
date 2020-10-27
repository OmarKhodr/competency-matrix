package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.EmployeeNotFoundException;
import com.example.postgresdemo.model.EmployeeWithSkill;
import com.example.postgresdemo.model.EmployeeWithSkills;
import com.example.postgresdemo.exception.ResourceNotFoundException;

import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.model.Skill;
import com.example.postgresdemo.repository.EmployeeRepository;

import com.example.postgresdemo.repository.SkillRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Data
@Setter
@Getter

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeController {

    @Autowired
    private EmployeeRepository questionRepository;

    @Autowired                                 //Added Bahaa
    private SkillRepository skillrepo;

    @GetMapping("/employees")
    public Page<Employee> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

//    @GetMapping("/employees")
//    public List<Employee> getQuestions()

    //Added by Omar
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = questionRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return employee;
    }


    @PostMapping("/employees")
    public Employee createQuestion(@Valid @RequestBody Employee question) {
        return questionRepository.save(question);
    }

    @PostMapping("/employees/withskill")                                                         //Added Bahaa
    public Employee createEmployeeWithSkill(@Valid @RequestBody EmployeeWithSkill EmpAndSkill){
        EmpAndSkill.getSkilldata().setEmp_id(EmpAndSkill.getEmployeedata());

        questionRepository.save(EmpAndSkill.getEmployeedata());
        skillrepo.save(EmpAndSkill.getSkilldata());
        return questionRepository.getOne(EmpAndSkill.getEmployeedata().getEmp_id());

    }

    @PostMapping("/employees/withskills")                                                       //Added Bahaa
    public Employee createEmployeeWithSkills (@Valid @RequestBody EmployeeWithSkills EmpAndSkills){
        questionRepository.save(EmpAndSkills.getEmployeedata());

        int skillslength= EmpAndSkills.getSkills().length;
        Skill skillList[]= EmpAndSkills.getSkills();

        for ( int i=0; i<skillslength; i++){
            skillList[i].setEmp_id(EmpAndSkills.getEmployeedata());
            skillrepo.save(skillList[i]);
        }
        return questionRepository.getOne(EmpAndSkills.getEmployeedata().getEmp_id());

    }

    @PostMapping("/skills/{employeeid}")                                                      //Added Bahaa
    public Employee addSkillToEmployee (@PathVariable("employeeid") Integer employeeid,@Valid @RequestBody Skill s){
        Employee employeeOfSkill = questionRepository.getOne(employeeid);
        s.setEmp_id(employeeOfSkill);
        skillrepo.save(s);

        return questionRepository.getOne(employeeid);


    }

    @DeleteMapping("/skills/{employeeid}/{skill}")                                            //Added Bahaa
    public ResponseEntity<?> DeleteSkillOfEmployee(@PathVariable("employeeid") int employeeid,@PathVariable String skill) {

        try {
            Skill skilltodelete = skillrepo.findSkillBySkillNameAndEmpId(employeeid, skill);


            skillrepo.delete(skilltodelete);

        }
        catch (IllegalArgumentException e){
            throw new ResourceNotFoundException("Skill "+skill+" not found with employee id: "+employeeid);
        }
        return ResponseEntity.ok().build();


    }

    @PutMapping("/skills/{employeeid}/{skill}")                                                                //Added Bahaa
    public Skill updateSkillofEmployee(@PathVariable("employeeid") int employeeid,@PathVariable("skill") String skill,
                                       @Valid @RequestBody Skill s){

        try{
            Skill skilltoupdate =  skillrepo.findSkillBySkillNameAndEmpId(employeeid,skill);
            skilltoupdate.setCompetency(s.getCompetency());
            return skillrepo.save(skilltoupdate);
        }

        catch (IllegalArgumentException e){
            throw new ResourceNotFoundException("Skill "+skill+" not found with employee id: "+employeeid);
        }

    }

    @PutMapping("/employees/{questionId}")
    public Employee updateQuestion(@PathVariable Integer questionId,
                                   @Valid @RequestBody Employee questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setFirst_name(questionRequest.getFirst_name());
                    question.setLast_name(questionRequest.getLast_name());
                    question.setPosition(questionRequest.getPosition());
                    question.setDepartment(questionRequest.getDepartment());
                    question.setNationality(questionRequest.getNationality());
                    question.setEffectiveness(questionRequest.getEffectiveness());
                    question.setEfficiency(questionRequest.getEfficiency());
                    question.setGender(questionRequest.getGender());
                    question.setHire_date(questionRequest.getHire_date());
                    question.setBranch_id(questionRequest.getBranch_id());
                    question.setPhone_num(questionRequest.getPhone_num());
                    question.setPhone_num(questionRequest.getPhone_num());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @DeleteMapping("/employees/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

//    @RequestMapping("/employees/getbyfnandpos/{first_name}/{position}")
//        public List<Employee> queryEmployeeByfnAndpos(@PathVariable("first_name") String first_name, @PathVariable("position") String position)
//    {
//        System.out.println(" "+ questionRepository.findByfirstnameAndPosition(first_name, position));
//        return questionRepository.findByfirstnameAndPosition(first_name,position);
//
//
//    }

    @RequestMapping("/employees/skillandlevel/{skill}/{competency}")
    public List<Employee> queryEmployeeBySkillandCompetency(@PathVariable("skill") String skill, @PathVariable("competency") float competency)
    {
        return questionRepository.findBySkillandCompetency(skill,competency);
    }

}

package com.example.postgresdemo.model;

import com.example.postgresdemo.controller.EmployeeController;
import com.example.postgresdemo.controller.ProjectController;
import com.example.postgresdemo.exception.NotEnoughEmployeesException;
import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.repository.EmployeeRepository;
import com.example.postgresdemo.repository.ProjectRepository;
import com.example.postgresdemo.repository.SkillRepository;
import com.example.postgresdemo.repository.WorkingOnRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class CompetencyAlgorithm {

    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private SkillRepository skillRepository;
    private WorkingOnRepository workingOnRepository;

    public CompetencyAlgorithm(EmployeeRepository employeeRepository,
                               ProjectRepository projectRepository,
                               SkillRepository skillRepository,
                               WorkingOnRepository workingOnRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.skillRepository = skillRepository;
        this.workingOnRepository = workingOnRepository;
    }

    public boolean overlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return ((start1.compareTo(start2)<=0 && end1.compareTo(start2)>0)
        || (start2.compareTo(start1)<=0 && end2.compareTo(start1)>0));
    }

    public boolean isAvailable(Employee employee, LocalDate start, LocalDate deadline) {
        List<WorkingOn> employeeProjectPairs = workingOnRepository.findByEmp_id(employee.getEmp_id());
        for (WorkingOn empProject: employeeProjectPairs) {
            Project project = empProject.getProject_id();
            LocalDate projectStart = project.getStart_date();
            LocalDate projectEnd = project.getEnd_date() == null ?
                    project.getDeadline() : project.getEnd_date();
            if (overlap(start, deadline, projectStart, projectEnd)) {
                return false;
            }
        }
        return true;
    }

    public void fillChoices(List<Skill> choices, List<Skill> skills, ProjectBlueprint blueprint) {
        if (skills == null || skills.size() == 0) return;
        LocalDate start = blueprint.getStart_date();
        LocalDate deadline = blueprint.getDeadline();
        for (Skill skill : skills) {
            Employee employee = skill.getEmp_id();
            if (!choices.contains(skill) && isAvailable(employee, start, deadline)) {
                choices.add(skill);
            }
        }
    }

    public List<List<Employee>> findTeams(Demand[] demands, ProjectBlueprint blueprint) throws NotEnoughEmployeesException {

        int len = demands.length;
        if (len == 0) return null;

        int minAvailableChoices = Integer.MAX_VALUE;

        List<List<Skill>> allChoices = new ArrayList<>();

        for (Demand d: demands) {

            List<Skill> choices = new ArrayList<>();

            int wantedNum = d.getNumOfEmployees();
            if (wantedNum == 0) throw new IllegalArgumentException();

            String name = d.getSkillName();
            String category = d.getSkillCategory();

            List<Skill> specificSkills = skillRepository.findSkillsBySkillNameAndCategory(name, category);
            fillChoices(choices, specificSkills, blueprint);
            List<Skill> categoricalSkills = skillRepository.findSkillsByCategory(category);
            fillChoices(choices, categoricalSkills, blueprint);

            if (choices.size() < wantedNum) {
                throw new NotEnoughEmployeesException(name);
            }

            allChoices.add(choices);

            if (minAvailableChoices > choices.size()) {
                minAvailableChoices = choices.size();
            }

        }

        List<List<Skill>> result = new ArrayList<>();
        for (List<Skill> L: allChoices) {
            result.add(L.subList(0, minAvailableChoices));
        }

        List<List<Employee>> employeeOptions = new ArrayList<>();


        for (List<Skill> L: result) {
            Collections.sort(L, new Comparator<Skill>() {
                @Override
                public int compare(Skill s1, Skill s2) {
                    Employee emp1 = s1.getEmp_id();
                    float emp1Efficiency = emp1.getEfficiency();
                    float emp1Effectiveness = emp1.getEffectiveness();
                    float emp1Competency = s1.getCompetency();
                    float res1 = (emp1Competency+emp1Effectiveness+emp1Efficiency)/3;

                    Employee emp2 = s2.getEmp_id();
                    float emp2Efficiency = emp2.getEfficiency();
                    float emp2Effectiveness = emp2.getEffectiveness();
                    float emp2Competency = s2.getCompetency();
                    float res2 = (emp2Competency+emp2Effectiveness+emp2Efficiency)/3;

                    return (int)(res1-res2);
                }
            });
            List<Employee> employees = new ArrayList<>();
            for (Skill skill: L) {
                employees.add(skill.getEmp_id());
            }
            employeeOptions.add(employees);
        }

        return employeeOptions;

    }

}

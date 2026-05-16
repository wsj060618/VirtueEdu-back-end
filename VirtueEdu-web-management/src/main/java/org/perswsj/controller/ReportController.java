package org.perswsj.controller;

import lombok.extern.slf4j.Slf4j;
import org.perswsj.model.ClazzCountOption;
import org.perswsj.model.DegreeCountOption;
import org.perswsj.model.JobOption;
import org.perswsj.model.Result;
import org.perswsj.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 查询员工职位人数数据
     * @return 员工职位人数列表
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("查询员工职位人数数据");
        // 查询员工职位人数数据
        JobOption jobOption = reportService.getEmpJobData();

        return Result.success(jobOption);
    }

    /**
     * 查询员工性别人数数据
     * @return 员工性别人数列表
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("查询员工性别数据");
        // 查询员工性别数据
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 查询班级人数数据
     * @return 班级人数列表
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("查询班级人数数据");
        // 查询班级人数数据
        ClazzCountOption clazzCountOption = reportService.getEmpClassData();
        return Result.success(clazzCountOption);
    }

    /**
     * 查询学生学历数据
     * @return 学生学历列表
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("查询学生学历数据");
        // 查询学生学历数据
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
}

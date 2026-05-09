package org.perswsj.controller;

import lombok.extern.slf4j.Slf4j;
import org.perswsj.anno.Log;
import org.perswsj.dto.StudentDto;
import org.perswsj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.perswsj.model.PageResult;
import org.perswsj.model.Result;
import org.perswsj.model.Student;
import org.perswsj.model.StudentQueryParam;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    /**
     * 查询学生分页数据
     * @param studentQueryParam 查询参数
     * @return 分页数据结果
     */
    @GetMapping
    public Result getStudentPage(StudentQueryParam studentQueryParam) {
        log.info("查询学生分页数据，查询参数：{}", studentQueryParam);
        // 查询学生分页数据
        PageResult<Student> students = studentService.getStudentPage(studentQueryParam);

        return Result.success(students);
    }

    /**
     * 新增学员
     * @param student 学员信息
     * @return 新增结果
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("新增学员，参数：{}", student);
        studentService.add(student);

        return Result.success();
    }

    /**
     * 根据ID查询学员数据
     * @param id 学员ID
     * @return 学员数据
     */
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Integer id) {
        log.info("根据ID查询学员数据: {}", id);
        // 根据ID查询学员数据
        StudentDto studentDto = studentService.getStudentById(id);

        return Result.success(studentDto);
    }

    /**
     * 修改学员信息
     * @param student 学员信息
     * @return 修改结果
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员信息，参数：{}", student);
        studentService.update(student);

        return Result.success();
    }

    /**
     * 批量删除学员
     * @param ids 学员ID
     * @return 删除结果
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学员，参数：{}", ids);
        studentService.delete(ids);

        return Result.success();
    }

    /**
     * 违纪处理
     * @param id 学员ID
     * @param score 违规分数
     * @return 处理结果
     */
    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违规处理，参数：学生Id：{}，违规{}分", id, score);
        studentService.violation(id, score);

        return Result.success();
    }
}

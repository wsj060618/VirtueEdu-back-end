package org.perswsj.service;

import org.perswsj.dto.StudentDto;
import org.perswsj.model.PageResult;
import org.perswsj.model.Student;
import org.perswsj.model.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 查询学生分页数据
     * @param studentQueryParam 查询参数
     * @return 学生分页数据
     */
    PageResult<Student> getStudentPage(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     * @param student 学员信息
     */
    void add(Student student);

    /**
     * 根据ID查询学员数据
     * @param id 学员ID
     * @return 学员数据
     */
    StudentDto getStudentById(Integer id);

    /**
     * 修改学员信息
     * @param student 学员信息
     */
    void update(Student student);

    /**
     * 批量删除学员
     * @param ids 学员ID数组
     */
    void delete(List<Integer> ids);

    /**
     * 违纪处理
     * @param id 学员ID
     * @param score 违规分数
     */
    void violation(Integer id, Integer score);
}

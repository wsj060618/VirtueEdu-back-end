package org.perswsj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.perswsj.dto.StudentDto;
import org.perswsj.mapper.studentMapper;
import org.perswsj.model.PageResult;
import org.perswsj.model.Student;
import org.perswsj.model.StudentQueryParam;
import org.perswsj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private studentMapper studentMapper;

    @Override
    public PageResult<Student> getStudentPage(StudentQueryParam studentQueryParam) {
        // 设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        // 查询学生分页数据
        Page<Student> rows = studentMapper.list(studentQueryParam);
        // 封装分页结果
        return new PageResult<>(rows.getTotal(), rows);
    }

    @Override
    public void add(Student student) {
        // 设置学员的创建时间和更新时间为当前时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        log.info("新增学员: {}", student);
        // 插入学员
        studentMapper.insert(student);
    }

    @Override
    public StudentDto getStudentById(Integer id) {
        StudentDto studentDto = studentMapper.getStudentById(id);
        return studentDto;
    }

    @Override
    public void update(Student student) {
        // 设置学员的更新时间为当前时间
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        // 批量删除学员
        studentMapper.delete(ids);
    }

    @Override
    public void violation(Integer id, Integer score) {
        // 违规处理
        studentMapper.updateViolation(id, score);
    }
}

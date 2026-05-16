package org.perswsj.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.perswsj.dto.StudentDto;
import org.perswsj.model.Student;
import org.perswsj.model.StudentQueryParam;

import java.util.List;

@Mapper
public interface studentMapper {
    /**
     * 查询班级下学生数量
     * @param clazzId 班级ID
     * @return 学生数量
     */
    int selectCountByClazzId(String clazzId);

    /**
     * 查询学生分页数据
     * @param studentQueryParam 查询参数
     * @return 学生分页数据
     */
    Page<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 新增学生
     * @param student 学生
     */
    void insert(Student student);

    /**
     * 根据ID查询学生数据
     * @param id 学生ID
     * @return 学生数据
     */
    StudentDto getStudentById(Integer id);

    /**
     * 修改学生信息
     * @param student 学生信息
     */
    void update(Student student);

    /**
     * 批量删除学生数据
     * @param ids 学生ID数组
     */
    void delete(List<Integer> ids);

    /**
     * 违规处理
     * @param id 学生ID
     * @param score 违规分数
     */
    void updateViolation(Integer id, Integer score);
}

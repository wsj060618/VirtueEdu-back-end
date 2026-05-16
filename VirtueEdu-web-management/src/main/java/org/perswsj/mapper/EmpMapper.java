package org.perswsj.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.perswsj.model.Emp;
import org.perswsj.model.EmpQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    // 原始分页查询
//    /**
//     * 查询员工总记录数
//     * @return 员工总记录数
//     */
//    @Select("SELECT COUNT(*) FROM emp e LEFT JOIN dept d ON e.dept_id = d.id;")
//    Long count();
//
//    /**
//     * 查询员工分页列表
//     * @param start 起始索引
//     * @param pageSize 每页记录数
//     * @return 员工分页列表
//     */
//    @Select("SELECT e.*, d.name AS dept_name FROM emp e LEFT JOIN dept d ON e.dept_id = d.id ORDER BY e.update_time DESC LIMIT #{start}, #{pageSize};")
//    List<Emp> list(Integer start, Integer pageSize);
    /**
     * 查询全部员工数据
     * @return 员工列表
     */
    @Select("SELECT e.* FROM emp e")
    List<Emp> getAll();

    /**
     * 查询员工分页列表
     * @return 员工分页列表
     */
    Page<Emp> list(EmpQueryParam empQueryParam);

     /**
      * 新增员工数据
      * @param emp 员工对象，包含新增员工数据
      */
     @Options(useGeneratedKeys = true, keyProperty = "id")
     @Insert("INSERT INTO emp (username, name, gender, image, job, salary, entry_date, phone, create_time, update_time, dept_id) " +
             "VALUES (#{username}, #{name}, #{gender}, #{image}, #{job}, #{salary}, #{entryDate}, #{phone}, #{createTime}, #{updateTime}, #{deptId});")
    void insert(Emp emp);

    /**
     * 批量删除员工数据
     * @param ids 员工ID集合
     */
    void deleteById(List<Integer> ids);

    /**
     * 查询员工详情
     * @param id 员工ID
     * @return 员工对象，包含员工工作经历列表
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工数据
     * @param emp 员工对象，包含更新员工数据
     */
    void updateById(Emp emp);

    /**
     * 职位人数统计
     * @return 职位人数统计列表
     */
    List<Map<String, Object>> countEmpJobData();

    /**
     * 性别人数统计
     * @return 性别人数统计列表
     */
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 班级人数统计
     * @return 班级人数统计列表
     */
    List<Map<String, Object>> countEmpClassData();

    /**
     * 学生学历统计
     * @return 学生学历统计列表
     */
    List<Map<String, Object>> countStudentDegreeData();

    /**
     * 根据部门ID查询部门下员工人数
     * @param deptId 部门ID
     * @return 部门下员工人数
     */
    Integer getEmpCountByDeptId(Integer deptId);
}

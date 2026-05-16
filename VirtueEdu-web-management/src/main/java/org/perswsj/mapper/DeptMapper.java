package org.perswsj.mapper;

import org.apache.ibatis.annotations.*;
import org.perswsj.model.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询部门全部数据
     * @return 部门列表
     */
    @Select("SELECT id, name, create_time, update_time FROM dept ORDER BY update_time DESC;")
    List<Dept> findAll();

    /**
     * 根据部门ID删除部门数据
     * @param id 部门ID
     */
    @Delete("DELETE FROM dept WHERE id = #{id};")
    void deleteById(Integer id);

    /**
    * 新增部门数据
    * @param dept 部门信息
    */
    @Insert("INSERT INTO dept (name, create_time, update_time) VALUES (#{name}, #{createTime}, #{updateTime});")
    void insert(Dept dept);

    /**
    * 根据部门ID查询部门数据
    * @param id 部门ID
     * @return 部门信息
    */
    @Select("SELECT id, name, create_time, update_time FROM dept WHERE id = #{id};")
    Dept selectById(Integer id);

    /**
    * 更新部门数据
    * @param dept 部门信息
    */
    @Update("UPDATE dept SET name = #{name}, update_time = #{updateTime} WHERE id = #{id};")
    void update(Dept dept);
}

package org.perswsj.service;

import org.perswsj.model.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询部门全部数据
     * @return 部门列表
     */
   List<Dept> findAll();

    /**
     * 根据部门ID删除部门数据
     * @param id 部门ID
     */
    void deleteById(Integer id);

    /**
     * 新增部门数据
     * @param dept 部门信息
     */
    void add(Dept dept);
    /**
     * 根据部门ID查询部门数据
     * @param id 部门ID
     * @return 部门信息
     */
    Dept getById(Integer id);

    /**
     * 更新部门数据
     * @param dept 部门信息
     */
    void update(Dept dept);
}

package org.perswsj.service;

import org.perswsj.model.Emp;
import org.perswsj.model.EmpQueryParam;
import org.perswsj.model.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询员工信息
     * @param empQueryParam 查询参数对象，包含分页和过滤条件
     * @return 分页结果
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工数据
     * @param emp 员工对象，包含新增员工数据
     */
    void save(Emp emp);

    /**
     * 批量删除员工数据
     * @param ids 员工ID集合
     */
    void delete(List<Integer> ids);

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
    void update(Emp emp);

    /**
     * 查询全部员工数据
     * @return 员工列表
     */
    List<Emp> getAll();
}

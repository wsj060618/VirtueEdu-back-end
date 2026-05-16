package org.perswsj.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.perswsj.model.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExpMapper {
    /**
     * 批量插入员工工作经历
     * @param workExpr 员工工作经历列表
     */
    void insertBatch(List<EmpExpr> workExpr);

    /**
     * 根据员工ID批量删除员工工作经历
     * @param empIds 员工ID集合
     */
    void deleteByEmpId(List<Integer> empIds);
}

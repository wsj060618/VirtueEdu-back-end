package org.perswsj.service;

import org.perswsj.model.ClazzCountOption;
import org.perswsj.model.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 查询员工职位人数数据
     * @return 员工职位人数对象
     */
    JobOption getEmpJobData();

    /**
     * 查询员工性别人数数据
     * @return 员工性别人数数据
     */
    List<Map<String, Object>> getEmpGenderData();


    /**
     * 查询每个班级人数数据
     * @return 班级人数对象
     */
    ClazzCountOption getEmpClassData();

    /**
     * 查询学生学历数据
     *
     * @return 学生学历列表
     */
    List<Map<String, Object>> getStudentDegreeData();
}

package org.perswsj.service.impl;

import org.perswsj.mapper.EmpMapper;
import org.perswsj.model.ClazzCountOption;
import org.perswsj.model.JobOption;
import org.perswsj.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> posList = list.stream().map(item -> item.get("pos")).toList();
        List<Object> numList = list.stream().map(item -> item.get("num")).toList();

        return new JobOption(posList, numList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public ClazzCountOption getEmpClassData() {
       List<Map<String, Object>> list = empMapper.countEmpClassData();
       List<Object> clazzList = list.stream().map(item -> item.get("clazz_name")).toList();
       List<Object> numList = list.stream().map(item -> item.get("value")).toList();
       return new ClazzCountOption(clazzList, numList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> list = empMapper.countStudentDegreeData();
        return list;
    }
}

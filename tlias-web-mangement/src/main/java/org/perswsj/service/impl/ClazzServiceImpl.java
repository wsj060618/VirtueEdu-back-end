package org.perswsj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.perswsj.dto.ClazzDto;
import lombok.extern.slf4j.Slf4j;
import org.perswsj.exception.ClassHasStudentException;
import org.perswsj.mapper.ClazzMapper;
import org.perswsj.mapper.studentMapper;
import org.perswsj.model.Clazz;
import org.perswsj.model.ClazzQueryParam;
import org.perswsj.model.PageResult;
import org.perswsj.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private studentMapper studentMapper;

    @Override
    public PageResult<Clazz> getClazzPage(ClazzQueryParam clazzQueryParam) {
        // 设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // 查询班级分页数据
        Page<Clazz> rows = clazzMapper.list(clazzQueryParam);
        log.info("查询班级分页数据，查询参数：{}", clazzQueryParam);
        // 计算班级状态
        LocalDate currentDate = LocalDate.now();
        for (Clazz clazz : rows) {
            LocalDate beginDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();

            if (currentDate.isAfter(endDate)) {
                clazz.setStatus("已结课");
            } else if (currentDate.isBefore(beginDate)) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }
        // 封装分页结果
        return new PageResult<>(rows.getTotal(), rows);
    }

    @Override
    public Clazz getClazzById(String id) {
        return clazzMapper.selectById(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        // 设置创建时间和修改时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        log.info("新增班级数据: {}", clazz);
        clazzMapper.insert(clazz);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        // 设置修改时间
        clazz.setUpdateTime(LocalDateTime.now());
        log.info("修改班级数据: {}", clazz);
        clazzMapper.updateById(clazz);
    }

    @Override
    public void deleteById(String id) {
        // 检查班级是否有学生
        int studentCount = studentMapper.selectCountByClazzId(id);

        if (studentCount > 0) {
            throw new ClassHasStudentException("该班级下有学生, 不能删除");
        }

        // 没有学生, 可以删除班级
        clazzMapper.deleteById(id);
        log.info("删除班级数据: {}", id);
    }

    @Override
    public List<ClazzDto> getAllClazz() {
        // 查询所有班级数据
        List<ClazzDto> clazzList = clazzMapper.findAll();
        log.info("查询所有班级数据，查询结果：{}", clazzList);
        return clazzList;
    }
}

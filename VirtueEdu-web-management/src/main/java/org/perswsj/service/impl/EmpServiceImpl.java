package org.perswsj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.perswsj.mapper.EmpExpMapper;
import org.perswsj.mapper.EmpLogMapper;
import org.perswsj.mapper.EmpMapper;
import org.perswsj.model.*;
import org.perswsj.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExpMapper empExpMapper;
    @Autowired
    private EmpLogMapper empLogMapper;

//    // 原始分页查询实现
//    /**
//     * 分页查询员工信息
//     * @param page 起始页码
//     * @param pageSize 每页记录数
//     * @return 分页结果
//     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        // 查询总记录数
//        Long total = empMapper.count();
//        // 查询结果列表
//        List<Emp> rows = empMapper.list((page - 1) * pageSize, pageSize);
//        // 封装分页结果
//        return new PageResult<>(total, rows);
//    }

    /**
     * 分页查询员工信息
     * @param empQueryParam 查询参数对象，包含分页和过滤条件
     * @return 分页结果
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 调用Mapper查询结果列表
        Page<Emp> rows = empMapper.list(empQueryParam);
        log.info("查询员工分页数据，分页参数：{}，查询参数：{}", rows.toString());
        // 封装分页结果
        return new PageResult<>(rows.getTotal(), rows);
    }

    /**
     * 新增员工数据
     * @param emp 员工对象，包含新增员工数据
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        // 存储基本信息
        // 设置员工的创建时间和更新时间为当前时间
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // 存储员工工作经历
        List<EmpExpr> workExpr = emp.getExprList();
        if (!CollectionUtils.isEmpty(workExpr)) {
            workExpr.forEach(item -> item.setEmpId(emp.getId()));
            empExpMapper.insertBatch(workExpr);
        }

        // 记录日志
        EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工数据，员工：" + emp);
        empLogMapper.insert(empLog);
    }

    /**
     * 批量删除员工数据
     * @param ids 员工ID集合
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void delete(List<Integer> ids) {
        // 批量删除员工数据
        empMapper.deleteById(ids);
        // 批量删除员工工作经历
        empExpMapper.deleteByEmpId(ids);
    }

    /**
     * 查询员工详情
     * @param id 员工ID
     * @return 员工对象，包含员工工作经历列表
     */
    @Override
    public Emp getInfo(Integer id) {
        // 查询员工详情信息
        Emp emp = empMapper.getInfo(id);

        return emp;
    }

    /**
     * 更新员工数据
     * @param emp 员工对象，包含更新员工数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        // 更新员工基本信息
        // 设置员工的更新时间为当前时间
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 更新员工工作经历
        // 先删除旧的工作经历
        empExpMapper.deleteByEmpId(Arrays.asList(emp.getId()));
        // 再新增新的工作经历
        List<EmpExpr> workExpr = emp.getExprList();
        if (!CollectionUtils.isEmpty(workExpr)) {
            workExpr.forEach(item -> item.setEmpId(emp.getId()));
            empExpMapper.insertBatch(workExpr);
        }
    }

    /**
     * 查询全部员工数据
     * @return 员工列表
     */
    @Override
    public List<Emp> getAll() {
        return empMapper.getAll();
    }
}

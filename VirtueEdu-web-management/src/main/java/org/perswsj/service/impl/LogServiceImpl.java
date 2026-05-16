package org.perswsj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.perswsj.dto.OperateLogDto;
import org.perswsj.mapper.OperateLogMapper;
import org.perswsj.model.LogQueryParam;
import org.perswsj.model.OperateLog;
import org.perswsj.model.PageResult;
import org.perswsj.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLogDto> getLogPage(LogQueryParam logQueryParam) {
        // 设置分页参数
        PageHelper.startPage(logQueryParam.getPage(), logQueryParam.getPageSize());
        // 查询操作日志分页数据
        Page<OperateLogDto> rows = operateLogMapper.list(logQueryParam);
        log.info("查询操作日志分页数据，查询参数：{}", logQueryParam);

        return new PageResult<>(rows.getTotal(), rows.getResult());
    }
}

package org.perswsj.service;

import org.perswsj.dto.OperateLogDto;
import org.perswsj.model.LogQueryParam;
import org.perswsj.model.PageResult;



public interface LogService {
    /**
     * 分页查询操作日志
     * @param logQueryParam 操作日志查询参数对象，包含查询条件
     * @return 分页结果
     */
    PageResult<OperateLogDto> getLogPage(LogQueryParam logQueryParam);
}

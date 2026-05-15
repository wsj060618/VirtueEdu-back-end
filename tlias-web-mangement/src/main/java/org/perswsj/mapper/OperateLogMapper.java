package org.perswsj.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.perswsj.dto.OperateLogDto;
import org.perswsj.model.LogQueryParam;
import org.perswsj.model.OperateLog;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface OperateLogMapper {
    /**
     * 查询操作日志分页数据
     * @param logQueryParam 操作日志查询参数对象，包含查询条件
     * @return 分页结果
     */
    Page<OperateLogDto> list(LogQueryParam logQueryParam);

    /**
     * 插入操作日志
     * @param operateLog 操作日志对象
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void insert(OperateLog operateLog);

}


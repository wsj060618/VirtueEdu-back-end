package org.perswsj.controller;

import lombok.extern.slf4j.Slf4j;
import org.perswsj.dto.OperateLogDto;
import org.perswsj.model.*;
import org.perswsj.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping("/page")
    public Result page(LogQueryParam logQueryParam) {
        PageResult<OperateLogDto> logList = logService.getLogPage(logQueryParam);

        return Result.success(logList);
    }
}

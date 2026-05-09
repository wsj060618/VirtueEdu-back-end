package org.perswsj.exception;


import org.perswsj.model.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHeader {
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错", e);
        return Result.error("程序出错，请联系管理员");
    }

    /**
     * 处理数据重复异常
     */
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据重复", e);
        String msg = e.getMessage();
        int i = msg.indexOf("Duplicate entry");
        String errMsg = msg.substring(i);
        String[] split = errMsg.split(" ");
        return Result.error(split[2] + "已存在");
    }

    /**
     * 处理“班级下有学生”的自定义异常
     */
    @ExceptionHandler(ClassHasStudentException.class)
    public Result handleClassHasStudentException(ClassHasStudentException e) {
        // 记录日志
        log.error("删除班级失败：{}", e.getMessage());
        // 返回统一格式的错误结果，前端弹窗提示
        return Result.error(e.getMessage());
    }

    /**
     * 处理“部门下有员工”的自定义异常
     */
    @ExceptionHandler(DeptHasEmpException.class)
    public Result handleDeptHasEmpException(DeptHasEmpException e) {
        // 记录日志
        log.error("删除部门失败：{}", e.getMessage());
        // 返回统一格式的错误结果，前端弹窗提示
        return Result.error(e.getMessage());
    }
}

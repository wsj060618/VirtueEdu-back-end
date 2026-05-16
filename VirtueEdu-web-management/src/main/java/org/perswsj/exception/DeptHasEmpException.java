package org.perswsj.exception;

// 自定义异常：部门下有员工，不能直接删除
public class DeptHasEmpException extends RuntimeException {
    public DeptHasEmpException(String message) {
        super(message);
    }
}

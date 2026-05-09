package org.perswsj.exception;

// 自定义异常类：ClassHasStudentException.java
public class ClassHasStudentException extends RuntimeException {
    // 构造方法，传入错误提示信息
    public ClassHasStudentException(String message) {
        super(message);
    }
}

package org.perswsj.anno;

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 只作用于方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
public @interface Log {
}

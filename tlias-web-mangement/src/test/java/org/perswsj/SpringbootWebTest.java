package org.perswsj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SpringbootWebTest {
    // 注入IOC容器
    @Autowired
    private ApplicationContext applicationContext;
    // 测试方法
    @Test
    public void scope() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Object applicationContextBean = applicationContext.getBean("clazzMapper");
            System.out.println(applicationContextBean);
        }
    }
}

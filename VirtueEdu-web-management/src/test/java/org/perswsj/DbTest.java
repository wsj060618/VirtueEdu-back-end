package org.perswsj;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

@SpringBootTest
public class DbTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testConnect() {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            assert false : "连接失败：" + e.getMessage();
        }
    }
}


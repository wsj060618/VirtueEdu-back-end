# VirtueEdu - 教育管理系统后端

基于 Spring Boot 构建的教育管理系统后端服务，提供班级、学生、员工、部门等核心模块的管理功能。

## 技术栈

- **框架**: Spring Boot 4.0.3
- **语言**: Java 17
- **数据库**: MySQL
- **ORM**: MyBatis
- **认证**: JWT
- **文件存储**: 阿里云 OSS
- **代码简化**: Lombok

## 项目结构
```
VirtueEdu-parent/                              # Maven 父工程
├── .gitignore                                # Git 忽略配置
├── pom.xml                                   # 父工程依赖管理
├── LICENSE                                   # MIT License 许可证
├── README.md                                 # 项目说明
├── document/                                 # 文档目录
│   ├── VirtueEdu web management.openapi.json  # OpenAPI 文档
│   └── virtue_edu_db_2026-05-16_124031.sql  # 数据库脚本
├── VirtueEdu-model/                          # 模型模块（实体类）
│   ├── pom.xml
│   └── src/main/java/org/perswsj/model/
│       ├── Clazz.java                        # 班级实体
│       ├── ClazzCountOption.java             # 班级统计选项
│       ├── ClazzQueryParam.java              # 班级查询参数
│       ├── DegreeCountOption.java            # 学历统计选项
│       ├── Dept.java                         # 部门实体
│       ├── Emp.java                          # 员工实体
│       ├── EmpExpr.java                      # 员工拓展实体
│       ├── EmpLog.java                       # 员工日志实体
│       ├── EmpQueryParam.java                # 员工查询参数
│       ├── JobOption.java                    # 职位选项
│       ├── LogQueryParam.java                # 日志查询参数
│       ├── LoginInfo.java                    # 登录信息实体
│       ├── OperateLog.java                   # 操作日志实体
│       ├── PageResult.java                   # 分页结果封装
│       ├── Result.java                       # 统一响应结果
│       ├── Student.java                      # 学生实体
│       └── StudentQueryParam.java            # 学生查询参数
├── VirtueEdu-utils/                          # 工具模块
│   ├── pom.xml
│   └── src/main/java/org/perswsj/utils/
│       ├── AliyunOSSOperator.java            # 阿里云 OSS 操作工具
│       ├── AliyunOssProperties.java          # OSS 配置属性
│       ├── CurrentHolder.java                # ThreadLocal 工具
│       └── JwtUtil.java                      # JWT 工具类
└── VirtueEdu-web-management/                 # Web 管理模块（启动模块）
├── logs/                                 # 日志目录
├── pom.xml
└── src/
├── main/
│   ├── java/org/perswsj/
│   │   ├── VirtueEduWebManagementApplication.java  # Spring Boot 启动类
│   │   ├── Interceptor/
│   │   │   └── LoginInterCeptor.java              # 登录拦截器
│   │   ├── anno/
│   │   │   └── Log.java                           # 自定义日志注解
│   │   ├── aop/
│   │   │   └── RecordRunTimeAspect.java           # 运行时间记录切面
│   │   ├── config/
│   │   │   └── WebConfig.java                     # Web 配置类
│   │   ├── controller/
│   │   │   ├── ClazzController.java               # 班级控制器
│   │   │   ├── DeptController.java                # 部门控制器
│   │   │   ├── EmpController.java                 # 员工控制器
│   │   │   ├── LogController.java                 # 日志控制器
│   │   │   ├── LoginController.java               # 登录控制器
│   │   │   ├── ReportController.java              # 报表控制器
│   │   │   ├── StudentController.java             # 学生控制器
│   │   │   └── UpdateController.java              # 更新控制器
│   │   ├── dto/
│   │   │   ├── ClazzDto.java                      # 班级 DTO
│   │   │   ├── LoginDto.java                      # 登录 DTO
│   │   │   ├── OperateLogDto.java                 # 操作日志 DTO
│   │   │   └── StudentDto.java                    # 学生 DTO
│   │   ├── exception/
│   │   │   ├── ClassHasStudentException.java      # 班级有学生异常
│   │   │   ├── DeptHasEmpException.java           # 部门有员工异常
│   │   │   └── GlobalExceptionHeader.java         # 全局异常处理
│   │   ├── mapper/
│   │   │   ├── ClazzMapper.java                   # 班级 Mapper
│   │   │   ├── DeptMapper.java                    # 部门 Mapper
│   │   │   ├── EmpExpMapper.java                  # 员工拓展 Mapper
│   │   │   ├── EmpLogMapper.java                  # 员工日志 Mapper
│   │   │   ├── EmpMapper.java                     # 员工 Mapper
│   │   │   ├── LoginMapper.java                   # 登录 Mapper
│   │   │   ├── OperateLogMapper.java              # 操作日志 Mapper
│   │   │   └── studentMapper.java                 # 学生 Mapper
│   │   └── service/
│   │       ├── ClazzService.java                  # 班级服务接口
│   │       ├── DeptService.java                   # 部门服务接口
│   │       ├── EmpService.java                    # 员工服务接口
│   │       ├── LogService.java                    # 日志服务接口
│   │       ├── LoginService.java                  # 登录服务接口
│   │       ├── ReportService.java                 # 报表服务接口
│   │       ├── StudentService.java                # 学生服务接口
│   │       └── impl/
│   │           ├── ClazzServiceImpl.java          # 班级服务实现
│   │           ├── DeptServiceImpl.java           # 部门服务实现
│   │           ├── EmpServiceImpl.java            # 员工服务实现
│   │           ├── LogServiceImpl.java            # 日志服务实现
│   │           ├── LoginServiceImpl.java          # 登录服务实现
│   │           ├── ReportServiceImpl.java         # 报表服务实现
│   │           └── StudentServiceImpl.java        # 学生服务实现
│   └── resources/
│       ├── application.yml                        # 应用配置
│       ├── logback.xml                           # Logback 日志配置
│       ├── static/                               # 静态资源
│       │   └── upload.html                       # 文件上传页面
│       └── org/perswsj/mapper/                    # MyBatis 映射文件
│           ├── ClazzMapper.xml
│           ├── EmpExpMapper.xml
│           ├── EmpMapper.xml
│           ├── LoginMapper.xml
│           ├── OperateLogMapper.xml
│           └── studentMapper.xml
└── test/java/org/perswsj/
├── DbTest.java                               # 数据库测试
├── Demo.java                                 # 示例测试
├── JwtTest.java                              # JWT 测试
├── LogTest.java                              # 日志测试
├── SpringbootWebTest.java                    # Spring Web 测试
└── VirtueEduWebManagementApplicationTests.java  # 应用测试
```

## 功能模块

| 模块 | 功能说明 |
|------|----------|
| **班级管理** | 班级的增删改查、分页查询 |
| **学生管理** | 学生信息管理、批量导入导出 |
| **员工管理** | 员工信息管理、职称管理 |
| **部门管理** | 部门信息管理 |
| **登录认证** | JWT令牌登录、权限校验 |
| **日志管理** | 操作日志记录与查询 |
| **报表管理** | 数据统计报表 |
```

## 环境要求
- JDK 17+
- MySQL 8.0+
- Maven 3.8+

## 快速开始

### 1. 克隆项目

```bash
git clone <项目仓库地址>
cd VirtueEdu-parent
```

### 2. 数据库配置

根据项目的数据库sql脚本（./document/virtue_edu_db_2026-05-16_124031.sql），创建数据库并修改 `application.yml` 中的数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/virtue_edu_db
    username: your_username
    password: your_password
```

### 3. 运行项目

```bash
mvn clean install
cd VirtueEdu-web-management
mvn spring-boot:run
```

### 4. 访问服务

服务启动后默认监听 `http://localhost:8080`

## API 接口示例
见 `./document/VirtueEdu web management.openapi.json`

### 登录接口

```http
POST /api/login
Content-Type: application/json

{
    "username": "admin",
    "password": "123456"
}
```

### 获取学生列表

```http
GET /api/students?page=1&size=10
Authorization: Bearer <token>
```

## 配置说明

### OSS 配置

在 `application.yml` 中配置阿里云 OSS：
修改 `endpoint`、`bucketName` 和 `region` 为您的阿里云 OSS 配置。

```yaml
aliyun:
  oss:
    endpoint: https://oss-cn-beijing.aliyuncs.com
    bucketName: your-bucket-name
    region: cn-beijing
```

### JWT 配置

环境变量配置：
- `JWT_SECRET`: JWT密钥
- `JWT_EXPIRE`: 过期时间（毫秒）

## 开发规范

- 使用 Lombok 简化代码
- 遵循 RESTful API 设计规范
- 使用 ThreadLocal 存储当前登录用户
- 全局异常处理统一返回格式

## 许可证

MIT License

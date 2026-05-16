package org.perswsj.controller;

import org.perswsj.anno.Log;
import org.perswsj.model.Dept;
import org.perswsj.model.Result;
import org.perswsj.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/depts") // 设置基础路径
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门全部数据
     * @return 部门列表
     */
    @GetMapping
    public Result list() {
        log.info("查询部门全部数据");
        return Result.success(deptService.findAll());
    }

//    // 基于HTTP DELETE方法删除部门数据
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        // 从请求参数中获取部门ID
//        String s_id = request.getParameter("id");
//        Integer id = Integer.parseInt(s_id);
//        // 调用服务层删除部门数据
//        deptService.deleteById(id);
//        System.out.println("删除部门数据，id=" + id);
//        return Result.success();
//    }
    // 注解方式删除部门数据
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id", required = false) Integer id) {
//        // 调用服务层删除部门数据
//        deptService.deleteById(id);
//        System.out.println("删除部门数据，id=" + id);
//        return Result.success();
//    }
    // 省略@RequestParam注解，直接使用方法参数接收部门ID（保证请求参数和形参相同）

    /**
     * 根据部门ID删除部门数据
     * @param id 部门ID
     * @return 删除结果
     */
    @Log
    @DeleteMapping
    public Result delete(Integer id) {
        log.info("删除部门数据，id={}", id);
        // 调用服务层删除部门数据
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门数据
     * @param dept 部门信息
     * @return 新增结果
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门数据，部门信息={}", dept);
        // 调用服务层新增部门数据
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据部门ID查询部门数据
     * @param id 部门ID
     * @return 部门信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据部门ID查询部门数据，id={}", id);
        // 调用服务层查询部门数据
        return Result.success(deptService.getById(id));
    }

    /**
     * 根据部门ID修改部门数据
     * @param dept 部门信息
     * @return 修改结果
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("根据部门ID修改部门数据，部门信息={}", dept);
        // 调用服务层修改部门数据
        deptService.update(dept);
        return Result.success();
    }
}

package org.perswsj.controller;

import lombok.extern.slf4j.Slf4j;
import org.perswsj.anno.Log;
import org.perswsj.model.Emp;
import org.perswsj.model.EmpQueryParam;
import org.perswsj.model.PageResult;
import org.perswsj.model.Result;
import org.perswsj.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    /**
     * 查询全部员工数据
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询全部员工数据");
        // 调用service查询全部员工数据
        List<Emp> emps = empService.getAll();

        return Result.success(emps);
    }

    /**
     * 实现员工列表分页查询
     * @param empQueryParam 查询参数对象，包含分页和过滤条件
     * @return 分页结果
    */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询员工列表数据，参数：{}", empQueryParam);
        // 调用service查询员工列表数据
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 实现新增员工数据功能
     * @param emp 员工对象，包含新增员工数据
     * @return 新增结果
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工数据，参数：{}", emp);
        // 调用service新增员工数据
        empService.save(emp);
        return Result.success();
    }

//    /**
//     * 实现批量删除员工数据功能（数组形式）
//     * @param ids 员工ID数组
//     * @return 删除结果
//     */
//    @DeleteMapping
//    public Result deleteById(Integer[] ids) {
//        log.info("批量删除员工数据，参数：{}", Arrays.toString(ids));
//
//        return Result.success();
//    }

    /**
     * 实现批量删除员工数据功能（集合形式）
     * @param ids 员工ID集合
     * @return 删除结果
     */
    @Log
    @DeleteMapping
    public Result deleteById(@RequestParam List<Integer> ids) {
        log.info("批量删除员工数据，参数：{}", ids);

        // 调用service批量删除员工数据
        empService.delete(ids);

        return Result.success();
    }

    /**
     * 查询员工详情
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询员工详情，参数：{}", id);
        // 调用service查询员工详情
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工数据
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工数据，参数：{}", emp);
        empService.update(emp);

        return Result.success();
    }
}

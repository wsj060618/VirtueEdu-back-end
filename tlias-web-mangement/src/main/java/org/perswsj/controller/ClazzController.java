package org.perswsj.controller;

import org.perswsj.anno.Log;
import org.perswsj.dto.ClazzDto;
import lombok.extern.slf4j.Slf4j;
import org.perswsj.model.Clazz;
import org.perswsj.model.ClazzQueryParam;
import org.perswsj.model.PageResult;
import org.perswsj.model.Result;
import org.perswsj.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    /**
     * 查询所有班级数据
     * @return 班级数据列表
     */
    @GetMapping("/list")
    public Result getAllClazz() {
        log.info("查询所有班级数据");
        // 查询所有班级数据
        List<ClazzDto> clazzList = clazzService.getAllClazz();
        return Result.success(clazzList);
    }

    /**
     * 分页查询班级数据
     * @param clazzQueryParam 查询参数，包含分页信息和查询条件
     * @return 分页结果
     */
    @GetMapping
    public Result getClazzPage(ClazzQueryParam clazzQueryParam) {
        log.info("查询班级分页数据: {}", clazzQueryParam);
        // 查询班级分页数据
        PageResult<Clazz> clazzList = clazzService.getClazzPage(clazzQueryParam);

        return Result.success(clazzList);
    }

    /**
     * 根据ID查询班级数据
     * @param id 班级ID
     * @return 班级数据
     */
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable String id) {
        log.info("根据ID查询班级数据: {}", id);
        // 根据ID查询班级数据
        Clazz clazz = clazzService.getClazzById(id);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("id", clazz.getId());
        data.put("name", clazz.getName());
        data.put("room", clazz.getRoom());
        data.put("beginDate", clazz.getBeginDate());
        data.put("endDate", clazz.getEndDate());
        data.put("masterId", clazz.getMasterId());
        data.put("subject", clazz.getSubject());
        data.put("createTime", clazz.getCreateTime());
        data.put("updateTime", clazz.getUpdateTime());

        return Result.success(data);
    }

    /**
     * 新增班级数据
     * @param clazz 班级对象，包含新增班级数据
     * @return 新增结果
     */
    @Log
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        log.info("新增班级数据: {}", clazz);
        // 新增班级数据
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /**
     * 修改班级数据
     * @param clazz 班级对象，包含修改班级数据
     * @return 修改结果
     */
    @Log
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("修改班级数据: {}", clazz);
        // 修改班级数据
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    /**
     * 删除班级数据
     * @param id 班级ID
     * @return 删除结果
     */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable String id) {
        log.info("删除班级数据: {}", id);
        // 删除班级数据
        clazzService.deleteById(id);
        return Result.success();
    }
}

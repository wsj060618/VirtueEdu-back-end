package org.perswsj.mapper;

import com.github.pagehelper.Page;
import org.perswsj.dto.ClazzDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.perswsj.model.Clazz;
import org.perswsj.model.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 查询所有班级数据
     * @return 班级数据列表
     */
    List<ClazzDto> findAll();

    /**
     * 分页查询班级列表
     * @param clazzQueryParam 查询参数
     * @return 分页结果
     */
    Page<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 根据ID查询班级数据
     * @param id 班级ID
     * @return 班级数据
     */
    Clazz selectById(String id);

    /**
     * 新增班级数据
     * @param clazz 班级对象，包含新增班级数据
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Clazz clazz);

    /**
     * 修改班级数据
     * @param clazz 班级对象，包含修改班级数据
     */
    void updateById(Clazz clazz);

    /**
     * 删除班级数据
     * @param id 班级ID
     */
    void deleteById(String id);

}

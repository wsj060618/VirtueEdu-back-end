package org.perswsj.service;

import org.perswsj.dto.ClazzDto;
import org.perswsj.model.Clazz;
import org.perswsj.model.ClazzQueryParam;
import org.perswsj.model.PageResult;

import java.util.List;

public interface ClazzService {

    /**
     * 查询班级分页数据
     * @param clazzQueryParam 班级查询参数，包含分页信息和查询条件
     * @return 分页数据
     */
    PageResult<Clazz> getClazzPage(ClazzQueryParam clazzQueryParam);

    /**
     * 根据ID查询班级数据
     * @param id 班级ID
     * @return 班级数据
     */
    Clazz getClazzById(String id);

    /**
     * 新增班级数据
     * @param clazz 班级对象，包含新增班级数据
     */
    void addClazz(Clazz clazz);

    /**
     * 修改班级数据
     * @param clazz 班级对象，包含修改班级数据
     */
    void updateClazz(Clazz clazz);

    /**
     * 删除班级数据
     * @param id 班级ID
     */
    void deleteById(String id);

    /**
     * 查询所有班级数据
     * @return 班级数据列表
     */
    List<ClazzDto> getAllClazz();
}

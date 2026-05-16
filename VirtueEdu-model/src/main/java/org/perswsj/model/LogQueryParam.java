package org.perswsj.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogQueryParam {
    private Integer page = 1;     //当前页码
    private Integer pageSize = 10; //每页记录数

    private Integer operateEmpId;   //操作人ID（预留）
    private String beginTime;       //开始时间（预留）
    private String endTime;         //结束时间（预留）
    private String className;       //类名（预留）
    private String methodName;      //方法名（预留）
}

package org.perswsj.model;

import lombok.Data;

@Data
public class StudentQueryParam {
    private String name;
    private Integer degree;
    private String clazzId;
    private Integer page;
    private Integer pageSize;
}

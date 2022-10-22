package com.qing.www.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Clazz implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 班级唯一id
     */
    @TableId(value = "clazzId", type = IdType.AUTO)
    private Integer clazzId;

    /**
     * 学院
     */
    private String institute;

    /**
     * 专业
     */
    private String major;

    /**
     * 班别
     */
    private Integer number;
}

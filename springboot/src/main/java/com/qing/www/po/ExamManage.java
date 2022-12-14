package com.qing.www.po;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 考试管理表
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@Getter
@Setter
@TableName("exam_manage")
public class ExamManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试编号
     */
    @TableId(value = "examCode", type = IdType.AUTO)
    private Integer examCode;

    /**
     * 该次考试介绍
     */
    private String description;

    /**
     * 课程名称
     */
    private String source;

    /**
     * 试卷编号
     */
    @TableField(value = "paperId")
    private Integer paperId;

    /**
     * 起始时间
     */
    @TableField(value = "startTime")
    private String startTime;

    /**
     * 结束时间
     */
    @TableField(value = "endTime")
    private String endTime;

    /**
     * 年级
     */
    private String grade;

    /**
     * 学期
     */
    private String term;

    /**
     * 专业
     */
    private String major;

    /**
     * 学院
     */
    private String institute;

    /**
     * 总分
     */
    @TableField(value = "totalScore")
    private Integer totalScore;

    /**
     * 考试类型
     */
    private String type;

    /**
     * 考生须知
     */
    private String tips;

    /**
     * 考试状态(0-未开始;1-进行中;2-已结束)
     */
    private Integer state;

}

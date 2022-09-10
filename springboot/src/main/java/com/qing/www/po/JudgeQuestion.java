package com.qing.www.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 判断题题库表
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@Getter
@Setter
@TableName("judge_question")
public class JudgeQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 试题编号
     */
    @TableId(value = "questionId", type = IdType.AUTO)
    private Integer questionId;

    /**
     * 考试科目
     */
    private String subject;

    /**
     * 试题内容
     */
    private String question;

    /**
     * 正确答案
     */
    private String answer;

    /**
     * 题目解析
     */
    private String analysis;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 难度等级
     */
    private String level;

    /**
     * 所属章节
     */
    private String section;
}

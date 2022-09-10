package com.qing.www.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 试卷管理表
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@Getter
@Setter
@TableName("paper_manage")
public class PaperManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 试卷编号
     */
    private Integer paperId;

    /**
     * 题目类型
     */
    private Integer questionType;

    /**
     * 题目编号
     */
    private Integer questionId;
}

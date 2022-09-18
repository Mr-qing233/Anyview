package com.qing.www.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@Getter
@Setter
@TableName("t_exam_paper_question_customer_answer")
public class ExamPaperQuestionCustomerAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer questionId;

    private Integer examPaperId;

    private Integer examPaperAnswerId;

    private Integer questionType;

    private Integer subjectId;

    private Integer customerScore;

    private Integer questionScore;

    private Integer questionTextContentId;

    private String answer;

    private Integer textContentId;

    private Boolean doRight;

    private Integer createUser;

    private LocalDateTime createTime;

    private Integer itemOrder;


}

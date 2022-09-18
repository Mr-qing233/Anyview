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
@TableName("t_question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer questionType;

    private Integer subjectId;

    private Integer score;

    private Integer gradeLevel;

    private Integer difficult;

    private String correct;

    private Integer infoTextContentId;

    private Integer createUser;

    private Integer status;

    private LocalDateTime createTime;

    private Boolean deleted;


}

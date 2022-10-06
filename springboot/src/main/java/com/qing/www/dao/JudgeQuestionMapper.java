package com.qing.www.dao;

import com.qing.www.po.JudgeQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 判断题题库表 Mapper 接口
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface JudgeQuestionMapper extends BaseMapper<JudgeQuestion> {
    @Select("select * from judge_question where questionId in (select questionId from paper_manage where questionType = 3 and paperId = #{paperId})")
    List<JudgeQuestion> findByIdAndType(Integer paperId);
}

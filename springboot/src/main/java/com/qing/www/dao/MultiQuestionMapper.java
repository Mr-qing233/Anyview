package com.qing.www.dao;

import com.qing.www.po.MultiQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 选择题题库表 Mapper 接口
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface MultiQuestionMapper extends BaseMapper<MultiQuestion> {
    @Select("select * from multi_question where questionId in (select questionId from paper_manage where questionType = 1 and paperId = #{paperId})")
    List<MultiQuestion> findByIdAndType(Integer PaperId);
}

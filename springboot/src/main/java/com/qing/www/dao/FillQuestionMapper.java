package com.qing.www.dao;

import com.qing.www.po.FillQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 填空题题库 Mapper 接口
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface FillQuestionMapper extends BaseMapper<FillQuestion> {
    @Select("select * from fill_question where questionId in (select questionId from paper_manage where questionType = 2 and paperId = #{paperId})")
    List<FillQuestion> findByIdAndType(Integer paperId);
}

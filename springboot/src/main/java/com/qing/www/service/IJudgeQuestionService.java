package com.qing.www.service;

import com.qing.www.po.JudgeQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 判断题题库表 服务类
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface IJudgeQuestionService extends IService<JudgeQuestion> {
    List<JudgeQuestion> findByIdAndType(Integer paperId);
}

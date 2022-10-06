package com.qing.www.service;

import com.qing.www.po.FillQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 填空题题库 服务类
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface IFillQuestionService extends IService<FillQuestion> {
    List<FillQuestion> findByIdAndType(Integer paperId);
}

package com.qing.www.service;

import com.qing.www.po.MultiQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 选择题题库表 服务类
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface IMultiQuestionService extends IService<MultiQuestion> {
    List<MultiQuestion> findByIdAndType(Integer PaperId);

}

package com.qing.www.service.impl;

import com.qing.www.entity.Question;
import com.qing.www.mapper.QuestionMapper;
import com.qing.www.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}

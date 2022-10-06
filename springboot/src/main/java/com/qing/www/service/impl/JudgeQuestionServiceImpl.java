package com.qing.www.service.impl;

import com.qing.www.po.JudgeQuestion;
import com.qing.www.dao.JudgeQuestionMapper;
import com.qing.www.service.IJudgeQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 判断题题库表 服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@Service
public class JudgeQuestionServiceImpl extends ServiceImpl<JudgeQuestionMapper, JudgeQuestion> implements IJudgeQuestionService {
    @Resource
    private JudgeQuestionMapper judgeQuestionMapper;
    @Override
    public List<JudgeQuestion> findByIdAndType(Integer paperId) {
        return judgeQuestionMapper.findByIdAndType(paperId);
    }
}

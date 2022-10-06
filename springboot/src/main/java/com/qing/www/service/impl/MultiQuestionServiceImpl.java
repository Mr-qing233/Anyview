package com.qing.www.service.impl;

import com.qing.www.po.MultiQuestion;
import com.qing.www.dao.MultiQuestionMapper;
import com.qing.www.service.IMultiQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 选择题题库表 服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@Service
public class MultiQuestionServiceImpl extends ServiceImpl<MultiQuestionMapper, MultiQuestion> implements IMultiQuestionService {
    @Resource
    private MultiQuestionMapper multiQuestionMapper;
    @Override
    public List<MultiQuestion> findByIdAndType(Integer PaperId) {
        return multiQuestionMapper.findByIdAndType(PaperId);
    }
}

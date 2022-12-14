package com.qing.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qing.www.po.FillQuestion;
import com.qing.www.dao.FillQuestionMapper;
import com.qing.www.service.IFillQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 填空题题库 服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@Service
public class FillQuestionServiceImpl extends ServiceImpl<FillQuestionMapper, FillQuestion> implements IFillQuestionService {
    @Resource
    private FillQuestionMapper fillQuestionMapper;

    @Override
    public List<FillQuestion> findByIdAndType(Integer paperId) {
        return fillQuestionMapper.findByIdAndType(paperId);
    }
}

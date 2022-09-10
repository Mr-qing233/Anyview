package com.qing.www.service.impl;

import com.qing.www.po.Score;
import com.qing.www.dao.ScoreMapper;
import com.qing.www.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 成绩管理表 服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

}

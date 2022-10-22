package com.qing.www.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qing.www.po.ExamManage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 考试管理表 服务类
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface IExamManageService extends IService<ExamManage> {
    List<ExamManage> findAll();
    IPage<ExamManage> findAll(Page<ExamManage> page);
    IPage<ExamManage> findAllByClazz(Page<ExamManage> page,Integer clazzId);
}

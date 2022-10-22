package com.qing.www.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qing.www.po.ExamManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 考试管理表 Mapper 接口
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface ExamManageMapper extends BaseMapper<ExamManage> {
    @Select("select * from exam_manage")
    List<ExamManage> findAll();

    @Select("select * from exam_manage")
    IPage<ExamManage> findAll(Page page);

    @Select("SELECT em1.* FROM exam_manage em1, exam_clazz e1 WHERE em1.`examCode` = e1.`examCode` AND e1.`clazzId` = #{clazzId}")
    IPage<ExamManage> findAllByClazz(Page page,Integer clazzId);

}

package com.qing.www.dao;

import com.qing.www.po.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qing.www.vo.StudentVo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
public interface StudentMapper extends BaseMapper<Student> {

    @Select("SELECT s1.`studentId`,s1.`studentName`,s1.`cardId`,s1.`clazzId`,c1.`institute`,c1.`major`,c1.`number`,s1.`sex`,s1.`grade`,s1.`permission` FROM student s1, clazz c1 WHERE s1.clazzId = c1.clazzId AND s1.cardId = #{cardId} AND s1.pwd = #{pwd}")
    StudentVo findStudentInfo(String cardId,String pwd);

    @Select("SELECT s1.`studentId`,s1.`studentName`,s1.`cardId`,s1.`clazzId`,c1.`institute`,c1.`major`,c1.`number`,s1.`sex`,s1.`grade`,s1.`permission` FROM student s1, clazz c1 WHERE s1.clazzId = c1.clazzId AND s1.cardId = #{cardId}")
    StudentVo confirmStudentInfo(String cardId);
}

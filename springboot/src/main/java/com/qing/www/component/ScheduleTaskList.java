package com.qing.www.component;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qing.www.dto.common.CommonEnum;
import com.qing.www.dto.common.CommonResult;
import com.qing.www.po.ExamManage;
import com.qing.www.service.IExamManageService;
import com.qing.www.util.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *     该类用来存放轮询任务
 * </p>
 */
@Component
public class ScheduleTaskList {

    private static Logger logger = LogManager.getLogger(ScheduleTaskList.class.getName());

    @Resource
    private IExamManageService examManageService;

    /**
     * 检查ExamManage中考试状态
     *
     * 考试状态0-未开始;1-进行中;2-已结束;-1-准备
     * 若当前时间小于开始时间-10min设为0
     * 若当前时间大于开始时间-10min小于开始时间设为-1
     * 若当前时间大于开始时间小于结束时间设为-1
     */
    public void checkExamManage(){
        DateTime time = new DateTime(new Date());
        List<ExamManage> examManages = examManageService.findAll();
        for(ExamManage examManage : examManages){
            DateTime startTime = new DateTime(examManage.getStartTime());
            DateTime endTime = new DateTime(examManage.getEndTime());

            UpdateWrapper<ExamManage> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("examCode",examManage.getExamCode());
            //未开始
            if(time.before(DateUtil.offsetMinute(startTime,-10))){
                examManage.setState(0);
            }
            //准备中
            if (time.isIn(DateUtil.offsetMinute(startTime,-10),startTime)){
                examManage.setState(-1);
            }
            //开始
            if (time.isIn(startTime,endTime)){
                examManage.setState(1);
            }
            //结束
            if (time.after(endTime)){
                examManage.setState(2);
            }
            boolean res = examManageService.update(examManage,updateWrapper);
            if (!res) {
                throw new ServiceException(CommonEnum.EXAM_UPDATE_ERROR) ;
            }
            logger.info("更新操作执行---");
        }
    }
}

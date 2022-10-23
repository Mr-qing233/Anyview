package com.qing.www.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qing.www.component.ClientWebSocket;
import com.qing.www.dto.common.CommonEnum;
import com.qing.www.dto.common.CommonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.qing.www.service.IExamManageService;
import com.qing.www.po.ExamManage;


/**
 * <p>
 * 考试管理表 前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@RestController
@RequestMapping("/exam-manage")
public class ExamManageController {

    private static Logger logger = LogManager.getLogger(ExamManageController.class.getName());

    @Resource
    private IExamManageService examManageService;

    @Resource
    private ClientWebSocket webSocket;
    @PostMapping
    public Boolean save(@RequestBody ExamManage examManage) {
        return examManageService.saveOrUpdate(examManage);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return examManageService.removeById(id);
    }

    @GetMapping
    public List<ExamManage> findAll() {
        return examManageService.list();
    }

    @GetMapping("/exams/{page}/{size}")
    public CommonResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        System.out.println("分页查询所有试卷");
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findAll(examManage);
        return CommonResult.Success(all);
    }

    @GetMapping("/exams/{page}/{size}/{clazzId}")
    public CommonResult findAllByClazz(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("clazzId") Integer clazzId){
        System.out.println("分页查询该班级所有试卷");
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findAllByClazz(examManage,clazzId);
        return CommonResult.Success(all);
    }

    @GetMapping("/exam/{examCode}")
    public CommonResult findById(@PathVariable("examCode") Integer examCode){
        System.out.println("根据ID查找");
        ExamManage res = examManageService.getById(examCode);
        if(res == null) {
            return CommonResult.Error(CommonEnum.EXAM_NOT_FOUND);
        }
        return CommonResult.Success(res);
    }


    @GetMapping("/{id}")
    public List<ExamManage> findOne(@PathVariable Integer id) {
        return examManageService.list();
    }

    @GetMapping("/page")
    public Page<ExamManage> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return examManageService.page(new Page<>(pageNum, pageSize));
    }
    @PutMapping("/examUpdate")
    public CommonResult update(@RequestBody ExamManage examManage){
        UpdateWrapper<ExamManage> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("examCode",examManage.getExamCode());

        boolean res = examManageService.update(examManage,updateWrapper);
        if (!res) {
            return CommonResult.Error(CommonEnum.EXAM_UPDATE_ERROR);
        }
        System.out.print("更新操作执行---");
        return CommonResult.Success();
    }

    @PutMapping("/examDelay/{time}")
    public CommonResult delay(@RequestBody ExamManage examManage,@PathVariable("time")Integer time) throws ParseException {
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(examManage.getEndTime());
        examManage.setEndTime(String.valueOf(DateUtil.offsetMinute(endTime,time)));

        UpdateWrapper<ExamManage> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("examCode",examManage.getExamCode());

        boolean res = examManageService.update(examManage,updateWrapper);
        if (!res) {
            return CommonResult.Error(CommonEnum.EXAM_UPDATE_ERROR);
        }
        System.out.print("更新操作执行---");
        return CommonResult.Success();
    }

    @DeleteMapping("/exam/{examCode}")
    public CommonResult deleteById(@PathVariable("examCode") Integer examCode){
        boolean res = examManageService.removeById(examCode);
        if(!res){
            return CommonResult.Error(CommonEnum.EXAM_DELETE_ERROR);
        }
        return CommonResult.Success();
    }

    @PostMapping("/startExam/{examCode}/{cardId}")
    public CommonResult startExam(@PathVariable("examCode") Integer examCode,@PathVariable("cardId") String cardId) throws ParseException {
        logger.info("开启倒计时");
        ExamManage examManage = examManageService.getById(examCode);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = format.parse(DateUtil.now());
        Date endTime = format.parse(examManage.getEndTime());
        int between = (int) DateUtil.between(now, endTime, DateUnit.SECOND);
        webSocket.sendMessage(cardId,between,2);

        return CommonResult.Success();
    }

}


package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qing.www.po.FillQuestion;
import com.qing.www.po.JudgeQuestion;
import com.qing.www.po.MultiQuestion;
import com.qing.www.service.IFillQuestionService;
import com.qing.www.service.IJudgeQuestionService;
import com.qing.www.service.IMultiQuestionService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qing.www.service.IPaperManageService;
import com.qing.www.po.PaperManage;


/**
 * <p>
 * 试卷管理表 前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@RestController
@RequestMapping("/paper-manage")
public class PaperManageController {

    @Resource
    private IPaperManageService paperManageService;
    @Resource
    private IFillQuestionService fillQuestionService;
    @Resource
    private IJudgeQuestionService judgeQuestionService;
    @Resource
    private IMultiQuestionService multiQuestionService;

    @PostMapping
    public Boolean save(@RequestBody PaperManage paperManage) {
        return paperManageService.saveOrUpdate(paperManage);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return paperManageService.removeById(id);
    }

    @GetMapping
    public List<PaperManage> findAll() {
        return paperManageService.list();
    }

    @GetMapping("/{id}")
    public List<PaperManage> findOne(@PathVariable Integer id) {
        return paperManageService.list();
    }

    @GetMapping("/page")
    public Page<PaperManage> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return paperManageService.page(new Page<>(pageNum, pageSize));
    }

    /**
     * 需要区分题目类型查找总题目
     */
    @GetMapping("/paper/{paperId}")
    public Map<Integer,List<?>> findById(@PathVariable("paperId") Integer paperId){
        List<MultiQuestion> multiQuestion = multiQuestionService.findByIdAndType(paperId);//选择题库1
        List<FillQuestion> fillQuestion = fillQuestionService.findByIdAndType(paperId);//选择题库2
        List<JudgeQuestion> judgeQuestions = judgeQuestionService.findByIdAndType(paperId);//选择题库3
        HashMap<Integer, List<?>> map = new HashMap<>();
        map.put(1,multiQuestion);
        map.put(2,fillQuestion);
        map.put(3,judgeQuestions);
        return map;
    }
}


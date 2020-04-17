package com.lagou.edu.controller;

import com.lagou.edu.common.ResultPack;
import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ResumeController
 * @Description TODO
 * @Author xsq
 * @Date 2020/4/16 16:03
 **/
@Controller
@RequestMapping("/resume")
public class ResumeController {


    @Autowired
    private ResumeService resumeService;

    /**
     * 增加用户
     *
     * @param resume
     */
    @RequestMapping("/addNewResume.do")
    @ResponseBody
    public ResultPack addNewResume(@RequestBody Resume resume) throws Exception {
        resumeService.addNewResume(resume);
        return ResultPack.success();
    }

    /**
     * 修改用户
     *
     * @param resume
     */
    @RequestMapping("/updateResume.do")
    @ResponseBody
    public ResultPack updateResume(@RequestBody Resume resume) {
        resumeService.updateResume(resume);
        return ResultPack.success();
    }

    /**
     * 查询所有用户
     */
    @RequestMapping("/queryAll.do")
    @ResponseBody
    public ResultPack queryAll() throws Exception {
        return ResultPack.success(resumeService.queryResumeList());
    }

    /**
     * 删除指定用户
     */
    @RequestMapping("/delResume.do")
    @ResponseBody
    public ResultPack delResume(Long id) throws Exception {
        resumeService.delResume(id);
        return ResultPack.success();
    }


}

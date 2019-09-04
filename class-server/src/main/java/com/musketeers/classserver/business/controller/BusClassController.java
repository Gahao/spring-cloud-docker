package com.musketeers.classserver.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.musketeers.classserver.business.service.impl.BusClassServiceImpl;
import com.musketeers.classserver.business.service.impl.BusClassphaseServiceImpl;
import com.musketeers.common.entity.business.BusClass;
import com.musketeers.common.entity.business.BusClassphase;
import com.musketeers.common.utils.JsonTool;
import com.musketeers.common.utils.RBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * <p>
 *  课程控制器
 * </p>
 *
 * @author fanjiahao
 * @since 2019-04-05
 */
@RestController
@RequestMapping(value={"/business/bus-class"},produces="application/json;charset=UTF-8")
@Api(tags = "课程接口")
public class BusClassController {

    @Autowired
    private BusClassServiceImpl busClassServiceImpl;
    @Autowired
    private BusClassphaseServiceImpl busClassphaseService;

    @RequestMapping(value="/getAllClass",method={RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "分页获取课程概要信息",notes = "不包含课程的详细介绍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value="当前页",dataType="Long",paramType="query"),
            @ApiImplicitParam(name="size",value="每页大小",dataType="Long",paramType="query"),
            @ApiImplicitParam(name="status",value="发布的标志，1代表发布，0代表不发布先在后台存储，不设参则返回全部课程",dataType="Long",paramType="query")
    })
    public RBody getAllClass(@RequestParam(required = false) Long current,
                             @RequestParam(required = false) Long size,
                             @RequestParam(required = false) String status){
        RBody rbody=new RBody();
        try {
            Page page = null;
            if(current != null && size != null) {
                page = new Page(current, size);
            } else {
                page = new Page(1, 999);
            }
            QueryWrapper<BusClass> queryWrapper = new QueryWrapper<>();
            queryWrapper.select(new String[]{"classid","classname","unitprice","info","status","useroriented","type","theme","imgURL","createtime","publishtime","location","days","focus"});
            if(status != null)
                queryWrapper.eq("status",status);
            queryWrapper.orderByDesc("createtime");
            IPage<BusClass> result = busClassServiceImpl.page(page,queryWrapper);//  querySummaryBusClass(page,queryWrapper);
            rbody.setRet(Boolean.TRUE, "成功返回课程信息", result);
        }catch (Exception ex){
            rbody.setRet(Boolean.FALSE, "返回课程信息失败", ex.getMessage());
        }finally {
            return rbody;
        }
    }

    @RequestMapping(value="/getClassByID",method={RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "通过classID获取课程",notes = "其中返回的busClassphaseList为课程分期信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classID",value="课程id",dataType="Integer",paramType="query")
    })
    public RBody getClassByID(HttpServletRequest request, HttpSession session){
        RBody rbody=new RBody();
        try{
            int classID=Integer.parseInt(request.getParameter("classID"));
            BusClass busClass=busClassServiceImpl.getClassByID(classID);
            rbody.setRet(Boolean.TRUE, "成功返回课程信息", busClass);
        }catch (Exception ex){
            rbody.setRet(Boolean.FALSE, "返回课程信息失败", ex.getMessage());
        }finally {
            return rbody;
        }
    }

    @RequestMapping(value="/deleteClassByID",method={RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "通过classID删除课程以及分期")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classID",value="课程id",dataType="Integer",paramType="query")
    })
    public RBody deleteClassByID(HttpServletRequest request, HttpSession session){
        RBody rbody=new RBody();
        try{
            int classID=Integer.parseInt(request.getParameter("classID"));
            QueryWrapper<BusClass> busClassQueryWrapper = new QueryWrapper<>();
            busClassQueryWrapper.eq("classid", classID);
            busClassServiceImpl.remove(busClassQueryWrapper);

            QueryWrapper<BusClassphase> busClassphaseQueryWrapper = new QueryWrapper<>();
            busClassphaseQueryWrapper.eq("classid",classID);
            busClassphaseService.remove(busClassphaseQueryWrapper);
            rbody.setRet(Boolean.TRUE, "成功删除课程信息");
        }catch (Exception ex){
            rbody.setRet(Boolean.FALSE, "删除课程信息失败", ex.getMessage());
        }finally {
            return rbody;
        }
    }

    @RequestMapping(value="/addClass",method={RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "添加新课程",notes = "同时会添加课程分期信息，分期信息使用list格式传入")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classname",value="课程名称",dataType="String"),
            @ApiImplicitParam(name="unitprice",value="课程价格",dataType="Double"),
            @ApiImplicitParam(name="info",value="课程简介",dataType="String"),
            @ApiImplicitParam(name="status",value="课程状态：1代表发布，0代表未发布",dataType="String"),
            @ApiImplicitParam(name="useroriented",value="课程面向用户",dataType="String"),
            @ApiImplicitParam(name="type",value="课程类型",dataType="String"),
            @ApiImplicitParam(name="theme",value="课程主题",dataType="String"),
            @ApiImplicitParam(name="imgURL",value="课程主题",dataType="String"),
            @ApiImplicitParam(name="rawcontent",value="课程详细内容介绍(json)",dataType="String"),
            @ApiImplicitParam(name="content",value="课程详细内容介绍(html)",dataType="String"),
            @ApiImplicitParam(name="busClassphaseList",value="数组方式传入分期数据：'phaseno'分期号，'begindate'开始日期,'enddate'结束日期,'contain'容纳人数",dataType="List"),
            @ApiImplicitParam(name="location",value="课程办课地点",dataType="String"),
            @ApiImplicitParam(name="days",value="课程时长",dataType="String")
    })
    public RBody addClass(@RequestBody BusClass busClass) {
        RBody rbody=new RBody();
        try{
            JSONObject jsonObject1=busClassServiceImpl.addNewClass(busClass);
            rbody.setRet(Boolean.TRUE,"成功新建课程",jsonObject1);
        }catch (Exception ex){
            rbody.setRet(Boolean.FALSE,"新建课程失败",ex.getMessage());
        }finally {
            return rbody;
        }
    }

    @RequestMapping(value="/updateClass",method={RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "更新课程信息",notes = "同时会更新课程分期信息，分期信息使用list格式传入")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classid",value="课程ID",dataType="Integer"),
            @ApiImplicitParam(name="classname",value="课程名称",dataType="String"),
            @ApiImplicitParam(name="unitprice",value="课程价格",dataType="Double"),
            @ApiImplicitParam(name="info",value="课程简介",dataType="String"),
            @ApiImplicitParam(name="status",value="课程状态：1代表发布，0代表未发布",dataType="String"),
            @ApiImplicitParam(name="useroriented",value="课程面向用户",dataType="String"),
            @ApiImplicitParam(name="type",value="课程类型",dataType="String"),
            @ApiImplicitParam(name="theme",value="课程主题",dataType="String"),
            @ApiImplicitParam(name="imgURL",value="课程主题",dataType="String"),
            @ApiImplicitParam(name="rawcontent",value="课程详细内容介绍(json)",dataType="String"),
            @ApiImplicitParam(name="content",value="课程详细内容介绍(html)",dataType="String"),
            @ApiImplicitParam(name="busClassphaseList",value="数组方式传入分期数据（只更新，删除不插入使用单独接口）：'phaseid'分期id(必须传入),'phaseno'分期号,'begindate'开始日期,'enddate'结束日期,'contain'容纳人数",dataType="List"),
            @ApiImplicitParam(name="location",value="课程办课地点",dataType="String"),
            @ApiImplicitParam(name="days",value="课程时长",dataType="String")
    })
    public RBody updateClass(@RequestBody BusClass busClass) {
        RBody rbody=new RBody();
        try{
            int flag=busClassServiceImpl.updateClass(busClass);
            if(flag>=1){
            rbody.setRet(Boolean.TRUE,"成功更新课程");
            }else {
                rbody.setRet(Boolean.FALSE,"不存在该课程");
            }
        }catch (Exception ex){
            rbody.setRet(Boolean.FALSE,"更新课程失败",ex.getMessage());
        }finally {
            return rbody;
        }
    }

    @RequestMapping(value="/alterClassStatus",method={RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "修改课程状态",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classid",value="课程ID",dataType="Integer"),
            @ApiImplicitParam(name="status",value="课程状态：1代表发布，0代表未发布",dataType="String")
    })
    public RBody alterClassStatus(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException, IOException {
        RBody rbody=new RBody();
        try{
            JSONObject jsonObject= JsonTool.parseRequestToJsonObject(request);
            int classid=jsonObject.getInteger("classid");
            String status=jsonObject.getString("status");

            BusClass busClass=new BusClass();
            busClass.setClassid(classid);
            busClass.setStatus(status);
            if(status.equals("1")){
                busClass.setPublishtime(new Date());
            }else{
                busClass.setPublishtime(null);
            }
            int flag=busClassServiceImpl.alterClassStatus(busClass);
            if(flag==1){
                rbody.setRet(Boolean.TRUE,"成功更新课程状态");
            }else {
                rbody.setRet(Boolean.FALSE,"更新课程状态失败，请检查是否存在该课程ID");
            }
        }catch (Exception ex){
            rbody.setRet(Boolean.FALSE,"更新课程状态失败",ex.getMessage());
        }finally {
            return rbody;
        }
    }

    @RequestMapping(value="/alterClassphase",method={RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "修改课程分期",notes = "flag=0(删除)时传入phaseid；flag=1(修改)时传入phaseno、begindate、enddate、contain；flag=2(插入)时传入classid,begindate,enddate,contain,signup")
    @ApiImplicitParams({
            @ApiImplicitParam(name="phaseid",value="课程分期ID",dataType="Integer"),
            @ApiImplicitParam(name="phaseno",value="分期号码",dataType="Integer"),
            @ApiImplicitParam(name="classid",value="课程ID是   ",dataType="Integer"),
            @ApiImplicitParam(name="begindate",value="课程开始时间",dataType="Date"),
            @ApiImplicitParam(name="enddate",value="课程结束时间",dataType="Date"),
            @ApiImplicitParam(name="contain",value="课程容纳人数",dataType="Integer"),
            @ApiImplicitParam(name="flag",value="状态：0代表删除，1代表修改，2代表插入",dataType="int")
    })
    public RBody alterClassphase(@RequestBody BusClassphase busClassphase,
                                 @RequestParam(required = true) int flag){
        RBody rbody=new RBody();
        try{
            int resurt=this.busClassServiceImpl.alterClassphase(busClassphase,flag);
            if(resurt==1){
                rbody.setRet(Boolean.TRUE,"成功更新课程分期状态");
            }else {
                rbody.setRet(Boolean.FALSE,"更新课程分期状态失败");
            }
        }catch (Exception ex){
            rbody.setRet(Boolean.FALSE,"更新课程状态失败",ex.getMessage());
        }finally {
            return rbody;
        }
        }

}


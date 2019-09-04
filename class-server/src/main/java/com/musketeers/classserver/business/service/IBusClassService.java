package com.musketeers.classserver.business.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.musketeers.common.entity.business.BusClass;
import com.musketeers.common.entity.business.BusClassphase;
import com.musketeers.common.entity.business.BusCust;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fanjiahao
 * @since 2019-04-05
 */
public interface IBusClassService extends IService<BusClass> {
    //依据课程id选择课程
    BusClass  getClassByID(int classID);
    //选择全部课程
    List<BusClass> busClassList();
    //存入新课程
    JSONObject addNewClass(BusClass nweClass);
    //更新原有课程信息
    int updateClass(BusClass nweClass);
    //课程报名
    void applyClass(String classids, BusCust busCust, String remark) throws Exception;
    //更新课程状态
    int alterClassStatus(BusClass nweClass);
    //根据status选择课程
    IPage<BusClass> querySummaryBusClass(IPage page, QueryWrapper<BusClass> queryWrapper);
    //根据flag对课程分期进行删除0、修改1，插入2
    int alterClassphase(BusClassphase busClassphase, int flag);
}

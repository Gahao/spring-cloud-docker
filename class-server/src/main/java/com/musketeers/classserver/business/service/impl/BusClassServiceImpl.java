package com.musketeers.classserver.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.musketeers.classserver.business.mapper.BusClassMapper;
import com.musketeers.classserver.business.mapper.BusClassphaseMapper;
import com.musketeers.classserver.business.service.IBusClassService;
import com.musketeers.common.entity.business.BusClass;
import com.musketeers.common.entity.business.BusClassphase;
import com.musketeers.common.entity.business.BusCust;
import com.musketeers.common.enums.SysEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fanjiahao
 * @since 2019-04-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BusClassServiceImpl extends ServiceImpl<BusClassMapper, BusClass> implements IBusClassService {

    @Autowired
    private BusClassMapper busClassMapper;

    @Autowired
    private BusClassphaseMapper busClassphaseMapper;

    @Override
    public BusClass getClassByID(int classID) {
        this.busClassMapper.increaseFocus(classID);
        return this.busClassMapper.getClassByID(classID);
    }

    @Override
    public List<BusClass> busClassList() {
        return this.busClassMapper.selectList(null);
    }

    @Override
    public JSONObject addNewClass(BusClass newClass) {
        Date createtime=new Date();
        newClass.setCreatetime(createtime);
        if(newClass.getStatus()==null){
            newClass.setStatus(SysEnum.CLASS_STATUS_UNOPEN);
            newClass.setPublishtime(null);
        }else if(newClass.getStatus().equals("1")){
            newClass.setPublishtime(createtime);
        }
        this.busClassMapper.insertNewClass(newClass);
        int classID=newClass.getClassid();
        List<BusClassphase> busClassphaseList=newClass.getBusClassphaseList();
        for(BusClassphase busClassphase: busClassphaseList){
            busClassphase.setClassid(classID);
        }
        this.busClassphaseMapper.insertClassphase(busClassphaseList);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("classID",newClass.getClassid());
        return jsonObject;
    }

    @Override
    public int updateClass(BusClass newClass) {
        if(newClass.getStatus()==null){
            newClass.setStatus(SysEnum.CLASS_STATUS_UNOPEN);
            newClass.setPublishtime(null);
        }else if(newClass.getStatus().equals("1")){
            newClass.setPublishtime(new Date());
        }else{
            newClass.setPublishtime(null);
        }
        int classUpdateFlag=this.busClassMapper.updateClass(newClass);
        int classphaseUpdateFlag=0;
        if(classUpdateFlag!=0) {
            classphaseUpdateFlag=this.busClassphaseMapper.updateClassphase(newClass.getBusClassphaseList());
        }
        return classUpdateFlag+classphaseUpdateFlag;
    }

    @Override
    public void applyClass(String classids, BusCust busCust, String remark) throws Exception {
    }

    @Override
    public int alterClassStatus(BusClass status) {
        return this.busClassMapper.alterStatus(status);
    }

    @Override
    public IPage<BusClass> querySummaryBusClass(IPage page, QueryWrapper<BusClass> queryWrapper){
        return this.busClassMapper.querySummeryBusClass(page,queryWrapper);
    }

    @Override
    public int alterClassphase(BusClassphase busClassphase, int flag) {
        int result=0;
        //删除
        if(flag==0){
            result=this.busClassphaseMapper.deleteClassphaseByID(busClassphase);
        }
        //修改
        if(flag==1){
            result=this.busClassphaseMapper.updateClassphaseByID(busClassphase);
        }
        //插入
        if(flag==2){
            result=this.busClassphaseMapper.insertClassphaseByID(busClassphase);
        }
        return result;
    }
}

package com.musketeers.common.entity.business.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderVo implements Serializable {
    /**
     * 客户信息
     */
    @Excel(name = "客户名称",height = 11, width = 15)
    private String custname;
    @Excel(name = "客户性别",height = 11, width = 15)
    private String custsex;
    @Excel(name = "客户年龄",height = 11, width = 5)
    private String age;
    @Excel(name = "客户出生日期",height = 11, width = 15)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String brithdate;
    @Excel(name = "客户手机",height = 11, width = 15)
    private String phonenum;
    @Excel(name = "客户邮箱",height = 11, width = 15)
    private String email;
    @Excel(name = "客户联系地址",height = 11, width = 15)
    private String adress;
    @Excel(name = "家长名称",height = 11, width = 15)
    private String parentname;
    @Excel(name = "家长手机",height = 11, width = 15)
    private String parentphonenum;
    @Excel(name = "客户身高",height = 11, width = 15)
    private Integer height;
    @Excel(name = "客户体重",height = 11, width = 15)
    private Double weight;
    @Excel(name = "客户证件号",height = 11, width = 15)
    private String cardno;
    @Excel(name = "客户在读学校",height = 11, width = 15)
    private String school;

    /**
     * 订单信息
     */
    @ExcelIgnore
    private String orderid;
    @Excel(name = "订单创建时间",height = 11, width = 15)
    private String createdate;
    @Excel(name = "订单总价",height = 11, width = 15)
    private Double total;
    @Excel(name = "订单状态",height = 11, width = 15)
    private String status;
    @Excel(name = "区域",height = 11, width = 15)
    private String region;
    @Excel(name = "备注",height = 11, width = 15)
    private String remark;

    /**
     * 课程分期信息
     */
    @Excel(name = "课程名称",height = 11, width = 15)
    private String classname;
    @Excel(name = "课程分期号",height = 11, width = 15)
    private String phaseno;
    @Excel(name = "课程开始时间",height = 11, width = 15)
    private String begindate;
    @Excel(name = "课程结束时间",height = 11, width = 15)
    private String enddate;
    @ExcelIgnore
    private String phaseids;
}

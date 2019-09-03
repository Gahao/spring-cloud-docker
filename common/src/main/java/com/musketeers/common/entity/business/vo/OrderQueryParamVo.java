package com.musketeers.common.entity.business.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.musketeers.common.entity.business.BusOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderQueryParamVo extends Page<BusOrder> {
    public String status;

    public String startdate;

    public String enddate;

    public String orderid;

    public String classname;

    public Integer phaseno;

    public String custname;

    public String phonenum;

    public String region;


}

package com.musketeers.common.entity.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusClassphase implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer phaseid;

    private Integer phaseno;

    private Integer classid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date begindate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date enddate;

    private Integer contain;

    private Integer signup;

}

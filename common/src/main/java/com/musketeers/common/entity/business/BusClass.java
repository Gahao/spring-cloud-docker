package com.musketeers.common.entity.business;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author fanjiahao
 * @since 2019-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusClass implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer classid;

    private String classname;

    private Double unitprice;

    private String info;

    private String status;

    private String useroriented;

    private String type;

    private String theme;

    @TableField("imgURL")
    private String imgURL;

    private String content;

    private String rawcontent;

    @TableField(exist = false)
    private List<BusClassphase> busClassphaseList;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publishtime;

    private String location;

    private String days;

    private int focus;
}

package com.musketeers.common.entity.business;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 媒体类对象
 * </p>
 *
 * @author fanjiahao
 * @since 2019-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mediaid;

    private String title;

    private String href;

    private String description;

    @TableField("imgURL")
    private String imgURL;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private String editor;

    private String ispublish;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publishtime;

    private String source;

    private String category;
}

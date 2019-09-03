package com.musketeers.common.entity.business;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class BusCust implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String custid;

    private String custname;

    private String phonenum;

    private String adress;

    private String email;

    private String custsex;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date brithdate;

    private String password;

    private String parentname;

    private String parentphonenum;

    //厘米
    private Integer height;

    //公斤
    private Double weight;

    private String cardno;

    private String school;

}

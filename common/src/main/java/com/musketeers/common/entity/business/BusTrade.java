package com.musketeers.common.entity.business;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class BusTrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String orderid;

    private String channel;

    private Double total;

    private String status;

    private String failresult;

    private Date lasttime;

    private String serialnum;

    private String notifymessage;

    private String sendmessage;

    /**
     * 发送的签名值
     */
    private String sign;

}

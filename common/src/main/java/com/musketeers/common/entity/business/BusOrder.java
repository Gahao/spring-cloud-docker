package com.musketeers.common.entity.business;

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
public class BusOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;

    private String custid;

    private Double total;

    private String status;

    private String region;

    private String remark;
}

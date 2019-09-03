package com.musketeers.common.entity.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class BusOrderdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderid;

    private Integer classid;

    private Integer classphaseid;


}

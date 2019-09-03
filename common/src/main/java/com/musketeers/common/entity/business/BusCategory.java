package com.musketeers.common.entity.business;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String categoryid;

    private String categoryname;


}

package com.musketeers.common.entity.system.vo;

import com.musketeers.common.entity.system.SysUser;
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
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserModifyVo extends SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String oldpassword;

    private String newpassword;

}

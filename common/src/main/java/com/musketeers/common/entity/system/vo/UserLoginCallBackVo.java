package com.musketeers.common.entity.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

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
public class UserLoginCallBackVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String usercode;

    private String username;

    private String isadmin;

    private String stationid;

    private String stationname;

    private List<String> authList;

}

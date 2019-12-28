package com.dmtd.hanfu.forum.dao;

import com.dmtd.hanfu.forum.entity.Integral;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <br>
 * Copyright (c) 12/28/2019 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.dao <br>
 * FileName: IntegralDao <br>
 * <br>
 *
 * @author duanmin
 * @created 12/28/2019-6:59 PM
 * @last Modified
 * @history
 */
@Mapper
@Component(value = "integralDao")
public interface IntegralDao {

    public Integral getIntegralByUserId(@Param("userId")Integer userId);

}

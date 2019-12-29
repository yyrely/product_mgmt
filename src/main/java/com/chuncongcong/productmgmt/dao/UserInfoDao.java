package com.chuncongcong.productmgmt.dao;

import com.chuncongcong.productmgmt.model.po.UserInfoPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HU
 * @date 2019/12/19 14:14
 */

@Mapper
public interface UserInfoDao extends MyMapper<UserInfoPo> {
}

package com.chuncongcong.productmgmt.dao;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author HU
 * @date 2019/12/19 14:15
 */

@RegisterMapper
public interface MyMapper<T> extends Mapper<T> {

}

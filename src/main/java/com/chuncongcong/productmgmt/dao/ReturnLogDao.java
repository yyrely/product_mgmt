package com.chuncongcong.productmgmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.dto.TotalNumsDto;
import com.chuncongcong.productmgmt.model.po.ReturnLogPo;
import com.chuncongcong.productmgmt.model.vo.ReturnLogQueryVo;

/**
 * @author HU
 * @date 2020/3/8 16:50
 */

@Mapper
public interface ReturnLogDao extends MyMapper<ReturnLogPo>{

	List<PurchaseLogDto> list(@Param("returnLogQueryVo") ReturnLogQueryVo returnLogQueryVo);

	TotalNumsDto countNumsAndPrice(@Param("returnLogQueryVo") ReturnLogQueryVo returnLogQueryVo);
}

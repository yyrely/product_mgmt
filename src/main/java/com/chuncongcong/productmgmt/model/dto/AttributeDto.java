package com.chuncongcong.productmgmt.model.dto;

import java.util.List;

import com.chuncongcong.productmgmt.model.po.ValuePo;
import lombok.Data;

/**
 * @author HU
 * @date 2019/12/21 14:58
 */

@Data
public class AttributeDto {

	private Long attributeId;

	private Long categoryId;

	private String attributeName;

	private List<ValuePo> valuePos;
}

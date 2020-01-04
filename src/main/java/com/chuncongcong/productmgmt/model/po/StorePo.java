package com.chuncongcong.productmgmt.model.po;

import javax.persistence.Table;

import lombok.Data;

/**
 * @author HU
 * @date 2020/1/4 17:37
 */

@Data
@Table(name = "store")
public class StorePo {

	private Long storeId;

	private String storeName;
}

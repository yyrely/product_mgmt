package com.chuncongcong.productmgmt.model.po;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * @author HU
 * @date 2019/12/27 10:55
 */

@Data
@Table(name = "sell_log")
public class SellLogPo extends BaseFiled {

	@Id
	@KeySql(dialect = IdentityDialect.MYSQL, useGeneratedKeys = true)
	private Long sellId;

	private Long productId;

	private Long skuId;

	private Integer sellNums;

	private BigDecimal sellPrice;

	private String sellUsername;

	private BigDecimal sellTotal;

	private LocalDateTime sellDate;
}

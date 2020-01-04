package com.chuncongcong.productmgmt.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author HU
 * @date 2020/1/4 16:01
 */

@Data
public class SellLogQueryVo {

	private LocalDate startDate;

	private LocalDate endDate;

	private LocalDateTime startDateTime;

	private LocalDateTime endDateTime;

	private Long storeId;
}

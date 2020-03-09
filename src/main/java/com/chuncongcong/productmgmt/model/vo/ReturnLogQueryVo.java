package com.chuncongcong.productmgmt.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author HU
 * @date 2020/3/8 19:35
 */

@Data
public class ReturnLogQueryVo {

	private LocalDate startDate;

	private LocalDate endDate;

	private LocalDateTime startDateTime;

	private LocalDateTime endDateTime;

	private Long storeId;
}

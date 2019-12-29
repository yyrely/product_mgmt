package com.chuncongcong.productmgmt.model.po;

import java.time.LocalDateTime;

import com.chuncongcong.productmgmt.model.annotation.Created;
import com.chuncongcong.productmgmt.model.annotation.Modified;
import lombok.Data;

/**
 * @author HU
 * @date 2019/12/19 19:52
 */

@Data
public abstract class BaseFiled {

	@Created
	private LocalDateTime created;
	
	@Modified
	private LocalDateTime modified;
}

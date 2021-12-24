package com.chuncongcong.productmgmt.model.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author HU
 * @date 2021/12/24 11:34
 */

@Data
public class PurchaseLogMergeVo {

    private Long purchaseId;

    private Long productId;

    private String productName;

    private String productNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseDate;

    private List<PurchaseLogSkuVo> purchaseLogSkuVoList = new ArrayList<>();
}

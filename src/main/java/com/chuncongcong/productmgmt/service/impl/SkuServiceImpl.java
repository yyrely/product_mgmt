package com.chuncongcong.productmgmt.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chuncongcong.productmgmt.dao.SkuDao;
import com.chuncongcong.productmgmt.exception.ServiceException;
import com.chuncongcong.productmgmt.model.dto.SkuDto;
import com.chuncongcong.productmgmt.model.dto.TotalNumsDto;
import com.chuncongcong.productmgmt.model.po.ProductPo;
import com.chuncongcong.productmgmt.model.po.ReturnLogPo;
import com.chuncongcong.productmgmt.model.po.SellLogPo;
import com.chuncongcong.productmgmt.model.po.SkuPo;
import com.chuncongcong.productmgmt.model.vo.SellSkuVo;
import com.chuncongcong.productmgmt.service.ProductService;
import com.chuncongcong.productmgmt.service.ReturnLogService;
import com.chuncongcong.productmgmt.service.SellLogService;
import com.chuncongcong.productmgmt.service.SkuService;

import tk.mybatis.mapper.weekend.Weekend;

/**
 * @author HU
 * @date 2019/12/22 15:29
 */

@Service
public class SkuServiceImpl implements SkuService {

	@Autowired
	private SkuDao skuDao;

	@Autowired
	private ProductService productService;

	@Autowired
	private SellLogService sellLogService;

	@Autowired
	private ReturnLogService returnLogService;

	@Override
	public void add(SkuPo skuPo) {
		skuDao.insert(skuPo);
	}

	@Override
	public SkuPo getById(Long skuId) {
		if(skuId == null) {
			throw new ServiceException("参数异常");
		}
		SkuPo skuPo = skuDao.selectByPrimaryKey(skuId);
		if(skuPo == null) {
			throw new ServiceException("参数异常");
		}
		return skuPo;
	}

	@Override
	public void update(SkuPo skuPo) {
		skuDao.updateByPrimaryKey(skuPo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateByOrigStockNums(SkuPo skuPo, Integer origStockNums) {
		Weekend<SkuPo> weekend = Weekend.of(SkuPo.class);
		weekend.weekendCriteria().andEqualTo(SkuPo::getSkuStock, origStockNums).andEqualTo(SkuPo::getSkuId, skuPo.getSkuId());
		int nums = skuDao.updateByExample(skuPo, weekend);
		if(nums != 1) {
			throw new ServiceException("商品数量异常，请刷新重试");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void sellSku(SellSkuVo sellSkuVo, String username) {
		ProductPo productPo = productService.getSimpleInfo(sellSkuVo.getProductId());
		SkuPo skuPo = getById(sellSkuVo.getSkuId());
		SellLogPo sellLogPo = new SellLogPo();
		sellLogPo.setStoreId(productPo.getStoreId());
		sellLogPo.setProductId(productPo.getProductId());
		sellLogPo.setSkuId(skuPo.getSkuId());
		sellLogPo.setSellNums(sellSkuVo.getSellNums());
		sellLogPo.setSellPrice(sellSkuVo.getSellPrice());
		sellLogPo.setSellTotal(sellSkuVo.getSellPrice().multiply(new BigDecimal(sellSkuVo.getSellNums())));
		sellLogPo.setSellUsername(username);
		sellLogPo.setSellDate(LocalDateTime.now());
		sellLogService.save(sellLogPo);

		Integer origStockNums = skuPo.getSkuStock();
		if (sellSkuVo.getSellNums().compareTo(origStockNums) > 0) {
			throw new ServiceException("库存不足，请检查");
		}
		skuPo.setSkuStock(origStockNums - sellSkuVo.getSellNums());
		SkuService skuServiceAop = (SkuService) AopContext.currentProxy();
		skuServiceAop.updateByOrigStockNums(skuPo, origStockNums);
	}

	@Override
	public List<SkuDto> getListByProductId(Long productId) {
		return skuDao.getListByProductId(productId);
	}

	@Override
	public TotalNumsDto nums(Long storeId) {
		return skuDao.countNumsAndPrice(storeId);
	}

	@Override
	public void returnSku(SellSkuVo sellSkuVo, String username) {
		ProductPo productPo = productService.getSimpleInfo(sellSkuVo.getProductId());
		SkuPo skuPo = getById(sellSkuVo.getSkuId());
		ReturnLogPo returnLogPo = new ReturnLogPo();
		returnLogPo.setStoreId(productPo.getStoreId());
		returnLogPo.setProductId(productPo.getProductId());
		returnLogPo.setSkuId(skuPo.getSkuId());
		returnLogPo.setReturnNums(sellSkuVo.getSellNums());
		returnLogPo.setTotalPrice(skuPo.getSkuInPrice().multiply(new BigDecimal(sellSkuVo.getSellNums())));
		returnLogPo.setReturnUsername(username);
		returnLogPo.setReturnDate(LocalDateTime.now());
		returnLogService.save(returnLogPo);

		Integer origStockNums = skuPo.getSkuStock();
		if (sellSkuVo.getSellNums().compareTo(origStockNums) > 0) {
			throw new ServiceException("库存不足，请检查");
		}
		skuPo.setSkuStock(origStockNums - sellSkuVo.getSellNums());
		SkuService skuServiceAop = (SkuService) AopContext.currentProxy();
		skuServiceAop.updateByOrigStockNums(skuPo, origStockNums);
	}
}

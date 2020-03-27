package com.chuncongcong.productmgmt.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.context.RequestContext;
import com.chuncongcong.productmgmt.dao.ProductAttributeDao;
import com.chuncongcong.productmgmt.dao.ProductDao;
import com.chuncongcong.productmgmt.dao.ProductValueDao;
import com.chuncongcong.productmgmt.dao.SkuValueDao;
import com.chuncongcong.productmgmt.exception.ServiceException;
import com.chuncongcong.productmgmt.model.dto.SkuDto;
import com.chuncongcong.productmgmt.model.po.CategoryPo;
import com.chuncongcong.productmgmt.model.po.ProductAttributePo;
import com.chuncongcong.productmgmt.model.po.ProductPo;
import com.chuncongcong.productmgmt.model.po.ProductValuePo;
import com.chuncongcong.productmgmt.model.po.PurchaseLogPo;
import com.chuncongcong.productmgmt.model.po.SkuPo;
import com.chuncongcong.productmgmt.model.po.SkuValuePo;
import com.chuncongcong.productmgmt.model.vo.ProductQueryVo;
import com.chuncongcong.productmgmt.model.vo.ProductVo;
import com.chuncongcong.productmgmt.model.vo.SkuVo;
import com.chuncongcong.productmgmt.model.vo.ValueVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.service.CategoryService;
import com.chuncongcong.productmgmt.service.ProductService;
import com.chuncongcong.productmgmt.service.PurchaseLogService;
import com.chuncongcong.productmgmt.service.SkuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

/**
 * @author HU
 * @date 2019/12/22 14:42
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductAttributeDao productAttributeDao;

    @Autowired
    private ProductValueDao productValueDao;

    @Autowired
    private SkuValueDao skuValueDao;

    @Autowired
    private SkuService skuService;

    @Autowired
    private PurchaseLogService purchaseLogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapperOperation modelMapperOperation;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductVo add(ProductVo productVo) {

    	// 判断是否存在货品
		ProductPo query = new ProductPo();
		query.setProductNo(productVo.getProductNo());
		ProductPo existProductPo = productDao.selectOne(query);
		if(existProductPo != null) {
			throw new ServiceException("该货号的商品已存在");
		}
		// 保存货品

        productVo.setStoreId(RequestContext.getUserInfo().getStoreId());
		ProductPo productPo = modelMapperOperation.map(productVo, ProductPo.class);
        productDao.insert(productPo);
        productVo.setProductId(productPo.getProductId());

        List<SkuVo> skuList = productVo.getSkuList();
        Set<Long> attributeSet = new HashSet<>();
        Set<Long> valueSet = new HashSet<>();
        for (SkuVo skuVo : skuList) {
            // 保存sku
            skuVo.setProductId(productPo.getProductId());
            skuVo.setStoreId(RequestContext.getUserInfo().getStoreId());
            SkuPo skuPo = modelMapperOperation.map(skuVo, SkuPo.class);
            skuService.add(skuPo);
            skuVo.setSkuId(skuPo.getSkuId());
            // 保存进货日志
            addPurchaseLog(skuPo, skuPo.getSkuStock());

            // 保存规格属性关系
            List<ValueVo> valueList = skuVo.getValueList();
            for (ValueVo valueVo : valueList) {
                if (!attributeSet.contains(valueVo.getAttributeId())) {
                    attributeSet.add(valueVo.getAttributeId());
                    ProductAttributePo productAttributePo = new ProductAttributePo();
                    productAttributePo.setProductId(productPo.getProductId());
                    productAttributePo.setAttributeId(valueVo.getAttributeId());
                    productAttributeDao.insert(productAttributePo);
                }
                if (!valueSet.contains(valueVo.getValueId())) {
                    valueSet.add(valueVo.getValueId());
                    ProductValuePo productValuePo = new ProductValuePo();
                    productValuePo.setProductId(productPo.getProductId());
                    productValuePo.setValueId(valueVo.getValueId());
                    productValueDao.insert(productValuePo);
                }
				SkuValuePo skuValuePo = new SkuValuePo();
				skuValuePo.setSkuId(skuPo.getSkuId());
				skuValuePo.setValueId(valueVo.getValueId());
				skuValueDao.insert(skuValuePo);
            }
        }
        return productVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductPo updateProduct(ProductVo productVo) {
        ProductPo productPo = checkProductId(productVo.getProductId());
        productPo.setProductName(productVo.getProductName());
        productPo.setProductPic(productVo.getProductPic());
        productPo.setProductDesc(productVo.getProductDesc());
        productDao.updateByPrimaryKey(productPo);
        return productPo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductVo updateSku(ProductVo productVo) {
        ProductPo productPo = checkProductId(productVo.getProductId());
        modelMapperOperation.map(productPo, productVo);
        productVo.getSkuList().forEach(skuVo -> {
            if (skuVo.getSkuId() == null) {
                // 进货（新增sku）
                skuVo.setStoreId(RequestContext.getUserInfo().getStoreId());
                skuVo.setProductId(productPo.getProductId());
                SkuPo skuPo = modelMapperOperation.map(skuVo, SkuPo.class);
                skuService.add(skuPo);
				skuVo.setSkuId(skuPo.getSkuId());
                Set<Long> attributes = productDao.getAttributes(productPo.getProductId());
                Set<Long> values = productDao.getValues(productPo.getProductId());
				skuVo.getValueList().forEach(valueVo -> {
                    if (!attributes.contains(valueVo.getAttributeId())) {
                        ProductAttributePo productAttributePo = new ProductAttributePo();
                        productAttributePo.setProductId(productPo.getProductId());
                        productAttributePo.setAttributeId(valueVo.getAttributeId());
                        productAttributeDao.insert(productAttributePo);
                    }
                    if (!values.contains(valueVo.getValueId())) {
                        ProductValuePo productValuePo = new ProductValuePo();
                        productValuePo.setProductId(productPo.getProductId());
                        productValuePo.setValueId(valueVo.getValueId());
                        productValueDao.insert(productValuePo);
                    }
                    SkuValuePo skuValuePo = new SkuValuePo();
                    skuValuePo.setSkuId(skuPo.getSkuId());
                    skuValuePo.setValueId(valueVo.getValueId());
                    skuValueDao.insert(skuValuePo);
                });

                // 保存进货日志
                if(skuPo.getSkuStock() > 0) {
                    addPurchaseLog(skuPo, skuPo.getSkuStock());
                }
            } else {
                // 修改sku
                SkuPo skuPo = skuService.getById(skuVo.getSkuId());
                if (skuVo.getSkuStock().compareTo(skuPo.getSkuStock()) > 0) {
                    // 进货（增加了已存在的sku）
                    int inStock = skuVo.getSkuStock() - skuPo.getSkuStock();
                    addPurchaseLog(skuPo, inStock);

                    skuPo.setSkuDesc(skuVo.getSkuDesc());
                    skuPo.setSkuOutPrice(skuVo.getSkuOutPrice());
                    Integer origStockNums = skuPo.getSkuStock();
                    skuPo.setSkuStock(skuVo.getSkuStock());
                    skuService.updateByOrigStockNums(skuPo, origStockNums);
                } else {
                    // 修改数据
                    skuPo.setSkuDesc(skuVo.getSkuDesc());
                    skuPo.setSkuOutPrice(skuVo.getSkuOutPrice());
                    skuService.update(skuPo);
                }
            }
        });
        return productVo;
    }

    @Override
    public ProductVo getInfo(Long productId) {
        ProductPo productPo = checkProductId(productId);
        CategoryPo categoryPo = categoryService.getById(productPo.getCategoryId());
        List<SkuDto> skuDtos = skuService.getListByProductId(productId);
        ProductVo productVo = modelMapperOperation.map(productPo, ProductVo.class);
        productVo.setCategoryName(categoryPo.getCategoryName());
        List<SkuVo> skuVos = modelMapperOperation.mapToList(skuDtos, SkuVo.class);
        productVo.setSkuList(skuVos);
        return productVo;
    }

	@Override
	public ProductPo getSimpleInfo(Long productId) {
		return checkProductId(productId);
	}

	@Override
    public Page<ProductPo> listProduct(Paging paging, ProductQueryVo productQueryVo) {
        Weekend<ProductPo> weekend = Weekend.of(ProductPo.class);
        WeekendCriteria<ProductPo, Object> weekendCriteria = weekend.weekendCriteria();
        weekendCriteria.andEqualTo(ProductPo::getStoreId, RequestContext.getUserInfo().getStoreId());
        if(StringUtils.isNotEmpty(productQueryVo.getProductNo())) {
			weekendCriteria.andLike(ProductPo::getProductNo, "%" + productQueryVo.getProductNo() + "%");
		}
		weekend.orderBy("created").desc();
        return PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
            .doSelectPage(() -> productDao.selectByExample(weekend));

	}

	private void addPurchaseLog(SkuPo skuPo, Integer inStock) {
        PurchaseLogPo purchaseLogPo = new PurchaseLogPo();
        purchaseLogPo.setStoreId(skuPo.getStoreId());
        purchaseLogPo.setProductId(skuPo.getProductId());
        purchaseLogPo.setSkuId(skuPo.getSkuId());
        purchaseLogPo.setPurchaseNums(inStock);
        purchaseLogPo.setTotalPrice(skuPo.getSkuInPrice().multiply(new BigDecimal(inStock)));
        purchaseLogPo.setPurchaseDate(LocalDateTime.now());
        purchaseLogPo.setPurchaseUsername(RequestContext.getUserInfo().getUsername());
        purchaseLogService.save(purchaseLogPo);
    }

    private ProductPo checkProductId(Long productId) {
        ProductPo productPo = productDao.selectByPrimaryKey(productId);
        if (productPo == null) {
            throw new ServiceException("参数异常");
        }
        return productPo;
    }
}

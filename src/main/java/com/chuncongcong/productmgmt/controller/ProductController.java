package com.chuncongcong.productmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.po.ProductPo;
import com.chuncongcong.productmgmt.model.vo.ProductQueryVo;
import com.chuncongcong.productmgmt.model.vo.ProductVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.page.SimplePagingObject;
import com.chuncongcong.productmgmt.service.ProductService;
import com.github.pagehelper.Page;

/**
 * @author HU
 * @date 2019/12/22 14:40
 */

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapperOperation modelMapperOperation;

	@PostMapping("/add")
	public Object add(@RequestBody @Validated ProductVo productVo) {
		return productService.add(productVo);
	}

	@PostMapping("/update")
	public Object updateProduct(@RequestBody @Validated ProductVo productVo) {
		ProductPo productPo = productService.updateProduct(productVo);
		return modelMapperOperation.map(productPo, ProductVo.class);
	}

	@PostMapping("/update/sku")
	public Object updateSku(@RequestBody ProductVo productVo) {
		return productService.updateSku(productVo);
	}

	@GetMapping("/list")
	public Object listProduct(Paging paging, ProductQueryVo productQueryVo) {
		Page<ProductPo> page = productService.listProduct(paging, productQueryVo);
		List<ProductVo> productVos = modelMapperOperation.mapToList(page.getResult(), ProductVo.class);
		return new SimplePagingObject<>(productVos, paging.getPageNum(), paging.getPageSize(), page.getTotal());
	}

	@GetMapping("/get/info/{productId}")
	public Object listProduct(@PathVariable("productId") Long productId) {
		return productService.getInfo(productId);
	}

}

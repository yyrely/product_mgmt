package com.chuncongcong.productmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.authorization.AuthUser;
import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.po.ProductPo;
import com.chuncongcong.productmgmt.model.vo.ProductQueryVo;
import com.chuncongcong.productmgmt.model.vo.ProductVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.page.SimplePagingObject;
import com.chuncongcong.productmgmt.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HU
 * @date 2019/12/22 14:40
 */

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapperOperation modelMapperOperation;

	@PostMapping("/add")
	public Object add(@RequestBody @Validated ProductVo productVo, Authentication authentication) throws Exception {
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		productVo.setStoreId(authUser.getStoreId());
		return productService.add(productVo, authUser.getName());
	}

	@PostMapping("/update")
	public Object updateProduct(@RequestBody ProductVo productVo) {
		ProductPo productPo = productService.updateProduct(productVo);
		return modelMapperOperation.map(productPo, ProductVo.class);
	}

	@PostMapping("/update/sku")
	public Object updateSku(@RequestBody ProductVo productVo, Authentication authentication) {
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		productVo.setStoreId(authUser.getStoreId());
		return productService.updateSku(productVo, authUser.getName());
	}

	@GetMapping("/list")
	public Object listProduct(Paging paging, ProductQueryVo productQueryVo, Authentication authentication) {
		AuthUser authUser =(AuthUser) authentication.getPrincipal();
		productQueryVo.setStoreId(authUser.getStoreId());
		Page<ProductVo> page = productService.listProduct(paging, productQueryVo);
		return new SimplePagingObject<>(page.getResult(), paging.getPageNum(), paging.getPageSize(), page.getTotal());
	}

	@GetMapping("/get/info/{productId}")
	public Object listProduct(@PathVariable("productId") Long productId) {
		return productService.getInfo(productId);
	}

}

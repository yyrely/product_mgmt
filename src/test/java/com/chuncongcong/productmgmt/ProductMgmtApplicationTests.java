package com.chuncongcong.productmgmt;

import com.chuncongcong.productmgmt.model.po.ProductPo;
import com.chuncongcong.productmgmt.model.vo.ProductQueryVo;
import com.chuncongcong.productmgmt.model.vo.ProductVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.service.ProductService;
import com.github.pagehelper.Page;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

@SpringBootTest
class ProductMgmtApplicationTests {

	@Autowired
	private ProductService productService;

	@Test
	public void testProductList() {
		Paging paging = new Paging();
		ProductQueryVo query = new ProductQueryVo();
		query.setStoreId(1L);
		Page<ProductPo> productPos = productService.listProduct(paging, query);
		System.out.println(productPos);
	}

}

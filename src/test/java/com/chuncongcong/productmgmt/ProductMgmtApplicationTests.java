package com.chuncongcong.productmgmt;

import com.chuncongcong.productmgmt.model.po.ProductPo;
import com.chuncongcong.productmgmt.model.vo.ProductQueryVo;
import com.chuncongcong.productmgmt.model.vo.ProductVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.service.ProductService;
import com.github.pagehelper.Page;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

@SpringBootTest
class ProductMgmtApplicationTests {

	@Autowired
	private ProductService productService;

	@Autowired
	private StringEncryptor stringEncryptor;

	@Test
	public void testProductList() {
		System.out.println(stringEncryptor.decrypt("AkL48Oi3cR5nLEjZJ3Y32Q=="));
	}

}

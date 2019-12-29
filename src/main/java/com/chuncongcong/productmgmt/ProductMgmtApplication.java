package com.chuncongcong.productmgmt;

import tk.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.chuncongcong.productmgmt.dao.*Dao")
@EnableAspectJAutoProxy(exposeProxy = true)
public class ProductMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMgmtApplication.class, args);
	}

}

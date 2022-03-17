package com.chuncongcong.productmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.chuncongcong.productmgmt.dao.*Dao")
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableAsync
public class ProductMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMgmtApplication.class, args);
	}

}

package com.chuncongcong.productmgmt.config.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * @author HU
 * @date 2020/1/4 14:45
 */

@Configuration
public class DateConfig {

	@Bean
	public Converter<String, LocalDate> localDateConverter() {
		return new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(String source) {
				return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
			}
		};
	}

}

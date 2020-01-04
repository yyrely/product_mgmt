package com.chuncongcong.productmgmt.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HU
 * @date 2019/12/20 20:57
 */

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		Jsr310ModuleConfig config = Jsr310ModuleConfig.builder()
				.dateTimePattern("yyyy-MM-dd HH:mm:ss")
				.datePattern("yyyy-MM-dd")
				.build();
		modelMapper.registerModule(new Jsr310Module(config));
		modelMapper.getConfiguration().setDeepCopyEnabled(true).setSkipNullEnabled(false)
				.setMatchingStrategy(MatchingStrategies.STRICT).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		return modelMapper;
	}

	@Bean
	public ModelMapperOperation modelMapperOperation() {
		return new ModelMapperOperation();
	}
}

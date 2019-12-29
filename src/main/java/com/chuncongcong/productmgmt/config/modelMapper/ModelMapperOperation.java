package com.chuncongcong.productmgmt.config.modelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author HU
 * @date 2019/12/20 21:01
 */

public class ModelMapperOperation {

	@Autowired
	private ModelMapper modelMapper;

	public <T> T map(Object source, Class<T> destinationType) {
		if(source == null) {
			return null;
		}
		return modelMapper.map(source, destinationType);
	}

	public <T> List<T> mapToList(Collection<?> source, Class<T> destinationType) {
		if (source == null || source.isEmpty()) {
			return Collections.emptyList();
		}
		List<T> arrayList = new ArrayList<>(source.size());
		for (Object o : source) {
			arrayList.add(map(o, destinationType));
		}
		return arrayList;
	}
}

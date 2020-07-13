package br.com.fileprocessor.service;

import java.util.Map;
import java.util.Set;

import br.com.fileprocessor.model.Model;

/**
 * Class that encapsulates all Model data.
 */
public class DataModel {

	private Map<String, Set<Model>> map;

	public DataModel(Map<String, Set<Model>> map) {
		this.map = map;
	}

	public Set<Model> getModelsOfType(String type) {
		return this.map.get(type);
	}

}
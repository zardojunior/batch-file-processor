package br.com.fileprocessor.model;

import java.util.Map;
import java.util.Set;

/**
 * Class that encapsulates all sales data.
 */
public class SalesData {

    private Map<String, Set<Model>> map;

    public SalesData(Map<String, Set<Model>> map) {
        this.map = map;
    }

    public Set<Model> getModelsOfType(String type) {
        return this.map.get(type);
    }

}

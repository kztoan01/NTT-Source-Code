package com.ntt.nttsearchfoodservice.service;


import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.ntt.nttsearchfoodservice.dto.Food;

import java.io.IOException;
import java.util.List;

public interface ElasticService {
    List<Food> autoSuggest(String partialName) throws IOException;
    List<Food> search(String partialName) throws IOException;

}

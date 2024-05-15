package com.ntt.nttsearchfoodservice.service.iml;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ntt.nttsearchfoodservice.dto.Food;
import com.ntt.nttsearchfoodservice.service.ElasticService;
import com.ntt.nttsearchfoodservice.util.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ElasticServiceIml implements ElasticService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;
    @Override
    public List<Food> autoSuggest(String partialName) throws IOException{
        Supplier<Query> supplier = ESUtil.createSupplierAutoSuggest(partialName);
        SearchResponse<Food> response = elasticsearchClient.search(s->
                s.index("food")
                        .query(supplier.get())
                        .size(30)
                        .from(0),Food.class);
//        SearchResponse<Food> response = elasticService.autoSuggest(searchValue);
        List<Hit<Food>> hitlist = response.hits().hits();
        List<Food> foodlist = new ArrayList<>();
        for (Hit<Food> hit : hitlist) {
            foodlist.add(hit.source());
        }
        foodlist.sort(Comparator.comparing(Food::getName));
        return foodlist;
    }

    @Override
    public List<Food> search(String partialName) throws IOException {
        Supplier<Query> supplier = ESUtil.createSupplierAutoSuggest(partialName);
        SearchResponse<Food> response = elasticsearchClient.search(s->
                s.index("food")
                        .query(supplier.get())
                        .size(5000)
                        .from(0)
                        ,Food.class);
//        SearchResponse<Food> response = elasticService.autoSuggest(searchValue);
        List<Hit<Food>> hitlist = response.hits().hits();
        List<Food> foodlist = new ArrayList<>();
        for (Hit<Food> hit : hitlist) {
            foodlist.add(hit.source());
        }
        foodlist.sort((Comparator.comparing(Food::getName)));
        return foodlist;
    }
}

package com.ntt.nttsearchfoodservice.configuration;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//import javax.annotation.Nonnull;
import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.ntt.nttsearchfoodservice.repository")
public class ElasticConfig extends ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.username}")
    private String username;
    @Value("${spring.elasticsearch.password}")
    private String password;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedToLocalhost()
                .usingSsl(buildSSLContext())
                .withBasicAuth(username,password)
                .build();
    }

    private static SSLContext buildSSLContext(){
        try{
            return new SSLContextBuilder().loadTrustMaterial(null, TrustSelfSignedStrategy.INSTANCE).build();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}

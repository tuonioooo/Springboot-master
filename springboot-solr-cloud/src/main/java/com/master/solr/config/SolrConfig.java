package com.master.solr.config;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * 描述: 开启 CloudSolrClient 连接
 * @author tuonioooo
 * @create 2017-10-18 18:15
 **/
@Configuration
@EnableSolrRepositories(basePackages = {"com.master.solr"})
public class SolrConfig {

    @Value("${spring.data.solr.host}")
    private String solrUrl;

    @Bean
    public CloudSolrClient solrClient() {
        return new CloudSolrClient.Builder()
            .withSolrUrl(solrUrl)
            .build();
    }

}

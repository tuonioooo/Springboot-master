package com.master.solr.po;


import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

/**
 * 描述: 映射的实体类必须有@ID主键
 *
 * @author yanpenglei
 * @create 2017-10-17 18:28
 **/
@SolrDocument(solrCoreName = "test_collection")
@Setter
@Getter
public class Ymq implements Serializable {

    @Id
    @Field
    private String id;

    @Field
    private String ymqTitle;

    @Field
    private String ymqUrl;

    @Field
    private String ymqContent;
}

package com.master.solr;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SolrTest {
public static void main(String[] args){
   /**
    * http://192.168.1.170:8983/solr/fbf   fbf是solr的一个core名称
    */
   //旧版写法
   //HttpSolrClient solrClient = new HttpSolrClient("http://192.168.127.87:8983/solr/test_collection");

   //新版写法
    HttpSolrClient solrClient =  new HttpSolrClient.Builder("http://192.168.127.87:8983/solr/test_collection")
            .build();


   SolrQuery query = new SolrQuery();

   String pageNo = "1";  //第几页
   String pageSize = "10"; //每页多少数据
   if(NumberUtils.isNumber(pageNo) && NumberUtils.isNumber(pageSize)){
       int startPage = Integer.valueOf(pageNo);
       int pageNum = Integer.valueOf(pageSize);
       query.setStart((startPage-1)*pageNum);//起始页，这里一定要注意，不能直接把pageNo赋值给start,start表示从第一个数据开始，第一条从0开始。
       query.setRows(pageNum);//每页显示数量
   }
   StringBuffer buffer = new StringBuffer();
   query.set("q","*:*"); //没有传入参数则全部查询
   QueryResponse rsp = null;
   try {
       rsp = solrClient.query( query );
   } catch (Exception e) {
       e.printStackTrace();
   }
   SolrDocumentList results = rsp.getResults();
   System.out.println(results.getNumFound());//查询总条数，该总条数是符合该条件下的总条数，并不是pageSize的数量。
 }
}
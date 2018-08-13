package com.master;

import com.master.utils.LuceneUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-8
 * Time: 17:53
 * info:
 */
public class LuceneMain {

    /**
     * 创建索引
     */
    public static void createIndex() throws Exception{
        //1 创建文件对象
        Document doc=new Document();
        //2.添加内容
        doc.add(new LongPoint("id",536563l));//Long类型
        doc.add(new TextField("title", "阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待", Field.Store.YES));//文本类型
        doc.add(new TextField("sellPoint", "清仓！仅北京，武汉仓有货！", Field.Store.YES));//文本类型
        doc.add(new StringField("image", "http://image.jt.com/", Field.Store.YES));//String类型
        //创建索引文件位置
        Directory dir = FSDirectory.open(Paths.get("indexDir/"));

        //引入分词器对象,对当前doc内的所有数据进行分词处理
        //Analyzer analyzer=new StandardAnalyzer();
        //Analyzer analyzer=new ChineseAnalyzer();

        //Version version = Version.LUCENE_7_1_0; //在 6.6 以上版本中 version 不再是必要的，并且，存在无参构造方法，可以直接使用默认的 StandardAnalyzer 分词器。

        Analyzer analyzer=new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);//创建输出流writer
        IndexWriter writer = new IndexWriter(dir,config);
        writer.addDocument(doc);//将索引对象写入索引文件
        writer.commit();
        //释放资源
        writer.close();
        dir.close();
    }

    public static void createIndexByUtils() throws Exception {
        System.out.println("*******************createIndexByUtils*************************");
        User user = new User();
        user.setId(1L);
        user.setName("ALLEN");
        user.setAge(25);
        user.setCreateAt(new Date());

        User user2 = new User();
        user2.setId(2L);
        user2.setName("JAMES");
        user2.setAge(30);
        user2.setCreateAt(new Date());

        Document document = LuceneUtils.objToDocument(user);
        Document document2 = LuceneUtils.objToDocument(user2);
        Directory directory = LuceneUtils.openFSDirectory("indexDir/");
        IndexWriterConfig writerConfig = LuceneUtils.getIndexWriterConfig();
        IndexWriter writer = LuceneUtils.getIndexWriter(directory,writerConfig);
        LuceneUtils.addIndex(writer, document);
        LuceneUtils.addIndex(writer, document2);
        LuceneUtils.closeIndexWriter(writer);
        directory.close();
    }

    public static void readerIndex() throws ParseException {
        System.out.println("*******************readerIndex*************************");
        Directory directory = LuceneUtils.openFSDirectory("indexDir/");
        IndexReader reader = LuceneUtils.getIndexReader(directory);
        IndexSearcher indexSearcher =LuceneUtils.getIndexSearcher(reader);

        QueryParser queryParser = LuceneUtils.createQueryParser("name", LuceneUtils.analyzer);//查询字段
        Query query = queryParser.parse("ALLEN");//输入查询条件（查询分词）

        List<Document> documents = LuceneUtils.query(indexSearcher, query);
        System.out.println("documents.size() = " + documents.size());
        documents.stream().forEach(d->{
            try {
                User user = LuceneUtils.documentToObj(d, User.class);
                System.out.println("user.toString() = " + user.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public static void remove() throws ParseException, IOException {
        System.out.println("*******************remove*************************");
        Directory directory = LuceneUtils.openFSDirectory("indexDir/");
        IndexReader reader = LuceneUtils.getIndexReader(directory);
        IndexSearcher indexSearcher =LuceneUtils.getIndexSearcher(reader);

        QueryParser queryParser = LuceneUtils.createQueryParser("name", LuceneUtils.analyzer);//查询字段
        Query query = queryParser.parse("ALLEN");//输入查询条件（查询分词）

        IndexWriterConfig writerConfig = LuceneUtils.getIndexWriterConfig();
        IndexWriter writer = LuceneUtils.getIndexWriter(directory,writerConfig);


        LuceneUtils.deleteIndex(writer, query);

        //或者
        //Term term = new Term("name", "JAMES") //field , text
        //LuceneUtils.deleteIndex(writer, term);

        LuceneUtils.closeIndexWriter(writer);
        directory.close();
    }

    public static void remove_term() throws ParseException, IOException {
        System.out.println("*******************remove_term*************************");
        Directory directory = LuceneUtils.openFSDirectory("indexDir/");

        IndexWriterConfig writerConfig = LuceneUtils.getIndexWriterConfig();
        IndexWriter writer = LuceneUtils.getIndexWriter(directory,writerConfig);

        Term term = new Term("name", "JAMES"); //field , text
        LuceneUtils.deleteIndex(writer, term);

        LuceneUtils.closeIndexWriter(writer);
        directory.close();
    }

    public static void main(String[] args) throws Exception {
        //createIndex();
        //createIndexByUtils();
        //remove();
        remove_term();
        readerIndex();
    }
}

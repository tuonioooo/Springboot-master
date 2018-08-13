package com.master.utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-13
 * Time: 11:13
 * info:
 */
public class LuceneUtils {

    private static LuceneManager luceneManager = LuceneManager.getInstance();

    public static Analyzer analyzer = new IKAnalyzer();

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 14:21
     *  @Params [dir]
     *  @Return org.apache.lucene.store.Directory
     *  @Info   打开索引目录
     */
    public static Directory openFSDirectory(String dir) {
        try {
            return FSDirectory.open(Paths.get(dir));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 14:24
     *  @Params [directory]
     *  @Return void
     *  @Info  关闭索引目录并销毁
     */
    public static void closeDirectory(Directory directory) throws IOException {
        if (directory != null) {
            directory.close();
            directory = null;
        }
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 14:26
     *  @Params [dir, conf]
     *  @Return org.apache.lucene.index.IndexWriter
     *  @Info  获取IndexWriter
     */
    public static IndexWriter getIndexWriter(Directory dir, IndexWriterConfig conf) {
        return luceneManager.getIndexWriter(dir, conf);
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 14:34
     *  @Params [dir, conf]
     *  @Return org.apache.lucene.index.IndexWriter
     *  @Info 获取IndexWriter
     */
    public static IndexWriter getIndexWriter(String dir, IndexWriterConfig conf) {
        Directory directory = openFSDirectory(dir);
        return luceneManager.getIndexWriter(directory, conf);
    }

    /**
     * 关闭IndexWriter
     * @param writer
     */
    public static void closeIndexWriter(IndexWriter writer) {
        if (null != writer) {
            try {
                writer.close();
                writer = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 14:36
     *  @Params []
     *  @Return org.apache.lucene.index.IndexWriterConfig
     *  @Info 获取IndexWriterConfig
     */
    public static IndexWriterConfig getIndexWriterConfig() {
        return new IndexWriterConfig(analyzer);
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 14:37
     *  @Params [dir, enableNRTReader{是否开启NRTReader}]
     *  @Return org.apache.lucene.index.IndexReader
     *  @Info 获取IndexReader
     */
    public static IndexReader getIndexReader(Directory dir, boolean enableNRTReader) {
        return luceneManager.getIndexReader(dir, enableNRTReader);
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 14:38
     *  @Params [dir]
     *  @Return org.apache.lucene.index.IndexReader
     *  @Info 获取IndexReader(默认不启用NRTReader)
     */
    public static IndexReader getIndexReader(Directory dir) {
        return luceneManager.getIndexReader(dir);
    }

    /**
     * 关闭IndexReader
     * @param reader
     */
    public static void closeIndexReader(IndexReader reader) {
        if (null != reader) {
            try {
                reader.close();
                reader = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭IndexReader和IndexWriter
     * @param reader
     * @param writer
     */
    public static void closeAll(IndexReader reader, IndexWriter writer) {
        closeIndexReader(reader);
        closeIndexWriter(writer);
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 15:47
     *  @Params [reader{IndexReader对象}, executor{如果你需要开启多线程查询，请提供ExecutorService对象参数}]
     *  @Return org.apache.lucene.search.IndexSearcher
     *  @Info 获取IndexSearcher(支持多线程查询)
     */
    public static IndexSearcher getIndexSearcher(IndexReader reader, ExecutorService executor) {
        return luceneManager.getIndexSearcher(reader, executor);
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 15:47
     *  @Params [reader]
     *  @Return org.apache.lucene.search.IndexSearcher
     *  @Info 获取IndexSearcher(不支持多线程查询)
     */
    public static IndexSearcher getIndexSearcher(IndexReader reader) {
        return luceneManager.getIndexSearcher(reader);
    }

    /**
     *  @Author daizhao
     *  @Date 2018-8-13 15:49
     *  @Params [field, analyzer]
     *  @Return org.apache.lucene.queryparser.classic.QueryParser
     *  @Info 创建QueryParser对象
     */
    public static QueryParser createQueryParser(String field, Analyzer analyzer) {
        return new QueryParser(field, analyzer);
    }


    /**
     * 更新索引文档
     * @param writer
     * @param term
     * @param document
     */
    public static void updateIndex(IndexWriter writer, Term term, Document document) {
        try {
            writer.updateDocument(term, document);
            writer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteIndex(IndexWriter writer, Term term){
        try {
            writer.deleteDocuments(term);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteIndex(IndexWriter writer, Query ...queries){
        try {
            writer.deleteDocuments(queries);
            writer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 添加索引文档
     * @param writer
     * @param document
     */
    public static void addIndex(IndexWriter writer, Document document) {
        updateIndex(writer, null, document);
    }

    /**
     * 索引文档查询
     * @param searcher
     * @param query
     * @return
     */
    public static List<Document> query(IndexSearcher searcher, Query query) {
        TopDocs topDocs = null;
        try {
            topDocs = searcher.search(query, Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScoreDoc[] scores = topDocs.scoreDocs;
        int length = scores.length;
        if (length <= 0) {
            return Collections.emptyList();
        }
        List<Document> docList = new ArrayList<Document>();
        try {
            for (int i = 0; i < length; i++) {
                Document doc = searcher.doc(scores[i].doc);
                docList.add(doc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docList;
    }



    /**
     * 将JavaBean转成Document对象
     * @param obj
     * @return
     * @throws Exception
     */
    public static Document objToDocument(Object obj) throws Exception{
        Document document = new Document();//创建Document对象
        Class clazz = obj.getClass(); //获取obj引用的对象字节码
        java.lang.reflect.Field[] reflectFields = clazz.getDeclaredFields();//通过对象字节码获取私有的属性
        for(java.lang.reflect.Field reflectField : reflectFields){//反射字段
            reflectField.setAccessible(true);//设置权限，可以读写私有变量
            String name = reflectField.getName();//获取字段名，例如id/title/content
            String value = reflectField.get(obj).toString();
            document.add(new Field(name, value, TextField.TYPE_STORED));//加入到Document对象中去，这时javabean的属性与document对象的属性相同
        }
        return document;
    }

    /**
     * 将Document对象转换成JavaBean对象
     * @param document
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T documentToObj(Document document, Class<T> clazz) throws Exception{
        Object obj = clazz.newInstance();
        java.lang.reflect.Field[] reflectFields = clazz.getDeclaredFields();
        for(java.lang.reflect.Field reflectField : reflectFields){
            reflectField.setAccessible(true);
            String name = reflectField.getName();//id/title/content
            String value = document.get(name);
            setFieldProperty(obj, name, value);
        }
        return (T) obj;
    }

    private static void setFieldProperty(Object obj, String name, String value){
        try {
            java.lang.reflect.Field nameField = obj.getClass().getDeclaredField(name);// 获取私有成员变量
            nameField.setAccessible(true);// 设置操作权限为true
            String fieldTypeName = nameField.getType().getName();
            switch (fieldTypeName){
                case "java.lang.Integer" :
                    nameField.set(obj, Integer.parseInt(value));
                    break;
                case "java.lang.Double" :
                    nameField.set(obj, Double.parseDouble(value));
                    break;
                case "java.lang.Float":
                    nameField.set(obj, Float.parseFloat(value));
                    break;
                case "java.lang.Long" :
                    nameField.set(obj, Long.valueOf(value));
                    break;
                case "java.lang.Short" :
                    nameField.set(obj, Short.parseShort(value));
                    break;
                case "java.lang.Byte" :
                    nameField.set(obj, Byte.parseByte(value));
                    break;
                case "java.lang.Boolean" :
                    nameField.set(obj, Boolean.parseBoolean(value));
                    break;
                case "java.lang.Character" :
                    nameField.set(obj, value.charAt(0));
                    break;
                case "java.lang.String" :
                    nameField.set(obj, value);
                    break;
                case "int" :
                    nameField.set(obj, Integer.parseInt(value));
                    break;
                case "double" :
                    nameField.set(obj, Double.parseDouble(value));
                    break;
                case "long" :
                    nameField.set(obj, Long.parseLong(value));
                    break;
                case "short" :
                    nameField.set(obj, Short.parseShort(value));
                    break;
                case "byte" :
                    nameField.set(obj, Byte.parseByte(value));
                    break;
                case "boolean" :
                    nameField.set(obj, Boolean.parseBoolean(value));
                    break;
                case "char" :
                    nameField.set(obj, value.charAt(0));
                    break;
                case "float" :
                    nameField.set(obj, Float.parseFloat(value));
                    break;
                default:
                    break;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}

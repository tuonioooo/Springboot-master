package com.master.utils;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
*  @Author tuonioooo
*  @Date 2018-8-13 14:17
*  @Info Lucene索引读写器/查询器单例获取工具类
*  @Blog https://blog.csdn.net/tuoni123
*/
public class LuceneManager {

    private volatile static IndexWriter writer;
    private volatile static IndexReader reader;
    private volatile static IndexSearcher searcher;

    private LuceneManager() {
    }

    private static class LuceneManagerPR{
        public static final LuceneManager singleton = new LuceneManager();
    }

    public static LuceneManager getInstance(){
        return LuceneManagerPR.singleton;
    }

    /**
     * 获取IndexWriter单例对象
     * @param dir
     * @param conf
     * @return
     */
    public IndexWriter getIndexWriter(Directory dir, IndexWriterConfig conf) {
        if (null == dir) {
            throw new IllegalArgumentException("Directory can not be null.");
        }
        if (null == conf) {
            throw new IllegalArgumentException("IndexWriterConfig can not be null.");
        }
        try {
            if (null == writer) {
                writer = new IndexWriter(dir, conf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }

    /**
     * 获取IndexReader对象
     * @param dir
     * @param enableNRTReader  是否开启NRTReader
     * @return
     */
    public IndexReader getIndexReader(Directory dir, boolean enableNRTReader) {
        if (null == dir) {
            throw new IllegalArgumentException("Directory can not be null.");
        }
        try {
            if (null == reader) {
                reader = DirectoryReader.open(dir);
            } else {
                if (enableNRTReader && reader instanceof DirectoryReader) {
                    reader = DirectoryReader.openIfChanged((DirectoryReader) reader);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }

    /**
     * 获取IndexReader对象(默认不启用NETReader)
     * @param dir
     * @return
     */
    public IndexReader getIndexReader(Directory dir) {
        return getIndexReader(dir, false);
    }

    /**
     * 获取IndexSearcher对象
     * @param reader    IndexReader对象实例
     * @param executor  如果你需要开启多线程查询，请提供ExecutorService对象参数
     * @return
     */
    public IndexSearcher getIndexSearcher(IndexReader reader, ExecutorService executor) {
        if (null == reader) {
            throw new IllegalArgumentException("The indexReader can not be null.");
        }
        if (null == searcher) {
            searcher = new IndexSearcher(reader, executor);
        }
        return searcher;
    }

    /**
     * 获取IndexSearcher对象(不支持多线程查询)
     * @param reader    IndexReader对象实例
     * @return
     */
    public IndexSearcher getIndexSearcher(IndexReader reader) {
        return getIndexSearcher(reader, null);
    }
}
package com.portal.common;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 通用类设计，其中包含索通用的常量以及一些通用的方法。
 *
 * @author allen-zyg .
 * @version 1.0.0
 */
public class LuceneUtil {

    /**
     * 写索引器.
     */
    private static IndexWriter indexWriter;
    /**读索引器.*/
    private static IndexReader indexReader;
    /**搜索.*/
    private static IndexSearcher indexSearcher;
    /***中文分词器.*/
    private static Analyzer analyzer;

    /**
     * 关流.
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (indexWriter != null) {
                    try {
                        indexWriter.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (indexReader != null) {
                    try {
                        indexReader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 获得IndexWriter对象
     *
     * @return IndexWriter
     */
    public static IndexWriter getIndexWriter() {
        if (indexWriter != null) {
            return indexWriter;
        }
        try {
            Directory dir = FSDirectory.open(new File(Config.getIndexPath()));
            //判断是否以及指定分词器，如未指定使用默认分词器
            Analyzer analyzer = getAnalyzer();
            //创建IndexWriter的配置
            IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
            indexWriter = new IndexWriter(dir, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    /**
     * 获得索引读取对象
     *
     * @return IndexReader .
     */
    public static IndexReader getIndexReader() {
        if (indexReader != null) {
            return indexReader;
        }
        try {
            File path = new File(Config.getIndexPath());
            if (!path.exists()) {
                if (path.isDirectory()) {
                    path.mkdirs();
                }
            }
            Directory dir = FSDirectory.open(path);
            indexReader = DirectoryReader.open(dir);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return indexReader;
    }

    /**
     * 索引查询对象
     *
     * @return IndexSearcher.
     */
    public static IndexSearcher getIndexSearcher() {
        if (indexSearcher != null) {
            return indexSearcher;
        }
        indexSearcher = new IndexSearcher(getIndexReader());

        return indexSearcher;

    }


    /**
     * 指定分词器
     * @param analyzer .
     */
    public static void setAnalyzer(Analyzer analyzer) {
        LuceneUtil.analyzer = analyzer;
        //更改解析器后重新初始化IndexWriter
        if (indexWriter != null) {
            try {
                indexWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            indexWriter = null;
        }
        //更改解析器后重新初始化IndexReader
        if (indexReader != null) {
            try {
                indexReader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            indexReader = null;
        }
    }

    /**
     * 获得分词器.
     * @return Analyzer .
     */
    public static Analyzer getAnalyzer() {
        return  analyzer == null ? analyzer = new IKAnalyzer() : analyzer;
    }

    /**
     * 分词器测试.
     * @param  analyzer analyzer.
     * @param text text.
     */
    public static void analyzerToken(Analyzer analyzer, String text) {
        System.out.println("测试分词器：" + analyzer.getClass().getSimpleName());
        try {
            TokenStream tokenStream = analyzer.tokenStream("content", text);
//			tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
                System.out.println(attribute.toString());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

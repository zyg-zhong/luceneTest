package com.portal.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.portal.common.Config;
import com.portal.common.LuceneUtil;
import com.portal.dao.IndexDao;
import com.portal.pojo.Products;
import com.portal.search.ResultModel;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

/**
 * 索引管理Dao实现类
 *
 * @author allen-zyg .
 * @version 1.0.0
 */
@Component
public class IndexDaoImpl implements IndexDao {

    /**
     * 创建索引库.
     *
     * @param list list
     */
    @Override
    public void createDump(List<Products> list) {
        /**
         * 获得IndexWriter对象.
         */
        IndexWriter writer = LuceneUtil.getIndexWriter();
        //创建索引前先清空
        try {
            writer.deleteAll();
            //写入日志：删除索引库成功
        } catch (IOException e1) {
            e1.printStackTrace();
            //写入在日志：删除索引库失败。
        }
        //重新建立索引
        for (Products model : list) {
            //创建文档对象
            Document document = new Document();
            //创建索引域    需要改的
            Field id = new IntField("pid", model.getPid(), Store.YES);
            Field name = new TextField("name", model.getName(), Store.YES);
            Field catalog = new IntField("catalog", model.getCatalog(), Store.YES);
            Field catalogName = new StringField("catalog_name", model.getCatalogName(), Store.YES);
            Field price = new DoubleField("price", model.getPrice(), Store.YES);
            Field number = new IntField("number", model.getNumber(), Store.YES);
            Field description = new TextField("discription", model.getDescription(), Store.NO);
            Field releaseTime = new LongField("release_time", model.getReleaseTime().getTime(), Store.YES);
            Field picture = new StoredField("picture", model.getPicture());
            //将域添加到文档对象中
            document.add(id);
            document.add(name);
            document.add(catalog);
            document.add(catalogName);
            document.add(price);
            document.add(number);
            document.add(description);
            document.add(releaseTime);
            document.add(picture);
            try {
                //写索引库
                writer.addDocument(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            //提交
            writer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 更加分类名称查询商品分类列表.
     *
     * @param catalogName .
     * @param page         .
     * @return ResultModel .
     */
    @Override
    public ResultModel getProductList(String queryString,
                                      String catalogName, Double priceStart,
                                      Double priceEnd, Integer page, String sort) {

        // 根据分类名称查询索引
        //查询的字段
        BooleanQuery query = new BooleanQuery();
        Query keyWordQuery = null;
        if (queryString != null && !"".equals(queryString)) {
            //查询域列表
            String[] fields = {"name", "discription"}; //  指定列表域  要该的
            //创建查询分析器
            QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_4_10_3, fields, LuceneUtil.getAnalyzer());
            //根据搜索关键词创建查询     关键字
            try {
                keyWordQuery = queryParser.parse(queryString);
                query.add(keyWordQuery, Occur.MUST);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            MatchAllDocsQuery matchAllDocsQuery = new MatchAllDocsQuery();
            query.add(matchAllDocsQuery, Occur.MUST);
        }
        //根据商品分类名称查询    分类
        if (catalogName != null && !"".equals(catalogName)) {
            Query catalogQuery = new TermQuery(new Term("catalog_name", catalogName));
            query.add(catalogQuery, Occur.MUST);
        }

        //根据价格区间过滤       过滤
        Filter filter = null;
        if (priceStart != null) {
            filter = NumericRangeFilter.newDoubleRange("price", priceStart, priceEnd, true, true);
        }
        //排序，默认根据价格排序      排序
        Sort sSort = new Sort();
        if ("1".equals(sort)) {
            SortField sortField = new SortField("price", SortField.Type.DOUBLE);
            sSort.setSort(sortField);
        } else {
            SortField sortField = new SortField("price", SortField.Type.DOUBLE, true);
            sSort.setSort(sortField);
        }
        ResultModel resultList = queryIndex(query, filter, sSort, page);
        //如果是根据关键字查询的结果，需要高亮显示关键字    高亮设置
        if (keyWordQuery != null) {
            QueryScorer queryScorer = new QueryScorer(keyWordQuery);
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span style=\"color:red\">", "</span>");
            Highlighter highlighter = new Highlighter(formatter, queryScorer);
            if (null != resultList.getProductList()) {
                for (Products model : resultList.getProductList()) {
                    try {
                        //高亮显示商品名称中的关键字
                        String name = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "name", model.getName());
                        System.out.println(model.getName() + "=>" + name);
                        //如果商品名称中没有关键字则返回null，此时不更新。
                        if (null != name) {
                            model.setName(name);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return resultList;
    }

    /**
     * 通用查询方法，根据指定的Query对象查询索引库
     * @param  filter .
     * @param query .
     * @param sort .
     * @param page  .
     * @return ResultModel .
     */
    private ResultModel queryIndex(Query query, Filter filter, Sort sort, int page) {
        //创建结果对象
        ResultModel resultModel = new ResultModel();

        //获得IndexSearcher对象
        IndexSearcher searcher = LuceneUtil.getIndexSearcher();
        //商品列表
        List<Products> productList = new ArrayList<>();
        try {
            ScoreDoc[] scoreDocs = searcher.search(query, filter, Config.getSearchMax(), sort).scoreDocs;
            //商品总数
            resultModel.setRecordCount(scoreDocs.length);
            //计算总页数   总记录数/每页展示条数
            int pageCount = scoreDocs.length / Config.getPageSize();
            if ((scoreDocs.length % Config.getPageSize()) > 0) {
                pageCount++;
            }
            resultModel.setPageCount(pageCount);
            //当前页
            resultModel.setCurPage(page);
            if (page > pageCount) {
                return resultModel;
            }
            //计算起始和结束下标
            int start = (page - 1) * Config.getPageSize();
            int end = page * Config.getPageSize();

            //起始下标大于记录总数
            if (start < 0 || start > scoreDocs.length) {
                return resultModel;
            }
            //结束下标大于记录总数时
            if (end > scoreDocs.length) {
                end = scoreDocs.length;
            }

            //遍历查询索引结果
            for (int i = start; i < end; i++) {
                Document document = searcher.doc(scoreDocs[i].doc);
                //商品对象    将查询到的结果返回赋值给商品模型对象。
                Products model = new Products();
                model.setPid(Integer.parseInt(document.get("pid")));
                model.setName(document.get("name"));
                model.setPrice(Double.parseDouble(document.get("price")));
                model.setPicture(document.get("picture"));
                //将商品对象添加到列表中
                productList.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultModel.setProductList(productList);
        return resultModel;
    }

}

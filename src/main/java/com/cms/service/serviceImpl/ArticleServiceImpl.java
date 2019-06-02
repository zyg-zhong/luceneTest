package com.cms.service.serviceImpl;

import com.cms.dao.ArticleDAO;
import com.cms.entity.Article;
import com.cms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * articleService.
 *
 * @author allen-zyg
 * @version 1.0.0
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    /**创建dao对象.*/
    @Autowired
    private ArticleDAO articleDAO;

    /**
     * queryAlllArticles.
     * @return List<Article>
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Article> queryAlllArticles() {
        return articleDAO.selectAllArticle();
    }

    /**
     * 添加文章.
     *
     * @param ids     id数组
     * @param article article文章实体
     * @return String
     */
    @Override
    public String addArtic(Integer[] ids, Article article) {
        //根据ids个数来插入
        for (int i = 0; i < ids.length; i++) {
            article.setArticleId(ids[i]);
            articleDAO.insert(article);
        }
        return "ok";
    }

    /*
     * 管理文章发布的目标栏目涉及的业务逻辑，
     * 第一种情况：在发布文章时，选择发布的栏目：（这一步在每个栏目下的表单一样）
     * 		1>前端：除了文章内容外，选择多个栏目的id，作为要发布到哪些栏目；
     * 		2>后端：添加时，通过id个数来插入，一旦发布，则每个栏目都有自己的数据记录。
     * 第二种情况：在后期，管理员在cms 更改文章发布的栏目，
     * 		情景一1>添加了要展示的栏目，
     * 			我们称他为分享给其他栏目。
     * 			1.判断该栏目是否有这篇文章
     * 				有了：提示已经存在，
     * 				没有：前端选择一个参数 ids[]	,后端接收数据，先插入到要添加的栏目（同添加操作）
     *
     * 		情景二2>删除栏目下的文章：
     * 			由于我们目前涉及的cms,每个栏目的文章列表都是在当前栏目下修改+
     * 			所以，管理员只能在当前栏目下，对当前栏目的文章删除，即可不让某个栏目展示。
     *
     *
     * 		注：除非cms 提供统一的发布和修改文章的入口，那么以上业务可以稍作整改。
     *
     *
     *
     *
     */

    /**
     * updateArticle.
     */
    public void updateArticle() {

    }

}

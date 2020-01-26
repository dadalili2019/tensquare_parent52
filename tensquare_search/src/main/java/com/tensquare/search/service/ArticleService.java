package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author caoqian
 * @ClassName ArticleService
 * @Date 2020/1/25 22:19
 * @Version 1.0
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 添加索引
     * @param article
     */
    public void save(Article article){
        articleDao.save(article);
    }

    /**
     * 根据key 分页查询
     * @param key
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByKey(String key, int page, int size) {
        PageRequest pageable =  PageRequest.of(page-1, size);
        return articleDao.findByTitleOrContentLike(key,key, pageable);
    }
}

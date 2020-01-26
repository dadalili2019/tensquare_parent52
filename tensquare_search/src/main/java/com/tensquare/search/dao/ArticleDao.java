package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author caoqian
 * @ClassName ArticleDao
 * @Date 2020/1/25 15:13
 * @Version 1.0
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);

}

package com.wzt.uml.service;

import com.wzt.uml.dao.ArticleMapper;
import com.wzt.uml.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public int addArticle(Article article) {
        return articleMapper.insert(article);
    }

    public List<Article> getLatstArticle(){
        return articleMapper.getRecentArticle();
    }

    public Article getArticleByID(int article_id){
        return articleMapper.selectByID(article_id);
    }

    public List<Article> getPersonalAT(String author){
        return articleMapper.getArtucleByAU(author);
    }
}

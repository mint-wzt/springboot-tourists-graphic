package com.wzt.uml.dao;

import com.wzt.uml.model.Article;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component
public interface ArticleMapper {

    @Select({"select article_id,title,author,content,time,thumbs from article order by article_id desc limit 20"})
    List<Article> getRecentArticle();

    @Insert({"insert into article(title,author,content,time) values(#{title},#{author},#{content},#{time})"})
    int insert(Article article);

    @Select({"select * from article where article_id=#{article_id}"})
    Article selectByID(int article_id);

    @Select({"select * from article where author=#{author}"})
    List<Article> getArtucleByAU(String author);

}

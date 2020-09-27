package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.entity.Article;
import com.example.demo.mybatis.entity.Connect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * Created by 20372 on 2020/9/13.
 */
@Mapper
public interface ArticleMapper {
    /**
     * 查询文本内容
     * @return
     */
    List<Article> findAllArticle();
    void addText(Article article);
    void deleteText(int id);
    Article findText(int articleId);
    int findArticleIdByTitle(String articleTitle);
    List<Article> findArticleById(int id);
    int[] findArticleIdByTagAndId(Connect connect);
    int cntArticle(int id);

    void deleteComment(int commentId);
}

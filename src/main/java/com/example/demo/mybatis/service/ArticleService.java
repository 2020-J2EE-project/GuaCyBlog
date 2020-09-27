package com.example.demo.mybatis.service;
import com.example.demo.mybatis.entity.Article;
import com.example.demo.mybatis.entity.Comment;
import com.example.demo.mybatis.entity.Connect;

import java.util.List;

/**
 * Created by 20372 on 2020/9/13.
 */
public interface ArticleService {


    List<Comment> findCommentsByArticleId(int articleId);

    void addComment(Comment comment);

    void addText(Article article);

    void deleteText(int id);

    Article findText(int articleId);

    List<Article> findAllArticle();

    List<Article> findArticleById(int id);

    List<Article> findArticleByTagAndId(Connect connect);

    int findArticleIdByTitle(String articleTitle);

    int cntArticle(int id);

    void deleteComment(int commentId);
}

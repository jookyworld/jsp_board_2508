package com.ll.jsp.board.boundedContext.article.repository;

import com.ll.jsp.board.boundedContext.article.dto.Article;
import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.db.DBConnection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

public class ArticleRepository {
    private List<Article> articleList;
    DBConnection dbConnection;

    public ArticleRepository() {
        dbConnection = Container.dbConnection;
        dbConnection.connect();
    }

    public List<Article> findAll() {
        articleList = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows("select * from article");
        System.out.println(rows);

        for (Map<String, Object> row : rows) {
            Article article = new Article(row);
            articleList.add(article);
        }
        return articleList;
//        return articleList.stream()
//                .sorted(Comparator.comparing(Article::getId).reversed()).toList();
    }

    public long save(String title, String content) {
        int id = dbConnection.insert("""
                INSERT INTO article SET 
                title='%s', 
                content='%s'
                """
                .formatted(title, content));

//        Article article = new Article(id, title, content);
//
//        articleList.add(article);

        return id;
    }

    public Article findById(Long id) {
        Map<String, Object> row = dbConnection.selectRow("SELECT * from article WHERE id=%d".formatted(id));
        return new Article(row);
//        return articleList.stream()
//                .filter(a -> a.getId() == id)
//                .findFirst()
//                .orElse(null);
    }

    public void modify(Long id, String title, String content) {
        Article article = findById(id);

        if (article == null) {
            return;
        }

        article.setTitle(title);
        article.setContent(content);
    }

    public void delete(Long id) {

        dbConnection.delete("DELETE FROM article WHERE id = %d".formatted(id));

        Article article = findById(id);
        if (article == null) return;
        articleList.remove(article);
    }
}

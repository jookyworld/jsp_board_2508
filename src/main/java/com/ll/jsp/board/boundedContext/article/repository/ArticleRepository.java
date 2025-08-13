package com.ll.jsp.board.boundedContext.article.repository;

import com.ll.jsp.board.boundedContext.article.dto.ArticleDto;
import com.ll.jsp.board.boundedContext.article.entity.Article;
import com.ll.jsp.board.boundedContext.base.Container;
import com.ll.jsp.board.boundedContext.member.dto.Member;
import com.ll.jsp.board.db.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    }

    public List<ArticleDto> joinMemberFindAll() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows("""
                SELECT A.id, A.title, A.content, A.regDate, M.username
                FROM article as A
                INNER JOIN member as M
                ON A.member_id = M.id
                """);

        for (Map<String, Object> row : rows) {
            ArticleDto articleDto = new ArticleDto(row);
            articleDtos.add(articleDto);
        }

        System.out.println(rows);

        return articleDtos;
    }

    public ArticleDto joinMemberFindById(long id) {
        Map<String, Object> row = dbConnection.selectRow("""
                SELECT A.id, A.title, A.content, A.regDate, M.username
                FROM article AS A
                INNER JOIN member AS M
                ON A.member_id = M.id
                WHERE A.id = %d
                """
                .formatted(id));
        return new ArticleDto(row);
    }

    public long save(String title, String content, Member member) {
        int id = dbConnection.insert("""
                INSERT INTO article SET
                title='%s',
                content='%s',
                regDate=now(),
                member_id=%d;
                """
                .formatted(title, content, member.getId()));
        return id;
    }

    public Article findById(Long id) {
        Map<String, Object> row = dbConnection.selectRow("SELECT * from article WHERE id=%d".formatted(id));
        return new Article(row);
    }

    public void modify(Long id, String title, String content) {
        dbConnection.insert("""
                UPDATE article SET
                title = '%s',
                content = '%s'
                WHERE id = %d
                """
                .formatted(title, content, id));
    }

    public void delete(Long id) {
        dbConnection.delete("DELETE FROM article WHERE id = %d".formatted(id));
    }
}

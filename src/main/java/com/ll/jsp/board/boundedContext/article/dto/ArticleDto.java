package com.ll.jsp.board.boundedContext.article.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArticleDto {
    private long id;
    private String title;
    private String content;
    private String regDate;
    private String author;

    public ArticleDto(Map<String, Object> row) {
        this.id = Long.parseLong(row.get("id").toString());
        this.title = row.get("title").toString();
        this.content = row.get("content").toString();
        this.regDate = row.get("regDate").toString();
        this.author = row.get("username").toString();
    }
}

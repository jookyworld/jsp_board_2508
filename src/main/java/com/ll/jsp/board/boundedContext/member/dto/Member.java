package com.ll.jsp.board.boundedContext.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Member {
    private final long id;
    private String username;
    private String password;
    private String name;
    private String regDate;

    public Member(Map<String, Object> row) {
        this.id = Long.parseLong(row.get("id").toString());
        this.username = row.get("username").toString();
        this.password = row.get("password").toString();
        this.name = row.get("name").toString();
        this.regDate = row.get("regDate").toString();
    }
}

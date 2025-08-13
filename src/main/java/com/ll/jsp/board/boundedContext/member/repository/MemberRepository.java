package com.ll.jsp.board.boundedContext.member.repository;

import com.ll.jsp.board.boundedContext.member.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class MemberRepository {
    private List<Member> members;
    private long lastId;

    public MemberRepository() {
        members = new ArrayList<>();
        makeTestData();
        lastId = members.get(members.size() - 1).getId();
    }

    void makeTestData() {
        LongStream.rangeClosed(1,5).forEach(i -> {
            Member member = new Member(i, "user" + i, "user" + 1, "user" + 1);
            members.add(member);
        });
    }

    public void save(String username, String password, String name) {
        long id = ++lastId;
        Member member = new Member(id, username, password, name);
        members.add(member);
    }

    public Member findByUsername(String username) {
        return members.stream()
                .filter(m -> m.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}

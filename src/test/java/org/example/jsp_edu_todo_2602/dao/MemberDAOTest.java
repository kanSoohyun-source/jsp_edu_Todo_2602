package org.example.jsp_edu_todo_2602.dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@Log4j2

class MemberDAOTest {
    private MemberDAO memberDAO;

    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }

    @Test
    public void selectOne() {
        String memberId = "user00";
        log.info(memberDAO.selectOne(memberId));
    }

    @Test
    public void updateUuidTest() {
        String memberId = "user01";
        String Uuid = UUID.randomUUID().toString();

        memberDAO.updateUuid(memberId, Uuid);
    }

}
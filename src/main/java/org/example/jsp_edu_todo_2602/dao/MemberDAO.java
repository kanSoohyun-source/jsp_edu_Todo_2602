package org.example.jsp_edu_todo_2602.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.domain.MemberVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2

public class MemberDAO {
    public MemberVo selectOne(String memberId) {
        MemberVo memberVo = null;
        String sql = "SELECT * FROM member WHERE member_id = ?";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                memberVo = MemberVo.builder()
                        .memberId(resultSet.getString("member_id"))
                        .passwd(resultSet.getString("passwd"))
                        .name(resultSet.getString("name")).build();
            }
            return memberVo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUuid(String memberId, String uuid) {
        // 자동 로그인 기능을 사용하여 임의의 문자열이 생성되 경우, 해당 memberId의 데이터 업데이트
        String sql = "UPDATE member SET uuid = ? WHERE member_id = ?";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, memberId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MemberVo selectByUuid(String uuid) {
        MemberVo memberVo = null;
        String sql = "SELECT * FROM member WHERE uuid = ?";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                memberVo = MemberVo.builder()
                        .memberId(resultSet.getString("member_id"))
                        .passwd(resultSet.getString("passwd"))
                        .name(resultSet.getString("name")).build();
            }
            return memberVo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

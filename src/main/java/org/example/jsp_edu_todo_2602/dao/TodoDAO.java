package org.example.jsp_edu_todo_2602.dao;

import lombok.Cleanup;
import org.example.jsp_edu_todo_2602.domain.TodoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    public String getTime() {
        String now = null;

        try (
                Connection connection = ConnectionUtil.INSTANCE.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select now()");
                ResultSet resultSet = preparedStatement.executeQuery()
                ) {
            resultSet.next();
            now = resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return now;
    }

    public String getTime2() {
        String now = null;
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            now = resultSet.getString(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return now;
    }

    // todo 등록
    public void insert(TodoVO todoVO) {
        String sql = "INSERT INTO todo (title, due_date, finish) VALUES (?, ?, ?)";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, todoVO.getTitle());
            preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
            preparedStatement.setBoolean(3, todoVO.isFinish());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // todo 목록 조회
    public List<TodoVO> selectAll () {
        List<TodoVO> todoVOList = new ArrayList<>();

        String sql = "SELECT * FROM todo";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoVO todoVO = TodoVO.builder()
                        .tno(resultSet.getLong("tno"))
                        .title(resultSet.getString("title"))
                        .dueDate(resultSet.getDate("due_date").toLocalDate())
                        .finish(resultSet.getBoolean("finish")).build();
                todoVOList.add(todoVO);
            }
            return todoVOList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // todo를 조회 -> 하나의 행을 반환하도록(매개변수로 primary key 받음)
    public TodoVO selectOne(long tno) {
        TodoVO todoVO = null;

        String sql = "SELECT * FROM todo WHERE tno = ?";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (int) tno);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                todoVO = TodoVO.builder()
                        .tno(resultSet.getLong("tno"))
                        .title(resultSet.getString("title"))
                        .dueDate(resultSet.getDate("due_date").toLocalDate())
                        .finish(resultSet.getBoolean("finish")).build();
            }
            return todoVO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // todo를 삭제 -> 매개변수로 primary key 받음
    public void delete(long tno) {
        String sql = "DELETE FROM todo WHERE tno = ?";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (int) tno);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // todo를 수정 -> tno를 제외한 모든 것을 수정
    public void update(TodoVO todoVO) {
        String sql = "UPDATE todo SET title = ?, due_date = ?, finish = ? WHERE tno = ?";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, todoVO.getTitle());
            preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
            preparedStatement.setBoolean(3, todoVO.isFinish());
            preparedStatement.setLong(4, todoVO.getTno());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

package org.example.jsp_edu_todo_2602.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TodoDTO {
    private Long tno; // 할일 번호
    private String title; // 제목
    private LocalDate dueDate; // 마감일
    private boolean finish; // 완료 여부
}

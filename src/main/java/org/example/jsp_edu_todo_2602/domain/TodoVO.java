package org.example.jsp_edu_todo_2602.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class TodoVO {
    private Long tno; // 할일 번호
    private String title; // 제목
    private LocalDate dueDate; // 마감일
    private boolean finish; // 완료 여부
}

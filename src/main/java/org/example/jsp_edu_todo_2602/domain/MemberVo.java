package org.example.jsp_edu_todo_2602.domain;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MemberVo {
    private String memberId; // 아이디
    private String passwd; // 비밀번호
    private String name; // 이름
    private String uuid;
}

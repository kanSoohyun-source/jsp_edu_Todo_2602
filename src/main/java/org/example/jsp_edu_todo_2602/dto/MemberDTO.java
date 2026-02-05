package org.example.jsp_edu_todo_2602.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MemberDTO {
    private String memberId; // 아이디
    private String passwd; // 비밀번호
    private String name; // 이름
    private String uuid;
}

package org.example.jsp_edu_todo_2602.service;

import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.dao.MemberDAO;
import org.example.jsp_edu_todo_2602.domain.MemberVo;
import org.example.jsp_edu_todo_2602.dto.MemberDTO;
import org.example.jsp_edu_todo_2602.util.MapperUtil;
import org.modelmapper.ModelMapper;

@Log4j2

public enum MemberService {
    INSTANCE;

    private final MemberDAO memberDAO;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();

    MemberService() {
        memberDAO = new MemberDAO();
    }

    // 로그인 처리 - 인증 여부
    public boolean isAuth(String memberId, String passwd) {
        boolean isAuth = false;
        MemberVo memberVO = memberDAO.selectOne(memberId);
        if(memberVO != null && memberVO.getPasswd().equals(passwd)) { // 아이디가 있고 비밀번호가 맞을 때
            isAuth = true;
        }
        return isAuth;
    }

    // UUID 수정
    public void modifyUuid(String memberId, String uuid) {
        memberDAO.updateUuid(memberId, uuid);
    }

    public MemberDTO getMemberByUuid(String uuid) {
        MemberVo memberVo = memberDAO.selectByUuid(uuid);
        if(memberVo != null) {
            MemberDTO memberDTO = modelMapper.map(memberVo, MemberDTO.class);
            log.info(memberDTO);
            return memberDTO;
        }
        return null;
    }

}

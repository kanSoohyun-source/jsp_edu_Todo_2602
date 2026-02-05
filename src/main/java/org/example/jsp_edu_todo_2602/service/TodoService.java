package org.example.jsp_edu_todo_2602.service;

import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.dao.TodoDAO;
import org.example.jsp_edu_todo_2602.domain.TodoVO;
import org.example.jsp_edu_todo_2602.dto.TodoDTO;
import org.example.jsp_edu_todo_2602.util.MapperUtil;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Log4j2

public enum TodoService {
    INSTANCE;

    private final TodoDAO todoDAO;
    private final ModelMapper modelMapper;

    TodoService() {
        todoDAO = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.getInstance();
    }

    // todo 등록
    public void add(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoDAO.insert(todoVO);
    }

    // todo 목록
    public List<TodoDTO> getAll() {
        List<TodoVO> todoVOList = todoDAO.selectAll();
        List<TodoDTO> todoDTOList = todoVOList.stream()
                .map(todoVO -> modelMapper.map(todoVO, TodoDTO.class)).toList();
        return todoDTOList;

        /*
        List<TodoDTO> todoDTOList = new ArrayList<>();
        List<TodoVO> todoVOList = todoDAO.selectAll();
        for (var todoVO : todoVOList) {
            todoDTOList.add(modelMapper.map(todoVO, TodoDTO.class));
        }
        return todoDTOList;
        */
    }

    // todo 조회
    public TodoDTO getOne(long tno) {
        return modelMapper.map(todoDAO.selectOne(tno), TodoDTO.class);
    }

    // todo 수정
    public  void modify(TodoDTO todoDTO) {
        todoDAO.update(modelMapper.map(todoDTO, TodoVO.class));
    }

    // todo 삭제
    public void remove(long tno) {
        todoDAO.delete(tno);
    }




}

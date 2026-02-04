<%@ page import="org.example.jsp_edu_todo_2602.dto.TodoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TodoDTO todoDTO = (TodoDTO) request.getAttribute("todoDTO");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="../inc/menu.jsp"/>

    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">

            </h1>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="form-group row">
                    <label class="col-sm-2">번호</label>
                    <div class="col-sm-3">
                        <%=todoDTO.getTno()%>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2">할일</label>
                    <div class="col-sm-3">
                        <%=todoDTO.getTitle()%>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2">마감일</label>
                    <div class="col-sm-5">
                        <%=todoDTO.getDueDate()%>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2">완료 여부</label>
                    <div class="col-sm-5">
                        <% String result = todoDTO.isFinish() ? "완료":"미완료";
                        out.println(result);%>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="./boardDelete.do?bno=<%=todoDTO.getTno()%>" class="btn btn-danger">삭제</a>
                        <a href="./boardModifyForm.do?bno=<%=todoDTO.getTno()%>" class="btn btn-danger">수정</a>
                        <a href="../todo/list" class="btn btn-primary">목록</a>
                    </div>
                </div>
            </div>

        </div>
        <hr>
    </div>
    <%@include file="../inc/footer.jsp"%>

</body>
</html>

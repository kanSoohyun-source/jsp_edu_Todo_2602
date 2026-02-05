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
                todo 수정
            </h1>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <form name="formAddProduct" action="/todo/modify" method="post" class="form-horizontal">
                    <div class="form-group row">
                        <input type="hidden" name="tno" id="tno" value="<%=todoDTO.getTno()%>">
                        <label class="col-sm-2" for="title">할일</label>
                        <div class="col-sm-3">
                            <input type="text" name="title" id="title" class="form-control" value="<%=todoDTO.getTitle()%>">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2" for="dueDate">마감일</label>
                        <div class="col-sm-5">
                            <input type="date" name="dueDate" id="dueDate" class="form-control" value="<%=todoDTO.getDueDate()%>">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2">완료 여부</label>
                        <div class="col-sm-5">
                            <%-- type="radio" => value 필요 --%>
                            <label><input type="radio" name="finish" value="on"
                                <%if (todoDTO.isFinish()) out.println("checked");%>>완료</label>
                            <label><input type="radio" name="finish" value="off"
                                <%if (!todoDTO.isFinish()) out.println("checked");%>>미완료</label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" class="btn btn-primary" value="등록">
                        </div>
                    </div>
                </form>
            </div>

        </div>
        <hr>
    </div>
    <%@include file="../inc/footer.jsp"%>

</body>
</html>

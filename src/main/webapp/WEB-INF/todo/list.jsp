<%@ page import="org.example.jsp_edu_todo_2602.dto.TodoDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<TodoDTO> dtoList = (List<TodoDTO>) request.getAttribute("dtoList");
    int no = dtoList.size();
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
                Todo 목록
            </h1>
        </div>
    </div>
    <div class="container">
        <div>
            <div class="text-right">
                <span class="badge badge-success">전체 <%=dtoList.size()%>건 </span>
            </div>
        </div>
        <div style="padding-top: 50px">
            <table class="table table-hover">
                <tr>
                    <th>번호</th>
                    <th>할일</th>
                    <th>마감일</th>
                    <th>완료 여부</th>
                </tr>
                <%
                    for (TodoDTO todoDTO : dtoList) {
                %>
                <tr>
                    <td><%=no--%>
                    </td>
                    <td><a href="../todo/read?tno=<%=todoDTO.getTno()%>"><%=todoDTO.getTitle()%>
                    </a></td>
                    <td><%=todoDTO.getDueDate()%>
                    </td>
                    <td><%=todoDTO.isFinish()%>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <hr>
    </div>
    <%@ include file="../inc/footer.jsp" %>
</body>
</html>

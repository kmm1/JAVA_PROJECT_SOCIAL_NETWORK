<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blog Form</title>
</head>
<body>
<%@include file="header.jsp" %>
<center>

    <table border="1" width="80%" cellpadding="5">
        <form action="${pageContext.request.contextPath}/saveActor" method="post">
            <thead>
            <tr>
                <th colspan="2">Создание новой записи</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Тема</td>
                <td><input type="text" name="title" value=""/></td>
            </tr>
            <tr>
                <td>Текст</td>
                <td><input type="text" name="text" value=""/></td>
            </tr>
            </tbody>
        </form>
    </table>

</center>

</body>
</html>
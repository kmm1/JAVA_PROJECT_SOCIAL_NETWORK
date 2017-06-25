<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>

<center>
    <table border="1" width="30%" cellpadding="5">
        <form action="${pageContext.request.contextPath}/category" method="post">
            <thead>
            <tr>
                <th colspan="2">Создать категорию</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>категория</td>
                <td><select name="enumCategory">
                    <option value="FINANCE">Финансы</option>
                    <option value="BUSINESS">Бизнес</option>
                    <option value="RELIGION">Религия</option>
                    <option value="SCIENCE">Наука</option>
                    <option value="CULTURE">Культура</option>
                    <option value="SPORT">Спорт</option>
                    <option value="TRAVEL">Путешествия</option>
                    <option value="DIFFERENT">Разное</option>
                </select></td>
            </tr>

            </tbody>
        </form>
    </table>

</center>
</body>
</html>






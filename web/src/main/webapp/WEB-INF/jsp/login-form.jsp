<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<center>
    <table border="1" width="30%" cellpadding="3">
        <form action="${pageContext.request.contextPath}/enter" method="post">
            <thead>
            <tr>
                <th colspan="2">войти</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>имя</td>
                <td><input type="name" name="name" value=""/></td>
            </tr>
            <tr>
                <td>пароль</td>
                <td><input type="password" name="password" value=""/></td>
            </tr>
            <tr>
                <td>вход</td>
                <td><input type="submit" value=войти></td>
            </tr>
            </tbody>
        </form>
    </table>

    <form action="${pageContext.request.contextPath}/registration" method="get">
        <tr>
            <td><input type="submit" name="prof" value=зарегистрироваться></td>
        </tr>
    </form>

</center>

</body>
</html>

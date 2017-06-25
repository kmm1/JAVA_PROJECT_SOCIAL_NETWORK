<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <table border="1" width="30%" cellpadding="5">
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <thead>
            <tr>
                <th colspan="2">Введите информацию</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>имя</td>
                <td><input type="text" name="name" value=""/></td>
            </tr>
            <tr>
                <td>email</td>
                <td><input type="text" name="email" value=""/></td>
            </tr>
            <tr>
                <td>пароль</td>
                <td><input type="password" name="password" value=""/></td>
            </tr>
            <tr>
                <td>пароль еще раз</td>
                <td><input type="password" name="passwordRepeat" value=""/></td>
            </tr>
            <tr>
                <td>зарегистрироваться</td>
                <td><input type="submit" value="зарегистрироваться"/></td>
            </tr>
            </tbody>
        </form>
    </table>

    <form action="${pageContext.request.contextPath}/index" method="get">
        <tr>
            <td>уже зарегистрированы?</td>
            <td><input type="submit" name="prof" value="вход"></input></td>
        </tr>
    </form>
</center>
</body>
</html>





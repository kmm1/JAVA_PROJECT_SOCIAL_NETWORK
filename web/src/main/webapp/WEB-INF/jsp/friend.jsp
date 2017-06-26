<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friend</title>
</head>
<body>
<%@include file="header.jsp" %>
<center>

    <table border="1" width="30%" cellpadding="5">
        <thead>
        <tr>
            <th colspan="5"><p>Мои друзья:</p></th>
        </tr>
        </thead>
        <thead>
        <tr>
            <th colspan="1">Id</th>
            <th colspan="1">имя</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="findAllFriendsByUserName" items="${findAllFriendsByUserName}">
            <tr>
                <c:if test="${empty findAllFriendsByUserName.toString()}">
                    <td> данных нет</td>
                </c:if>
                <c:if test="${not empty findAllFriendsByUserName.toString()}">
                <td>${findAllFriendsByUserName}
                    </c:if>
            </tr>
            <tr>
                <c:if test="${empty findAllFriendsByUserName}">
                    <td> данных нет</td>
                </c:if>
                <c:if test="${not empty findAllFriendsByUserName}">
                <td>${findAllFriendsByUserName}
                    </c:if>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <table border="1" width="30%" cellpadding="5">
        <thead>
        <tr>
            <th colspan="5"><p>Полученные запросы на добавления в друзья:</p></th>
        </tr>
        </thead>
        <thead>
        <tr>
            <th colspan="1">Id</th>
            <th colspan="1">имя</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="friends" items="${findAllMyFriendRequestsResived}">
            <tr>
                <td>${findAllMyFriendRequestsResived}</td>
                <td>${findAllMyFriendRequestsResived}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table border="1" width="30%" cellpadding="5">
        <thead>
        <tr>
            <th colspan="5"><p>Отправленные запросы на добавления в друзья:</p></th>
        </tr>
        </thead>
        <thead>
        <tr>
            <th colspan="1">Id</th>
            <th colspan="1">имя</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="friends" items="${findAllMyFriendRequestsSent}">
            <tr>
                <td>${findAllMyFriendRequestsSent}</td>
                <td>${findAllMyFriendRequestsSent}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table border="1" width="30%" cellpadding="5">
        <form action="${pageContext.request.contextPath}/sendFriendRequest" method="post">
            <thead>
            <tr>
                <th colspan="2">Отправить запрос на добавления в друзья</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Введите имя друга</td>
                <td><input type="text" name="userReceiver" value=""/></td>
            </tr>
            <tr>
                <td>Отправить запрос</td>
                <td><input type="submit" value="Отправить запрос"/></td>
            </tr>
            </tbody>
        </form>
    </table>

    <table border="1" width="30%" cellpadding="5">
        <form action="${pageContext.request.contextPath}/acceptFriendRequest" method="post">
            <thead>
            <tr>
                <th colspan="2">Подтвердить запрос от друга</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Введите имя друга</td>
                <td><input type="text" name="userReceiver" value=""/></td>
            </tr>
            <tr>
                <td>Подтвердить запрос</td>
                <td><input type="submit" value="Подтвердить запрос"/></td>
            </tr>
            </tbody>
        </form>
    </table>


</center>


</body>
</html>

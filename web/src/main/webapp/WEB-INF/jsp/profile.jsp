<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%@include file="header.jsp" %>

<center>


    <table border="1" width="50%" cellpadding="5">
        <thead>
        <tr>
            <th colspan="1">Профиль: ${userName}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Обо мне: ${profile.get(0).aboutMe}</td>
        </tr>
        <tr>
            <td>Пол: ${profile.get(0).gender}<br></td>
        </tr>
        <tr>
           <td> Я живу в: ${profile.get(0).homeAddress.country}, ${profile.get(0).homeAddress.city} <br></td>
        </tr>
        <tr>
            <td>Я работаю в: ${profile.get(0).workAddress.country}, ${profile.get(0).homeAddress.city}<br></td>
        </tr>
        <tr>
            <td>Семья и отношения: ${profile.get(0).maritalStatus} <br></td>
        </tr>
        <tr>
            <td>Дата
                рождения:${profile.get(0).birthday.dayOfBirth},${profile.get(0).birthday.monthOfBirth}, ${profile.get(0).birthday.yearOfBirth}<br></td>
        </tr>
        </tbody>
    </table>

    <form action="${pageContext.request.contextPath}/addProfile" method="get">
        <tr>
            <td><input type="submit" value=редактировать профиль></td>
        </tr>
    </form>

</center>


</body>
</html>

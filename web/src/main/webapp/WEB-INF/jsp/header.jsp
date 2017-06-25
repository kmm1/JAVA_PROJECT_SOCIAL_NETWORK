<%@ page contentType="text/html;charset=UTF-8" language="java" %>
Вы зарегистрированы как: <%= session.getAttribute("userName")%>
<li><a href="${pageContext.request.contextPath}/logout">Выйти</a></li>

<nav class="one">
    <ul>
        <li><a href="${pageContext.request.contextPath}/blog">Блог</a></li>
        <li><a href="${pageContext.request.contextPath}/profile">Профайл</a></li>

    </ul>
</nav>

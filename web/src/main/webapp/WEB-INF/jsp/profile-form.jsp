
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>

<center>
    <table border="1" width="30%" cellpadding="5">
        <form action="${pageContext.request.contextPath}/profile" method="post">
            <thead>
            <tr>
                <th colspan="2">Введите информацию</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>дата рождения</td>
                <td><select name="dayOfBirth">
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="24">24</option>
                    <option value="25">25</option>
                    <option value="26">26</option>
                    <option value="27">27</option>
                    <option value="28">28</option>
                    <option value="29">29</option>
                    <option value="30">30</option>
                    <option value="31">31</option>
                </select></td>
            </tr>

            <tr>
                <td>месяц рождения</td>
                <td><select name="monthOfBirth">
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select></td>
            </tr>

            <tr>
            <td>год рождения</td>
                <td><input type="text" name="yearOfBirth" value=""/></td>
            </tr>

            <tr>
                <td>пол</td>
                <td><select name="gender">
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                </select></td>
            </tr>

            <tr>
                <td>Семья и отношения</td>
                <td><select name="maritalStatus">
                    <option value="SINGLE">Single</option>
                    <option value="MARRIED">Married</option>
                    <option value="WIDOWED">Widowed</option>
                    <option value="DIVORCED">Divorced</option>
                </select></td>
            </tr>


            </tbody>
        </form>
    </table>

</center>
</body>
</html>






<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: MamishevDA
  Date: 25.07.2016
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<link href="../../resources/css/css.css" rel="stylesheet" type="text/css">--%>
    <link href="${pageContext.servletContext.contextPath}/resources/css/css.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users</title>
</head>
<body>
<div id="header">
    <jsp:include page="header.jsp" flush="true"/>
</div>
<div id="menu">
    <jsp:include page="leftPage.jsp" flush="true"/>
</div>

<div id="content">${currentUser}
    <table class="mainList" border="1">
        <tr>
            <td><b>Имя пользователя</b></td>
            <td><b>Удалить можно только пользователей без книг!</b></td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><a href="#" class="js-editBtn" data-name="${user.name}">${user.name}</a></td>
                <td><a href="#" class="js-deleteBtn" data-name="${user.name}">удалить пользователя</a></td>
            </tr>
        </c:forEach>
    </table>
    <button style="margin: 14px" id="openAddUserModal">Добавить пользователя
    </button>
</div>
<div id="footer">
    <div class="link">
        Перейти <a href="index">на главную</a> страницу
    </div>
</div>

<div id="addUserModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">x</span>
        <form action="addUser"
              method="POST" accept-charset="UTF-8">
            <div class="main">
                <input type="hidden" name="id" value="0"/>
                <p class="head">Введите информацию о пользователе:</p>
                <div>
                    <div class="list">
                        <div> Логин<br>
                            <input type="text" name="login" value="" readonly="readonly"/>
                        </div>
                        <div>
                            Пароль<br>
                            <input type="text" autofocus="" name="pwd" value=""/>
                        </div>
                    </div>
                </div>
                <input type="submit" value="Добавить пользователя"/>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/javascript/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/javascript/users.js"></script>
</body>
</html>

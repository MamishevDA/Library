<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: MamishevDA
  Date: 24.07.2016
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/css/css.css"/>">--%>
    <link href="${pageContext.servletContext.contextPath}/resources/css/css.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Books</title>
</head>

<body>
<div id="header">
    <jsp:include page="header.jsp" flush="true"/>

</div>
<div id="menu">
    <jsp:include page="leftPage.jsp" flush="true"/>
</div>

<div id="content">
    <div style="margin-left: 14px"> Log in as <b>${currentUser.name}</b><br></div>
    <button style="margin: 14px" id="openAddBookModal">Добавить новую
        книгу
    </button>
    <table class="mainList" border="1" id="booksTable">
        <thead>
        <tr>
            <td><b>ISN</b></td>
            <td><b>Автор</b></td>
            <td><b>Название</b></td>
            <td><b>Кем взята</b></td>
            <td><b>Удалить</b></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="book">
            <tr>
                <td><a href="#" class="js-isnbtn" data-isn="${book.isn}">${book.isn}</a></td>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td><a href="#" class="js-takeBookBtn" data-isn="${book.isn}">${book.user}</a></td>
                <td><a href="#" class="js-deleteBtn" data-isn="${book.isn}">удалить книгу</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <input type="hidden" id="bookCount" value="${fn:length(books)}"/>
    <button style="margin-top: 10px; margin-left: 190px" id="getNextBooks">Получить еще 5
    </button>
</div>
<div id="footer">
    <div class="link">
        Перейти <a href="index">на главную</a> страницу
    </div>
</div>


<div id="addBookModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">x</span>
        <form action="addBook"
              method="POST" accept-charset="UTF-8">
            <div class="main">
                <input type="hidden" name="id" value="0"/>
                <p class="head">Введите информацию о книге:</p>
                <div>
                    <div class="list">
                        <div> Код книги<br>
                            <input type="text" autofocus="" name="isn" value=""/>
                        </div>
                        <div>
                            Автор<br>
                            <input type="text" name="author" value=""/>
                        </div>
                        <div>
                            Название книги<br>
                            <input type="text" name="title" value=""/></div>
                    </div>
                </div>
                <input type="submit" value="Добавить книгу"/>
            </div>
        </form>
    </div>
</div>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/css.css"/>">
<%--<script type="text/javascript" src="<c:url value="/resources/javascript/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/books.js"/>"></script>--%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/javascript/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/javascript/books.js"></script>
</body>
</html>

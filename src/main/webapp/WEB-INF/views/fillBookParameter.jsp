<%--
  Created by IntelliJ IDEA.
  User: MamishevDA
  Date: 26.07.2016
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- <jsp:include page="headerPage.jsp" flush="true" /> --%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/resources/css/css.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8"/>

    <title>добавить книгу</title>
</head>
<body>
<div id="header">
    <jsp:include page="header.jsp" flush="true"/>
</div>
<div id="menu">
    <jsp:include page="leftPage.jsp" flush="true"/>
</div>

<div id="content" style="float:left; width:100%;">
    <form action="addBook"
          method="GET" accept-charset="UTF-8">
        <div class="main">
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
<div id="footer">
    <div class="link">
        Перейти <a href="index">на главную</a> страницу
    </div>
</div>
</body>
</html>

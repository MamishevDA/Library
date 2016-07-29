<%--
  Created by IntelliJ IDEA.
  User: MamishevDA
  Date: 29.07.2016
  Time: 6:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%-- <link href="../../resources/css/css.css" rel="stylesheet" type="text/css">--%>
    <link href="${pageContext.servletContext.contextPath}/resources/css/css.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Поля не заполнены</title>
</head>
<body>
<div id="header">
    <jsp:include page="header.jsp" flush="true"/>
</div>
<div id="menu">
    <jsp:include page="leftPage.jsp" flush="true"/>
</div>

<div id="content">
    ${message}
</div>
<div id="footer">
    <div class="link">
        Перейти <a href="index">на главную</a> страницу
    </div>
</div>

</body>
</html>



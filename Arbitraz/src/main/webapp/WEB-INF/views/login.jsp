<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 16.09.19
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logowanie</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<form:form method="post" modelAttribute="dataLogin">
    <div class="mx-auto" style="width: 500px;">
        <br><H3 class="mx-auto" style="width:200px;">LOGOWANIE</H3><br>
        <div class="input-group flex-nowrap">
            <div class="input-group-prepend">
                <span class="input-group-text" style="min-width: 150px">Email</span>
            </div>
            <form:input path="email" type="email" class="form-control" placeholder="Email" aria-label="Email" aria-describedby="addon-wrapping"></form:input>
        </div>

        <div class="input-group flex-nowrap">
            <div class="input-group-prepend">
                <span class="input-group-text" style="min-width: 150px">Hasło</span>
            </div>
            <form:input path="password" type="password" class="form-control" placeholder="Hasło" aria-label="Hasło" aria-describedby="addon-wrapping"></form:input>
        </div>
        <div>
            <button type="submit" class="btn btn-secondary" style="min-width: 500px">Zaloguj</button>
        </div>
        <form:errors path="*"/>
    </div>
    </form:form>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
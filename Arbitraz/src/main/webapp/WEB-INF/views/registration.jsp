<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 15.09.19
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<form:form method="post" modelAttribute="data"><br>
    <div class="mx-auto" style="width: 500px;">

        <H3 class="mx-auto" style="width:200px;">REJESTRACJA</H3><br>

        <div class="input-group flex-nowrap">
            <div class="input-group-prepend">
                <span class="input-group-text" style="min-width: 150px">Email</span>
            </div>
            <form:input path="email" type="email" class="form-control" placeholder="Email" aria-label="Email" aria-describedby="addon-wrapping"></form:input>
        </div>
        <p>
            <form:errors path="email"/>
        </p>

        <div class="input-group flex-nowrap">
            <div class="input-group-prepend">
                <span class="input-group-text" style="min-width: 150px">Hasło</span>
            </div>
            <form:input path="password" type="password" class="form-control" placeholder="Hasło" aria-label="Hasło" aria-describedby="addon-wrapping"></form:input>
        </div>
        <p>
            <form:errors path="password"/>
        </p>

        <div class="input-group flex-nowrap">
            <div class="input-group-prepend">
                <span class="input-group-text" style="min-width: 150px">Powtórz hasło</span>
            </div>
            <form:input path="rePassword" type="password" class="form-control" placeholder="Powtórz hasło" aria-label="Powtórz hasło" aria-describedby="addon-wrapping"></form:input>
        </div>
        <p>
            <form:errors path="rePassword"/>
        </p>

        <div class="input-group flex-nowrap">
            <div class="input-group-prepend">
                <span class="input-group-text" style="min-width: 150px">Imię</span>
            </div>
            <form:input path="firstName" type="text" class="form-control" placeholder="Imię" aria-label="Imię" aria-describedby="addon-wrapping"></form:input>
        </div>
        <p>
            <form:errors path="firstName"/>
        </p>
        <div class="input-group flex-nowrap">
            <div class="input-group-prepend">
                <span class="input-group-text" style="min-width: 150px">Nazwisko</span>
            </div>
            <form:input path="lastName" type="text" class="form-control" placeholder="Nazwisko" aria-label="Nazwisko" aria-describedby="addon-wrapping"></form:input>
        </div>
        <p>
            <form:errors path="lastName"/>
        </p><br>
        <div>
            <button type="submit" class="btn btn-secondary" style="min-width: 245px">Zarejestruj</button>
            <button type="reset" class="btn btn-secondary" style="min-width: 245px">Wyczyść</button>

        </div>
    </div>
</form:form>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
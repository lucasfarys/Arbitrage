<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 09.10.19
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/" var="mainUrl"/>
<html>
<head>
        <title>Dodaj nową walutę</title>
        <jsp:include page="header.jsp"/>
</head>
<body>
<form>
        <center><br><H3>Dodaj nowy coin</H3></center><br>
        <div class="input-group mb-3 container" style="max-width: 700px;">
                <select class="custom-select" id="exchange">
                        <option selected>Wybierz giełdę</option>
                        <c:forEach var="ex" varStatus="loop" items="${exchanges}">
                                <option value="${ex.id}">${ex.name}</option>
                        </c:forEach>
                </select>
                <select class="custom-select" id="coins">
                    <option selected>Wybierz coin</option>
                    <c:forEach var="c" varStatus="loop" items="${coins}">
                        <option value="${c.id}">${c.coinName}</option>
                    </c:forEach>
                </select>
                <input type="text" class="form-control" id="newCoin" placeholder="Unikalna nazwa">
                <button type="button" class="btn btn-dark" style="min-width: 200px" id="addButton" value="${mainUrl}">Dodaj Unikalny Coin</button>
        </div>
        <div class="container">
            <center><p id="message"></p></center>
        </div>
        <div class="input-group mb-3 container" style="max-width: 700px;">

                <input type="text" class="form-control" id="addNewCoin" placeholder="Nazwa nowego coina">
                <button type="button" class="btn btn-dark" style="min-width: 200px" id="addCoin" value="${mainUrl}">Dodaj Nowy Coin</button>

        </div>
        <div class="container" style="max-width: 600px;">
            <table class="table table-striped" id="coinsList">
                <thead>
                <tr>
                    <td scope="col">Lista aktualnych coinów dla wybranej giełdy</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="coin" items="${coins}" varStatus="loop">
                    <tr style="-ms-text-autospace: ideograph-alpha">
                        <td>${coin.coinName} </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div><br><br>
</form>
</body>
        <jsp:include page="footer.jsp"/>
        <script src="${mainUrl}resources/js/addNewCoin.js"></script>

</html>

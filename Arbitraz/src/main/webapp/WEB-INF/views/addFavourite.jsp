<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 01.10.19
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/" var="mainUrl"/>
<html>
<head>
    <title>Dodaj kurs do ulubionych</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
    <%--<form action="${mainUrl}dashboard/delfavourite"  method="post">--%>
        <center><br><br><H3>Dodaj kurs do ulubionych</H3></center><br><br>
        <div class="input-group mb-3 container" style="max-width: 700px;">
            <select class="custom-select" id="exchangeFirst">
                <option selected>Giełda 1</option>
                <c:forEach var="ex" varStatus="loop" items="${exchange}">
                        <option value="${loop.count}">${ex.name}</option>
                </c:forEach>
            </select>
            <select class="custom-select" id="exchangeSecond">
                <option selected>Giełda 2</option>
                <c:forEach var="ex" varStatus="loop" items="${exchange}">
                    <option value="${loop.count}">${ex.name}</option>
                </c:forEach>
            </select>
            <select class="custom-select" id="course">
                <option selected>Kurs</option>
                <c:forEach var="coin" varStatus="loop" items="${coins}">
                <option value="${loop.count}">${coin.coinName}</option>
                </c:forEach>
            </select>
        </div>
        <center>
            <button type="button" class="btn btn-dark" style="min-width: 200px" id="saveButton" value="${mainUrl}">Dodaj</button>
        </center><br><br>
        <div class="container">
            <center><p id="message"></p></center>
        </div>
        <center>
            <div class="container" style="max-width: 600px;">
                <table class="table table-striped" id="favouriteTable">
                    <thead>
                    <tr>
                        <th scope="col">Nazwa pary walutowej</th>
                        <th scope="col">Usuń</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="favourite" items="${favouriteCoinList}" varStatus="loop">
                        <tr style="-ms-text-autospace: ideograph-alpha">
                            <td>${favourite.exchangeFirst.name} | ${favourite.exchangeSecond.name}
                                 | ${favourite.coin.coinName}</td>
                            <td>
                                <a role="button"  class="btn btn-link" id="dasboardButton"
                                   href="${mainUrl}delfavourite/${favourite.id}">
                                    <i class="fas fa-trash" style="color: grey"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </center><br><br>
    <%--</form>--%>
</body>
    <jsp:include page="footer.jsp"/>
    <script src="${mainUrl}resources/js/addFavourite.js"></script>

</html>

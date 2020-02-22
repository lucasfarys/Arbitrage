<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 11.10.19
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Porównanie wszystkich coinów</title>
        <jsp:include page="header.jsp"/>
</head>
<body>
    <div class="container">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Kup</th>
                <th scope="col">Giełda</th>
                <th scope="col">Cena</th>
                <th scope="col"></th>
                <th scope="col">Sprzedaj</th>
                <th scope="col">Cena</th>
                <th scope="col">Różnica</th>
            </tr>
            </thead>
            <tbody>
                    <c:forEach var="coin" items="${coinList}" varStatus="loop">
                        <tr>
                            <td>${coin.coinName}</td>
                            <td>${coin.nameExOne}</td>
                            <td><fmt:formatNumber maxFractionDigits="8" value="${coin.valueCoinOne}"/></td>
                            <td><i class="fas fa-angle-double-right"></i></td>
                            <td><center>${coin.nameExSecond}</center></td>
                            <td><fmt:formatNumber maxFractionDigits="8" value="${coin.valueCoinSecond}"/></td>
                            <td><fmt:formatNumber maxFractionDigits="2" value="${coin.profit}"/>%</td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
    </div>
</body>
        <jsp:include page="footer.jsp"/>
</html>

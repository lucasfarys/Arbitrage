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
                <th scope="col">Zysk</th>
            </tr>
            </thead>
            <tbody>
                    <c:forEach var="coinName" items="${coinNames}" varStatus="loop">
                        <tr>
                            <td>${coinName}</td>
                            <td>${exchangeNamesFirst.get(loop.count-1)}</td>
                            <td>${dataFirst.get(loop.count-1)}</td>
                            <td><i class="fas fa-angle-double-right"></i></td>
                            <td><center>${exchangeNamesSecond.get(loop.count-1)}</center></td>
                            <td>${dataSecond.get(loop.count-1)}</td>
                            <td>${profit.get(loop.count-1)} %</td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
    </div>
</body>
        <jsp:include page="footer.jsp"/>
</html>

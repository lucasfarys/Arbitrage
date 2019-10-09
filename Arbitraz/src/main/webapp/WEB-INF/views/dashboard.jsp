<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 29.09.19
  Time: 09:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/" var="mainUrl"/>
<html>
<head>
    <title>Chart</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
    <br><div class="input-group mb-3 container" style="max-width: 700px;">
        <select class="custom-select" id="exchangeSelected">
            <option selected>Wybierz parę walutową</option>
            <c:forEach var="ex" varStatus="loop" items="${favourites}">
                <option ask="${loop.count}">${ex}</option>
            </c:forEach>
        </select>
    </div>
    <div class="container" id="chartContainer">
        <canvas id="myChart"></canvas>
    </div>
    <div class="container">
        <canvas id="myChartDifference"></canvas>
    </div><br><br>
</body>
    <jsp:include page="footer.jsp"/>
    <script src="${mainUrl}resources/js/lineChart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</html>

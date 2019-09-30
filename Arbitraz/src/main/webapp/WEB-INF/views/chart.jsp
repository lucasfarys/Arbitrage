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
    <div class="container">
        <%--<script>--%>
            <%--var dataForChart = [--%>
                <%--<c:forEach var="value" items="${btcPln}" varStatus="i">--%>
                <%--&lt;%&ndash;<c:if test="${i<btcPln.size()}">&ndash;%&gt;--%>
                <%--${value} ,--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                <%--</c:forEach>--%>
            <%--]--%>
        <%--</script>--%>
        <canvas id="myChart"></canvas>
    </div>
</body>
    <jsp:include page="footer.jsp"/>
    <script src="${mainUrl}resources/js/lineChart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <link rel="stylesheet" href="${mainUrl}resources/css/css2.css"/>
</html>

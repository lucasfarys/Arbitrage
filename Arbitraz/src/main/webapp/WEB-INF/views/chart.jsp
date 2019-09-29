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
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"/>
    <link rel="stylesheet" href="${mainUrl}resources/css/css2.css"/>
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
        <script src="${mainUrl}resources/js/lineChart.js" type="text/javascript"></script>
        <canvas id="myChart"></canvas>
    </div>
</body>
    <jsp:include page="footer.jsp"/>
</html>

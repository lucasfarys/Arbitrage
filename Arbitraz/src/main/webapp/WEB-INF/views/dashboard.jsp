<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 28.09.19
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panel</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
    <h4>Bitbay / Bittrex</h4>
    <table>
        <tr>
            <td>ask</td>
            <td>bid</td>
            <td>ask</td>
            <td>bid</td>
        </tr>
        <c:forEach var="value" items="${bitbayValue}">
            <tr>
                <td>${value.askETHBTC}</td>
                <td>${value.bidBTCETH}</td>
                <td>${value}</td>
                <td>${value}</td>
            </tr>
        </c:forEach>
    </table>

</body>
<jsp:include page="footer.jsp"></jsp:include>

</html>

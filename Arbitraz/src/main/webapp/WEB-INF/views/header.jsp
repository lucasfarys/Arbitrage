<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 15.09.19
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"/>
<script src="https://kit.fontawesome.com/1f691db00d.js"></script>
<%--<script src="https://code.jquery.com/jquery-3.1.1.slim.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script defer src="https://use.fontawesome.com/releases/v5.7.2/js/all.js" integrity="sha384-0pzryjIRos8mFBWMzSSZApWtPl/5++eIfzYmTgBBmXYdhvxPc+XcFEk+zJwDgWbP" crossorigin="anonymous"></script>
<%--<link rel="stylesheet" href="${mainUrl}resources/css/css.css"/>--%>

<c:url value="/" var="mainUrl"/>

<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${mainUrl}"><i class="fab fa-bitcoin"></i>Arbitraż</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <c:if test="${principal!=null}">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="${mainUrl}dashboard">Panel <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${mainUrl}dashboard/addfavourite">Konfiguracja <span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </c:if>
        <ul class="navbar-nav  ml-auto">
            <c:if test="${principal==null}">
            <li class="nav-item active">
                <a class="nav-link" href="${mainUrl}login">Logowanie <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${mainUrl}register">Rejestracja <span class="sr-only">(current)</span></a>
            </li>
            </c:if>
            <c:if test="${principal!=null}">
                <li class="nav-item active">
                    <a class="nav-link">Witaj ${name}<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link btn-outline-success" href="${mainUrl}logout">Wyloguj<span class="sr-only">(current)</span></a>
                </li>
            </c:if>
        </ul>
    </div>
</nav><br><br>

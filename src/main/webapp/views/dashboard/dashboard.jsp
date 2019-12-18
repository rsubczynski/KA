<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<body>


<div class="app-window">

    <sec:authorize access="hasRole('ROLE_GUEST')">
        <div class="app-window-container">
            <div class="alert alert-warning" role="alert">
                <spring:message code="label.dashboard.guestMessage"/>
            </div>
        </div>
    </sec:authorize>

    <div class="app-window-container">

        <h3><spring:message code="label.dashboard.title"/></h3>
        <br>

        <spring:message code="label.dashboard.content"/>
    </div>

</div>

</body>
</html>

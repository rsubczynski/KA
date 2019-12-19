<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div id="login-box" class="login-page">
    <div class="form">
        <form:form method="post" action="" modelAttribute="accommodationForm">

        <spring:message code="label.firstName" var="firstNameMsg"/>
        <form:input path="firstName" placeholder='${firstNameMsg}'/>
        <c:set var="firstNameHasBindError">
            <form:errors path="firstName"/>
        </c:set>

        <c:if test="${not empty firstNameHasBindError}">
            <div class="error"><form:errors path="firstName"/></div>
        </c:if>

        <spring:message code="label.lastName" var="lastNameMsg"/>
        <form:input path="lastName" placeholder='${lastNameMsg}'/>
        <c:set var="lastNameHasBindError">
            <form:errors path="lastName"/>
        </c:set>

        <c:if test="${not empty lastNameHasBindError}">
            <div class="error"><form:errors path="lastName"/></div>
        </c:if>


        <spring:message code="label.telephone" var="telephoneMsg"/>
        <form:input path="telephone" placeholder='${telephoneMsg}'/>
        <c:set var="telephoneHasBindError">
            <form:errors path="telephone"/>
        </c:set>

        <c:if test="${not empty telephoneHasBindError}">
            <div class="error"><form:errors path="telephone"/></div>
        </c:if>


        <spring:message code="label.secretCode" var="secretCodeMsg"/>
        <form:input path="secretCode" placeholder='${secretCodeMsg}'/>
        <c:set var="secretCodeHasBindError">
            <form:errors path="secretCode"/>
        </c:set>

        <c:if test="${not empty secretCodeHasBindError}">
            <div class="error"><form:errors path="secretCode"/></div>
        </c:if>


        <button name="submit" type="submit" value="submit"><spring:message code="label.addAppUser"/></button>
    </div>
</div>
</form:form>
</body>
</html>

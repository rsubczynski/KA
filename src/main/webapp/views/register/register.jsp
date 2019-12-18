<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>App user page</title>
    <script src="https://www.google.com/recaptcha/api.js"></script>
</head>

<body onload='document.registerForm.username.focus();'>

<div id="login-box" class="login-page">
    <div class="form">

        <h3><spring:message code="label.register"/></h3>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>


        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

        <form:form name='registerForm' action="/register" method='POST' modelAttribute="registerForm">
            <c:set var="emailHasBindError">
                <form:errors path="email"/>
            </c:set>

            <c:set var="passwordHasBindError">
                <form:errors path="password"/>
            </c:set>

            <form:input type='text' name='email' value='' placeholder="Email" path="email"/>
            <c:if test="${not empty emailHasBindError}">
                <div class="error">${emailHasBindError}</div>
            </c:if>

            <form:input type='password' name='password' path="password" placeholder="Password"/>

            <c:if test="${not empty passwordHasBindError}">
                <div class="error">${passwordHasBindError}</div>
            </c:if>


            <div class="text-center">
                <div class="g-recaptcha" data-sitekey="6Lfql8YUAAAAANflaGrNYMQVcRpL4fZ9QCOVUD03"></div>
            </div>
            <c:if test="${not empty captchaError}">
                <br>
                <div class="error"><spring:message code="error.invalid.recaptchaCode"/></div>
            </c:if>
            <br>


            <button name="submit" type="submit" value="submit"><spring:message code="label.register"/></button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <p class="message"><a href="${pageContext.request.contextPath}/login"><spring:message
                    code="label.backToLoginPage"/></a></p>
        </form:form>
    </div>
</div>
</body>
</html>

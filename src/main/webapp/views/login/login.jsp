<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<body onload='document.loginForm.username.focus();'>

<div id="login-box" class="login-page">
    <div class="form">

        <h3><spring:message code="label.login"/></h3>

        <c:if test="${not empty error}">
            <div class="error"><spring:message code="label.errorMessage"/></div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg"><spring:message code="label.logoutMessage"/></div>
        </c:if>

        <c:if test="${not empty accountActive}">
            <div class="alert alert-warning" role="alert"><spring:message code="label.accountActive"/></div>
        </c:if>

        <c:if test="${not empty accountNotActive}">
            <div class="alert alert-warning" role="alert">Zjebales</div>
        </c:if>


        <form name='loginForm' action="<c:url value='/login'/>" method='POST'>


            <input type='text' name='login' value='' placeholder="Email"/>
            <input type='password' name='password' placeholder=<spring:message code="label.password"/>>
            <button name="submit" type="submit" value="submit"><spring:message code="label.login"/></button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <p class="message"><spring:message code="label.createAccountMessage1"/> <a href="/register"><spring:message code="label.createAccountMessage2"/></a></p>
        </form>
    </div>
</div>
</body>
</html>

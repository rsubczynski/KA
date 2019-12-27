<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.min.css" rel="stylesheet">
<link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
    <div class="navbar-collapse">

        <a class="navbar-brand" href="/dashboard">OSIEDLE</a>

        <ul class="navbar-nav ml-auto">

            <sec:authorize access="hasRole('ROLE_USER')">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="user" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><spring:message code="label.user"/></a>
                    <div class="dropdown-menu" aria-labelledby="user">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/user/data"><spring:message code="label.userData"/></a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/user/meter"><spring:message code="label.meterReading"/></a>
                    </div>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_SUPER_USER')">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="superUser" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><spring:message code="label.superUser"/></a>
                    <div class="dropdown-menu" aria-labelledby="superUser">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/superUser/serviceFees"><spring:message code="serviceFees.title"/></a>
                    </div>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="admin" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><spring:message code="label.admin"/></a>
                    <div class="dropdown-menu" aria-labelledby="admin">
                        <a class="dropdown-item" href="#">TO DO option1</a>
                        <a class="dropdown-item" href="#">TO DO option1</a>
                        <a class="dropdown-item" href="#">TO DO option1</a>
                    </div>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_GUEST')">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="unknown-user" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><spring:message code="label.guest"/></a>
                    <div class="dropdown-menu" aria-labelledby="unknown-user">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/accommodation"><spring:message code="label.accommodation"/></a>
                    </div>
                </li>
            </sec:authorize>
        </ul>
    </div>

    <div class="navbar-collapse collapse w-100 order-3 dual-collapse0">
        <ul class="navbar-nav ml-auto">
            <sec:authorize access="isAnonymous()">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown09" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false"><spring:message code="label.language"/></a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdown09">
                    <a class="dropdown-item" href="?lang=pl"><span class="flag-icon flag-icon-pl"></span>  <spring:message code="label.languagePl"/></a>
                    <a class="dropdown-item" href="?lang=en"><span class="flag-icon flag-icon-gb"></span>  <spring:message code="label.languageGB"/></a>
                    <a class="dropdown-item" href="?lang=de"><span class="flag-icon flag-icon-de"></span>  <spring:message code="label.languageDE"/></a>
                </div>
            </li>
            </sec:authorize>
            <sec:authorize access="!isAnonymous()">
                <li class="nav-item">
                    <a class="nav-link" href="javascript:formSubmit()"><spring:message code="label.logout"/></a>
                </li>
            </sec:authorize>
        </ul>
    </div>
</nav>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<!-- csrf for log out-->
<form action="/logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</div>

<div class="header">
    <span><spring:message code="label.title"/></span
</div>



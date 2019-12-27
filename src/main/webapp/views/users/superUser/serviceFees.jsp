<jsp:useBean id="block" scope="request" type="pl.dmcs.rkotas.domain.Block"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
      integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="app-window">

    <c:if test="${not empty pageError}">
        <div class="error"><spring:message code="label.page.error"/></div>

    </c:if>

    <c:if test="${empty pageError}">

        <div class="app-window-container">
            <h1><spring:message code="label.blockAddress"/></h1>
            <br>
            <table class="table col-xs-1 text-center">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="label.city"/></th>
                    <th scope="col"><spring:message code="label.street"/></th>
                    <th scope="col"><spring:message code="label.buildNumber"/></th>
                    <th scope="col"><spring:message code="label.postalCode"/></th>
                    <th scope="col"><spring:message code="label.country"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${block.blockAddress.city}</td>
                    <td>${block.blockAddress.street}</td>
                    <td>${block.blockAddress.blockNumber}</td>
                    <td>${block.blockAddress.postalCode}</td>
                    <td>${block.blockAddress.country}</td>
                </tr>
                </tbody>
            </table>

            <nav id="payment" aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:if test="${not empty previouslyPage}">
                        <li class="page-item"><a class="page-link"
                                                 href="/superUser/serviceFees?page=${previouslyPage}">
                            <spring:message code="button.prev"/></a></li>
                    </c:if>

                    <c:if test="${not empty nextPage}">
                        <li class="page-item"><a class="page-link" href="/superUser/serviceFees?page=${nextPage}">
                            <spring:message code="button.next"/></a></li>
                    </c:if>
                </ul>
            </nav>

        </div>

        <div class="app-window-container">
            <body>
            <jsp:useBean id="flats" scope="request" type="java.util.List"/>

            <table class="table col-xs-1 text-center">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="label.flatData"/></th>
                    <th scope="col"><spring:message code="label.flatArea"/></th>
                    <th scope="col"><spring:message code="label.secretCode"/></th>
                    <th scope="col"><spring:message code="label.user"/></th>
                    <th scope="col"><spring:message code="label.charges"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${flats}" var="flat">

                    <c:forEach items="${flat.bills}" var="bill">
                        <c:if test="${bill.paymentStatus.name() == 'WAITING'}">
                            <c:set var="isBill" value="${true}"/>
                        </c:if>
                    </c:forEach>

                    <tr class="${isBill ? "alert alert-info" : ""}">
                        <c:set var="isBill" value="${false}"/>
                        <td>${flat.localeNumber}</td>
                        <td>${flat.flatArea}</td>
                        <td>${flat.secretCode}</td>

                        <jsp:useBean id="userDataService" scope="request"
                                     type="pl.dmcs.rkotas.service.AppUserDataService"/>
                        <c:if test="${userDataService.findByFlatId(flat.id) !=null}">

                            <td>
                                <p>
                                    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample"
                                       role="button" aria-expanded="false" aria-controls="collapseExample">
                                        <spring:message code="serviceFees.button.show"/>
                                    </a>
                                </p>
                                <div class="collapse" id="collapseExample">
                                    <div class="card card-body">
                                        <c:set var="userData" value="${userDataService.findByFlatId(flat.id)}"/>
                                        <spring:message code="label.firstName"/>: ${userData.firstName} <br>
                                        <spring:message code="label.lastName"/>: ${userData.lastName} <br>
                                        <spring:message code="label.telephone"/>: ${userData.phoneNumber} <br>
                                    </div>
                                </div>

                            </td>
                        </c:if>
                        <c:if test="${userDataService.findByFlatId(flat.id) == null}">
                            <td>
                                <button class="btn btn-success" disabled="true">
                                    <spring:message code="serviceFees.button.free"/></button>
                            </td>
                        </c:if>
                        <td>
                            <a href="${pageContext.request.contextPath}/superUser/payment?flat=${flat.id}">
                                <button class="btn btn-primary">
                                    <spring:message code="label.charges"/></button>
                            </a>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            </body>
        </div>

    </c:if>
</div>

</body>
</html>


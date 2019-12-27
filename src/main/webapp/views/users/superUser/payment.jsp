<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>

</style>

<body>
<div class="app-window">
    <div class="app-window-container">

        <table class="table col-xs-1 text-center">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col"><spring:message code="label.charges"/></th>
                <th scope="col"><spring:message code="label.charges.rate"/></th>
                <th scope="col"><spring:message code="label.charges.count"/></th>
                <th scope="col"><spring:message code="label.charges.price"/></th>
            </tr>
            </thead>

            <jsp:useBean id="bills" scope="request" type="java.util.List"/>
            <tbody>

            <c:forEach items="${bills}" var="bill">
                <jsp:useBean id="formatter" scope="request" type="pl.dmcs.rkotas.util.AppDataFormatter"/>

                <c:if test="${bill.paymentStatus.name() == 'ACCEPTED'}">
                    <c:set var="css" value="${'alert alert-success'}"/>
                    <spring:message code="paymentStatus.accepted" var="msg"/>
                </c:if>
                <c:if test="${bill.paymentStatus.name() == 'WAITING'}">
                    <c:set var="css" value="${'alert alert-warning'}"/>
                    <spring:message code="paymentStatus.waiting" var="msg"/>
                </c:if>
                <c:if test="${bill.paymentStatus.name() == 'REJECTED'}">
                    <c:set var="css" value="${'alert alert-danger'}"/>
                    <spring:message code="paymentStatus.rejected" var="msg"/>
                </c:if>
                <c:if test="${bill.paymentStatus.name() == 'NEW'}">
                    <c:set var="css" value="${'alert alert-info'}"/>
                    <spring:message code="paymentStatus.new" var="msg"/>
                </c:if>

                <tr><td></td></tr>

                <tr class="${css}">
                    <th scope="row"> <spring:message code="label.charges.data"/>: ${formatter.getFormattedDate(bill.localeData)}</th>
                    <td><strong>${msg}</strong></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>

                <tr class="${css}">
                    <th scope="row">1</th>
                    <td><spring:message code="label.charges.coldWater"/></td>
                    <td>${bill.coldWater.rate} [m<sup>3</sup>]</td>
                    <td>${bill.coldWater.count}</td>
                    <td>${bill.coldWater.price}</td>
                </tr>
                <tr class="${css}">
                    <th scope="row">2</th>
                    <td><spring:message code="label.charges.hotWater"/></td>
                    <td>${bill.hotWater.rate} [m<sup>3</sup>]</td>
                    <td>${bill.hotWater.count}</td>
                    <td>${bill.hotWater.price}</td>
                </tr>
                <tr class="${css}">
                    <th scope="row">3</th>
                    <td><spring:message code="label.charges.electric"/></td>
                    <td>${bill.electricityList.rate} [kW/h]</td>
                    <td>${bill.electricityList.count}</td>
                    <td>${bill.electricityList.price}</td>
                </tr>
                <tr class="${css}">
                    <th scope="row">4</th>
                    <td><spring:message code="label.charges.heating"/></td>
                    <td>${bill.heating.rate} [kW/h]</td>
                    <td>${bill.heating.count}</td>
                    <td>${bill.heating.price}</td>
                </tr>
                <tr class="${css}">
                    <th scope="row">5</th>
                    <td><spring:message code="label.charges.repairFund"/></td>
                    <td>${bill.repairFund.rate} zł</td>
                    <td>${bill.repairFund.count}</td>
                    <td>${bill.repairFund.price}</td>
                </tr>
                <c:if test="${bill.paymentStatus.name() == 'WAITING'}">
                    <tr class="${css}">
                        <th scope="row"></th>
                        <td></td>
                        <td></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/superUser/acceptedBill?bill=${bill.id}">
                                <button class="btn btn-success">
                                    <spring:message code="button.accept"/></button>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/superUser/rejectedBill?bill=${bill.id}">
                                <button class="btn btn-danger">
                                    <spring:message code="button.rejected"/></button>
                            </a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

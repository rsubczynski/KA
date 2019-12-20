<jsp:useBean id="rates" scope="request" type="pl.dmcs.rkotas.domain.Rates"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>

<div class="app-window">

    <div class="app-window-container">
        <h1><spring:message code="label.meterReading"/></h1>
    </div>
    <%--@elvariable id="meterForm" type="pl.dmcs.rkotas.dto.MeterForm"--%>
    <form:form method="post" action="/user/meter" modelAttribute="meterForm">
    <div class="app-window-container">
        <br>
        <table class="table col-xs-1">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col"><spring:message code="label.charges"/></th>
                <th scope="col"><spring:message code="label.charges.rate"/></th>
                <th scope="col"><spring:message code="label.charges.count"/></th>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td><spring:message code="label.charges.coldWater"/></td>
                <td>${rates.coldWaterRate} [m<sup>3</sup>]</td>
                <td><form:input type='text' path="coldWater"/></td>
                <td><form:errors path="coldWater" cssClass="error-table"/></td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td><spring:message code="label.charges.hotWater"/></td>
                <td>${rates.hotWaterRate} [m<sup>3</sup>]</sup></td>
                <td><form:input type='text' path="hotWater"/></td>
                <td><form:errors path="hotWater" cssClass="error-table"/></td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td><spring:message code="label.charges.electric"/></td>
                <td>${rates.electricityRate} [kW/h]</td>
                <td><form:input type='text' path="electricity"/></td>
                <td><form:errors path="electricity" cssClass="error-table"/></td>
            </tr>
            <tr>
                <th scope="row">4</th>
                <td><spring:message code="label.charges.heating"/></td>
                <td>${rates.heatingRate} [kW/h]</td>
                <td><form:input type='text' path="heating"/></td>
                <td><form:errors path="heating" cssClass="error-table"/></td>
            </tr>
            <tr>
                <th scope="row">5</th>
                <td><spring:message code="label.charges.repairFund"/></td>
                <td>${rates.repairFundRate} zł</td>
                <td>1</td>
                <td></td>
            </tr>
            </tbody>
        </table>

        <button class="btn btn-primary"> Dodaj</button>
        </form:form>
        <br>
        <br>
        <div class="alert alert-info">
            Tabla opłat z dnia <i>${date}</i>
        </div>

    </div>
</div>

</body>
</html>
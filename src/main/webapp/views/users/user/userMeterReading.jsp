<jsp:useBean id="rates" scope="request" type="pl.dmcs.rkotas.domain.Rates"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>

<div class="app-window">

    <div class="app-window-container">
        <h1><spring:message code="label.meterReading"/></h1>
    </div>

    <div class="app-window-container">
        <br>
        <table class="table col-xs-1">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col"><spring:message code="label.charges"/></th>
                <th scope="col"><spring:message code="label.charges.rate"/></th>
                <th scope="col"><spring:message code="label.charges.count"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td><spring:message code="label.charges.coldWater"/></td>
                <td>${rates.coldWaterRate} [m<sup>3</sup>]</td>
                <td><input></td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td><spring:message code="label.charges.hotWater"/></td>
                <td>${rates.hotWaterRate} [m<sup>3</sup>]</sup></td>
                <td><input></td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td><spring:message code="label.charges.electric"/></td>
                <td>${rates.electricityRate} [kW/h]</td>
                <td><input></td>
            </tr>
            <tr>
                <th scope="row">4</th>
                <td><spring:message code="label.charges.heating"/></td>
                <td>${rates.heatingRate} [kW/h]</td>
                <td><input></td>
            </tr>
            <tr>
                <th scope="row">5</th>
                <td><spring:message code="label.charges.repairFund"/></td>
                <td>${rates.repairFundRate} zł</td>
                <td><input></td>
            </tr>

            <tr>
                <th></th>
                <td></td>
                <td></td>

            </tr>

            </tbody>
        </table>

        <button class="btn btn-primary"> Dodaj</button>
        <br>
        <br>
        <div class="btn btn-info">
            Tabla opłat z dnia <i>${date}</i>
        </div>

    </div>
</div>

</body>
</html>
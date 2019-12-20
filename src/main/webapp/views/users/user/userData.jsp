<jsp:useBean id="bill" scope="request" type="pl.dmcs.rkotas.domain.Bill"/>
<jsp:useBean id="user" scope="request" type="pl.dmcs.rkotas.domain.AppUser"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<body>
<div class="app-window">

    <div class="app-window-container">
        <h1><spring:message code="label.userData"/></h1>
    </div>

    <div class="app-window-container">
        <h1><spring:message code="label.yourData"/></h1>
        <br>
        <table class="table col-xs-1 text-center">
            <thead>
            <tr>
                <th scope="col"><spring:message code="label.email"/></th>
                <th scope="col"><spring:message code="label.firstName"/></th>
                <th scope="col"><spring:message code="label.lastName"/></th>
                <th scope="col"><spring:message code="label.telephone"/></th>
                <th scope="col"><spring:message code="label.editUserData"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${user.email}</td>
                <td> ${user.userData.firstName}</td>
                <td>${user.userData.lastName}</td>
                <td>${user.userData.phoneNumber}</td>
                <td>
                    <form action="/user/edit">
                    <button class="btn btn-primary" type="submit">
                        <spring:message code="button.editUserData"/></button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>

    </div>

    <div class="app-window-container">

        <h1><spring:message code="label.flatData"/></h1>
        <br>
        <table class="table col-xs-1 text-center">
            <thead>
            <tr>
                <th scope="col"><spring:message code="label.flatArea"/></th>
                <th scope="col"><spring:message code="label.localNumber"/></th>
                <th scope="col"><spring:message code="label.street"/></th>
                <th scope="col"><spring:message code="label.postalCode"/></th>
                <th scope="col"><spring:message code="label.city"/></th>
                <th scope="col"><spring:message code="label.country"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${user.userData.flat.flatArea} m2</td>
                <td>${user.userData.flat.localeNumber}</td>
                <td>${user.userData.flat.blockAddress.street}</td>
                <td>${user.userData.flat.blockAddress.postalCode}</td>
                <td>${user.userData.flat.blockAddress.city}</td>
                <td>${user.userData.flat.blockAddress.country}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="app-window-container">

    <c:if test="${not empty pageError}">
        <div class="error"><spring:message code="label.page.error"/></div>

    </c:if>

    <c:if test="${empty pageError}">

        <h1><spring:message code="label.charges"/></h1>
        <h6><spring:message code="label.charges.data"/> : ${data}</h6>

        <br>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col"><spring:message code="label.charges"/></th>
                <th scope="col"><spring:message code="label.charges.rate"/></th>
                <th scope="col"><spring:message code="label.charges.count"/></th>
                <th scope="col"><spring:message code="label.charges.price"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td><spring:message code="label.charges.coldWater"/></td>
                <td>${bill.coldWater.rate}</td>
                <td>${bill.coldWater.count}</td>
                <td>${bill.coldWater.price}</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td><spring:message code="label.charges.hotWater"/></td>
                <td>${bill.hotWater.rate}</td>
                <td>${bill.hotWater.count}</td>
                <td>${bill.hotWater.price}</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td><spring:message code="label.charges.electric"/></td>
                <td>${bill.electricityList.rate}</td>
                <td>${bill.electricityList.count}</td>
                <td>${bill.electricityList.price}</td>
            </tr>
            <tr>
                <th scope="row">4</th>
                <td><spring:message code="label.charges.heating"/></td>
                <td>${bill.heating.rate}</td>
                <td>${bill.heating.count}</td>
                <td>${bill.heating.price}</td>
            </tr>
            <tr>
                <th scope="row">5</th>
                <td><spring:message code="label.charges.repairFund"/></td>
                <td>${bill.repairFund.rate}</td>
                <td>${bill.repairFund.count}</td>
                <td>${bill.repairFund.price}</td>
            </tr>

            <tr>
                <th scope="row">6</th>
                <td><spring:message code="label.charges.sum"/></td>
                <td></td>
                <td></td>
                <td>${bill.totalCount}</td>
            </tr>


            <c:if test="${bill.totalCount>0}">
                <tr>
                    <th scope="row">7</th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <button class="btn btn-primary" type="button"><spring:message code="button.pay"/></button>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>

        <nav id="payment" aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${not empty previouslyPage}">
                    <li class="page-item"><a class="page-link"
                                             href="/user/data?page=${previouslyPage}#payment">
                        <spring:message code="button.prev"/></a></li>
                </c:if>

                <c:if test="${not empty nextPage}">
                    <li class="page-item"><a class="page-link" href="/user/data?page=${nextPage}#payment">
                        <spring:message code="button.next"/></a></li>
                </c:if>
            </ul>
        </nav>
    </div>
    </c:if>

</div>

</div>


</body>
</html>
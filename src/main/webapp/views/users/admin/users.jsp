<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="app-window">
    <div class="app-window-container">
        <h3><spring:message code="label.users"/></h3>
        <br>
        <div id="accordion">

            <div class="card">
                <div class="card-header" id="headingOne">
                    <h5 class="mb-0">
                        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne"
                                aria-expanded="true" aria-controls="collapseOne">
                            <spring:message code="label.guest"/>
                        </button>
                    </h5>
                </div>

                <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col"><spring:message code="label.email"/></th>
                                    <th scope="col"><spring:message code="label.active"/></th>
                                    <th scope="col">Uid</th>
                                    <th scope="col"><spring:message code="label.secretCode"/></th>
                                    <th scope="col"><spring:message code="label.userEdit.title"/></th>
                                    <th scope="col"><spring:message code="label.delete"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${guestList}" var="user">
                                    <tr>
                                        <td>${user.email}</td>
                                        <td>${user.enable}</td>
                                        <td>${user.uidUser}</td>
                                        <td>${user.secretFlatCode}</td>
                                        <td>
                                            <button class="btn btn-primary">Edit</button>
                                        </td>
                                        <td>
                                            <button class="btn btn-danger">Usuń</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingTwo">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo"
                                aria-expanded="false" aria-controls="collapseTwo">
                            <spring:message code="label.user"/>
                        </button>
                    </h5>
                </div>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col"><spring:message code="label.email"/></th>
                                    <th scope="col"><spring:message code="label.active"/></th>
                                    <th scope="col">Uid</th>
                                    <th scope="col"><spring:message code="label.secretCode"/></th>
                                    <th scope="col"><spring:message code="label.firstName"/></th>
                                    <th scope="col"><spring:message code="label.lastName"/></th>
                                    <th scope="col"><spring:message code="label.telephone"/></th>
                                    <th scope="col"><spring:message code="label.userEdit.title"/></th>
                                    <th scope="col"><spring:message code="label.delete"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userList}" var="user">
                                    <tr>
                                        <td>${user.email}</td>
                                        <td>${user.enable}</td>
                                        <td>${user.uidUser}</td>
                                        <td>${user.secretFlatCode}</td>
                                        <td>${user.userData.firstName}</td>
                                        <td>${user.userData.lastName}</td>
                                        <td>${user.userData.phoneNumber}</td>
                                        <td>
                                            <button class="btn btn-primary">Edit</button>
                                        </td>
                                        <td>
                                            <button class="btn btn-danger">Usuń</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingThree">
                    <h5 class="mb-0">
                        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree"
                                aria-expanded="false" aria-controls="collapseThree">
                            <spring:message code="label.superUser"/>
                        </button>
                    </h5>
                </div>
                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col"><spring:message code="label.email"/></th>
                                    <th scope="col"><spring:message code="label.active"/></th>
                                    <th scope="col"><spring:message code="label.firstName"/></th>
                                    <th scope="col"><spring:message code="label.lastName"/></th>
                                    <th scope="col"><spring:message code="label.telephone"/></th>
                                    <th scope="col"><spring:message code="label.block"/></th>
                                    <th scope="col"><spring:message code="label.userEdit.title"/></th>
                                    <th scope="col"><spring:message code="label.delete"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${superUserList}" var="user">
                                    <tr>
                                        <td>${user.email}</td>
                                        <td>${user.enable}</td>
                                        <td>${user.userData.firstName}</td>
                                        <td>${user.userData.lastName}</td>
                                        <td>${user.userData.phoneNumber}</td>
                                        <td>
                                            <p>
                                                <a class="btn btn-primary" data-toggle="collapse"
                                                   href="#collapseExample"
                                                   role="button" aria-expanded="false" aria-controls="collapseExample">
                                                    <spring:message code="serviceFees.button.show"/>
                                                </a>
                                            </p>
                                            <div class="collapse" id="collapseExample">
                                                <div class="card card-body">
                                                    <jsp:useBean id="blockService" scope="request"
                                                                 type="pl.dmcs.rkotas.service.BlockService"/>
                                                    <c:if test="${blockService.findAllByAdministratorId(user.id) !=null}">
                                                        <c:forEach
                                                                items="${blockService.findAllByAdministratorId(user.id)}"
                                                                var="block">
                                                            <strong><spring:message code="label.block"/></strong><br>
                                                            <spring:message
                                                                    code="label.city"/>: ${block.blockAddress.city} <br>
                                                            <spring:message
                                                                    code="label.street"/>: ${block.blockAddress.street}
                                                            <br>
                                                            <spring:message
                                                                    code="label.blockNumber"/>: ${block.blockAddress.blockNumber}
                                                            <br>
                                                            <spring:message
                                                                    code="label.postalCode"/>: ${block.blockAddress.postalCode}
                                                            <br>
                                                            <spring:message
                                                                    code="label.country"/>:  ${block.blockAddress.country}
                                                            <br>
                                                            <br>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                            </div>

                                        </td>
                                        <td>
                                            <button class="btn btn-primary">Edit</button>
                                        </td>
                                        <td>
                                            <button class="btn btn-danger">Usuń</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingFour">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#CollapseFour"
                                    aria-expanded="false" aria-controls="CollapseFour">
                                <spring:message code="label.admin"/>
                            </button>
                        </h5>
                    </div>
                    <div id="CollapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col"><spring:message code="label.email"/></th>
                                        <th scope="col"><spring:message code="label.active"/></th>
                                        <th scope="col"><spring:message code="label.firstName"/></th>
                                        <th scope="col"><spring:message code="label.lastName"/></th>
                                        <th scope="col"><spring:message code="label.telephone"/></th>
                                        <th scope="col"><spring:message code="label.delete"/></th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${adminList}" var="user">
                                        <tr>
                                            <td>${user.email}</td>
                                            <td>${user.enable}</td>
                                            <td>${user.userData.firstName}</td>
                                            <td>${user.userData.lastName}</td>
                                            <td>${user.userData.phoneNumber}</td>
                                            <td>
                                                <button class="btn btn-primary">Edit</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

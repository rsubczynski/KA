<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
<h1><spring:message code="label.appUser"/></h1>
<form:form method="post" action="addAppUser.html" modelAttribute="appUser">
    <table>
        <tr>
            <td><form:hidden path="id"/>
        </tr>
        <tr>
            <td><form:label path="login"><spring:message code="label.login"/></form:label></td>
            <td><form:input path="login"/></td>
            <td><form:errors path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
            <td><form:input type="password" path="password" /></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="enabled"><spring:message code="label.enabled"/></form:label></td>
            <td><form:checkbox path="enabled" /></td>
            <td><form:errors path="enabled"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName"><spring:message code="label.firstName"/></form:label></td>
            <td><form:input path="firstName" /></td>
            <td><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName"><spring:message code="label.lastName"/></form:label></td>
            <td><form:input path="lastName" /></td>
            <td><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
            <td><form:input path="telephone" /></td>
            <td><form:errors path="telephone"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${appUser.id==0}">
                    <input type="submit" value="<spring:message code="label.addAppUser"/>"/>
                </c:if>
                <c:if test="${appUser.id!=0}">
                    <input type="submit" value="<spring:message code="label.editAppUser"/>"/>
                </c:if>
            </td>
        </tr>
    </table>

    <h3><spring:message code="label.userList"/></h3>
    <c:if  test="${!empty appUserList}">
        <table class="data">
            <tr>
                <th><spring:message code="label.firstName"/></th>
                <th><spring:message code="label.lastName"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.telephone"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${appUserList}" var="appUser">
                <tr>
                    <td>${appUser.firstName} </td>
                    <td>${appUser.lastName} </td>
                    <td>${appUser.email}</td>
                    <td>${appUser.telephone}</td>
                    <td><a href="delete/${appUser.id}.html">delete</a></td>
                    <td><a href="appUsers.html?appUserId=${appUser.id}">edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</form:form>
</body>
</html>


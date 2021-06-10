<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="Admin panel" showMenu="false">
    <div class="row mb-3 mx-3">
        <common:back/>
    </div>
    <div class="row justify-content-center font-italic mb-3">
        Found <c:out value="${users.totalElements}"/> results!
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Location</th>
            <th scope="col">Date Registered</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users.content}" varStatus="status">
            <tr>
                <td><c:out value="${status.count}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.location}"/></td>
                <td><c:out value="${user.registrationDate}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <util:pagination basePath="/admin" page="${users}"/>
</common:page>

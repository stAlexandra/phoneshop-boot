<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="false" type="java.lang.String" %>
<%@ attribute name="sort" required="false" type="java.lang.String" %>
<%@ attribute name="var" required="true" type="java.lang.String" rtexprvalue="false" %>
<%@ variable alias="url" name-from-attribute="var" scope="AT_BEGIN" variable-class="java.lang.String"%>

<c:url var="url" value="/phones">
    <c:if test="${not empty page}">
        <c:param name="page" value="${page}"/>
    </c:if>
    <c:choose>
        <c:when test="${not empty sort}">
            <c:param name="sort" value="${sort}"/>
        </c:when>
        <c:when test="${not empty param.sort}">
            <c:param name="sort" value="${param.sort}"/>
        </c:when>
    </c:choose>
    <c:if test="${not empty param.query}">
        <c:param name="query" value="${param.query}"/>
    </c:if>
    <c:if test="${not empty param.fromPrice}">
        <c:param name="fromPrice" value="${param.fromPrice}"/>
    </c:if>
    <c:if test="${not empty param.toPrice}">
        <c:param name="toPrice" value="${param.toPrice}"/>
    </c:if>
</c:url>
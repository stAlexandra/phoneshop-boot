<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ attribute name="field" required="true" type="java.lang.String" %>
<c:set var="upperArrow" value="&#x25B2;"/>
<c:set var="downArrow" value="&#x25BC;"/>
<c:set var="sortAsc" value="${field},asc"/>
<c:set var="sortDesc" value="${field},desc"/>
<util:pageUrl basePath="/phones" var="sortAscUrl" sort="${sortAsc}"/>
<util:pageUrl basePath="/phones" var="sortDescUrl" sort="${sortDesc}"/>
<c:choose>
    <c:when test="${sortAsc eq param.sort}">
        <a href="#" class="btn-link disabled" tabindex="-1" aria-disabled="true"><c:out value="${upperArrow}" escapeXml="false"/></a>
    </c:when>
    <c:otherwise>
        <a href="${sortAscUrl}"><c:out value="${upperArrow}" escapeXml="false"/></a>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${sortDesc eq param.sort}">
        <a href="#" class="btn-link disabled" tabindex="-1" aria-disabled="true"><c:out value="${downArrow}" escapeXml="false"/></a>
    </c:when>
    <c:otherwise>
        <a href="${sortDescUrl}"><c:out value="${downArrow}" escapeXml="false"/></a>
    </c:otherwise>
</c:choose>
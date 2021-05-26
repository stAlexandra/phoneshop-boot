<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="basePath" required="true" type="java.lang.String" %>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="maxPages" required="false" type="java.lang.Integer" %>

<c:if test="${not empty page}">
  <c:url var="prevPageUrl" value="${basePath}">
    <c:param name="page" value="${page.previousOrFirstPageable().getPageNumber()}"/>
  </c:url>
  <c:url var="nextPageUrl" value="${basePath}">
    <c:param name="page" value="${page.nextOrLastPageable().getPageNumber()}"/>
  </c:url>
  <c:choose>
    <c:when test="${not empty maxPages}">    
      <c:set var="pagesToDisplay" value="${page.totalPages < maxPages ? page.totalPages : maxPages}"/>
    </c:when>
    <c:otherwise>
      <c:set var="pagesToDisplay" value="${page.totalPages}"/>
    </c:otherwise>
  </c:choose>
  
  <nav>
    <ul class="pagination justify-content-center">
      <li class="page-item ${page.hasPrevious() ? '' : 'disabled'}">
        <a class="page-link" href="${prevPageUrl}" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
          <span class="sr-only">Previous</span>
        </a>
      </li>
      <c:forEach begin="0" end="${pagesToDisplay - 1}" varStatus="status">
        <c:url var="pageUrl" value="${basePath}">
          <c:param name="page" value="${status.index}"/>
        </c:url>
        <li class="page-item ${page.getNumber() == status.index ? 'active' : ''}">
          <a class="page-link" href="${pageUrl}">${status.count}</a>
        </li>
      </c:forEach>
      <li class="page-item ${page.hasNext() ? '' : 'disabled'}">
        <a class="page-link" href="${nextPageUrl}" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
          <span class="sr-only">Next</span>
        </a>
      </li>
    </ul>
  </nav>
</c:if>
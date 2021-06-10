<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-body">
        <form>
        <div class="row mb-3">
            <label for="queryInput" class="col-sm-3 col-form-label">Search query:</label>
            <div class="col-sm-9">
            <input type="search" class="form-control" id="queryInput" name="query" value="${param.query}">
            </div>
        </div>
        <div class="row mb-3">
            <label for="fromPriceInput" class="col-sm-3 col-form-label">From price:</label>
            <div class="col-sm-9">
            <input type="text" class="form-control" id="fromPriceInput" name="fromPrice" value="${param.fromPrice}">
            </div>
        </div>
        <div class="row mb-3">
            <label for="toPriceInput" class="col-sm-3 col-form-label">To price:</label>
            <div class="col-sm-9">
            <input type="text" class="form-control" id="toPriceInput" name="toPrice" value="${param.toPrice}">
            </div>
        </div>
        <c:if test="${not empty param.sort}">
            <input type="hidden" name="sort" value="${param.sort}"/>
        </c:if>
        <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>
</div>
<div class="row justify-content-center">
    <a href="<c:url value="/phones"/>">Reset search parameters</a>
</div>
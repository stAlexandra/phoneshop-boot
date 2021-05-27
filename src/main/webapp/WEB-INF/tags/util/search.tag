<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="basePath" required="true" type="java.lang.String" %>

<div class="card">
    <div class="card-body">
        <form>
        <div class="row mb-3">
            <label for="queryInput" class="col-sm-3 col-form-label">Search query:</label>
            <div class="col-sm-9">
            <input type="search" class="form-control" id="queryInput" name="q">
            </div>
        </div>
        <div class="row mb-3">
            <label for="fromPriceInput" class="col-sm-3 col-form-label">From price:</label>
            <div class="col-sm-9">
            <input type="text" class="form-control" id="fromPriceInput" name="fromPrice">
            </div>
        </div>
        <div class="row mb-3">
            <label for="toPriceInput" class="col-sm-3 col-form-label">To price:</label>
            <div class="col-sm-9">
            <input type="text" class="form-control" id="toPriceInput" name="toPrice">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>
</div>
<div class="row justify-content-center">
    <a href="<c:url value="${basePath}"/>">Reset search parameters</a>
</div>
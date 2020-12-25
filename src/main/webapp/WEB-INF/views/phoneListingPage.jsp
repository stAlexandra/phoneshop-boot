<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="<c:url value="/css/phoneshop.css"/>">
    <meta charset="utf-8">
    <title>Phone listing page</title>
</head>
<body>
<p>
    Found <c:out value="${phones.totalElements}"/> phones!
</p>
<table>
    <thead>
    <tr>
        <th>Image</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="phone" items="${phones.content}">
        <tr>
            <td>
                <img src="<c:url value="${phone.imageUrl}"/>">
            </td>
            <td><c:out value="${phone.brand}"/></td>
            <td><c:out value="${phone.model}"/></td>
            <td><c:out value="$ ${phone.price}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
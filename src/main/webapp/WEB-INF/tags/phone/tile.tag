<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="phone" required="true" type="com.expertsoft.phoneshop.persistence.model.Phone" %>
<c:url var="pdpLink" value="/phones/${phone.id}"/>
<tr>
    <td>
        <a href="${pdpLink}">
            <img src="<c:url value="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.image}"/>" width="100" height="100" alt="Phone image">
        </a>
    </td>
    <td><c:out value="${phone.brand}"/></td>
    <td><a href="${pdpLink}"><c:out value="${phone.model}"/></a></td>
    <td><c:out value="$ ${phone.price}"/></td>
</tr>
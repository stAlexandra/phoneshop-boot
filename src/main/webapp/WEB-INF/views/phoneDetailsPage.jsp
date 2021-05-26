<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="Phone details" showMenu="true">
    <div class="row mb-3">
        <common:back/>
    </div>
    <div class="row justify-content-center align-items-center">
        <div class="d-flex justify-content-center col-4 p-4">
            <img src="<c:url value="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.image}"/>"
                 width="376" height="376" alt="Phone image">
        </div>
        <div class="col-4">
            <div class="row">
                <p>
                    <b>Brand: </b>${phone.brand}
                </p>
            </div>
            <div class="row">
                <p>
                    <b>Model: </b>${phone.model}
                </p>
            </div>
            <div class="row">
                <p>
                    <b>Description: </b>${phone.description}
                </p>
            </div>
        </div>
    </div>
</common:page>

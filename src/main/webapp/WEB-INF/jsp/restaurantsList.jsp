<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function ajaxEvent() {

            var xmlHttp;
            try // Firefox, Opera 8.0+, Safari
            {
                xmlHttp = new XMLHttpRequest();
            } catch (e) {
                try // Internet Explorer
                {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                } catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {
                        alert("Your browser does not support AJAX!");
                        return false;
                    }
                }
            }

            xmlHttp.onreadystatechange = function() {
                if (xmlHttp.readyState == 4) {
                    document.getElementById("menudiv").innerHTML = xmlHttp.responseText;
                }
            }

            var queryString = document.getElementById("queryString").value;

            xmlHttp.open("POST", "/project/ajaxservice.htm?itemName="+queryString, true);
            xmlHttp.send();
        }
    </script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="page-header">
    <h1>Need something to eat?</h1>
    <br>
    <ul class="nav nav-pills">
        <li class="active"><a href="${contextPath}/restaurants.htm">Restaurants</a></li>
        <li><a href="${contextPath}/my-orders.htm">My Orders</a></li>
        <li><a href="${contextPath}/customer-info.htm">My Information</a></li>
        <li><a href="${contextPath}/viewCart.htm">My Cart</a></li>
        <li><a href="${contextPath}/user/login.htm">Logout</a></li>
    </ul>
</div>

<p>welcome ${user.name}</p>
<label>Input what you want to eat here:</label>
<input type="text"  id="queryString" size="30" onkeyup="ajaxEvent()" />
<div id="menudiv">
</div>

<c:forEach var="restaurant" items="${sessionScope.restaurants}">
    <div class="list-group">
        <a href="${contextPath}/menu.htm?resId=${restaurant.userId}" class="list-group-item">
            <h4 class="list-group-item-heading">
                    ${restaurant.name}
            </h4>
        </a>
    </div>
</c:forEach>
</body>
</html>
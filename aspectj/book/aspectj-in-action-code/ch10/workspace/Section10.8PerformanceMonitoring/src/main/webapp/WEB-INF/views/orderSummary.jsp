<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<title>Order Summary</title>
</head>

<body>
<div>

<h1>Order Summary</h1>

<ul>
	<c:forEach items="${orders}" var="order">
		<li><a href="orderDetails.htm?orderId=${order.id}">${order.id}</a></li>
	</c:forEach>
</ul>

</div>
</body>

</html>
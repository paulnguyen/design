<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Product Details</title>
</head>

<body>
<div>

<h1>Product Details</h1>

<form:form modelAttribute="product">
	<table>
		<c:if test="${not empty product.id}">
			<tr>
				<td>Product:</td>
				<td>${product.id}</td>
			</tr>
		</c:if>
	
		<tr>
			<td>Name:</td>
			<td><form:input path="name" size="30" maxlength="30"/></td>
		</tr>
		<tr>
			<td>
        		<c:choose>
          			<c:when test="${empty product.id}">
            			<p class="submit"><input type="submit" value="Add Product"/></p>
          			</c:when>
          			<c:otherwise>
            			<p class="submit"><input type="submit" value="Update Product"/></p>
            		</c:otherwise>
            	</c:choose>
            </td>
		</tr>
	</table>
</form:form>

</div>
</body>

</html>
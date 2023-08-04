<%--
  Created by IntelliJ IDEA.
  User: stef
  Date: 28-Jul-23
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
  <title>Form</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsps/css/form.css">
</head>
<body>
<c:if test = "${requestScope.error != null}">
  <div class="alert alert-danger" role="alert">
    <c:out value="${requestScope.error}"/>
  </div>
</c:if>


<div class="form-wrapper">
<form method="post" action="<c:out value="${context}"/>/coffee_shop">
  <input type="hidden" name="action" value="<c:out value="${requestScope.action}"/>">
  <input type="hidden" name="clientId" value="<c:out value="${requestScope.client.clientID}"/>">
  <div class="mb-3">
    <label for="first_name" class="form-label">First Name</label>
    <input type="text" class="form-control" id="first_name" name="first_name" value="<c:out value="${requestScope.client.clientFirstName}"/>">
  </div>
  <div class="mb-3">
    <label for="last_name" class="form-label">Last Name</label>
    <input type="text" class="form-control" id="last_name" name="last_name" value="<c:out value="${requestScope.client.clientLastName}"/>">
  </div>
  <div class="mb-3">
    <label class="form-label" for="email">Email</label>
    <input type="text" class="form-control" id="email" name="email" value="<c:out value="${requestScope.client.clientEmail}"/>">
  </div>
  <div class="mb-3">
    <label class="form-label" for="phone">Phone Number</label>
    <input type="text" class="form-control" id="phone" name="phone" value="<c:out value="${requestScope.client.clientPhoneNumber}"/>">
  </div>

  <button type="submit" class="btn btn-primary">Submit</button>
  <a href="<c:out value="${context}"/>/coffee_shop">
    <button type="button" class="btn btn-success">Home</button></a>
</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>

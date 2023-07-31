<%--
  Created by IntelliJ IDEA.
  User: stef
  Date: 28-Jul-23
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Search Client</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <style>
        /* Hide the input-container by default */
        #input-container {
            display: none;
        }
    </style>
</head>

<body>

<c:if test = "${requestScope.error != null}">
    <div class="alert alert-danger" role="alert">
        <c:out value="${requestScope.error}"/>
    </div>
</c:if>

<label for="select-option">Choose an option:</label>
<select id="select-option" onchange="showInputs()">
    <option value="default" selected disabled>Select an option</option>
    <option value="phone">Phone</option>
    <option value="name">Name</option>
    <option value="email">Email</option>
</select>

<div id="input-container">
    <input type="text" id="phone" placeholder="Phone">
    <input type="text" id="first_name" placeholder="First Name">
    <input type="text" id="last_name" placeholder="Last Name">
    <input type="text" id="email" placeholder="Email">
</div>

<script>
    function showInputs() {
        var selectElement = document.getElementById("select-option");
        var inputContainer = document.getElementById("input-container");
        var selectedOption = selectElement.value;

        inputContainer.style.display = "block";

        // Hide both input fields by default
        document.getElementById("phone").style.display = "none";
        document.getElementById("first_name").style.display = "none";
        document.getElementById("last_name").style.display = "none";
        document.getElementById("email").style.display = "none";

        // Show the appropriate input fields based on the selected option
        if (selectedOption === "phone") {
            document.getElementById("phone").style.display = "block";
        } else if (selectedOption === "name") {
            document.getElementById("first_name").style.display = "block";
            document.getElementById("last_name").style.display = "block";
        }
        else
        {
            document.getElementById("email").style.display = "block";
        }
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>

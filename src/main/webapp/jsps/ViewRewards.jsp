<%--
  Created by IntelliJ IDEA.
  User: stef
  Date: 28-Jul-23
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>Rewards</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        .row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .container {
            border: 1px solid #ccc;
            padding: 20px;
            width: 18%;
            box-sizing: border-box;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease-in-out;
            display: flex; /* Added: Use flexbox for centering text */
            flex-direction: column; /* Added: Stack the text elements vertically */
            justify-content: center; /* Added: Center text vertically */
            text-align: center; /* Added: Center text horizontally */
        }

        .container:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
        }

        .text {
            font-size: 16px;
            color: #333;
            margin-bottom: 10px;
        }

        .image {
            width: 100%;
            height: auto;
            max-height: 180px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        h1 {text-align: center;}

    </style>
</head>

<body>

<a href="<c:out value="${context}"/>/coffee_shop">
    <button type="button" class="btn btn-success">Home</button></a>

<h1>Rewards</h1>

<c:forEach items="${requestScope.rewards}" var="reward" varStatus="status">
    <c:if test="${status.index % 5 == 0}">
        <div class="row">
    </c:if>


    <div class="container">
        <div class="text">
            <c:out value="${reward.reward}"/>
        </div>
        <img class="image" src="<%=request.getContextPath()%>/jsps/resources/${requestScope.images[status.index]}" alt="Example Image">
        <div class="text">
            Number of entries necessary: <c:out value="${reward.numberOfEntries}"/>
        </div>
    </div>
        <c:if test="${(status.index + 1 ) % 5 == 0 || status.last}">
        </div>
        </c:if>
</c:forEach>

</body>
</html>

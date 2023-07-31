

<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!doctype html>


<html>
<head>
    <title>Coffee Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsps/css/styles_home_page.css">
</head>


<body>


<div class="container">
    <table>
        <tr>
            <td>
                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_add_client}"/>">
                    <button class="btn-add-client">ADD A CLIENT</button>
                </a>
            </td>
            <td>
                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_view_clients}"/>">
                    <button class="btn-view-clients">VIEW ALL CLIENTS</button>
                </a>

            </td>
            <td>
                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_search_client}"/>">
                    <button class="btn-search-client">SEARCH A CLIENT</button>
                </a>

            </td>
        </tr>
        <tr>
            <td>
                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_add_entry}"/>">
                    <button class="btn-add-entry">ADD AN ENTRY</button>
                </a>

            </td>
            <td>
                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_view_number_of_entries_for_a_client}"/>">
                    <button class="btn-view-no-entries">VIEW NUMBER OF ENTRIES FOR A CLIENT</button>
                </a>

            </td>
            <td>
                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_view_rewards}"/>">
                    <button class="btn-view-rewards">VIEW ALL REWARDS</button>
                </a>

            </td>
        </tr>
    </table>
</div>






<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
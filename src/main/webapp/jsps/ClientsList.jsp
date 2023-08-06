<%--
  Created by IntelliJ IDEA.
  User: stef
  Date: 28-Jul-23
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
    <title>Clients list</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsps/css/view_clients.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsps/css/center_home_button.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsps/css/search.css">
</head>

<c:if test = "${requestScope.error != null}">
    <div class="alert alert-danger" role="alert">
        <c:out value="${requestScope.error}"/>
    </div>
</c:if>


<a href="<c:out value="${context}"/>/coffee_shop">
    <button type="button" class="btn btn-success">Home</button></a>

<div class="text-center">
    <h1>Clients List</h1>
</div>

<table class="table table-hover">
    <thead>
    <tr>
        <th>#</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Number of Entries</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.clientsList}" var="client">
        <tr>
            <td><c:out value="${client.clientID}"/></td>
            <td><c:out value="${client.clientFirstName}"/></td>
            <td><c:out value="${client.clientLastName}"/></td>
            <td><c:out value="${client.clientEmail}"/></td>
            <td><c:out value="${client.clientPhoneNumber}"/></td>
            <td><c:out value="${requestScope.coffeeShop.numberOfEntries(client.clientID)}"/> </td>
            <td>
                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_add_entry}"/>&clientId=<c:out value="${client.clientID}"/>">
                <button type="button" class="button_plus"></button>
                </a>
                    &nbsp;
                    &nbsp;
                    &nbsp;
                    &nbsp;
                    &nbsp;
                    &nbsp;

                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_edit_client}"/>&clientId=<c:out value="${client.clientID}"/>">
                    <button type="button" class="btn btn-success">EDIT</button></a>

                <!-- Button trigger modal -->
                <button type="button" id="triggerDelete<c:out value="${client.clientID}"/>" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal<c:out value="${client.clientID}"/>">DELETE</button></a>

                <!-- Modal -->
                <div class="modal fade" id="deleteModal<c:out value="${client.clientID}"/>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel<c:out value="${client.clientID}"/>" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel<c:out value="${client.clientID}"/>">Confirm deletion</h5>
                                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p> Are you sure you want to delete <c:out value="${client.clientFirstName}"/> <c:out value="${client.clientLastName}"/>?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_delete_client}"/>&clientId=<c:out value="${client.clientID}"/>">
                                    <button type="button" class="btn btn-primary">Yes</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>

<script>

    $(document).ready(function(){
        <c:forEach items="${requestScope.clientsList}" var="client">
        $("#triggerDelete<c:out value="${client.clientID}"/>").click(function(event){
            event.stopPropagation();
            $("#deleteModal<c:out value="${client.clientID}"/>").modal("show");
        });
        </c:forEach>
    });
</script>
</body>
</html>
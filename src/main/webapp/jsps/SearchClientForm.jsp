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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsps/css/search.css">
</head>

<body>

<c:if test = "${requestScope.error != null}">
    <div class="alert alert-danger" role="alert">
        <c:out value="${requestScope.error}"/>
    </div>
</c:if>

<a href="<c:out value="${context}"/>/coffee_shop">
    <button type="button" class="btn btn-success">Home</button></a>


<form method="post">
    <label for="option">Choose an option:</label>
    <select id="option" name="option" onchange="handleOptionChange()">
        <option value="default" selected disabled>Select an option</option>
        <option value="phone">Phone</option>
        <option value="name">Name</option>
        <option value="email">Email</option>
        <!-- Add more options as needed -->
    </select>

    <div id="inputFields">
        <!-- Input text fields will be dynamically added here -->
    </div>

    <button type="submit">Submit</button>
</form>

<c:if test="${requestScope.error == null && requestScope.showTable==true}">


<div id="client">
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
            <tr>
                <td><c:out value="${requestScope.client.clientID}"/></td>
                <td><c:out value="${requestScope.client.clientFirstName}"/></td>
                <td><c:out value="${requestScope.client.clientLastName}"/></td>
                <td><c:out value="${requestScope.client.clientEmail}"/></td>
                <td><c:out value="${requestScope.client.clientPhoneNumber}"/></td>
                <td><c:out value="${requestScope.coffeeShop.numberOfEntries(requestScope.client.clientID)}"/> </td>
                <td>

                    <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_add_entry}"/>&clientId=<c:out value="${requestScope.client.clientID}"/>">
                        <button type="button" class="button_plus"></button></a>

                    &nbsp;
                    &nbsp;
                    &nbsp;
                    &nbsp;
                    &nbsp;
                    &nbsp;
                    <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_edit_client}"/>&clientId=<c:out value="${requestScope.client.clientID}"/>">
                        <button type="button" class="btn btn-success">EDIT</button></a>

                    <!-- Button trigger modal -->
                    <button type="button" id="triggerDelete<c:out value="${requestScope.client.clientID}"/>" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal<c:out value="${requestScope.client.clientID}"/>">DELETE</button>

                    <!-- Modal -->
                    <div class="modal fade" id="deleteModal<c:out value="${requestScope.client.clientID}"/>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel<c:out value="${requestScope.client.clientID}"/>" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel<c:out value="${requestScope.client.clientID}"/>">Confirm deletion</h5>
                                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p> Are you sure you want to delete <c:out value="${requestScope.client.clientFirstName}"/> <c:out value="${requestScope.client.clientLastName}"/>?</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                    <a href="<c:out value="${context}"/>/coffee_shop?action=<c:out value="${requestScope.action_delete_client}"/>&clientId=<c:out value="${requestScope.client.clientID}"/>">
                                        <button type="button" class="btn btn-primary">Yes</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>

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
</div>

</c:if>

<script>
    function handleOptionChange() {
        var selectedOption = document.getElementById("option").value;
        var inputFieldsContainer = document.getElementById("inputFields");

        // Remove any existing input text fields
        while (inputFieldsContainer.firstChild) {
            inputFieldsContainer.removeChild(inputFieldsContainer.firstChild);
        }

        // Create and add input text fields based on the selected option
        if (selectedOption === "phone") {
            var phone = document.createElement("input");
            phone.type = "text";
            phone.name = "phone";
            phone.placeholder = "Phone";
            inputFieldsContainer.appendChild(phone);
        } else if (selectedOption === "name") {
            var first_name = document.createElement("input");
            first_name.type = "text";
            first_name.name = "first_name";
            first_name.placeholder = "First name";
            inputFieldsContainer.appendChild(first_name);

            var last_name = document.createElement("input");
            last_name.type = "text";
            last_name.name = "last_name";
            last_name.placeholder = "Last name";
            inputFieldsContainer.appendChild(last_name);
        } else if (selectedOption === "email") {
            var email = document.createElement("input");
            email.type = "text";
            email.name = "email";
            email.placeholder = "Email";
            inputFieldsContainer.appendChild(email);
        }
    }
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>



</body>
</html>

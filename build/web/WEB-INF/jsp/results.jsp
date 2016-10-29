<%-- 
    Document   : results
    Created on : Apr 19, 2016, 11:11:14 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cloud Project</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <link href="usecase.css" rel="stylesheet" type="text/css"/>
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script src="useCase.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="text-right">
            <form action="logout.htm" method='post'>
                <input type="submit" value="Logout"/>
                <input type="hidden" name="action" value="logout" />
            </form>
        </div>
        <div class="text-right">
            <%@page import="java.net.InetAddress" %>
            <%String ip = "";
                InetAddress inetAddress = InetAddress.getLocalHost();
                ip = inetAddress.getHostAddress();%>
            <h1 class="text">Server's IP address - <span><%=ip%></span></h1>

        </div>
        <h1 class="text" id="welcome">Users have been created successfully!</h1>
        <form action="backToUseCase.htm">
            <input type="submit" value="Back to Usecases"/>
        </form>

        <h3>List of user created - </h3>
        <div class="row">
            <div class="col-lg-5"></div>
            <div class="col-lg-6">
                <table class="">
                    <tr>
                        <th>Username</th>
                    </tr>
                    <c:forEach items="${sessionScope.usernameList}"  var ="user">
                        <tr>
                            <td>${user.username}</td> 
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>

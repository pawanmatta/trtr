<%-- 
    Document   : useCase
    Created on : Mar 30, 2016, 8:42:12 PM
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
        <h1 class="text" id="welcome">Welcome <%=session.getAttribute("username")%>!</h1>
        <div class="row">
            <div class="col-lg-3"></div>
            <div class="col-lg-1">
                <form action="usecase1.htm" method='post'>
                    <input type="submit" value="Usecase 1"/>
                </form>
            </div>
            <div class="col-lg-1">
                    <form action="usecase2.htm" method='post'>
                    <input type="submit" value="Usecase 2"/>
                </form>
            </div>
            <div class="col-lg-1">
                <form action="usecase3.htm" method='post'>
                    <input type="submit" value="Usecase 3"/>
                </form>
            </div>
            <div class="col-lg-1">
                <form action="usecase4.htm" method='post'>
                    <input type="submit" value="Usecase 4"/>
                </form>
            </div>
            <div class="col-lg-1">
                <!--<form action="usecase5.htm" id = "showUserForm" method='post'>-->
                    <input type="button" id = "showUserForm"  value="Usecase 5"/>
                <!--</form>-->
            </div>
            <div class="col-lg-3"></div>
        </div>
        <br/>
        <div class="row" id ="askUser">
            <form action="usecase5.htm" method='post'>
                <input type='text' id="userCount" name='userCount' placeholder='Number of users?' maxlength="3">
                <br/>
                <input type="submit" value="Submit"/>
            </form>
        </div>
        
    </body>
</html>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cloud Project</title>
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:600,700,300'>
        <link href="index.css" rel="stylesheet" type="text/css"/>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="373640431625-ro0j67m7gdaa5mon42od1vm396j7dvjt.apps.googleusercontent.com">
    </head>

    <body>

        <div class="wrap">

            <div class="flip-container" id='flippr'>
                <div class="flipper">
                    <div class="front"></div>
                    <div class="back"></div>
                </div>
            </div>

            <h1 class="text" id="welcome">Welcome, <span>please login.</span></h1>

            <form method='post' id="theForm" action="usecase.htm">

                <input type='text' id="username" name='username' placeholder='Username'>
                <input type='text' id='password' name='password' placeholder='Password'>

                <div class='login'>
                    <a href="#"><i class="icon-cog"></i> I've forgotten my password</a>
                    <input type='submit' value='Login'>
                    <input type="hidden" name="action" value="login" />
                </div><!-- /login -->
            </form>
        </div><!-- /wrap -->

        <div>
            <%@page import="java.net.InetAddress" %>
            <%String ip = "";
                InetAddress inetAddress = InetAddress.getLocalHost();
                ip = inetAddress.getHostAddress();%>
            <h1 class="text">Server's IP address - <span><%=ip%></span></h1>

        </div>
            <form method="post" action="loginviagoogle.htm">
                <input type='submit' value='LoginViaGoogle'>
            </form>

        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    </body>
</html>

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.cloud.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author dishujindal
 */
public class LoginGoogleController implements Controller{

    
 private final String clientId = "373640431625-ro0j67m7gdaa5mon42od1vm396j7dvjt.apps.googleusercontent.com";
 private final String clientSecret = "hn1Pb58Ih6g-4_aF4XCUXOA7";

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
    StringBuilder oauthUrl = new StringBuilder().append("https://accounts.google.com/o/oauth2/auth")
   .append("?client_id=").append(clientId) // the client id from the api console registration
   .append("&response_type=code")
   .append("&scope=openid%20email") // scope is the api permissions we are requesting
   .append("&redirect_uri=http://localhost:8080/CloudProject/usecase.htm") // the servlet that google redirects to after authorization
   .append("&state=this_can_be_anything_to_help_correlate_the_response%3Dlike_session_id")
   .append("&access_type=offline") // here we are asking to access to user's data while they are not signed in
   .append("&approval_prompt=force"); // this requires them to verify which account to use, if they are already signed in
    
   hsr1.sendRedirect(oauthUrl.toString());
   return null;
    }
    
}

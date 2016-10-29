/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.cloud.Controller;


import com.neu.cloud.DAO.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author dishujindal
 */

public class UseCaseController implements Controller {

 private final String clientId = "373640431625-ro0j67m7gdaa5mon42od1vm396j7dvjt.apps.googleusercontent.com";
 private final String clientSecret = "hn1Pb58Ih6g-4_aF4XCUXOA7";
   LoginDAO loginDAO = new LoginDAO();

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        
         String code = request.getParameter("code");
    
   // get the access token by post to Google
      String body = post("https://accounts.google.com/o/oauth2/token", ImmutableMap.<String,String>builder()
     .put("code", code)
     .put("client_id", clientId)
     .put("client_secret", clientSecret)
     .put("redirect_uri", "http://localhost:8080/CloudProject/usecase.htm")
     .put("grant_type", "authorization_code").build());
 
      
      JSONObject jsonObject = null;
			
			// get the access token from json and request info from Google
			try {
				jsonObject = (JSONObject) new JSONParser().parse(body);
			} catch (ParseException e) {
				throw new RuntimeException("Unable to parse json " + body);
			}
			
			// google tokens expire after an hour, but since we requested offline access we can get a new token without user involvement via the refresh token
			String accessToken = (String) jsonObject.get("access_token");
					
			// you may want to store the access token in session
			request.getSession().setAttribute("access_token", accessToken);
			
			// get some info about the user with the access token
			String json = get(new StringBuilder("https://www.googleapis.com/oauth2/v1/userinfo?access_token=").append(accessToken).toString());
			
			// now we could store the email address in session
                        
                      JSONObject jsonObject1= null;
                      jsonObject = (JSONObject) new JSONParser().parse(json);
			// return the json of the user's basic info
                        String username=(String)jsonObject.get("username");
                        session.setAttribute("username", username);
                      //response.getWriter().println(json);
                      mv.setViewName("useCase");
		return mv;	
	}  
                        
                        
                        
     public String get(String url) throws ClientProtocolException, IOException {
		return execute(new HttpGet(url));
	}
	
	// makes a POST request to url with form parameters and returns body as a string
	public String post(String url, Map<String,String> formParameters) throws ClientProtocolException, IOException {	
		HttpPost request = new HttpPost(url);
			
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		for (String key : formParameters.keySet()) {
			nvps.add(new BasicNameValuePair(key, formParameters.get(key)));	
		}

		request.setEntity(new UrlEncodedFormEntity(nvps));
		
		return execute(request);
	}
	
	// makes request and checks response code for 200
	private String execute(HttpRequestBase request) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);
	    
		HttpEntity entity = response.getEntity();
	    String body = EntityUtils.toString(entity);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Expected 200 but got " + response.getStatusLine().getStatusCode() + ", with body " + body);
		}

	    return body;
	}

//        String value = request.getParameter("action");
//
//        // if user clicks on logout
//        if (value.equals("logout")) {
//
//            session.invalidate(); //completes the session
//            mv.setViewName("index");
//        } if(value.equals("login")) {
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//
//            if ("admin".equals(username) && "admin".equals(password)){
////            boolean isUserexist = loginDAO.checkUser(username, password);
////            if (isUserexist) {
//                session.setAttribute("username", username);
////                System.out.println(isUserexist);
//                mv.setViewName("useCase");
//            } else {
////                System.out.println(isUserexist);
//                mv.setViewName("index");
//            }
//        }
//        mv.setViewName("useCase");
//        return mv;

    
}

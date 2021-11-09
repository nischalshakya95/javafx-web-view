package org.personal.javafxwebview.webview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.personal.javafxwebview.webview.controller.WebViewController;
import org.personal.javafxwebview.webview.model.User;

public class MyObject {

    static int counter = 0;

    // This method is called from the javascript
    public void doIt(String userJson) throws JsonProcessingException {
        System.out.println("doIt() method called " + ++counter);

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);
        System.out.println(user.getAge());
        System.out.println(user.getName());

        // Pass the lowerCase as an parameter to the showResult() function declared in the javascript
        WebViewController.javascriptConnector.call("showResult", objectMapper.writeValueAsString(user));
    }

    public void log(String text) {
        System.out.println(text);
    }

    public void error(String text) {
        System.err.println(text);
    }


}

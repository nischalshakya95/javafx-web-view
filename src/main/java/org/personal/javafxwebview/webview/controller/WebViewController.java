package org.personal.javafxwebview.webview.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.personal.javafxwebview.webview.MyObject;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WebViewController implements Initializable {

    @FXML
    private WebView webView;

    public static JSObject javascriptConnector;

    private WebEngine webEngine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Class webEngineClazz = WebEngine.class;
//
//        Field debuggerField = null;
//        try {
//            debuggerField = webEngineClazz.getDeclaredField("debugger");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        debuggerField.setAccessible(true);
//
//        Debugger debugger = null;
//        try {
//            debugger = (Debugger) debuggerField.get(webView.getEngine());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        try {
//            DevToolsDebuggerServer.startDebugServer(debugger, 51742);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }

        webView.setVisible(true);
        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {

            // We need to keep the object in the field to prevent the garbage collection of it.
            private final MyObject myObject = new MyObject();

            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                System.out.println(webView.getEngine().getLoadWorker().exceptionProperty());

                if (newValue != Worker.State.SUCCEEDED) {
                    return;
                }

                javascriptConnector = (JSObject) webEngine.executeScript("window");
                // Make the myObject visible to the javascript. So that it can access myObject.methodName()
                javascriptConnector.setMember("console", myObject);

                System.out.println(webView.getEngine().getLoadWorker().exceptionProperty());
            }
        });

        File file = new File("index.html");
//        var url = "https://pp.dev.app.nurixtx.net/login?redirect_uri=/login";
        var url = "https://whatismyipaddress.com/";
//        var url = "https://google.com/";
        webEngine.load(file.toURI().toString());
        webEngine.load(url);
    }

    @FXML
    private void incrementCounter() {
        // Execute the function myFunction() declared in the javascript
        webEngine.executeScript("myFunction()");
    }
}

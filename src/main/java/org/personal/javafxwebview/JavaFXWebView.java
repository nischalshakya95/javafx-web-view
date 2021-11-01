package org.personal.javafxwebview;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.File;

public class JavaFXWebView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("fire JS");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (webengine != null) {
                    webengine.executeScript("myFunction()");
                }
            }
        });

        publishServices();
        StackPane root = new StackPane();
        HBox hh = new HBox();
        hh.getChildren().add(btn);
        hh.getChildren().add(webview);

        root.getChildren().add(hh);

        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private WebEngine webengine;
    private static WebView webview;

    private void publishServices() {
        try {
            webview = new WebView();
            webview.setVisible(true);
            webengine = webview.getEngine();
            webengine.setJavaScriptEnabled(true);
            webengine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                    if (newValue != Worker.State.SUCCEEDED) {
                        return;
                    }
                    JSObject window = (JSObject) webengine.executeScript("window");
                    window.setMember("myObject", new MyObject());
                }
            });

            File file = new File("index.html");
            webengine.load(file.toURI().toString());
        } catch (Exception ex) {
            System.err.print("error " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}

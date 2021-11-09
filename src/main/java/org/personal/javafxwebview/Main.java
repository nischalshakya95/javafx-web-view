package org.personal.javafxwebview;

import javafx.application.Application;
import javafx.stage.Stage;
import org.personal.javafxwebview.webview.WebViewLauncher;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        WebViewLauncher.launch();
    }

    public static void main(String[] args) {
        launch();
    }
}

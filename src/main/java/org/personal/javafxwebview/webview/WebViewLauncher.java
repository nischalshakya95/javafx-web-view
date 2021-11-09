package org.personal.javafxwebview.webview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WebViewLauncher {

    private final static String WEB_VIEW_RESOURCE_URL = "/org/personal/javafxwebview/webview/webview.fxml";

    public static void launch() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WebViewLauncher.class.getResource(WEB_VIEW_RESOURCE_URL));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("WebView");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

//            stage.setOnCloseRequest(e -> {
//                System.exit(0);
//                try {
//                    DevToolsDebuggerServer.stopDebugServer();
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            });

        } catch (IOException e) {
            System.out.println("Exception occurred while loading web view " + e.getMessage());
            e.printStackTrace();
        }
    }

}

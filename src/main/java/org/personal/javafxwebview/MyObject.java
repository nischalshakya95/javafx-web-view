package org.personal.javafxwebview;

public class MyObject {

    static int counter = 0;

    public void doIt(String s) {
        var lowerCase = s.toLowerCase();
        System.out.println("lower case " + lowerCase);
        System.out.println("doIt() method called " + ++counter);

        JavaFXWebView.javascriptConnector.call("showResult", lowerCase);
    }

}

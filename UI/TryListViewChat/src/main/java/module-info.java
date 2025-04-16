module TryListViewChat {
    requires javafx.web;

    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.remote_driver;
    requires org.seleniumhq.selenium.chrome_driver;
    requires org.seleniumhq.selenium.devtools_v131;
    requires org.seleniumhq.selenium.http;
    requires org.testng;
    requires dev.failsafe.core;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;
    requires static lombok;
    requires org.seleniumhq.selenium.support;
    requires org.seleniumhq.selenium.chromium_driver;
    requires org.seleniumhq.selenium.manager;
    requires com.google.gson;
    requires MaterialFX;
    requires fr.brouillard.oss.cssfx;
    requires annotations;  // Add other required JavaFX modules here.
    requires javafx.fxml;
    requires okhttp3;
    opens TryListViewChat to javafx.fxml;

    opens TryListViewChat.application to
            javafx.graphics, javafx.fxml, com.google.gson, org.seleniumhq.selenium.api;
//    opens pickpal.entity to com.google.gson, org.seleniumhq.selenium.api;
//    opens pickpal.scraper to com.google.gson, org.seleniumhq.selenium.api;
//    opens pickpal.algorithms to com.google.gson, org.seleniumhq.selenium.api;
//    opens pickpal.navigators to com.google.gson, org.seleniumhq.selenium.api;
//    opens pickpal.controller to com.google.gson, org.seleniumhq.selenium.api;
    //opens pickpal to com.google.gson, org.seleniumhq.selenium.api;

    exports TryListViewChat;
    exports TryListViewChat.application;
    exports TryListViewChat.entity;
    opens TryListViewChat.entity to javafx.fxml;
}
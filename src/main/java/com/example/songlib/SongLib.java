package com.example.songlib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SongLib extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("song-Lib.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 582, 400);
        primaryStage.setTitle("Song Library");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);

    }
}


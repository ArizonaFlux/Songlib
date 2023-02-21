package com.example.songlib;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;



public class MyController {
    @FXML
    private Button add;
    private Button edit;
    private Button delete;

    private Button yes;
    private Button no;

    @FXML
    private TextField songName;
    @FXML
    private TextField artist;
    @FXML
    private TextField album;
    @FXML
    private TextField year;

    @FXML
    private ListView<String> list;

    @FXML
    private AnchorPane myAnchorPane;

    String songNameText;
    String artistText;
    String albumText;
    String yearText;

    library lib = new library("Songs.csv");

    public void adding(ActionEvent event) {
        System.out.println("Add Button Clicked!");

        if(!confirming())
            return;

        songNameText = songName.getText();
        artistText = artist.getText();
        albumText = album.getText();
        yearText = year.getText();

        if (!lib.add(songNameText, artistText, albumText, yearText))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Input! Please retype your input");

            alert.showAndWait();
        }
        try{
            lib.save("Songs.csv");
        }
        catch (Exception e){
            System.out.println("Failed to save file in adding" + e);
        };

        reload();
    }

    public void editing(ActionEvent event) throws IOException {
        System.out.println("Edit Button Clicked!");

        if(!confirming())
            return;

        songNameText = songName.getText();
        artistText = artist.getText();
        albumText = album.getText();
        yearText = year.getText();

        song selected = lib.get_selected();

        if (selected.name.length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit failed");
            alert.setHeaderText(null);
            alert.setContentText("Unsuccessful edit!");

            alert.showAndWait();
        }
        else
        {
            if(!lib.edit(selected.name, selected.artist, songNameText, artistText, albumText, yearText)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edit failed");
                alert.setHeaderText(null);
                alert.setContentText("Unsuccessful edit!");

                alert.showAndWait();
            }
        }

        reload();
        try{
            lib.save("Songs.csv");
        }
        catch (Exception e){
            System.out.println("Failed to save file in adding" + e);
        };
    }

    public void deleting(ActionEvent event) {
        System.out.println("Delete Button Clicked!");

        if(!confirming())
            return;

        song selected = lib.get_selected();

        if (selected.name.length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete failed");
            alert.setHeaderText(null);
            alert.setContentText("Nothing to delete!");

            alert.showAndWait();
        }
        else
        {
            if(!lib.delete(selected.name, selected.artist)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete failed");
                alert.setHeaderText(null);
                alert.setContentText("Nothing to delete!");

                alert.showAndWait();
            }
        }

        reload();
        try{
            lib.save("Songs.csv");
        }
        catch (Exception e){
            System.out.println("Failed to save file in adding" + e);
        };
    }

    public boolean confirming() {
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setHeaderText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void reload() {
        list.getItems().clear();

        for (song item : lib.to_song())
        {
            list.getItems().add(item.to_list());
        }
    }

    public void initialize(){
        reload();

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String temp[] = list.getSelectionModel().getSelectedItem().split("\\|");

                if(temp.length == 0) return;

                String name = temp[0].trim();
                String artist = temp[1].trim();
                lib.select(name, artist);

                System.out.println(lib.get_selected());
            }
        });

        if(lib.to_song().length != 0) {
            list.getSelectionModel().select(0);
            list.getFocusModel().focus(0);
        }
    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
    }



}
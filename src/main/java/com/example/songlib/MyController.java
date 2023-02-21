package com.example.songlib;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javafx.scene.control.Alert;

import javafx.scene.control.Dialog;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ChoiceDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;


public class MyController {
    @FXML
    private Button add;
    private Button edit;
    private Button delete;

    private Button yes;
    private Button no;

    @FXML
    private TextField songName;
    private TextField artist;
    private TextField album;
    private TextField year;

    @FXML
    private ListView<song> list;

    @FXML
//    private AnchorPane confirm;

    String songNameText;
    String artistText;
    String albumText;
    String yearText;

    public void adding(ActionEvent event) {
        System.out.println("Add Button Clicked!");

        yes.setVisible(false);


        songNameText = songName.getText();
        artistText = songName.getText();
        albumText = songName.getText();
        yearText = songName.getText();



//        System.err.println("Add Button Clicked!");
    }

    public void editing(ActionEvent event) {
        System.out.println("Edit Button Clicked!");


    }

    public void deleting(ActionEvent event) {
        System.out.println("Delete Button Clicked!");


    }

//    public boolean confirming(ActionEvent event) {
//        Stage stage = (Stage) confirm.getScene().getWindows;
//    }


}
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.*;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import  javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Scanner;

public class Histogram extends Application{
char _Alpha; //Holds our current Alphabet Letter

int _letterTotal = 0; //Holds the total amount of letters for the current letter check.
FileInputStream _in = null; //Takes in our file string
String _fileString; //Holds our filePath
boolean _Check = false; //Mainly was used for testing

    @Override
    public void start(Stage _primaryStage) throws Exception{


        //Creates TextField File Path
        TextField _filePath = new TextField();

        //Creates Label for file name
        Label _fileName = new Label("Filename: ");

        //Creates button
        Button _buttonFile = new Button("View");

        //Creates Category AxisX this handles what letters are shown on our graph
            CategoryAxis _XAxis = new CategoryAxis();

        //Creates Number AxisY handles the amount of letters for each letter shown
            NumberAxis _YAxis = new NumberAxis();

        //Creates Bar Chart to create our histogram
        BarChart _barChart = new BarChart(_XAxis, _YAxis);

        BorderPane _pane = new BorderPane(); //BorderPane is our base pane, we use this so we can stack hbox/vbox
        VBox _VBox = _createVBox(_barChart); //Creates our vbox (This handles our BarChart)
        _pane.setCenter(_VBox); //We then set it to the center of our BorderPane.

        HBox _hboxlocal = createHbox(_fileName, _filePath,_buttonFile); //Creates our HBox (This handles our textfield,
        //Labels and Button
        _pane.setBottom(_hboxlocal); //We then set it to the bottom of our BorderPane


        XYChart.Series _letterGraph = new XYChart.Series(); //We create our XYChart called _letterGraph so we can add
        //it to our BarChart object

        //This button handles our file path and calculating the amount of letters given in it
        _buttonFile.setOnAction(e-> {
            File _file = new File(_filePath.getText()); //We pass our _filePath TextField as a new File
                    _DataCalculator(_letterGraph, _file, _buttonFile); //This function does our calculation
                });


        _barChart.getData().add(_letterGraph); //After our calculation is performed we add our _letterGraph data to display on our bar chart


        //Displays our stacked borderPane
        Scene scene = new Scene(_pane, 500, 500);
        _primaryStage.setScene(scene);
        _primaryStage.setTitle("Question 4");
        _primaryStage.show();
    }


    public void _DataCalculator(XYChart.Series _Graph, File _file, Button _Button){ //This is horribly optimized for speed/time complexity
        //Due to me not using a proper sorting algorithm
            if(_file.exists()) {//Checks if the file we provided when our button was pressed exists
                try {
                    String _nextLine;
                    for(_Alpha = 'A'; _Alpha <= 'Z'; ++_Alpha) { //Create a char forloop that goes from A to Z
                        Scanner _search = new Scanner(_file); //Given n Letter we scan the first line in our file
                        while(_search.hasNextLine()) {       //We then check to make sure we have another line to read
                            _nextLine = _search.nextLine(); //We check our current line
                            if (_nextLine != null) {
                                for (int i = 0; i < _nextLine.length(); i++) { //We then loop through each char of our line
                                    if (_Alpha == _nextLine.toUpperCase().charAt(i)) { //If we find n Alphabet letter
                                        _letterTotal++;//We add to our letter total for n letter
                                        _Check = true;
                                    } else {
                                        _Check = false;
                                    } //We do this for each line looking for our first letter once we have finished A
                                    //Then we do the same check for B ect all the way to Z
                                }

                            }
                        }
                        //Once we have checked every line we display the amount of times we found that letter and then repeat until we have checked every one
                        _Graph.getData().add(new XYChart.Data(String.valueOf(_Alpha), _letterTotal));
                        _letterTotal = 0; //After we have checked n letter we reset our letterTotal/letterCount and loop again
                        _nextLine = null;//We set our _nextLine to NULL as we need to reset it for the next letter
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not Found"); //If file we provided isn't found then our exception is thrown
                }
            }
    }

    public HBox createHbox(Label _fileName, TextField _filePath, Button _buttonFile){//This Hbox handles our lables/fields/buttons
        HBox _HBox = new HBox();
        _HBox.setAlignment(Pos.BOTTOM_LEFT);
        _HBox.getChildren().add(_fileName);

        _HBox.setAlignment(Pos.BOTTOM_RIGHT);
        _HBox.getChildren().add(_filePath);

        _HBox.setAlignment(Pos.BOTTOM_RIGHT);
        _HBox.getChildren().add(_buttonFile);

        return _HBox;
    }

    public VBox _createVBox(BarChart _BarChart){ //This Vbox handles our BarChart
        VBox _VBox = new VBox(_BarChart);
        return _VBox;
    }
}

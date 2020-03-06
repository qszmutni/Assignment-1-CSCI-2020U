package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class CardPicker extends Application {
    @Override
    public void start(Stage primaryStage){
        //Creating a gridpane to hold the cards
        GridPane Pane = new GridPane();
        Pane.setAlignment(Pos.CENTER);
        Pane.setPadding(new Insets(10,10,10,10));

        Random rand = new Random(System.currentTimeMillis());

        //Picking random cards from 1 to 54, need to do 0 -> 53 then add 1 because we dont have a 0 card.
        //Combining the "image/" string with the random number generated then adding ".png" to the end
        //I decided this was better than loading all the cards and picking one through an array
        String First = "image/";
        int firstint = rand.nextInt(53) + 1;
        First += firstint;
        First += ".png";

        String Second = "image/";
        int secondint = rand.nextInt(53) + 1;
        Second += secondint;
        Second += ".png";

        String Third = "image/";
        int thirdint = rand.nextInt(53) + 1;
        Third += thirdint;
        Third += ".png";


        //Creating imageviews with the images we just randomly picked.
        ImageView FirstCard = new ImageView(new Image(First));
        ImageView SecondCard = new ImageView(new Image(Second));
        ImageView ThirdCard = new ImageView(new Image(Third));

        //Adding those imageviews to the pane.
        Pane.add(FirstCard, 0, 0);
        Pane.add(SecondCard, 1, 0);
        Pane.add(ThirdCard, 2, 0);


        //Displaying scene
        Scene scene = new Scene(Pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

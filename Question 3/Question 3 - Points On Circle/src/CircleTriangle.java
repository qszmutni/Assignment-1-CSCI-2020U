import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;

import java.util.Random;


public class CircleTriangle extends Application {

    //Creating variables for circles, lines and text objects.
    //MainCir is the main circle that the other points(circles) revolve around
    //Each Line1 or Text1 object corresponds with the right number aka Line1, Text1, Circle1
    private Circle MainCir = new Circle(200,125,40);
    private Circle Circle1 = new Circle(10,10,5);
    private Circle Circle2 = new Circle(10,10,5);
    private Circle Circle3 = new Circle(10,10,5);
    private Line Line1 = new Line();
    private Line Line2 = new Line();
    private Line Line3 = new Line();
    private Text Text1 = new Text();
    private Text Text2 = new Text();
    private Text Text3 = new Text();

    @Override
    public void start(Stage primaryStage){
        Pane pane = new Pane();


        //Creating random variable
        Random rand = new Random(System.currentTimeMillis());

        //Setting fill and cursors for circles
        MainCir.setFill(Color.WHITE);
        MainCir.setStroke(Color.BLACK);


        Circle1.setCursor(Cursor.HAND);
        Circle1.setFill(Color.RED);


        Circle2.setCursor(Cursor.HAND);
        Circle2.setFill(Color.RED);


        Circle3.setCursor(Cursor.HAND);
        Circle3.setFill(Color.RED);

        //Creating max range and min range for randomization
        double rangeMin = -3.14;
        double rangeMax = 3.14;

        //The random values created are radian values that determine where the circle is going
        //to be placed.
        double randomValue1 = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        double randomValue2 = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        double randomValue3 = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();

        //With the random values generated we can now call the function OrbitCircleAround
        OrbitCircleAround(MainCir, Circle1, randomValue1);
        OrbitCircleAround(MainCir, Circle2, randomValue2);
        OrbitCircleAround(MainCir, Circle3, randomValue3);

        //Adding all javafx objects to the pane.
        pane.getChildren().addAll(MainCir, Circle1, Circle2, Circle3,
                Line1, Line2, Line3, Text1, Text2, Text3);


        //Displaying scene
        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Setting events to trigger onMouseDragged, basically figures
        //out the angle in radians of the mouse relative to the "MainCir" circle
        //and positions the circle that was dragged accordingly.
        Circle1.setOnMouseDragged(e ->{
            double X = ((e.getX()) - MainCir.getCenterX());
            double Y = ((e.getY()) - MainCir.getCenterY());

            double Angle = Math.atan2(Y, X);
            OrbitCircleAround(MainCir, Circle1, Angle);
        });

        Circle2.setOnMouseDragged(e ->{
            double X = ((e.getX()) - MainCir.getCenterX());
            double Y = ((e.getY()) - MainCir.getCenterY());

            double Angle = Math.atan2(Y, X);
            OrbitCircleAround(MainCir, Circle2, Angle);
        });

        Circle3.setOnMouseDragged(e ->{
            double X = ((e.getX()) - MainCir.getCenterX());
            double Y = ((e.getY()) - MainCir.getCenterY());

            double Angle = Math.atan2(Y, X);
            OrbitCircleAround(MainCir, Circle3, Angle);
        });


    }
    public void OrbitCircleAround(Circle Center, Circle Orbiter, double Rads){
        //Orbits "Orbiter" circle around the center circle by using the angle in radians of the
        //mouse to calculate the new position of the orbiter circle correctly.
        //NOTE: This function calls another "SetLinesAndAngles" so that the values
        //on screen update properly.

        Orbiter.setCenterX(Center.getCenterX() + Math.cos(Rads) * Center.getRadius());
        Orbiter.setCenterY(Center.getCenterY() + Math.sin(Rads) * Center.getRadius());
        SetLinesAndAngles();
    }
    public void SetLinesAndAngles(){
        //Update lines and recalculate angles and displays them based
        //on the formula given in class.
        Line1.setStartX(Circle1.getCenterX());
        Line1.setStartY(Circle1.getCenterY());
        Line1.setEndX(Circle2.getCenterX());
        Line1.setEndY(Circle2.getCenterY());
        Line2.setStartX(Circle1.getCenterX());
        Line2.setStartY(Circle1.getCenterY());
        Line2.setEndX(Circle3.getCenterX());
        Line2.setEndY(Circle3.getCenterY());
        Line3.setStartX(Circle2.getCenterX());
        Line3.setStartY(Circle2.getCenterY());
        Line3.setEndX(Circle3.getCenterX());
        Line3.setEndY(Circle3.getCenterY());


        //Calculate variables needed for the assignment formula.
        double a = new Point2D(Circle3.getCenterX(), Circle3.getCenterY()).
                distance(Circle2.getCenterX(), Circle2.getCenterY());
        double b = new Point2D(Circle3.getCenterX(), Circle3.getCenterY()).
                distance(Circle1.getCenterX(), Circle1.getCenterY());
        double c = new Point2D(Circle2.getCenterX(), Circle2.getCenterY()).
                distance(Circle1.getCenterX(), Circle1.getCenterY());

        //Computing formula with newly calculated variables.
        double A = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
        double B = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
        double C = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

        //Setting text so that it shows properly.
        SetText(Circle1, Text1, A);
        SetText(Circle2, Text2, B);
        SetText(Circle3, Text3, C);

    }
    void SetText(Circle circle, Text text, double Angle){
        //Sets text object to be just a little above the circle to the right
        //and sets text to the correct angle in degrees rather than radians.
        text.setX(circle.getCenterX());
        text.setY(circle.getCenterY() - (circle.getRadius() + circle.getRadius()/2));
        text.setText(String.format("%.2f", Math.toDegrees(Angle)));
    }
}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class InvestmentCalc extends Application {

    //Initializing Textfield Variables
    TextField _investAmountField = new TextField();
    TextField _yearsField = new TextField();
    TextField _AnnualIntRateField = new TextField();
    TextField _futureValueDis = new TextField();

    //Initializing Label Variables
    Label _investAmountLabel = new Label("Investment Amount");
    Label _yearsLabel = new Label("Years");
    Label _AnnualIntRateLabel = new Label("Annual Interest Rate");

    //Initializing Button
    Button _Calculate = new Button("Calculate");
    @Override
    public void start(Stage _primaryStage) throws Exception{
        GridPane _grid = new GridPane(); //Setting up our GridPane and padding
        _grid.setPadding(new Insets(10, 10, 10, 10));
        _grid.add(_investAmountLabel, 0, 0);
        _grid.add(_investAmountField, 1, 0);
        _grid.add(_yearsLabel, 0, 1);
        _grid.add(_yearsField, 1, 1);
        _grid.add(_AnnualIntRateLabel, 0, 2);
        _grid.add(_AnnualIntRateField, 1, 2);
        _futureValueDis.setDisable(true); //Disabling our textField so its not editable
        _grid.add(_futureValueDis, 1, 3);
        _grid.add(_Calculate, 1, 4);


        //Check button press
        _Calculate.setOnAction(e->{
            //We run our calculation given the equation making sure to parse our textfields into the required data type
            //We divide our _AnnualIntRateField by 100 due to it being a percentage
            String _temp = String.valueOf(_futureValue(Double.parseDouble(_investAmountField.getText()),
                    Double.parseDouble(_AnnualIntRateField.getText())/100, Integer.parseInt(_yearsField.getText())));

            //Our _temp String holds our value of the calculation so we can then set it to our final answer
            _futureValueDis.setText(_temp);
        });


        //We setup our Scene to display our _grid
        Scene scene = new Scene(_grid, 500, 500);
        _primaryStage.setScene(scene);
        _primaryStage.setTitle("InvestmentCalc");
        _primaryStage.show();
    }

   //This function calculates our _futureValue. We divide our _AnnualIntRate by 12 due to the equation requiring monthly
    public double _futureValue(double _investAmount, double _AnnualIntRate, int _year){
        return _investAmount * (Math.pow((1 + (_AnnualIntRate/12)), _year * 12));
    }
}

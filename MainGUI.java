import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class MainGUI extends Application
{


    BorderPane borderPane;
    Stage stage;

    public MainGUI()
    {

        stage = new Stage();
        stage.setTitle("Welcome ");
        borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ECEFF1"), CornerRadii.EMPTY, Insets.EMPTY)));
        setNavigation();
        setGenerator();
        stage.setMaximized(true);
        stage.setScene(new Scene(borderPane));
        stage.show();
    }

    public void start(Stage primaryStage) throws Exception
    {
        //Can't use, already used in LoginForm....(Throws exception) Everything goes in the constructor, or in methods called by the constructor.
    }

    public void setNavigation()
    {
        VBox zipMenu = new VBox();
        zipMenu.setSpacing(20);
        zipMenu.setPadding(new Insets(30, 0, 0, 0));
        Text zipCodeText = new Text();
        zipCodeText.setText("Zip Codes");
        zipCodeText.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 27));
        zipCodeText.setTextAlignment(TextAlignment.CENTER);

        zipMenu.setAlignment(Pos.TOP_CENTER);
        zipMenu.getChildren().add(zipCodeText);

        //This code is dope.
        for (String a : ZipCode.zipList.keySet())
        {
            Button zipButton = new Button();
            zipButton.setBorder(Border.EMPTY);
            zipButton.setBackground(new Background(new BackgroundFill(Paint.valueOf("#E0E0E0"), CornerRadii.EMPTY, Insets.EMPTY)));
            zipButton.setOpacity(.87);
            zipButton.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            zipButton.setMaxWidth(Double.MAX_VALUE);
            //Sets the click action to be to call displayAccount, with current account passed in as value.
            zipButton.setOnAction(event -> displayAccount(ZipCode.zipList.get(a)));
            zipButton.setText(a);
            zipMenu.getChildren().add(zipButton);
        }

        ScrollPane mainScroll = new ScrollPane();
        mainScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        mainScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroll.setMaxWidth(400);
        mainScroll.setContent(zipMenu);

        borderPane.setLeft(mainScroll);
    }

    /**
     * Displays accounts, allows for editing of zipCode details.
     *
     * @param zipCode zipCode that gets displayed.
     */
    public void displayAccount(ZipCode zipCode)
    {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(100, 100, 100, 100));
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        Text genTitle = new Text();
        genTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 35));
        genTitle.setTextAlignment(TextAlignment.CENTER);
        genTitle.setText(zipCode.getZipcode());

        Text total = new Text();
        total.setText(zipCode.toString());
        total.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        gridPane.add(genTitle, 0, 0, 2, 1);
        gridPane.add(total, 1, 2);

        borderPane.setCenter(gridPane);
    }

    public void setGenerator()
    {
        VBox main = new VBox();
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(100, 100, 100, 0));
        pane.setVgap(20);

        Button saveAll = new Button("Save All Zip Code Data");
        saveAll.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        saveAll.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Zip Code Data");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Microsoft Word Document (*.doc)", "*.doc");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(stage);
            if (file != null)
            {
                Analyzer.saveToFile(file);
                Text saveText;
                GridPane grid;
                Stage saveStage = new Stage();
                saveStage.resizableProperty().setValue(Boolean.FALSE);
                saveStage.initStyle(StageStyle.UTILITY);
                grid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(25, 25, 25, 25));

                saveText = new Text("Save Successful");
                saveText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                grid.add(saveText, 0, 0);

                saveStage.setScene(new Scene(grid));
                saveStage.show();

            }
        });

        pane.add(saveAll, 0, 1);

        main.getChildren().add(pane);
        borderPane.setRight(main);

    }
}


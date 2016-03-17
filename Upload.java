import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * This method houses the installer for the program
 *
 * @author Ian Hoegen
 */
public class Upload extends Application
{
    public static void main(String args[])
    {
        launch(args);
    }

    public File selectedFile = null;

    /**
     * This method launches the GUI for the installer
     *
     * @param primaryStage Required for javaFX
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Text sceneTitle;
        Button btn;
        GridPane grid;
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setTitle("Choose File");
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        sceneTitle = new Text("Statistic Analyzer");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(sceneTitle, 0, 0, 2, 1);
        Text action = new Text("Choose File");
        action.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        grid.add(action, 0, 1, 2, 1);

        btn = new Button("Choose directory");
        Button next = new Button("Next");
        btn.setAlignment(Pos.CENTER_LEFT);
        grid.add(btn, 0, 3, 1, 1);
        next.setAlignment(Pos.CENTER_RIGHT);
        grid.add(next, 15, 8);

        Text fileLocation = new Text("");
        action.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        grid.add(fileLocation, 0, 4, 2, 1);

        btn.setOnAction(event -> {
            String USER_HOME = System.getProperty("user.home");
            File dir = new File(USER_HOME);
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Upload CSV");
            chooser.setInitialDirectory(dir);
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            chooser.getExtensionFilters().add(extFilter);
            File file = chooser.showOpenDialog(primaryStage);
            if (file != null)
            {
                selectedFile = file;
                fileLocation.setText(selectedFile.toString());
            }
        });
        next.setOnAction(event -> {
            if (selectedFile != null)
            {
                primaryStage.close();
                new Analyzer(selectedFile);
            }

        });
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }


}

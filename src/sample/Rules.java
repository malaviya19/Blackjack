package sample;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.paint.ImagePattern;
import javafx.application.Application;
import java.io.FileNotFoundException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import java.io.File;

public class Rules extends Application implements Runnable{
    GridPane root = new GridPane();
    HBox cards = new HBox();
    VBox Rules = new VBox();


    public void StartUp() throws FileNotFoundException {
        Stage primaryStage  = new Stage();

        Button back = new Button("BACK");
        back.setShape(new Circle(3));
        back.setMaxSize(130,50);
        back.setMinSize(130,50);
        back.setStyle("-fx-background-color: Black");
        back.setTextFill(Color.WHITE);
        back.setFont(new Font(14));

        back.setOnAction((event) -> {
            Runnable screenStart = new StartScreen();
            Thread screen = new Thread(screenStart);
            primaryStage.close();
            screen.run();
        });

        // creates the background
        Image img = new Image("sample/resources/images/table.png");
        Rectangle upBG = new Rectangle(520, 640);
        upBG.setFill(new ImagePattern(img));

        // displays to playing cards in the top-right corner
        ImageView imageJack1 = new ImageView("sample/resources/Cards/11.png");
        ImageView imageJack2 = new ImageView("sample/resources/Cards/50.png");
        imageJack1.setRotate(imageJack1.getRotate() - 10);
        imageJack2.setRotate(imageJack2.getRotate() + 10);
        cards.setAlignment(Pos.TOP_RIGHT);
        cards.getChildren().addAll(imageJack1, imageJack2);


        root.getChildren().addAll(upBG, getRules(), cards, back);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rules");
        primaryStage.show();
    }

    // Function: access a txt file and displays the text in a vBox
    private VBox getRules() {
        File file = new File("src/sample/Rules.txt");
        Scanner scanFile = null;
        try {
            scanFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // searches through every word
        while (scanFile.hasNext()) {
            //adds each line found into the vBox
            Text rule = new Text(scanFile.nextLine());
            rule.setFill(Color.WHITE);
            rule.setFont(Font.font(15));
            Rules.getChildren().add(rule);
        }
        scanFile.close();

        return Rules;
    }

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    StartUp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import java.awt.*;
import java.io.*;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.application.Platform;
import javafx.scene.shape.Circle;


public class StartScreen extends Application implements Runnable {
    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    startS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

    };
    public void startS() throws FileNotFoundException {
        Stage primaryStage = new Stage();
        GridPane pane = new GridPane();
        Button start = new Button("START");
        Button rules = new Button("INSTRUCTIONS");
        Button highScores = new Button("HIGH SCORES");
        Button exit = new Button("EXIT");

        start.setShape(new Circle(3));
        start.setMaxSize(130,50);
        start.setMinSize(130,50);
        start.setStyle("-fx-background-color: Black");
        start.setTextFill(Color.WHITE);
        start.setFont(new Font(14));

        rules.setShape(new Circle(1.5));
        rules.setMaxSize(130,50);
        rules.setMinSize(130,50);
        rules.setStyle("-fx-background-color: Black");
        rules.setTextFill(Color.WHITE);
        rules.setFont(new Font(14));

        highScores.setShape(new Circle(1.5));
        highScores.setMaxSize(130,50);
        highScores.setMinSize(130,50);
        highScores.setStyle("-fx-background-color: Black");
        highScores.setTextFill(Color.WHITE);
        highScores.setFont(new Font(14));

        exit.setShape(new Circle(1.5));
        exit.setMaxSize(130,50);
        exit.setMinSize(130,50);
        exit.setStyle("-fx-background-color: Black");
        exit.setTextFill(Color.WHITE);
        exit.setFont(new Font(14));

        start.setOnAction((event) -> {
            Runnable gameStart = new BlackjackApp();
            Thread game = new Thread(gameStart);
            primaryStage.close();
            game.run();
        });

        rules.setOnAction((event) -> {
            Runnable ruleStart = new Rules();
            Thread rule = new Thread(ruleStart);
            primaryStage.close();
            rule.run();
        });

        highScores.setOnAction((event) -> {
            Score score = new Score();
            primaryStage.close();
            score.checkBal();
            System.exit(0);
        });

        exit.setOnAction((event) -> {
            System.exit(0);
        });

        Image image = new Image("sample/resources/images/blackjack.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,new BackgroundSize(450,600,
                true,true,true,true));

        Background background = new Background(backgroundImage);
        pane.setBackground(background);


        //pane.setMaxSize(450,600);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.setAlignment(Pos.CENTER_RIGHT);

        pane.add(start,1,0);
        pane.add(rules,1,3);
        pane.add(highScores,1,5);
        pane.add(exit,1,7);

        Scene scene = new Scene(pane);
        primaryStage.setMaxHeight(750);
        primaryStage.setMaxWidth(600);
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

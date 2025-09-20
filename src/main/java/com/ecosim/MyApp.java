package com.ecosim;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyApp extends Application {
    public Map map = new Map(50, 10);
    @Override
    public void start(Stage stage) {

        TextArea simAscii = new TextArea("");
        simAscii.setEditable(false); 
        simAscii.setFont(new Font("Monospaced Bold", 15));
        simAscii.setStyle("-fx-background-color: black; -fx-text-fill: darkblue;");
        VBox root = new VBox(simAscii);
        VBox.setVgrow(simAscii, Priority.ALWAYS); 
        simAscii.setMaxWidth(Double.MAX_VALUE); 
        simAscii.setMaxHeight(Double.MAX_VALUE); 

        Scene scene = new Scene(root, 1000, 1000);
        //root.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(0), Insets.EMPTY)));

        stage.setTitle("hopefully this works");
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            long lastUpdate = 0;
            int frameCount = 0;
            boolean flip = true;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 64_000_000) { 
                    simAscii.setText(map.toString());
                    map.Update();
                    frameCount++;
                    lastUpdate = now;
                }
            }
        };
        timer.start();
        // map.AddCell(new Cell(new Coordinate(0, 0, map), "A1"));
        // map.AddCell(new Cell(new Coordinate(0, 1, map), "A2"));
    }
}
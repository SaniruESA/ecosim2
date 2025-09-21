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
    public Map map = new Map(50, 25);
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
                if (now - lastUpdate >= 83_333_333) { 
                    simAscii.setText(map.toString());
                    frameCount++;
                    lastUpdate = now;
                }
            }
        };
        timer.start();
        map.AddCell(new GrassCell(new Coordinate(1, 0, map)));
        map.AddCell(new GrassCell(new Coordinate(2, 1, map)));
        map.AddCell(new GrassCell(new Coordinate(3, 2, map)));
        map.AddCell(new GrassCell(new Coordinate(4, 3, map)));
        map.AddCell(new GrassCell(new Coordinate(5, 4, map)));
        map.AddCell(new WaterSourceCell(new Coordinate(14, 5, map), 7));
    }
}
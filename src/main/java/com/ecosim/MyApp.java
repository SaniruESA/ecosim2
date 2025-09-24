package com.ecosim;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyApp extends Application {
    public Map map = new Map(50, 50);
    public CreatureManager creatureManager = new CreatureManager(map);
    /** 
     * @param stage
     */
    @Override
    public void start(Stage stage) {
    final int size = 15;
    Canvas mapCanvas = new Canvas(map.cols * size, map.rows * size);
    GraphicsContext gc = mapCanvas.getGraphicsContext2D();
    gc.setFont(new Font("Monospaced", size));

    Canvas topCanvas = new Canvas(map.cols * size, map.rows * size);
    GraphicsContext topGc = topCanvas.getGraphicsContext2D();
    topGc.setFont(new Font("Monospaced", size));

    Button pauseBtn = new Button("Recalculate Water");
    HBox controls = new HBox(pauseBtn);

    javafx.scene.layout.StackPane stack = new javafx.scene.layout.StackPane(mapCanvas, topCanvas);
    VBox root = new VBox(controls, stack);
    VBox.setVgrow(stack, Priority.ALWAYS);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));

    Scene scene = new Scene(root, Math.max(800, map.cols * size), Math.max(600, map.rows * size));
    stage.setTitle("hopefully this works");
    stage.setScene(scene);
    stage.show();
    

    pauseBtn.setOnAction(e -> map.recalculateWater());

    AnimationTimer timer = new AnimationTimer() {
        long lastUpdate = 0;
        @Override
        public void handle(long now) {
            if (now - lastUpdate >= 83_333_333) { 
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());
                for(int y = 0; y < map.rows; y++){
                    for(int x = 0; x < map.cols; x++){
                        Cell c = map.getCellAt(x, y);
                        String text = (c == null) ? "  " : c.toString();
                        Color color = Color.WHITE;
                        if(c != null){
                            c.Update();
                            color = c.getColor();
                        }
                        gc.setFill(color);
                        gc.fillText(text, x * size, (y + 1) * size - 4);
                    }
                }
                topGc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());
                for(Creature c : creatureManager.getCreatures()){
                    c.Update();
                    String text = c.toString();
                    Color color = c.getColor();
                    topGc.setFill(color);
                    topGc.fillText(text, c.position.getX() * size, (c.position.getY() + 1) * size - 4);
                }
                
                
                lastUpdate = now;
            }
        }
        };
        timer.start();
        map.AddCellInRange(0, 0, 20, 5, GrassCell.class);
        map.AddCell(new WaterSourceCell(new Coordinate(17, 10, map), 10, true));
        creatureManager.AddCreature(new Creature(new Coordinate(0, 20, map)));
    }
}
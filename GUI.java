import Game.Game;
import Game.Point;
import Game.Snake;
import Game.Direction;
import LinkedList.LinkedListNode;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public final int MAP_WIDTH=12;
    public final int MAP_HEIGHT=12;

    //javafx gui component
    Scene scene;
    Map map;
    Label label;

    //timer
    Timer t;

    //game logic
    Game game;

    @Override
    public void start(Stage stage) throws Exception {
        game = new Game(MAP_WIDTH, MAP_HEIGHT);

        map = new Map(MAP_WIDTH, MAP_WIDTH);
        map.setLayoutX(5);
        map.setLayoutY(60);

        label = new Label();
        label.setLayoutX(5);
        label.setLayoutY(5);
        label.setPrefWidth(300);
        label.setPrefHeight(30);
        label.setText("Score: 0");
        label.setStyle("-fx-font-family:\"Arial\";-fx-background-color:black;-fx-font-size:30px;-fx-text-fill:white;");

        scene = new Scene(new Group(label, map));
        scene.setOnKeyPressed(this::keyPress);
        scene.setFill(Color.BLACK);

        stage.setScene(scene);
        stage.setHeight(480);
        stage.setWidth(390);
        stage.setTitle("Game");
        stage.setResizable(false);
        stage.show();

        t = new Timer(this);
        t.start();
    }

    private void keyPress(KeyEvent e) {
        //System.out.println(e.getCode().getCode());
        //set up the direction that user put
        if (e.getCode() == KeyCode.W) {
            game.setDirection(Direction.Up);
        } else if (e.getCode() == KeyCode.A) {
            game.setDirection(Direction.Left);
        } else if (e.getCode() == KeyCode.S) {
            game.setDirection(Direction.Down);
        } else if (e.getCode() == KeyCode.D) {
            game.setDirection(Direction.Right);
        }
    }
    

    @Override
    public void stop() throws Exception {
        t.Stop();
    }
}

class Map extends Pane {
    private Rectangle[][] rect;
    private int map_width;
    private int map_height;

    private final int TILE_SIZE = 30;
    private final int TILE_GAP = 1;

    public Map(int w, int h) {
        this.setPrefWidth(w * TILE_SIZE + TILE_GAP);
        this.setPrefHeight(h * TILE_SIZE + TILE_GAP);
        this.setStyle("-fx-background-color:black;-fx-border-color:green;");

        map_width = w;
        map_height = h;
        rect = new Rectangle[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                rect[y][x] = new Rectangle();
                rect[y][x].setWidth(TILE_SIZE - TILE_GAP);
                rect[y][x].setHeight(TILE_SIZE - TILE_GAP);
                rect[y][x].setLayoutX(x * TILE_SIZE + TILE_GAP);
                rect[y][x].setLayoutY(y * TILE_SIZE + TILE_GAP);

                this.getChildren().add(rect[y][x]);
            }
        }
    }

    public void setTile(int x, int y, Color color) {
        if (x >= 0 && x < map_width && y >= 0 && y < map_height) {
            rect[y][x].setFill(color);
        }
    }

    public void setSnake(Snake s) {
        LinkedListNode node = s.head();
        Point pt = (Point) node.data;
        this.setTile(pt.x, pt.y, Color.DARKCYAN);

        node = node.next;
        while (node != null) {
            pt = (Point) node.data;
            this.setTile(pt.x, pt.y, Color.CYAN);
            node = node.next;
        }
    }

    public void setFood(Point pt) {
        this.setTile(pt.x, pt.y, Color.RED);
    }

    public void clear() {
        for (int y = 0; y < map_height; y++) {
            for (int x = 0; x < map_width; x++) {
                rect[y][x].setFill(Color.BLACK);
            }
        }
    }

}

class Timer extends Thread {
    private boolean quit = false;
    private GUI gui;

    public Timer(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        while (!quit) {
            //
            Platform.runLater(new Runnable(){
                public void run(){
                    gui.game.move();
                    if(gui.game.isGameover()) {
                        gui.label.setText("Game Over");
                        quit = true;
                        return;
                    }

                    //update gui
                    gui.map.clear();
                    gui.map.setSnake(gui.game.getSnake());
                    gui.map.setFood(gui.game.getFood());
                    gui.label.setText("Score " + gui.game.getScore());
                }
            });

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void Stop() {
        quit = true;
    }

}

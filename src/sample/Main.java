package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello Scene Builder");
//        Group root = new Group();
//        Canvas canvas = new Canvas(300,250);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        drawShapes(gc);
//        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root,1000,1000));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);

    }


    public static void main(String[] args) {
        launch(args);
//        int[][] sampleMap = {
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//        };
//        Map map = new Map(10,10);
//        map.initMap(sampleMap);
//        Snake snake = new Snake(new Cell(4,9));
//        AlgorithmFactory algorithmFactory = new AlgorithmFactory();
//        for(int i=0; i<4; i++) {
//            map.generateFood();
//            Algorithm algorithm = algorithmFactory.getAlgorithm("BFS");
//            LinkedList<Cell> result = algorithm.generate(map, snake);
//            Iterator<Cell> iterator = result.iterator();
//            while(iterator.hasNext()) {
//                Cell currentCell = iterator.next();
//                System.out.println(currentCell.getRow() + " " + currentCell.getCol());
//            }
//            snake = new Snake(map.getFood());
//        }
    }
}

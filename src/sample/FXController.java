package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import sample.algorithm.BreadthFirstSearch;
import sample.graphics.Visualizer;
import sample.map.Map;

import java.util.Timer;
import java.util.TimerTask;

public class FXController
{
    // private Map map = new Map();
    private Visualizer visualizer = new Visualizer(null, null);
    @FXML
    private Canvas canvas;

    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField inputText;

    // The reference of outputText will be injected by the FXML loader
    @FXML
    private TextArea outputText;

    // Add a public no-args constructor
    public FXController()
    {
    }

    @FXML
    private void initialize()
    {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setImageSmoothing(false);
    }

    @FXML
    private void printOutput()
    {
        outputText.setText(inputText.getText());
    }

    boolean a = true;
    @FXML
    private void moveSnake(ActionEvent event) {
        if (a) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    GraphicsContext graphics = canvas.getGraphicsContext2D();
                    visualizer.draw(graphics);
                }
            };
            timer.scheduleAtFixedRate(task, 0, 100);
            a = false;
        }
//        GraphicsContext graphics = canvas.getGraphicsContext2D();
//        visualizer.draw(graphics);
    }
}
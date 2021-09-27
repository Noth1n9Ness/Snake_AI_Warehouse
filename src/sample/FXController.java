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
    }

    @FXML
    private void printOutput()
    {
        outputText.setText(inputText.getText());
    }

    @FXML
    private void moveSnake(ActionEvent event) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        visualizer.draw(graphics);
    }
}
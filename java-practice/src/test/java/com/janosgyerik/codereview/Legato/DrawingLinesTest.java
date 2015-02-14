package com.janosgyerik.codereview.Legato;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DrawingLinesTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Legato's Lines");

        Line redLine = new Line(10, 10, 200, 10) {
            {
                setStroke(Color.RED);
                setStrokeWidth(3);
                getStrokeDashArray().addAll(10d, 5d, 15d, 5d, 20d);
                setStrokeDashOffset(0);
            }
        };
        Line whiteLine = new Line(10, 30, 200, 30) {
            {
                setStroke(Color.WHITE);
                setStrokeLineCap(StrokeLineCap.ROUND);
                setStrokeWidth(10);
            }
        };
        Line blueLine = new Line(10, 50, 200, 50) {
            {
                setStroke(Color.BLUE);
                setStrokeLineCap(StrokeLineCap.BUTT);
                setStrokeWidth(5);
            }
        };

        Slider slider = new Slider(0, 100, 0) {
            {
                setLayoutX(10);
                setLayoutY(95);
            }
        };

        Text offsetText = new Text("Stroke Dash Offset: 0") {
            {
                setX(10);
                setY(80);
                setStroke(Color.WHITE);
            }
        };

        slider.valueProperty().addListener(
                (ov, curVal, newVal) -> offsetText.setText(
                        "Stroke Dash Offset: " + Math.round(slider.getValue()))
        );
        redLine.strokeDashOffsetProperty().bind(slider.valueProperty());

        Group root = new Group();

        ObservableList<Node> children = root.getChildren();
        children.add(redLine);
        children.add(whiteLine);
        children.add(blueLine);
        children.add(slider);
        children.add(offsetText);

        Scene scene = new Scene(root, 300, 150, Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
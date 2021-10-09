package com.example.helloworld;


import javafx.fxml.FXMLLoader;
import java.util.HashSet;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.text.Font;




public class HelloApplication extends Application implements EventHandler<ActionEvent> {

    private Label textTurn;

    private Button[][] matrix;
    private PlayerOne playerOne = new PlayerOne();
    private PlayerTwo playerTwo = new PlayerTwo();

    @Override
    public void start(Stage window) {

        this.matrix = new Button[3][3];

        BorderPane layout = new BorderPane();

        this.textTurn = new Label("Turn: X");
        this.textTurn.setFont(Font.font("Monospaced", 40));


        layout.setTop(this.textTurn);


        GridPane board = new GridPane();
        board.setHgap(10);
        board.setVgap(10);
        board.setPadding(new Insets(10, 10, 10, 10));


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button(" ");
                btn.setFont(Font.font("Monospaced", 40));
                String id = String.format("%d %d", i, j);
                btn.setId(id);
                btn.setOnAction(this);
                this.matrix[i][j] = btn;
                board.add(btn, i, j);
            }
        }



        layout.setCenter(board);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }




    public static void main(String[] args) {
        launch(HelloApplication.class);
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        Button sourceButton = (Button) actionEvent.getSource();

        String[] parts = sourceButton.getId().split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);




        if (this.textTurn.getText().contains("X") && this.matrix[x][y].getText().equals(" ")) {

            sourceButton.setText(this.playerOne.getTurn());
            matrix[x][y].setText(this.playerOne.getTurn());
            checkForWin();
            this.textTurn.setText("Turn: O");

        } else if (this.textTurn.getText().contains("O") && this.matrix[x][y].getText().equals(" ")) {

            sourceButton.setText(this.playerTwo.getTurn());
            matrix[x][y].setText(this.playerTwo.getTurn());
            checkForWin();
            this.textTurn.setText("Turn: X");

        }


        int countX = 0;
        int countO = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j].getText().equals("X")) {
                    countX++;
                } else if (matrix[i][j].getText().equals("O")) {
                    countO++;
                }
            }
        }

        if (countX == 5 && countO == 4) {
            this.textTurn.setText("The end!");
        }


        if (checkForWin()) {
            this.textTurn.setText("The end!");
        }


    }

    public boolean checkForWin() {

        boolean foundWinner = false;

        String row1 = matrix[0][0].getText() + matrix[0][1].getText() + matrix[0][2].getText();
        String row2 = matrix[1][0].getText() + matrix[1][1].getText() + matrix[1][2].getText();
        String row3 = matrix[2][0].getText() + matrix[2][1].getText() + matrix[2][2].getText();
        String row4 = matrix[0][0].getText() + matrix[1][0].getText() + matrix[2][0].getText();
        String row5 = matrix[0][1].getText() + matrix[1][1].getText() + matrix[2][1].getText();
        String row6 = matrix[0][2].getText() + matrix[1][2].getText() + matrix[2][2].getText();
        String row7 = matrix[0][0].getText() + matrix[1][1].getText() + matrix[2][2].getText();
        String row8 = matrix[0][2].getText() + matrix[1][1].getText() + matrix[2][0].getText();


        if (row1.equals("XXX") || row1.equals("OOO")) {
            foundWinner = true;


        } else if (row2.equals("XXX") || row2.equals("OOO")) {
            foundWinner = true;

        } else if (row3.equals("XXX") || row3.equals("OOO")) {
            foundWinner = true;

        } else if (row4.equals("XXX") || row4.equals("OOO")) {
            foundWinner = true;

        } else if (row5.equals("XXX") || row5.equals("OOO")) {
            foundWinner = true;

        } else if (row6.equals("XXX") || row6.equals("OOO")) {
            foundWinner = true;

        } else if (row7.equals("XXX") || row7.equals("OOO")) {
            foundWinner = true;

        } else if (row8.equals("XXX") || row8.equals("OOO")) {
            foundWinner = true;

        }
        return foundWinner;
    }
}
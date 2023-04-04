package com.example.testscenebuilder;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class HelloController {
    private enum Operation {
        ADD, SUB, MUL, DIV, EQ
    }

    private double curDig;
    private  Operation curOperation = Operation.EQ;

    @FXML
    private TextField display;
    @FXML
    private Button buttonMul;
    @FXML
    private Button buttonPlus;
    @FXML
    private Button buttonMinus;
    @FXML
    private Button buttonDiv;
    @FXML
    private Button buttonEq;

    @FXML
    public void initialize() {
        display.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!".0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
                if ((Objects.equals(keyEvent.getCharacter(), ".")) && (display.getText().contains(".") || Objects.equals(display.getText(), ""))) {
                    System.out.print(keyEvent.getCharacter());
                    keyEvent.consume();
                }
                if ((Objects.equals(keyEvent.getCharacter(), "0")) && (Objects.equals(display.getText(), "0"))) {
                    System.out.print(keyEvent.getCharacter());
                    keyEvent.consume();
                }
                        if (Objects.equals(keyEvent.getCharacter(), "*"))
                        {
                            buttonMul.fire();
                        }
                        if (Objects.equals(keyEvent.getCharacter(), "+"))
                        {
                            buttonPlus.fire();
                        }
                        if (Objects.equals(keyEvent.getCharacter(), "-"))
                        {
                            buttonMinus.fire();
                        }
                        if (Objects.equals(keyEvent.getCharacter(), "/"))
                        {
                            buttonDiv.fire();
                        }
                        if (Objects.equals(keyEvent.getCharacter(), "="))
                        {
                            buttonEq.fire();
                        }
            }
        });
    }



    @FXML
    protected void onButton1() {
        setDig("1");
    }


    @FXML
    protected void onButton2() {
        setDig("2");
    }

    @FXML
    protected void onButton3() {
        setDig("3");
    }

    @FXML
    protected void onButton4() {
        setDig("4");
    }

    @FXML
    protected void onButton5() {
        setDig("5");
    }
    @FXML
    protected void onButton6() {
        setDig("6");
    }
    @FXML
    protected void onButton7() {
        setDig("7");
    }
    @FXML
    protected void onButton8() {
        setDig("8");
    }
    @FXML
    protected void onButton9() {
        setDig("9");
    }
    @FXML
    protected void onButton0() {
        setDig("0");
    }

    @FXML
    protected void onPlusButton() {
        setOper(Operation.ADD);
    }


    @FXML
    protected void onMinusButton() {
        setOper(Operation.SUB);
    }


    @FXML
    protected void onDivButton() {
        setOper(Operation.DIV);
    }


    @FXML
    protected void onMulButton() {
        setOper(Operation.MUL);
    }



    @FXML
    protected void onDelButton() {
        curDig = 0.0;
        curOperation = Operation.EQ;
        display.setText("0");

    }

    @FXML
    protected void onEqButton() {
        equalAction(display);

    }
    @FXML
    protected void onHelloButtonClick() {
        display.setText("Welcome to JavaFX Application!");
    }


    private void equalAction(TextField text) {

        if (text.getText().isEmpty()) {
            return;
        }

        double result = 0.0;

        double secondDig = Double.parseDouble(text.getText());
        if (curOperation == Operation.DIV && secondDig == 0.0) {
            text.setText("");
            curDig=0.0;
            curOperation = Operation.EQ;
        }

        switch (curOperation) {
            case SUB -> result = curDig - secondDig;
            case DIV -> result = curDig / secondDig;
            case MUL -> result = curDig * secondDig;
            case ADD -> result = curDig + secondDig;
            case EQ -> {
                return;
            }
        }

        curOperation = Operation.EQ;
        curDig = result;

        if (curDig == Math.round(curDig)) {
            text.setText(Math.round(curDig) + "");
            return;
        }

        text.setText(curDig + "");
    }

    protected void setDig(String dig) {
        String curText = display.getText();
        if (curText.startsWith("0") && curText.length() == 1) {
            display.setText(dig);
            return;
        }

        display.setText(curText + dig);
    }


    protected void setOper(Operation operation) {
        if (display.getText().isEmpty()) {
            return;
        }
        if (curOperation != Operation.EQ) {
            equalAction(display);
        }

        curDig = Double.parseDouble(display.getText());
        curOperation = operation;
        display.setText("");
    }

}
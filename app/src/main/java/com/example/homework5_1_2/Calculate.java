package com.example.homework5_1_2;

import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculate implements View.OnClickListener {
    private TextView text;
    private NumerCreator numerCreator;
    private BigDecimal numberOne;
    private BigDecimal numberTwo;

    private String operation;
    public Calculate (NumerCreator numerCreator, View ... buttons){
        text = numerCreator.getText();
        this.numerCreator = numerCreator;
        for(int i = 0; i < buttons.length; i++) {

            buttons[i].setOnClickListener(this);

        }
    }
    @Override
    public void onClick(View v) {
        numerCreator.setNum("");
        switch (v.getId()){
            case (R.id.btnPlus):{
                numberOne = new BigDecimal(text.getText().toString());
                operation = "+";
                text.setText("");
                break;
            }
            case (R.id.btnMinus):{
                numberOne = new BigDecimal(text.getText().toString());
                operation = "-";
                text.setText("");
                break;
            }
            case (R.id.btnMulty):{
                numberOne = new BigDecimal(text.getText().toString());
                operation = "*";
                text.setText("");
                break;
            }
            case (R.id.btnDel):{
                numberOne = new BigDecimal(text.getText().toString());
                operation = "/";
                text.setText("");
                break;
            }
            case (R.id.btnpercent):{
                if(!(numberOne.equals(0))){
                    numberTwo = new BigDecimal(text.getText().toString());
                    numberTwo = (numberTwo.multiply(numberOne)).movePointLeft(2);
                    text.setText(numberTwo.toString());
                    break;
                }
            }
            case (R.id.btnEqual):{
                if(!(numberOne.equals(0))) {
                    numberTwo = new BigDecimal(text.getText().toString());
                    switch (operation){
                        case("+"):{
                            text.setText((numberTwo.add(numberOne)).toString());
                            break;
                        }
                        case ("-"):{
                            text.setText(numberOne.subtract(numberTwo).toString());
                            break;
                        }
                        case ("*"):{
                            text.setText(numberTwo.multiply(numberOne).toString());
                            break;
                        }
                        case ("/"):{
                            text.setText(numberOne.divide(numberTwo, 5, RoundingMode.CEILING).toString());
                            break;
                        }
                    }
                }
                numberOne = new BigDecimal(0);
                numberTwo = new BigDecimal(0);
                operation = "";
                break;

            }
            case (R.id.newBtnPlus):{
                numberOne = new BigDecimal(text.getText().toString());
                operation = "+";
                text.setText("");
                break;
            }
            case (R.id.newBtnMinus):{
                numberOne = new BigDecimal(text.getText().toString());
                operation = "-";
                text.setText("");
                break;
            }
            case (R.id.newBtnMulty):{
                numberOne = new BigDecimal(text.getText().toString());
                operation = "*";
                text.setText("");
                break;
            }
            case (R.id.newBtnDel):{
                numberOne = new BigDecimal(text.getText().toString());
                operation = "/";
                text.setText("");
                break;
            }
            case (R.id.newBtnpercent):{
                if(!(numberOne.equals(0))){
                    numberTwo = new BigDecimal(text.getText().toString());
                    numberTwo = (numberTwo.multiply(numberOne)).movePointLeft(2);
                    text.setText(numberTwo.toString());
                    break;
                }
            }
            case (R.id.newBtnEqual):{
                if(!(numberOne.equals(0))) {
                    numberTwo = new BigDecimal(text.getText().toString());
                    switch (operation){
                        case("+"):{
                            text.setText((numberTwo.add(numberOne)).toString());
                            break;
                        }
                        case ("-"):{
                            text.setText(numberOne.subtract(numberTwo).toString());
                            break;
                        }
                        case ("*"):{
                            text.setText(numberTwo.multiply(numberOne).toString());
                            break;
                        }
                        case ("/"):{
                            text.setText(numberOne.divide(numberTwo, 5, RoundingMode.CEILING).toString());
                            break;
                        }
                    }
                }
                numberOne = new BigDecimal(0);
                numberTwo = new BigDecimal(0);
                operation = "";

            }

        }
    }
}

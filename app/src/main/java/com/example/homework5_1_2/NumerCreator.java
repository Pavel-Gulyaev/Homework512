package com.example.homework5_1_2;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class NumerCreator implements View.OnClickListener {

    public void setNum(String num) {
        this.num = num;
    }

    private String num = "";
    private boolean dotted;

    public TextView getText() {
        return text;
    }

    private TextView text;


    public NumerCreator (View ... buttons){

        for(int i = 0; i < buttons.length; i++) {
            if (buttons[i].getId()==R.id.text){
                text = (TextView) buttons[i];
            } else {
                buttons[i].setOnClickListener(this);
            }
        }
    }

    public String getNum() {
        return num;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case (R.id.btnC ):{
                if(!(num.equals(""))) {
                    num = num.substring(0, num.length() - 1);
                }
            }

            case (R.id.btndot): {
                if (!dotted) {
                    num += ((Button) v).getText().toString();
                    dotted = true;
                }
                break;
            }
            case (R.id.btnsign): {
                if (num.contains("-")) {
                    num = num.substring(1, num.length());
                } else {
                    num = "-" + num;
                }


                break;
            }
            case (R.id.newBtnC ):{
                if(!(num.equals(""))) {
                    num = num.substring(0, num.length() - 1);
                }
            }

            case (R.id.newBtndot): {
                if (!dotted) {
                    num += ((Button) v).getText().toString();
                    dotted = true;
                }
                break;
            }
            case (R.id.newBtnsign): {
                if (num.contains("-")) {
                    num = num.substring(1, num.length());
                } else {
                    num = "-" + num;
                }


                break;
            }
            default: {
                if (num.equals("0") || num.equals("-0")) {
                    num = "";
                }
                num += ((Button) v).getText().toString();

                if (num.equals("0")) {
                    num = "";
                }
            }
        }
        text.setText(num);
    }
}

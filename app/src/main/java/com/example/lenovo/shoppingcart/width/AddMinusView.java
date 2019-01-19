package com.example.lenovo.shoppingcart.width;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.shoppingcart.R;

public class AddMinusView extends LinearLayout {
    private TextView addTV,minusTv;
    private EditText numTv;
    private int num;

    public AddMinusView(Context context) {
        this(context,null);
    }

    public AddMinusView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddMinusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        final View view = LayoutInflater.from(context).inflate(R.layout.add_minus, this, true);
        addTV = view.findViewById(R.id.add);
        minusTv = view.findViewById(R.id.minus);
        numTv = view.findViewById(R.id.et_num);
        addTV.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View v) {
                num = Integer.parseInt(numTv.getText().toString());
                num++;
                numTv.setText(num +"");
                if (addMinsCallback!=null){
                    addMinsCallback.numCallback(num);
                }
            }
        });
        minusTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num = Integer.parseInt(numTv.getText().toString());
                num--;
                if(num==0){
                    num=1;
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }
                numTv.setText(num+"");
                if (addMinsCallback!=null){
                    addMinsCallback.numCallback(num);
                }
            }
        });
    }

    public void setNum(int num) {
       numTv.setText(num+"");
    }

    private AddMinsCallback addMinsCallback;

    public void setAddMinsCallback(AddMinsCallback addMinsCallback) {
        this.addMinsCallback = addMinsCallback;
    }

    public interface AddMinsCallback{
        void numCallback(int num);
    }

}

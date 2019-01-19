package com.example.shopping2.width;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping2.R;

public class AddminsView extends LinearLayout {

    private TextView min,max;
    private EditText ed_num;
    private int num=1;
    public AddminsView(Context context) {
        this(context,null);
    }

    public AddminsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddminsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        final View view = LayoutInflater.from(context).inflate(R.layout.addmin, this, true);
        min = view.findViewById(R.id.min);
        max = view.findViewById(R.id.max);
        ed_num=view.findViewById(R.id.ed_num);
        min.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num=Integer.parseInt(ed_num.getText().toString());
                num--;
                if(num==0){
                    num=1;
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }
                ed_num.setText(num+"");
                adadmin.addmins(num);
            }
        });
        max.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num=Integer.parseInt(ed_num.getText().toString());
                num++;
                ed_num.setText(num+"");
                adadmin.addmins(num);
            }
        });
    }

    public void setNum(int num) {
        ed_num.setText(num+"");
    }

    private Adadmin adadmin;

    public void setAdadmin(Adadmin adadmin) {
        this.adadmin = adadmin;
    }

    public interface Adadmin{
        void addmins(int num);
    }
}

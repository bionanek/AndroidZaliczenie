package com.example.artur.timeger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.artur.timeger.interfaces.IButtonClickedListener;
import com.example.artur.timeger.model.Quest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bionanek on 30-11-2016.
 */

public class QuestBox extends TableLayout
{

    // stuff important so that the button click
    // (and other events which will happen here) may be handled in MainActivity
    private List<IButtonClickedListener> listeners = new ArrayList<>();
    public void addListener(IButtonClickedListener listener)
    {
        listeners.add(listener);
    }

    private void notifyListeners(String value)
    {
        for(IButtonClickedListener listener : listeners)
        {
            listener.onQuestBoxButtonClick(value);
        }
    }

    // the end of that important stuff...
    // contructor below and the rest of methods...
    // ...they are awesome

    public QuestBox(Context con, TableLayout.LayoutParams lp, Quest quest)
    {
        super(con);

        this.addView(createFirstRow(con, quest), lp);
        this.addView(createSecondRow(con, quest), lp);
        this.addView(createThirdRow(con, quest), lp);
    }

    private TableRow createFirstRow(Context con, Quest quest)
    {
        TableRow firstRowInKAFEL = new TableRow(con);
        firstRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));

        TableLayout firstColumnInFirstRowKAFEL = new TableLayout(con);
        TableRow.LayoutParams paramsColumnInFirstRowKAFEL = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        );
        firstColumnInFirstRowKAFEL.setLayoutParams(paramsColumnInFirstRowKAFEL);
        firstRowInKAFEL.addView(firstColumnInFirstRowKAFEL);

        //TODO if(yyyy/MM.dd)today.date) -> dateformat= HH:mm:ss/else yyyy/MM/ddd
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //2016/11/16 12:08
        Date date = null;
        try
        {
            date = dateFormat.parse(quest.getQuestDate());
        }
        catch (Exception ex)
        {
            Log.e("Data", ex.toString());
        }
        System.out.println(dateFormat.format(date));

        TextView txtdateOfExecution = new TextView(con);
        txtdateOfExecution.setTypeface(null, Typeface.BOLD_ITALIC);
        txtdateOfExecution.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        txtdateOfExecution.setText(dateFormat.format(date));
        txtdateOfExecution.setLayoutParams(new TableLayout.LayoutParams
                (
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
        txtdateOfExecution.setGravity(Gravity.CENTER);

        firstColumnInFirstRowKAFEL.addView(txtdateOfExecution);

        TableLayout secondColumnInFirstRowKAFEL = new TableLayout(con);
        secondColumnInFirstRowKAFEL.setLayoutParams(paramsColumnInFirstRowKAFEL);
        firstRowInKAFEL.addView(secondColumnInFirstRowKAFEL);

        ImageView btnBell = new ImageView(con);
        btnBell.setImageResource(R.drawable.bell_icon);

        if(quest.IsAlarmSet())
            btnBell.setColorFilter(Color.YELLOW);
        else
            btnBell.setColorFilter(Color.BLACK);

        btnBell.setScaleX(1.0f);
        btnBell.setScaleY(1.0f);
        btnBell.setLayoutParams(new TableLayout.LayoutParams
                (
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

        secondColumnInFirstRowKAFEL.addView(btnBell);


        return firstRowInKAFEL;
    }

    private TableRow createSecondRow(Context con, Quest quest)
    {
        TableRow secondRowInKAFEL = new TableRow(con);
        secondRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));

        TextView txtTitleOfKAFEL = new TextView(con);
        String title = quest.getDescription();

        txtTitleOfKAFEL.setText(title);
        txtTitleOfKAFEL.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        txtTitleOfKAFEL.setTypeface(null, Typeface.BOLD_ITALIC);
        txtTitleOfKAFEL.setGravity(Gravity.CENTER);
        txtTitleOfKAFEL.setLayoutParams(new TableRow.LayoutParams
                (
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));


        secondRowInKAFEL.addView(txtTitleOfKAFEL);

        return secondRowInKAFEL;
    }

    private TableRow createThirdRow(Context con, Quest quest)
    {
        TableRow thirdRowInKAFEL = new TableRow(con);
        thirdRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));

        final Button btnExecute = new Button(con);
        btnExecute .setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));
        if(quest.getStatus() == null)
        {
            btnExecute.setText("Wykonaj!");
            btnExecute.setEnabled(true);
        }
        else
        {
            btnExecute.setText("Już Wykonano!");
            btnExecute.setEnabled(false);
        }
        btnExecute .setTypeface(null, Typeface.BOLD);
        btnExecute .setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        // so here we are taking care of such an important stuff which
        // I mentioned about at the beginning of this class :3
        // it sends the value of pressed button straight to the MainActivity
        // via IButtonClickedListener interface. It's cool.
        btnExecute .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyListeners(btnExecute.getText().toString());
            }
        });
        thirdRowInKAFEL.addView(btnExecute);

        return thirdRowInKAFEL;
    }
}

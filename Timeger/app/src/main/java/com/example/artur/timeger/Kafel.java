package com.example.artur.timeger;

import android.content.Context;
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

import com.example.artur.timeger.model.Quest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by bionanek on 30-11-2016.
 */

public class Kafel
{
    public Kafel(Context con, TableLayout columnInMainTable, TableLayout.LayoutParams lp)
    {
        //to jest cos od pierwszego labela
        TableRow firstRowInKAFEL = new TableRow(con);
        firstRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));
        columnInMainTable.addView(firstRowInKAFEL, lp);

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
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        TextView txtdateOfExecution = new TextView(con);
        txtdateOfExecution.setTypeface(null, Typeface.BOLD_ITALIC);
        txtdateOfExecution.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        txtdateOfExecution.setText(dateFormat.format(date));
        txtdateOfExecution.setLayoutParams(new TableLayout.LayoutParams(
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
        btnBell.setColorFilter(Color.YELLOW);
        btnBell.setScaleX(1.0f);
        btnBell.setScaleY(1.0f);
        btnBell.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f
        ));

        secondColumnInFirstRowKAFEL.addView(btnBell);





        TableRow secondRowInKAFEL = new TableRow(con);
        secondRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));
        columnInMainTable.addView(secondRowInKAFEL, lp);

        TextView txtTitleOfKAFEL = new TextView(con);
        String title = new String();
        title="Title";
        txtTitleOfKAFEL.setText(title);
        txtTitleOfKAFEL.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        txtTitleOfKAFEL.setTypeface(null, Typeface.BOLD_ITALIC);
        txtTitleOfKAFEL.setGravity(Gravity.CENTER);
        txtTitleOfKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));


        secondRowInKAFEL.addView(txtTitleOfKAFEL);


        TableRow thirdRowInKAFEL = new TableRow(con);
        thirdRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));
        columnInMainTable.addView(thirdRowInKAFEL, lp);

        final Button btnExecute = new Button(con);
        btnExecute .setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));
        btnExecute .setText("Wykonaj!");
        btnExecute .setTypeface(null, Typeface.BOLD);
        btnExecute .setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        btnExecute .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        thirdRowInKAFEL.addView(btnExecute );
    }

    public Kafel(Context con, TableLayout columnInMainTable, TableLayout.LayoutParams lp, Quest quest)
    {
        TableRow firstRowInKAFEL = new TableRow(con);
        firstRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));
        columnInMainTable.addView(firstRowInKAFEL, lp);

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
        txtdateOfExecution.setLayoutParams(new TableLayout.LayoutParams(
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
        btnBell.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f
        ));

        secondColumnInFirstRowKAFEL.addView(btnBell);





        TableRow secondRowInKAFEL = new TableRow(con);
        secondRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));
        columnInMainTable.addView(secondRowInKAFEL, lp);

        TextView txtTitleOfKAFEL = new TextView(con);
        String title = quest.getDescription();

        txtTitleOfKAFEL.setText(title);
        txtTitleOfKAFEL.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        txtTitleOfKAFEL.setTypeface(null, Typeface.BOLD_ITALIC);
        txtTitleOfKAFEL.setGravity(Gravity.CENTER);
        txtTitleOfKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));


        secondRowInKAFEL.addView(txtTitleOfKAFEL);


        TableRow thirdRowInKAFEL = new TableRow(con);
        thirdRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        ));
        columnInMainTable.addView(thirdRowInKAFEL, lp);

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
        btnExecute.setEnabled(true);
        btnExecute .setTypeface(null, Typeface.BOLD);
        btnExecute .setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        btnExecute .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        thirdRowInKAFEL.addView(btnExecute );
    }
}

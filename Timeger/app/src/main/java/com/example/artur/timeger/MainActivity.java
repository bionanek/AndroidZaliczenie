package com.example.artur.timeger;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.artur.timeger.interfaces.IButtonClickedListener;
import com.example.artur.timeger.model.Quest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IButtonClickedListener,
        NavigationView.OnNavigationItemSelectedListener
{

    //elo co tam u ciebie kuba?
    private FloatingActionButton fab_one,fab_two,fab_three;
    private Animation open,close,rotate,rotateBack;
    private static final int NUM_ROWS = 10;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateButton();

        fab_one = (FloatingActionButton) findViewById(R.id.fab);
        fab_two = (FloatingActionButton) findViewById(R.id.fab2);
        fab_three = (FloatingActionButton) findViewById(R.id.fab3);

        open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.open);
        close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.close);
        rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        rotateBack = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_back);


        fab_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {

                    fab_two.startAnimation(close);
                    fab_three.startAnimation(close);
                    fab_one.startAnimation(rotateBack);

                    fab_two.setClickable(false);
                    fab_three.setClickable(false);
                    isOpen = false;
                }
                else
                {
                    fab_two.startAnimation(open);
                    fab_three.startAnimation(open);
                    fab_one.startAnimation(rotate);

                    fab_two.setClickable(true);
                    fab_three.setClickable(true);
                    isOpen = true;
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.myProfile) {
            // Handle the camera action
        } else if (id == R.id.myFriends) {

        } else if (id == R.id.addDatabaseSQLite) {
            Toast.makeText(MainActivity.this,"Utworzono baze danych",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.checkIfDatabaseExist) {
            Toast.makeText(MainActivity.this,"Baza danych juz istnieje",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.shareAppWithSomeone) {

        } else if (id == R.id.quitApplication) {
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // KAFELKI MADE BY WUJEK SAJMON

    private void populateButton() {

        TableLayout table = (TableLayout) findViewById(R.id.TableFourButtons);
        table.setBackgroundColor(Color.WHITE);

        // creating a list of Quest objects to add them to Quest boxes
        List<Quest> quests = new ArrayList<>();
        int day = 1;
        for(int i = 0; i < 10; i++)
        {
            Quest quest = new Quest();
            quest.setDescription("TEST" + i);
            quest.setQuestDate("2016/11/" + day);
            if(i%3 == 0)
            {
                quest.setStatus("Done");
                quest.setAlarm("test dzwonka");
            }
            quests.add(quest);

            day++;
        }
        int counter = 0;

        //tutaj robi do chuja pana wiersze
        for (int row = 0; row < quests.size()/2; row++)
        { //NUM_ROWS
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams
                    (
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
                    ));

            TableLayout.LayoutParams lp =
                    new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5, 5, 5, 5);
            tableRow.setLayoutParams(lp);
            table.addView(tableRow, lp);

            //tutaj do chuja pana robi kolumny
            for (int col = 0; col < 2; col++)
            {
                TableLayout columnInMainTable = new TableLayout(this);
                TableRow.LayoutParams paramsColumInMainTable = new TableRow.LayoutParams
                        (
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                        );
                columnInMainTable.setBackgroundColor(Color.rgb(53,136,244));
                paramsColumInMainTable .setMargins(5,0,5,0);
                columnInMainTable.setLayoutParams(paramsColumInMainTable );
                tableRow.addView(columnInMainTable);

                // creating a questBox object which is a type of TableLayout, so
                // that it can be passed as a parameter to .addView below.
                QuestBox questBox = new QuestBox(this, lp, quests.get(counter));
                questBox.addListener(this);
                columnInMainTable.addView(questBox, lp);
                counter++;


                // this huge commented code block is just for safety reasons.
                // yep.
                /*TableRow firstRowInKAFEL = new TableRow(this);
                firstRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                columnInMainTable.addView(firstRowInKAFEL, lp);

                TableLayout firstColumnInFirstRowKAFEL = new TableLayout(this);
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

                TextView txtdateOfExecution = new TextView(this);
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

                TableLayout secondColumnInFirstRowKAFEL = new TableLayout(this);
                secondColumnInFirstRowKAFEL.setLayoutParams(paramsColumnInFirstRowKAFEL);
                firstRowInKAFEL.addView(secondColumnInFirstRowKAFEL);

                ImageView btnBell = new ImageView(this);
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





                TableRow secondRowInKAFEL = new TableRow(this);
                secondRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                columnInMainTable.addView(secondRowInKAFEL, lp);

                TextView txtTitleOfKAFEL = new TextView(this);
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


                TableRow thirdRowInKAFEL = new TableRow(this);
                thirdRowInKAFEL.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                columnInMainTable.addView(thirdRowInKAFEL, lp);

                final Button btnExecute = new Button(this);
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
                        gridButtonClicked(String.valueOf(btnExecute .getText()));
                    }
                });
                thirdRowInKAFEL.addView(btnExecute );*/

            }
        }
    }

    public void onQuestBoxButtonClick(String value)
    {
        Toast.makeText(this,"Button clicked id:"+value,Toast.LENGTH_SHORT).show();
    }
}

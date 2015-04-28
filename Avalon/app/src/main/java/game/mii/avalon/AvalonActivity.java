package game.mii.avalon;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AvalonActivity extends ActionBarActivity {
    private Spinner spNumberOfPlayers;
    private ArrayAdapter<String> numberOfPlayersList;
    private Context mContext;
    private String[] numbers = {"5","6","7","8","9","10"};
    private CheckBox cbPercival;
    private CheckBox cbMorgana;
    private CheckBox cbMordred;
    private CheckBox cbOberon;
    private Button btnStart;
    private int numberOfPlayers = 5;
    private TextView labelGoodGuys;
    private TextView labelBadGuys;
    private String strGoodGuys = "正義方 : ";
    private String strBadGuys = "邪惡方 : ";
    private int goodCount = 3;
    private int badCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avalon);
        mContext = this.getApplicationContext();
        initUI();
        initSpinner();
        initListeners();
    }
    private void initUI (){
        spNumberOfPlayers = (Spinner)findViewById(R.id.spinnerPlayers);
        cbPercival = (CheckBox)findViewById(R.id.cbPercival);
        cbMorgana = (CheckBox)findViewById(R.id.cbMorgana);
        cbMordred = (CheckBox)findViewById(R.id.cbMordred);
        cbOberon = (CheckBox)findViewById(R.id.cbOberon);
        btnStart = (Button)findViewById(R.id.button);
        labelGoodGuys = (TextView)findViewById(R.id.tvGoodGuy);
        labelBadGuys = (TextView)findViewById(R.id.tvBadGuy);
        labelGoodGuys.setText(strGoodGuys + goodCount);
        labelBadGuys.setText(strBadGuys + badCount);
    }
    private boolean isResetPercivalValue = false;
    private CheckBox.OnCheckedChangeListener chkListener = new CheckBox.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(!isResetCheckboxValue) {
                if (numberOfPlayers == 5) {
                    if (buttonView == cbPercival) {
                        if (cbPercival.isChecked()) {
                            cbMorgana.setEnabled(true);
                            cbMordred.setEnabled(true);
                            Toast.makeText(mContext, "5人遊戲時,當使用派西維爾到遊戲的同時,也選擇莫德雷德或莫甘娜使用到遊戲中", Toast.LENGTH_SHORT).show();
                            if(!cbMorgana.isChecked()) {
                                cbMorgana.setChecked(true);
                            }
                        } else {
                            isResetPercivalValue = true;
                            if(cbMorgana.isChecked()) {
                                cbMorgana.setChecked(false);
                            }
                            if(cbMordred.isChecked()) {
                                cbMordred.setChecked(false);
                            }
                            isResetPercivalValue = false;
                            cbMorgana.setEnabled(false);
                            cbMordred.setEnabled(false);
                        }
                    } else if(buttonView == cbMorgana && !isResetPercivalValue) {
                        cbMordred.setChecked(!cbMorgana.isChecked());
                    } else if(buttonView == cbMordred && !isResetPercivalValue) {
                        cbMorgana.setChecked(!cbMordred.isChecked());
                    }

                }
                if (numberOfPlayers == 6) {
                    if (buttonView == cbMorgana){
                        cbMordred.setEnabled(!cbMorgana.isChecked());
                        cbOberon.setEnabled(!cbMorgana.isChecked());
                    } else if (buttonView == cbMordred) {
                        cbMorgana.setEnabled(!cbMordred.isChecked());
                        cbOberon.setEnabled(!cbMordred.isChecked());
                    } else if (buttonView == cbOberon) {
                        cbMorgana.setEnabled(!cbOberon.isChecked());
                        cbMordred.setEnabled(!cbOberon.isChecked());
                    }
                }
                if (numberOfPlayers == 7 || numberOfPlayers == 8 || numberOfPlayers == 9) {
                    if (buttonView == cbMorgana || buttonView == cbMordred) {
                        cbOberon.setEnabled(!cbMorgana.isChecked() || !cbMordred.isChecked());
                    } else if (buttonView == cbMordred || buttonView == cbOberon) {
                        cbMorgana.setEnabled(!cbMordred.isChecked() || !cbOberon.isChecked());
                    } else if (buttonView == cbOberon || buttonView == cbMorgana) {
                        cbMordred.setEnabled(!cbOberon.isChecked() || !cbMorgana.isChecked());
                    }
                }
            }
        }
    };
    private boolean isResetCheckboxValue = false;
    private void resetCheckboxValue() {
        isResetCheckboxValue = true;
        if(cbPercival.isChecked()) {
            cbPercival.setChecked(false);
        }
        if(cbMorgana.isChecked()) {
            cbMorgana.setChecked(false);
        }
        if(cbMordred.isChecked()) {
            cbMordred.setChecked(false);
        }
        if(cbOberon.isChecked()) {
            cbOberon.setChecked(false);
        }
        isResetCheckboxValue = false;
    }
    private void initListeners(){
        cbPercival.setOnCheckedChangeListener(chkListener);
        cbMorgana.setOnCheckedChangeListener(chkListener);
        cbMordred.setOnCheckedChangeListener(chkListener);
        cbOberon.setOnCheckedChangeListener(chkListener);
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GameSetting newGame = new GameSetting();
                newGame.GameSetting(numberOfPlayers, cbPercival.isChecked(), cbMorgana.isChecked(), cbMordred.isChecked(), cbOberon.isChecked());
            }
        });
    }
    private void initSpinner(){
        numberOfPlayersList = new ArrayAdapter<String>(AvalonActivity.this,
                android.R.layout.simple_spinner_item, numbers);
        spNumberOfPlayers.setAdapter(numberOfPlayersList);
        spNumberOfPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
                //Toast.makeText(mContext, "你選的是" + numberOfPlayers[position], Toast.LENGTH_SHORT).show();
                numberOfPlayers = Integer.parseInt(numbers[position]);
                if (numberOfPlayers > 5) {
                    cbMorgana.setEnabled(true);
                    cbMordred.setEnabled(true);
                    cbOberon.setEnabled(true);
                } else if (numberOfPlayers == 5)
                {
                    cbMorgana.setEnabled(false);
                    cbMordred.setEnabled(false);
                    cbOberon.setEnabled(false);
                }
                switch(numberOfPlayers){
                    case 5:
                        goodCount = 3;
                        badCount = 2;
                        break;
                    case 6:
                        goodCount = 4;
                        badCount = 2;
                        break;
                    case 7:
                        goodCount = 4;
                        badCount = 3;
                        break;
                    case 8:
                        goodCount = 5;
                        badCount = 3;
                        break;
                    case 9:
                        goodCount = 6;
                        badCount = 3;
                        break;
                    case 10:
                        goodCount = 6;
                        badCount = 4;
                        break;
                }
                labelGoodGuys.setText(strGoodGuys + goodCount);
                labelBadGuys.setText(strBadGuys + badCount);
                resetCheckboxValue();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_avalon, menu);
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
}

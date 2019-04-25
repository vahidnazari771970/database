package com.example.vahid.database;

import android.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    EditText edt1,edt2,edt3;
    databasemanager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn_insert);
        btn2 = findViewById(R.id.btn_view);
        btn3 = findViewById(R.id.btn_update);
        btn4 = findViewById(R.id.btn_delete);

        edt1 = findViewById(R.id.edt_name);
        edt2 = findViewById(R.id.edt_family);
        edt3 = findViewById(R.id.edt_id);
        dbm=new databasemanager(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.i("mahdi"," insert button clicked");


                String mname = edt1.getText().toString();
                String mfamily = edt2.getText().toString();
                String mid = edt3.getText().toString();

                if(TextUtils.isEmpty(mid)||TextUtils.isEmpty(mname)||
                        TextUtils.isEmpty(mfamily)){

                    Toast.makeText(MainActivity.this," تمامی فیلدها باید تکمیل شود ",
                            Toast.LENGTH_LONG).show();
                }
                else{

                person iperson = new person();
                iperson.Pid=mid;
                iperson.Pname=mname;
                iperson.Pfamily=mfamily;
                dbm.insertperson(iperson);
                Toast.makeText(MainActivity.this," اطلاعات باموفقیت ذخیره شد ",
                        Toast.LENGTH_LONG).show();
                    Log.i("mahdi","data inserted");}


            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.i("mahdi","view button clicked");
                String vid = edt3.getText().toString();
                person vper = dbm.getperson(vid);
                edt1.setText(((person) vper).Pname);
                edt2.setText(((person) vper).Pfamily);
                Log.i("mahdi","data viewed");

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uname = edt1.getText().toString();
                String ufamily = edt2.getText().toString();
                String uid = edt3.getText().toString();
                person uperson = new person();
                uperson.Pid=uid;
                uperson.Pname=uname;
                uperson.Pfamily=ufamily;
                dbm.updateperson(uperson);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String delid = edt3.getText().toString();
                person dperson = new person();
                dperson.Pid=delid;
                Boolean del = dbm.deleteperson(dperson);
                if (del==true){
                    Toast.makeText(MainActivity.this," اطلاعات حذف شد ",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this," در حذف اطلاعات مشکلی وجود دارد ",
                            Toast.LENGTH_LONG).show();

                }


            }
        });

    }
}

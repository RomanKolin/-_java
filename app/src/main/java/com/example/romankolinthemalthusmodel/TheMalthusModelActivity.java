package com.example.romankolinthemalthusmodel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class TheMalthusModelActivity extends AppCompatActivity
{
    EditText editText1xi;
    EditText editText2initialyear;
    EditText editText3finalyear;
    EditText editText4r;
    EditText editText5m;
    ListView listView1xi;
    ArrayAdapter<List> list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themalthusmodelactivity);

        editText1xi = findViewById(R.id.editText1xi);
        editText2initialyear = findViewById(R.id.editText2initialyear);
        editText3finalyear = findViewById(R.id.editText3finalyear);
        editText4r = findViewById(R.id.editText4r);
        editText5m = findViewById(R.id.editText5m);
        listView1xi = findViewById(R.id.listView1xi);

        list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView1xi.setAdapter(list);
    }

    public void onclickpopulationsize(View populationsize)
    {
        list.clear();

        if (editText1xi.getText().toString().equals("") || editText2initialyear.getText().toString().equals("") || editText3finalyear.getText().toString().equals("") || editText4r.getText().toString().equals("") || editText5m.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_LONG).show();
        else
        {
            int iy, fy;
            double r, m, pop, pop1;

            pop = Integer.parseInt(editText1xi.getText().toString());
            iy = Integer.parseInt(editText2initialyear.getText().toString());
            fy = Integer.parseInt(editText3finalyear.getText().toString());
            r = Double.parseDouble(editText4r.getText().toString());
            m = Double.parseDouble(editText5m.getText().toString());

            if (iy > fy)
                Toast.makeText(getApplicationContext(), "Начальный год не должен быть больше конечного", Toast.LENGTH_LONG).show();
            else
            {
                DecimalFormat dfpop1 = new DecimalFormat("#.##");
                for (int i = iy; i <= fy; i++)
                {
                    pop1 = (r - m) * pop + pop;

                    List lst = new List();
                    lst.list = "Год: " + i + "; Xᵢ = " + String.valueOf(Math.floor(pop)).substring(0, String.valueOf(Math.floor(pop)).length() - 2) + ", Xᵢ+1 = " + dfpop1.format(pop1);
                    list.add(lst);
                    pop = pop1;
                }
                list.notifyDataSetChanged();
            }
        }
    }
}
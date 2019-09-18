package com.shettydev.exporttocsv;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {

    exportToExcel();
    }

    private void exportToExcel() {
        String Fnamexls="testfile"  + ".xls";

        File sdCard = Environment.getExternalStorageDirectory();

        File directory = new File (sdCard.getAbsolutePath() + "/newfolder");
        directory.mkdirs();

        File file = new File(directory, Fnamexls);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;
        try {
            int a = 1;
            workbook = Workbook.createWorkbook(file, wbSettings);
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);

            for (int i=0;i< 20;i++){

                Label label0 = new Label(0,i,"HEADING"+i);
                Label label3 = new Label(1,i,"Heading"+i);
                Label label5 = new Label(2,i,"Heading"+i);
                Label label6 = new Label(3,i,"Heading"+i);
                Label label7 = new Label(4,i,"Heading"+i);
                Label label8= new Label(5,i,"Heading"+i);

                Label label4 = new Label(1,1,String.valueOf(a));
                try {

                    sheet.addCell(label0);
                    sheet.addCell(label4);
                    sheet.addCell(label5);
                    sheet.addCell(label6);
                    sheet.addCell(label7);
                    sheet.addCell(label8);

                    sheet.addCell(label3);
                } catch (RowsExceededException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }


            workbook.write();

            try {
                workbook.close();
            } catch (WriteException e) {

                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

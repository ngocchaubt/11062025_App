package com.example.k22411csampleproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.k22411csampleproject.connectors.EmployeeConnector;
import com.example.k22411csampleproject.connectors.SQLiteConnector;
import com.example.k22411csampleproject.models.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    CheckBox chkSaveLoginInfo;
    private boolean doubleBackToExitPressedOnce = false;
    private static final long DOUBLE_BACK_PRESS_THRESHOLD = 1200;
    //cop 3 cái này bên SQL
    String DATABASE_NAME="SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Cop cái này bên main của SQL
        processCopy();
    }


    private void addViews() {
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        chkSaveLoginInfo=findViewById(R.id.chkSaveLoginInfor);
    }

    public void do_login(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        EmployeeConnector ec=new EmployeeConnector();

        Employee emp=ec.login(new SQLiteConnector(this).openDatabase(),username,password);
        if (emp!=null)
        {
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,
                    "Login failed, please check your account again!", Toast.LENGTH_SHORT).show();
        }
    }

    public void do_exit(View view) {
        // xác thực nhận tuong tac người dùng từ thông báo
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        Resources res = getResources();
        // title
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        // nội dung cửa sổ:, kh được hardcode, phải đa ngôn ngữ
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        // biểu tượng:
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //thiết lập tương tác Yes
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //System.exit(0);
                finish();
            }
        });
        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false); // không cho tắt màn hình khi bấm phía bên ngoài
        dialog.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (doubleBackToExitPressedOnce) {
                finish();
                return true;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, DOUBLE_BACK_PRESS_THRESHOLD);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void saveLoginInInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        boolean isSave=chkSaveLoginInfo.isChecked();
        editor.putString("USERNAME",username);
        editor.putString("PASSWORD",password);
        editor.putBoolean("SAVED",isSave);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInInformation();
    }
    public void restoreLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION",MODE_PRIVATE);
        String username = preferences.getString("USERNSME","");
        String password = preferences.getString("PASSWORD","");
        boolean isSave=preferences.getBoolean("SAVED",true);
        if(isSave)
        {
            edtUsername.setText(username);
            edtPassword.setText(password);
            chkSaveLoginInfo.setChecked(isSave);
        }
    }

    //phục hồi dữ liệu
    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();
    }
    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    public void CopyDataBaseFromAsset()
    {
        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
}
package vn.edu.ntu.thanhnuong.hienthisms_content;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    boolean duocphepdocSMS = false;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(checkPermissionEnable(Manifest.permission.READ_SMS))
            duocphepdocSMS = true;
        else
            requestForPermission(Manifest.permission.READ_SMS);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private boolean checkPermissionEnable(String permission)
    {
        int result = ContextCompat.checkSelfPermission(this, permission);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else
            return false;
    }

    private  void  requestForPermission(String permission){
        ActivityCompat.requestPermissions(this, new String[]{permission}, 113);
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[]  grantResult){
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if(requestCode == 113){
            if(grantResult.length > 0){
                if(grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    duocphepdocSMS = true;
                }
            }
        }
    }
}

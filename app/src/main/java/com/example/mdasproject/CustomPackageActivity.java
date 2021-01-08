package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mdasproject.models.Box;
import com.example.mdasproject.models.LoggedUser;
import com.example.mdasproject.models.OtherRecipient;
import com.example.mdasproject.models.PackageRecipient;
import com.example.mdasproject.models.WrappingPaper;

public class CustomPackageActivity extends AppCompatActivity {
    private RadioGroup radioGroupRecipient;
    private RadioGroup radioGroupPackage;
    String recipient = "";
    String pack = "";
    RadioButton rbRecipient;
    RadioButton rbPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_package);

        radioGroupRecipient = findViewById(R.id.radioGroupRecipient);
        radioGroupPackage = findViewById(R.id.radioGroupPackage);

        radioGroupRecipient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroupRecipient.findViewById(i);
                recipient = rb.getText().toString();
//                Toast.makeText(CustomPackageActivity.this,recipient,Toast.LENGTH_SHORT).show();
            }
        });

        radioGroupPackage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroupPackage.findViewById(i);
                pack = rb.getText().toString();
//                Toast.makeText(CustomPackageActivity.this,pack,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void applyPackage(View view){
        if(recipient.equals("") && pack.equals("")){
            //disable
        }
        PackageRecipient packageRecipient;
        String result = "";
        if(recipient.equals("Myself")){
            if(pack.equals("Box")){
                packageRecipient = new LoggedUser(new Box());
            }
            else {
                packageRecipient = new LoggedUser(new WrappingPaper());
            }
            Log.d("AAA", packageRecipient.set());
            CartDetailsActivity.result = packageRecipient.set();
        }
        else {
            if(pack.equals("Box")){
                packageRecipient = new OtherRecipient(new Box());
                Log.d("AAA", packageRecipient.set());
            }
            else {
                packageRecipient = new OtherRecipient(new WrappingPaper());
                Log.d("AAA", packageRecipient.set());
            }
            CartDetailsActivity.result = packageRecipient.set();
        }

        if(!CartDetailsActivity.result.equals("")){
//            Intent intent = new Intent(CustomPackageActivity.this, CartDetailsActivity.class);
//            intent.putExtra("OPT_CHOSEN", result);
//            startActivity(intent);
            finish();
        }
    }
}
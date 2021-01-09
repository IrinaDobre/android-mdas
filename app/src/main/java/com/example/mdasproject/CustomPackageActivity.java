package com.example.mdasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button buttonAddToOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_package);

        radioGroupRecipient = findViewById(R.id.radioGroupRecipient);
        radioGroupPackage = findViewById(R.id.radioGroupPackage);
        buttonAddToOrder = findViewById(R.id.buttonAddOrder);

        radioGroupRecipient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroupRecipient.findViewById(i);
                recipient = rb.getText().toString();
            }
        });

        radioGroupPackage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroupPackage.findViewById(i);
                pack = rb.getText().toString();

                if(!recipient.equals("") && !pack.equals("")){
                    buttonAddToOrder.setEnabled(true);
                }
            }
        });


    }

    public void applyPackage(View view){
        PackageRecipient packageRecipient;

        if(recipient.equals("Myself")){
            if(pack.equals("Box")){
                packageRecipient = new LoggedUser(new Box());
                CartDetailsActivity.SELECTED_TYPE_OF_PACKAGE = "Box";
            }
            else {
                packageRecipient = new LoggedUser(new WrappingPaper());
                CartDetailsActivity.SELECTED_TYPE_OF_PACKAGE  = "Wrapping paper";
            }
            CartDetailsActivity.result = packageRecipient.set();
            Log.d("DESIGN_PATTERN_BRIDGE", CartDetailsActivity.result);
        }
        else {
            if(pack.equals("Box")){
                packageRecipient = new OtherRecipient(new Box());
                CartDetailsActivity.SELECTED_TYPE_OF_PACKAGE  = "Box";
            }
            else {
                packageRecipient = new OtherRecipient(new WrappingPaper());
                CartDetailsActivity.SELECTED_TYPE_OF_PACKAGE  = "Wrapping paper";
            }
            CartDetailsActivity.result = packageRecipient.set();
            Log.d("DESIGN_PATTERN_BRIDGE", CartDetailsActivity.result);
        }

        if(!CartDetailsActivity.result.equals("")){
            finish();
        }
    }
}
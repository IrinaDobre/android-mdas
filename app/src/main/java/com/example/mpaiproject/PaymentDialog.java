package com.example.mpaiproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.mpaiproject.models.User;
import com.example.mpaiproject.models.designpatterns.strategy.CardPaymentStrategy;
import com.example.mpaiproject.models.designpatterns.strategy.CashPaymentStrategy;
import com.example.mpaiproject.models.designpatterns.strategy.CryptocurrencyPaymentStrategy;
import com.example.mpaiproject.models.designpatterns.strategy.MobilePaymentStrategy;

public class PaymentDialog extends DialogFragment {

    private Activity activity;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this.getActivity();

    }

    public static PaymentDialog newInstance() {
        return new PaymentDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = activity.getLayoutInflater().inflate(R.layout.activity_payment_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setNegativeButton("Cancel", (dialogInterface, i) -> this.dismiss())
                .setView(view)
                .setPositiveButton("Apply",(dialogInterface, i) -> {
                    Intent intent = new Intent(activity, CartDetailsActivity.class);
                    radioGroup = view.findViewById(R.id.radioGroup);
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = view.findViewById(selectedId);
                    String radioButtonText = radioButton.getText().toString();
                    switch(radioButtonText){
                        case "Card":
                            User.setPlaymentMethod(new CardPaymentStrategy());
                            break;
                        case "Cash":
                            User.setPlaymentMethod(new CashPaymentStrategy());
                            break;
                        case "Cryptocurrency":
                            User.setPlaymentMethod(new CryptocurrencyPaymentStrategy());
                            break;
                        case "Mobile payment":
                            User.setPlaymentMethod(new MobilePaymentStrategy());
                            break;
                    }
                    startActivity(intent);
                })
                .create();
        dialog.show();
        return dialog;
    }


}
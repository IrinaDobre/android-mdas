package com.example.mpaiproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.mpaiproject.models.designpatterns.decorator.GiftTag;
import com.example.mpaiproject.models.designpatterns.decorator.GreetingCard;
import com.example.mpaiproject.models.designpatterns.decorator.PackageDecorator;
import com.example.mpaiproject.models.designpatterns.decorator.PaperBow;
import com.example.mpaiproject.models.designpatterns.bridge.Box;
import com.example.mpaiproject.models.designpatterns.bridge.WrappingPaper;
import com.example.mpaiproject.models.designpatterns.bridge.PackageType;

import static com.example.mpaiproject.CartDetailsActivity.decorationResult;
import static com.example.mpaiproject.CartDetailsActivity.extraDecorationPrice;

public class ExtraDecorationsDialog extends DialogFragment {

    private Activity activity;
    private RadioGroup radioGroupDecorations;
    private RadioButton radioButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this.getActivity();

    }

    public static ExtraDecorationsDialog newInstance() {
        return new ExtraDecorationsDialog();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = activity.getLayoutInflater().inflate(R.layout.activity_extra_decorations_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setNegativeButton("Cancel", (dialogInterface, i) -> this.dismiss())
                .setView(view)
                .setPositiveButton("Apply",(dialogInterface, i) -> {
                    radioGroupDecorations = view.findViewById(R.id.radioGroupDecorations);
                    int selectedId = radioGroupDecorations.getCheckedRadioButtonId();
                    radioButton = view.findViewById(selectedId);
                    String radioButtonText = radioButton.getText().toString();
                    PackageType packageType;
                    if(CartDetailsActivity.SELECTED_TYPE_OF_PACKAGE.equals("Box")) {
                        packageType = new Box();
                    } else {
                        packageType = new WrappingPaper();
                    }
                    decorationResult = "";
                    extraDecorationPrice = 0;
                    PackageDecorator decorator;
                    switch(radioButtonText){
                        case "Paper Bow":
                            decorator = new PaperBow(packageType);
                            decorationResult += decorator.apply() + " with extra tax: " + decorator.getPrice();
                            extraDecorationPrice = decorator.getPrice();
                            break;
                        case "Gift Tag":
                            decorator = new GiftTag(packageType);
                            decorationResult += decorator.apply() + " with extra tax: " + decorator.getPrice();
                            extraDecorationPrice = decorator.getPrice();
                            break;
                        case "Greeting Card":
                            decorator = new GreetingCard(packageType);
                            decorationResult += decorator.apply() + " with extra tax: " + decorator.getPrice();
                            extraDecorationPrice = decorator.getPrice();
                            break;
                    }
                })
                .create();
        dialog.show();
        return dialog;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        ((CartDetailsActivity)activity).actionAfterDismissDialog();
        super.onDismiss(dialog);
    }
}
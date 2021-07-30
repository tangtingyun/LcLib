package com.step.lclib.debug.preference.utils;

import android.content.Context;
import android.content.DialogInterface;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.step.lclib.R;
import com.step.lclib.debug.preference.model.PreferenceItem;


/**
 * Created by Ali Asadi on 05/12/2018.
 */
public class DialogUtils {
    public interface OnSaveClicked {
        void onSavePreferenceClicked(PreferenceItem item, String newValue);
    }

    public static void showEditValueDialog(@NonNull Context context, @NonNull final PreferenceItem item, OnSaveClicked onSaveClicked) {


        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_value, null);

        final EditText edittext = view.findViewById(R.id.valueText);
        TextView textView = view.findViewById(R.id.keyText);

        textView.setText(item.getKey());
        edittext.setText(String.valueOf(item.getValue()));

        alert.setView(view);

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onSaveClicked != null) {
                    onSaveClicked.onSavePreferenceClicked(item, edittext.getText().toString());
                }
            }
        });
        alert.setNegativeButton("Cancel", null);
        alert.show();
    }
}

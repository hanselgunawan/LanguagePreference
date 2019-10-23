package com.hanseltritama.languagepreference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView language_textview;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.english_lang) {
            sharedPreferences.edit().putString("language", "English").apply();
        } else if(item.getItemId() == R.id.indonesia_lang) {
            sharedPreferences.edit().putString("language", "Indonesia").apply();
        }

        changeLanguageText();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.hanseltritama.languagepreference", MODE_PRIVATE);
        String chosen_language = sharedPreferences.getString("language", null);

        language_textview = findViewById(R.id.language_textview);
        language_textview.setText(chosen_language);

        if(chosen_language == null) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose Language")
                    .setMessage("What language do you want to use?")
                    .setPositiveButton("Indonesia", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sharedPreferences.edit().putString("language", "Indonesia").apply();
                            changeLanguageText();
                        }
                    })
                    .setNegativeButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sharedPreferences.edit().putString("language", "English").apply();
                            changeLanguageText();
                        }
                    })
                    .show();
        }
    }

    public void changeLanguageText() {
        language_textview.setText(sharedPreferences.getString("language", null));
    }
}

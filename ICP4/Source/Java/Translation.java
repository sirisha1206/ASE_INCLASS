package tutorial.cs5551.com.trasnlate_app_sirisha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Translation extends AppCompatActivity {

    String sourceText, sourceLanguage, destinationLanguage;
    TextView outputTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        outputTextView = findViewById(R.id.txt_Result);
    }
    public void logout(View v) {
        // Redirecting to Login Screen on logout
        Intent redirect = new Intent(Translation.this, MainActivity.class);
        startActivity(redirect);
    }
    public void translateText(View v) {
        // Getting required parameters from UI
        TextView sourceLanguageView = findViewById(R.id.source_language);
        TextView destinationLanguageView = findViewById(R.id.destination_language);
        TextView sourceTextView = findViewById(R.id.txt_Email);

        sourceLanguage = sourceLanguageView.getText().toString();
        destinationLanguage = destinationLanguageView.getText().toString();
        sourceText = sourceTextView.getText().toString();

        // API Service
        String getURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                "key=trnsl.1.1.20151023T145251Z.bf1ca7097253ff7e." +
                "c0b0a88bea31ba51f72504cc0cc42cf891ed90d2&text=" + sourceText + "&" +
                "lang=" + sourceLanguage + "-" + destinationLanguage;
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                        final String convertedText = convertedTextArray.get(0).toString();
                        Log.d("JSON Result --> ", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setText(convertedText);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());

        }

    }
}
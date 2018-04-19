package com.kanu_lp.masterintentsample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kanu_lp.masterintentlib.MasterIntent;

public class MasterIntentActivity extends AppCompatActivity implements View.OnClickListener{

    Button button_PhoneCall,button_Gallery_Intent,button_Instagram,button_WhatsappText,button_WhatsappTextAndImage,button_PlayStore,button_Email,button_Map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_intent);

        bindviews();
        if(MasterIntent.intentCheckNetwork(getApplicationContext())){
            Toast.makeText(this, "Internet connected", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Internet not connected", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindviews() {
        button_PhoneCall = (Button)findViewById(R.id.button_dialphone);
        button_PhoneCall.setOnClickListener(this);
        button_Gallery_Intent = (Button)findViewById(R.id.button_intentGalleryImage);
        button_Gallery_Intent.setOnClickListener(this);
        button_Instagram = (Button)findViewById(R.id.button_intentInstagram);
        button_Instagram.setOnClickListener(this);
        button_WhatsappText = (Button)findViewById(R.id.button_intentWhatsappText);
        button_WhatsappText.setOnClickListener(this);
        button_WhatsappTextAndImage = (Button)findViewById(R.id.button_intentWhatsappTextAndImage);
        button_WhatsappTextAndImage.setOnClickListener(this);
        button_PlayStore = (Button)findViewById(R.id.button_intentPlayStore);
        button_PlayStore.setOnClickListener(this);
        button_Email = (Button)findViewById(R.id.button_intentemail);
        button_Email.setOnClickListener(this);
        button_Map = (Button)findViewById(R.id.button_intentmap);
        button_Map.setOnClickListener(this);
        Button button_intentNextActivity = (Button) findViewById(R.id.button_intentNextActivity);
        button_intentNextActivity.setOnClickListener(this);
        Button button_webview = (Button) findViewById(R.id.button_webview);
        button_webview.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case MasterIntent.MASTER_IMAGE_REQUEST:
                if(resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    MasterIntent.intentShareAllImageAndText(getApplicationContext(),"MasterIntent library by Karan Gajjar @kanulp.github.io",selectedImage);
                }
                break;
            case MasterIntent.MASTER_IMAGE_REQUEST_INSTAGRAM:
                if(resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();

                    MasterIntent.intentInstagram(getApplicationContext(),selectedImage);
                }
                break;
            case MasterIntent.MASTER_IMAGE_REQUEST_WHATSAPP:
                    if (resultCode==RESULT_OK){
                        Uri selectedImage = data.getData();
                        MasterIntent.intentWhatsappTextAndImage(getApplicationContext(),"MasterIntent library by Karan Gajjar @kanulp.github.io",selectedImage);
                    }
                break;
            case MasterIntent.MASTER_IMAGE_REQUEST_EMAIL:
                        Uri selectedImage = null;
                        if (resultCode==RESULT_OK) {
                            selectedImage = data.getData();
                        }
                        MasterIntent.intentEmail(getApplicationContext(), "karangajjar.lp@gmail.com", "Issue in MasterIntent library by Karan Gajjar @kanulp.github.io",selectedImage);
            break;
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.button_dialphone:
                MasterIntent.intentDialPhone(getApplicationContext(),"+919687784160");
                break;

            case R.id.button_intentGalleryImage:

                Intent i = MasterIntent.intentGalleryImage();
                startActivityForResult(i,MasterIntent.MASTER_IMAGE_REQUEST);
                break;

            case R.id.button_intentInstagram:

                Intent instaintent = MasterIntent.intentGalleryImage();
                startActivityForResult(instaintent,MasterIntent.MASTER_IMAGE_REQUEST_INSTAGRAM);
                break;

            case R.id.button_intentWhatsappText:
                MasterIntent.intentWhatsappText(getApplicationContext(),"MasterIntent library by Karan Gajjar @kanulp.github.io");
                break;

            case R.id.button_intentWhatsappTextAndImage:
                Intent i1 = MasterIntent.intentGalleryImage();
                startActivityForResult(i1,MasterIntent.MASTER_IMAGE_REQUEST_WHATSAPP);
                break;

            case R.id.button_intentPlayStore:
                MasterIntent.intentPlayStore(getApplicationContext());
                break;

            case R.id.button_intentemail:
                Intent i2 = MasterIntent.intentAnyFile();
                startActivityForResult(i2,MasterIntent.MASTER_IMAGE_REQUEST_EMAIL);
                break;

            case R.id.button_intentmap:
                MasterIntent.showMap(getApplicationContext(),"43.653226,-79.383184");
                break;

            case R.id.button_intentNextActivity:
                Bundle b = new Bundle();
                b.putString("name","kanulp.github.io");
                MasterIntent.intentNextActivity(getApplicationContext(), MasterIntentTargetActivity.class,b);
                break;

            case R.id.button_webview:
                Bundle webb = new Bundle();
                webb.putString("url","http://kanulp.github.io/");
                MasterIntent.intentNextActivity(getApplicationContext(), com.kanu_lp.masterintentlib.WebView.class,webb);

        }
    }
}

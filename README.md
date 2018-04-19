[![](https://jitpack.io/v/kanulp/MasterIntent.svg)](https://jitpack.io/#kanulp/MasterIntent)

# MasterIntent
A lightweight android library for your project which include necessary # intents and # webview and also # customizable splashscreen

#### To use Library include jitpak.io in root level gradle file 

  ```allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  #### Also add include this line in app level gradle file 
  
 ``` dependencies {
	        implementation 'com.github.kanulp:MasterIntent:1.0.0'
	}
```

### Below is Sample code for using intents 
```java

//PhoneCall
MasterIntent.intentDialPhone(getApplicationContext(),"99999999");

//Gallery Image  
Intent instaintent = MasterIntent.intentGalleryImage();
startActivityForResult(instaintent,MasterIntent.MASTER_IMAGE_REQUEST_INSTAGRAM); //use available static request code


MasterIntent.intentWhatsappText(getApplicationContext(),"MasterIntent library by Karan Gajjar @kanulp.github.io");
 
 // in your onActivityResult
case MasterIntent.MASTER_IMAGE_REQUEST_WHATSAPP:
  if (resultCode==RESULT_OK){
      Uri selectedImage = data.getData();
     MasterIntent.intentWhatsappTextAndImage(getApplicationContext(),"MasterIntent library by Karan Gajjar @kanulp.github.io",selectedImage);
      }

//where wou want to call 
Intent i1 = MasterIntent.intentGalleryImage();
startActivityForResult(i1,MasterIntent.MASTER_IMAGE_REQUEST_WHATSAPP);


// fun getplaystoreurl available to use 
MasterIntent.intentPlayStore(getApplicationContext());

//pass string with lat,long and for label pass it like "lat,long(my map)"
MasterIntent.showMap(getApplicationContext(),"43.653226,-79.383184");

//got to next activity pass data with bundle 
Bundle b = new Bundle();
b.putString("name","kanulp.github.io");
MasterIntent.intentNextActivity(getApplicationContext(), MasterIntentTargetActivity.class,b); //bundle is nullable 

//and many more check sample app

```

### Using Webview 

include this in your manifest.xml

```xml
<activity
    android:name="com.kanu_lp.masterintentlib.WebView"
    android:theme="@style/Theme.AppCompat.NoActionBar"
    >
</activity>
```

#### Add java code to call

```java
Bundle webb = new Bundle();
webb.putString("url","http://kanulp.github.io/"); // have to use url tag for web link
MasterIntent.intentNextActivity(getApplicationContext(), com.kanu_lp.masterintentlib.WebView.class,webb);
```



### Using Splash Screen 

create one activity SplashScreen.java/.kt in your project and no need to add layout. Use code below to customize splashscreen

```java 
SplashScreen setup = new SplashScreen(SplashActivity.this)
                .setBackgroundColor(R.color.colorAccent)
                .setNextActivity(MasterIntentActivity.class)
                .setSplashTimeOut(2000)
                .setFooterText("Karan Gajjar : @kanu_lp")
                .setCenterText("My cool lib", R.color.colorPrimaryDark)
                .setLogo(R.drawable.bg1)
                .setBundleExtras(bb);

        setup.getCenterTextView().setAllCaps(true);
        View myview = setup.createView();
        setContentView(myview);
```


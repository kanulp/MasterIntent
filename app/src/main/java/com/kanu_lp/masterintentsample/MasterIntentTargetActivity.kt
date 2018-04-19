package com.kanu_lp.masterintentsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class MasterIntentTargetActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_intent_target)


        //IntentUtils.dialPhone()
        var b : Bundle? = intent.extras
        val name = b?.getString("name")

        Toast.makeText(applicationContext,name.toString(),Toast.LENGTH_SHORT).show()
    }

}
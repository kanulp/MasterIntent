package com.kanu_lp.masterintentlib

import android.os.Bundle
import android.widget.TextView
import android.view.LayoutInflater
import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.view.WindowManager
import android.content.Intent
import android.os.Handler


class SplashScreen(activity: Activity) {

    var mActivity: Activity? = activity

    private var mInflater: LayoutInflater? = null

    var image_splash: ImageView? = null

    var text_center: TextView? = null

    var text_footer: TextView? = null

    var center_text: String? = null

    var footer_text: String? = null

    var splashlayout: RelativeLayout? = null

    var bundle: Bundle? = null

     var mView: View? = null

    private var splashBackgroundColor = 0

    private var mLogo : Int = 0

    private var NextActivity: Class<*>? = null

    private var SPLASH_TIME_OUT = 1800

    init {
        this.mInflater = LayoutInflater.from(activity)
        this.mView = mInflater?.inflate(R.layout.splashscreen, null)
        this.splashlayout = mView?.findViewById(R.id.splashlayout) as RelativeLayout
    }

    fun setFullScreen(): SplashScreen {
        mActivity?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        return this
    }
    fun setNextActivity(nextActivity: Class<*>): SplashScreen {
        this.NextActivity = nextActivity
        return this
    }

    fun setSplashTimeOut(timout: Int): SplashScreen {
        this.SPLASH_TIME_OUT = timout
        return this
    }

    fun setBackgroundColor(color: Int): SplashScreen {
        this.splashBackgroundColor = color
        splashlayout?.setBackgroundColor(splashBackgroundColor)
        return this
    }

    fun setLogo(logo: Int): SplashScreen {
        this.mLogo = logo
        image_splash = mView?.findViewById(R.id.image_splash) as ImageView
        image_splash?.setImageResource(mLogo)
        return this
    }
    @JvmOverloads
    fun setFooterText(text: String, color: Int?=null): SplashScreen {
        this.footer_text = text
        text_footer = mView?.findViewById(R.id.text_footer) as TextView
        text_footer?.text = text
        if (color != null) {
            text_center?.setTextColor(color)
        }
        return this
    }

    fun setCenterText(text: String,color: Int?): SplashScreen{
        this.center_text = text
        text_center = mView?.findViewById(R.id.text_center) as TextView
        text_center?.text = text
        if (color != null) {
            text_center?.setTextColor(color)
        }
        return this
    }

    fun getLogo(): ImageView?  = image_splash


    fun getCenterTextView(): TextView? = text_center

    fun getFooterTextView(): TextView? = text_footer

    fun setBundleExtras(bundle: Bundle): SplashScreen {
        this.bundle = bundle
        return this
    }

    fun createView(): View? {
        setHandler()
        return mView
    }



    private fun setHandler() {
        if (NextActivity != null) {
            Handler().postDelayed({
                val i = Intent(mActivity, NextActivity)
                if (bundle != null) {
                    i.putExtras(bundle)
                }
                mActivity?.startActivity(i)
                mActivity?.finish()
            }, SPLASH_TIME_OUT.toLong())
        }
    }
}
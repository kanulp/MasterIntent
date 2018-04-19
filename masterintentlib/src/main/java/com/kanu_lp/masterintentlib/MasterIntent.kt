package com.kanu_lp.masterintentlib

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.net.ConnectivityManager
import android.os.Bundle

class MasterIntent{

    companion object{
        val TAG : String = "MasterIntent"
        @JvmField
        val MASTER_IMAGE_REQUEST = 100
        @JvmField
        val MASTER_IMAGE_REQUEST_INSTAGRAM = 101
        @JvmField
        val MASTER_IMAGE_REQUEST_WHATSAPP = 102
        @JvmField
        val MASTER_IMAGE_REQUEST_EMAIL = 103

        @JvmStatic
        fun intentDialPhone(context: Context ,phoneNumber: String): Unit {
            val intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$phoneNumber")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
            Log.d(TAG,"Phone called to $phoneNumber")
        }

        @JvmStatic
        fun intentGalleryImage(): Intent {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            return intent
        }
        @JvmStatic
        fun intentAnyFile(): Intent {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "*/*"
            return intent
        }

        @JvmOverloads
        @JvmStatic
        fun intentNextActivity(context: Context,targetActivity: Class<*>, bundle:Bundle? = null):Unit{
            val intent = Intent(context, targetActivity)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            context.startActivity(intent)
            Log.d(TAG,"Next Activity Called")
        }

        @JvmStatic
        fun intentInstagram(context: Context,uri: Uri):Unit{
            val intent = Intent(Intent.ACTION_SEND)
            intent.`package` = "com.instagram.android"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.type = "image/*"
            try {
                context.startActivity(Intent.createChooser(intent, "Share image").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

            }catch (ex : ActivityNotFoundException){
                Log.d(TAG,ex.message)
            }
        }

        //whatsapp
        @JvmStatic
        fun intentWhatsappText(context: Context, text: String){
            var intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.`package` = "com.whatsapp"
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            try {
                context.startActivity(intent)
            } catch (ex: android.content.ActivityNotFoundException) {
                Log.d(TAG,ex.message)
            }
        }


        @JvmStatic
        fun intentWhatsappTextAndImage(context: Context,text:String,uri: Uri){
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.`package` = "com.whatsapp"
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.type = "image/*"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            try {
                context.startActivity(intent)
            } catch (ex: android.content.ActivityNotFoundException) {
                Log.d(TAG,ex.message)
            }
        }

        @JvmStatic
        fun intentShareAllImageAndText(context: Context,text: String,uri: Uri){
            val shareAllIntent = Intent(android.content.Intent.ACTION_SEND)
            shareAllIntent.type = "text/plain"
            shareAllIntent.putExtra(android.content.Intent.EXTRA_TEXT, text)
            shareAllIntent.putExtra(Intent.EXTRA_STREAM, uri)
            shareAllIntent.type="image/*"
            Log.d(TAG,"all uri : "+uri.toString())

            try {
                context.startActivity(Intent.createChooser(shareAllIntent, "Share") .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            } catch (e: Exception) {
                Log.d(TAG,e.message)
            }
        }

        @JvmStatic
        fun intentPlayStore(context: Context){
            val appPackageName = context.packageName
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            } catch (ex: android.content.ActivityNotFoundException) {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getPlayStoreUrl(context))).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                Log.d(TAG,ex.message)

            }
        }

        @JvmStatic
        fun intentEmail(context: Context,address : String,subject:String,uri: Uri?){
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:$address")
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            try {
                context.startActivity(intent)
                Log.d(TAG,"uri : "+uri.toString())
            } catch (e: Exception) {
                Log.d(TAG,e.message)
            }
        }

        @JvmStatic
        fun showMap(context: Context,text: String) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$text"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.`package` = "com.google.android.apps.maps"
                context.startActivity(intent)
            } catch (e: Exception) {
                Log.d(TAG,e.toString())
            }

        }

        @JvmStatic
        fun intentCheckNetwork(context: Context): Boolean {
            val connectivityManager: ConnectivityManager
            var connected = false
            try {
                connectivityManager = context
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val networkInfo = connectivityManager.activeNetworkInfo
                connected = networkInfo != null && networkInfo!!.isAvailable &&
                        networkInfo!!.isConnected
                return connected

            } catch (e: Exception) {
                Log.v(TAG, e.toString())
            }
            return connected
        }

        @JvmStatic
        fun getPlayStoreUrl(context: Context) =  "https://play.google.com/store/apps/details?id=${context.packageName}"

    }
}
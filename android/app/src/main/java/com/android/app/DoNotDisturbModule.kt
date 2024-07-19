package com.android.app

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class DoNotDisturbModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "DoNotDisturbModule"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @ReactMethod
    fun enableDoNotDisturb() {
        val notificationManager =
            reactApplicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.isNotificationPolicyAccessGranted) {
            val currentFilter = notificationManager.currentInterruptionFilter
            if (currentFilter == NotificationManager.INTERRUPTION_FILTER_NONE) {
                Toast.makeText(
                    reactApplicationContext,
                    "Do Not Disturb already enabled!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
                Toast.makeText(
                    reactApplicationContext,
                    "Do Not Disturb is enabled!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            reactApplicationContext.startActivity(intent)
        }
    }
}

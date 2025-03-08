package co.feip.fefu2025

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {
    private var count: Int = 0
    private val internetReceiver = InternetReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        @Suppress("DEPRECATION")
        ContextCompat.registerReceiver(
            this,
            internetReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION), // deprecated
            ContextCompat.RECEIVER_EXPORTED
        )

        setContentView(R.layout.main_activity)

        val counter = findViewById<TextView>(R.id.counter)
        counter.text = "$count"
        counter.setOnClickListener {
            counter.text = "${++count}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(internetReceiver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("count")
        findViewById<TextView>(R.id.counter).text = "$count"
    }
}

class InternetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        Log.d(
            "InternetReceiver",
            when {
                network == null || capabilities == null -> "Internet is disabled"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "Wifi is enabled"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "Cellular is enabled"
                else -> "Internet is disabled"
            }
        )
    }
}

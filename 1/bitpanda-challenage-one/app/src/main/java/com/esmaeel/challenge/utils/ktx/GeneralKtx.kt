package com.esmaeel.challenge.utils.ktx

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.NumberFormat

fun Fragment.showToast(message: String?) = message?.let {
    makeToast(requireContext(), message)
}

fun makeToast(context: Context, message: String?) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun Activity.showToast(message: String?) = message?.let {
    makeToast(this, message)
}

fun Double.getPrice(precision: Int = 5, currencyCode: String = "EUR"): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance().also {
        it.maximumFractionDigits = precision
        try {
            it.currency = java.util.Currency.getInstance(currencyCode)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return format.format(this)
}

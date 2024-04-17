package com.yandey.rxjava3_android.util

import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showDialog(
    title: String,
    message: String,
    view: View? = null,
    neutralButtonText: String = "",
    onNeutralAction: ((DialogInterface) -> Unit)? = null,
    positiveButtonText: String,
    onPositiveAction: (DialogInterface) -> Unit
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setView(view)
        .setNeutralButton(neutralButtonText) { dialog, _ ->
            onNeutralAction?.invoke(dialog)
        }
        .setPositiveButton(positiveButtonText) { dialog, _ ->
            onPositiveAction(dialog)
        }
        .show()
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}
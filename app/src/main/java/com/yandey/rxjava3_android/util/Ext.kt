package com.yandey.rxjava3_android.util

import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showDialog(
    title: String,
    message: String,
    view: View? = null,
    neutralButtonText: String,
    onNeutralAction: (DialogInterface) -> Unit,
    positiveButtonText: String,
    onPositiveAction: () -> Unit
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setView(view)
        .setNeutralButton(neutralButtonText) { dialog, _ ->
            onNeutralAction(dialog)
        }
        .setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveAction()
        }
        .show()
}
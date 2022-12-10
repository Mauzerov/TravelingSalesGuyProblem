package com.mauzerov.travelingsalesguyproblem.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.provider.MediaStore
import android.view.View
import android.widget.Toast


fun saveViewAsImage(view: View, fileName: String, context: Context) {
    val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
    Canvas(bitmap).apply {
        drawColor(0xFFFFFFFF.toInt())
        view.draw(this)
    }

    context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        }
    )?.let {
        val fos = context.contentResolver.openOutputStream(it)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos?.close()
        Toast.makeText(context, "Zapisano do ${it.path}", Toast.LENGTH_SHORT).show()
    }

}
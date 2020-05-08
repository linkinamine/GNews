package com.benallouch.yunar.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.benallouch.yunar.R
import kotlin.properties.Delegates


inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
        initialValue: ArrayList<T>,
        crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
        crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
        Delegates.observable(initialValue) { _, old, new ->
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        areItemsTheSame(old[oldItemPosition], new[newItemPosition])

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        areContentsTheSame(old[oldItemPosition], new[newItemPosition])

                override fun getOldListSize(): Int = old.size

                override fun getNewListSize(): Int = new.size
            }).dispatchUpdatesTo(this@basicDiffUtil)
        }

fun String.toTextDrawable(
        fontSize: Int, context: Context
): TextDrawable? {
    return TextDrawable.builder()
            .beginConfig()
            .textColor(resolveTextColor(R.color.white_cream, context))
            .useFont(Typeface.MONOSPACE)
            .fontSize(fontSize) /* size in px */
            .bold()
            .withBorder(8)
            .toUpperCase()
            .endConfig()
            .buildRect(this, resolveTextColor(R.color.colorAccent, context))
}

@Suppress("DEPRECATION")
private fun resolveTextColor(color: Int, context: Context) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.resources.getColor(color, context.theme)
        } else {
            context.resources.getColor(color)
        }

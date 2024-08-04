package com.example.neurotalk.presentation.main.home.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.neurotalk.R

class ChatItemDivider(context: Context) : RecyclerView.ItemDecoration() {

    private val isDarkThemeEnabled = when (
        context.resources.configuration.uiMode
                and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK
    ) {
        android.content.res.Configuration.UI_MODE_NIGHT_NO -> false
        else -> true
    }

    private val paint: Paint = Paint().apply {
        color = context.getColor(
            if (isDarkThemeEnabled) R.color.white else R.color.black
        )
        strokeWidth = context.resources.getDimensionPixelSize(R.dimen.divider_width).toFloat()
    }

    private val verticalPadding: Int =
        context.resources.getDimensionPixelSize(R.dimen.divider_padding_vertical)
    private val horizontalPadding: Int =
        context.resources.getDimensionPixelSize(R.dimen.divider_padding_horizontal)
    private val itemTopPadding: Int =
        context.resources.getDimensionPixelSize(R.dimen.item_top_padding)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + horizontalPadding
        val right = parent.width - parent.paddingRight - horizontalPadding

        for (childIndex in 0 until parent.childCount) {
            val child = parent.getChildAt(childIndex)

            if (parent.getChildAdapterPosition(child) == parent.adapter?.itemCount?.minus(1)) {
                continue
            }

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin + verticalPadding
            val bottom = top + paint.strokeWidth.toInt() // ??

            c.drawLine(left.toFloat(), top.toFloat(), right.toFloat(), top.toFloat(), paint)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(horizontalPadding, itemTopPadding, horizontalPadding, verticalPadding)
    }

}
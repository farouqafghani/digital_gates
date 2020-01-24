package com.example.mazintask.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by Farouq Afghani at 12-2-2019
 */

class ItemPaddingDecoration : RecyclerView.ItemDecoration {

    private var mTopOffset: Int = 0
    private var mBottomOffset: Int = 0
    private var mStartOffset: Int = 0
    private var mEndOffset: Int = 0

    constructor(itemOffset: Int) {
        mTopOffset = dpToPx(itemOffset)
        mBottomOffset = mTopOffset
        mStartOffset = mTopOffset
        mEndOffset = mTopOffset
    }

    constructor(context: Context, @DimenRes mStartEndOffset: Int, @DimenRes mTopBottomOffset: Int) {
        this.mStartOffset = context.resources.getDimensionPixelSize(mStartEndOffset)
        mEndOffset = mStartEndOffset
        this.mTopOffset = context.resources.getDimensionPixelSize(mTopBottomOffset)
        mBottomOffset = mTopOffset
    }

    constructor(mStartEndOffset: Int, mTopBottomOffset: Int) {
        this.mStartOffset = dpToPx(mStartEndOffset)
        this.mEndOffset = dpToPx(mStartEndOffset)
        this.mTopOffset = dpToPx(mTopBottomOffset)
        this.mBottomOffset = dpToPx(mTopBottomOffset)
    }

    constructor(start: Int = 0, end: Int = 0, top: Int = 0, bottom: Int = 0) {
        this.mStartOffset = dpToPx(start)
        this.mEndOffset = dpToPx(end)
        this.mTopOffset = dpToPx(top)
        this.mBottomOffset = dpToPx(bottom)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mStartOffset, mTopOffset, mEndOffset, mBottomOffset)
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}
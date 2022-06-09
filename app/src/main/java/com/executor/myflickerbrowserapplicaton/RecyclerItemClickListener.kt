package com.executor.myflickerbrowserapplicaton

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.OnReceiveContentListener
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnRecyclerClickListener,
) : RecyclerView.SimpleOnItemTouchListener(), RecyclerView.OnItemTouchListener {
    private val TAG = "RecyclerItemClickListen"

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private val gestureDetector =
        GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                Log.d(TAG, "onSingleTapUp: starts")
                val childView = e?.let { recyclerView.findChildViewUnder(it.x, e.y) }
                Log.d(TAG, "onSingleTapUp: calling listener.onItemLongClick")
                listener.onItemClick(childView!!, recyclerView.getChildAdapterPosition(childView))
                return true
            }

            override fun onLongPress(e: MotionEvent?) {
                Log.d(TAG, "onLongPress: starts")
                val childView = e?.let { recyclerView.findChildViewUnder(it.x, e.y) }
                Log.d(TAG, "onLongPress: calling listener.onItemlongClick()")
                listener.onItemClick(childView!!, recyclerView.getChildAdapterPosition(childView))
            }
        })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, ".onInterceptTouchEvent: starts $e")
        val result = gestureDetector.onTouchEvent(e)
        Log.d(TAG, ".onInterceptTouchEvent() returning: $result")
//        return super.onInterceptTouchEvent(rv, e)
        return result
    }

}
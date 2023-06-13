package io.jamgenie.ui.utils

import android.os.Handler
import android.os.Looper
import android.os.Message

interface CountdownTimerListener {
    fun onTick(timeRemaining: Long)
    fun onFinish()
}

abstract class CountdownTimer : CountdownTimerListener {
    private var isTimerRunning = false
    private var isTimerPaused = false

    private var mTime: Long = 0
    private var localTime: Long = 0
    private var timeInterval: Long = 0
    private var mHandler: Handler? = null


    constructor(timeInMillis: Long, intervalInMillis: Long = INTERVAL) {
        init(timeInMillis, intervalInMillis)
    }

    private fun init(time: Long, interval: Long) {
        setCountdownTime(time)
        setTimeInterval(interval)
        initCountdownTimer()
    }

    private fun initCountdownTimer() {
        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == MSG) {
                    if (!isTimerPaused) {
                        if (localTime <= mTime) {
                            onTick(mTime - localTime)
                            localTime += timeInterval
                            sendMessageDelayed(mHandler!!.obtainMessage(MSG), timeInterval)
                        } else start()
                    }
                }
            }
        }
    }

    fun isTimerRunning(): Boolean {
        return isTimerRunning
    }

    fun start() {
        if (isTimerRunning) return
        isTimerRunning = true
        isTimerPaused = false
        localTime = 0
        mHandler!!.sendMessage(mHandler!!.obtainMessage(MSG))
    }


    fun stop() {
        isTimerRunning = false
        mHandler!!.removeMessages(MSG)
        onFinish()
    }

    fun cancelCountDownTimer() {
        isTimerRunning = false
        mHandler!!.removeMessages(MSG)
    }

    @Synchronized
    fun isTimerPaused(): Boolean {
        return isTimerPaused
    }

    @Synchronized
    fun pause() {
        this.isTimerPaused = true
    }

    @Synchronized
    fun resume() {
        this.isTimerPaused = false
        mHandler!!.sendMessage(mHandler!!.obtainMessage(MSG))
    }

    private fun setCountdownTime(timeInMillis: Long) {
        var timeInMillisTemp = timeInMillis
        if (isTimerRunning) return
        if (mTime <= 0) if (timeInMillis < 0) timeInMillisTemp *= -1
        mTime = timeInMillis
    }


    fun getRemainingTime(): Long {
        return if (isTimerRunning) {
            mTime
        } else 0
    }


    private fun setTimeInterval(intervalInMillis: Long) {
        var intervalInMillis = intervalInMillis
        if (isTimerRunning) return
        if (timeInterval <= 0) if (intervalInMillis < 0) intervalInMillis *= -1
        timeInterval = intervalInMillis
    }


    companion object {
        private const val INTERVAL = 1000L
        private const val MSG = 1
    }
}
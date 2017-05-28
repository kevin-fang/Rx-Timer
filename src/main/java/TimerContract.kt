/**
 * Timer contract (Kotlin)
 */
class TimerContract {

    interface ITimerPresenter {
        fun startTimer()
        fun pauseTimer()
        fun resetTimer(time: Int)
    }

    interface ITimerView {
        fun updateTime(time: String)
    }

}

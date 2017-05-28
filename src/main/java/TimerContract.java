/**
 * Created by kfang on 5/27/2017.
 */
public class TimerContract {
    interface ITimerModel {
        void startTimer();
        void pauseTimer();
    }
    interface ITimerPresenter {
        void startTimer();
        void pauseTimer();
    }

    interface ITimerView {
        void updateTime(String time);
    }

}

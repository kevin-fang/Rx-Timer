
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Timer presenter
 */
public class TimerPresenter implements TimerContract.ITimerPresenter {
    int initialMinutes;
    Subscription subscription;
    TimerContract.ITimerView timerView;
    long currentTime = -1;
    long totalSeconds;

    TimerPresenter(int initialMinutes, TimerContract.ITimerView timerView) {
        this.initialMinutes = initialMinutes;
        this.timerView = timerView;
    }

    public void startTimer() {
        totalSeconds = initialMinutes * 60;
        if (currentTime != -1) {
            totalSeconds = currentTime;
        }
        timerView.updateTime(formatTime(totalSeconds));
        subscription = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take((int) totalSeconds)
                .map(new Func1<Long, String>() {
                    public String call(Long aLong) {
                        currentTime = totalSeconds - aLong.intValue() - 1;
                        return formatTime(currentTime);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    public void onCompleted() {
                        timerView.updateTime("Timer is up!");
                        totalSeconds = initialMinutes * 60;
                    }

                    public void onError(Throwable throwable) {

                    }

                    public void onNext(String s) {
                        timerView.updateTime(s);
                    }
                });
    }

    static String formatTime(long seconds) {
        long currentSeconds = seconds % 60;
        long currentMinutes = seconds / 60;
        return String.format("%02d:%02d", currentMinutes, currentSeconds);
    }

    public void pauseTimer() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

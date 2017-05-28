import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Timer Model
 */

public class TimerView implements TimerContract.ITimerView {
    TimerContract.ITimerModel timer;
    JLabel timeDisplay;
    TimerContract.ITimerPresenter presenter;

    public static final int INITIAL_MINTUTES = 1;

    public static void main(String[] args) {
        TimerView view = new TimerView();
        view.setupSwing();
        view.presenter = new TimerPresenter(INITIAL_MINTUTES, view);
        view.updateTime(TimerPresenter.formatTime(INITIAL_MINTUTES * 60));
    }

    public void updateTime(String time) {
        timeDisplay.setText(time);
    }

    private void setupSwing() {
        JFrame frame = new JFrame();
        JButton start = new JButton("Start Timer");
        start.setBounds(150, 100, 100, 40);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                presenter.startTimer();
            }
        });
        JButton stop = new JButton("Stop Timer");
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                presenter.pauseTimer();
            }
        });
        stop.setBounds(150, 150, 100, 40);

        timeDisplay = new JLabel("00:00", SwingConstants.CENTER);
        timeDisplay.setBounds(150, 200, 100, 40);
        frame.add(timeDisplay);
        frame.add(start);
        frame.add(stop);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

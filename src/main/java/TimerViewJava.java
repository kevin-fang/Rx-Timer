// TimerView.java

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerViewJava implements TimerContract.ITimerView {
    private static final int DEFAULT_MINUTES = 3;
    private JLabel timeDisplay = new JLabel("00:00", SwingConstants.CENTER);
    private final TimerContract.ITimerPresenter presenter = (TimerContract.ITimerPresenter)(new TimerPresenter(DEFAULT_MINUTES, this));


    public void updateTime(@NotNull String time) {
        this.timeDisplay.setText(time);
    }

    void setupSwing() {
        JFrame frame = new JFrame();
        JButton start = new JButton("Start Timer");
        start.setBounds(150, 100, 100, 40);
        start.addActionListener(new ActionListener() {
            public final void actionPerformed(ActionEvent it) {
                presenter.startTimer();
            }
        });
        JButton stop = new JButton("Stop Timer");
        stop.addActionListener(new ActionListener() {
            public final void actionPerformed(ActionEvent it) {
                presenter.pauseTimer();
            }
        });
        stop.setBounds(150, 150, 100, 40);
        JButton reset = new JButton("Reset Timer");
        final JTextPane minuteBox = new JTextPane();
        StyledDocument doc = minuteBox.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, 1);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        minuteBox.setBounds(150, 275, 100, 20);
        reset.setBounds(140, 200, 120, 40);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent it) {
                if (minuteBox.getText() == null) {
                    presenter.resetTimer(DEFAULT_MINUTES);
                } else {

                    int currentMinutes = Integer.valueOf(minuteBox.getText());
                    presenter.resetTimer(currentMinutes);
                }

            }
        });

        this.timeDisplay = new JLabel("00:00", SwingConstants.CENTER);
        JLabel timeDisplay = this.timeDisplay;

        timeDisplay.setBounds(150, 240, 100, 40);
        frame.add(this.timeDisplay);
        frame.add(start);
        frame.add(stop);
        frame.add(minuteBox);
        frame.add(reset);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
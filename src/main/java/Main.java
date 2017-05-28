/**
 * Main class
 */
public class Main {

    private static final int INITIAL_MINUTES = 3;

    public static void main(String[] args) {
        TimerViewJava view = new TimerViewJava();
        view.setupSwing();
        view.updateTime(TimerPresenter.formatTime(INITIAL_MINUTES * 60));
    }
}

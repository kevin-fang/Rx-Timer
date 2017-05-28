import javax.swing.*
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants
import javax.swing.text.StyledDocument
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Timer Model
 */

class TimerView : TimerContract.ITimerView {
    internal var timeDisplay: JLabel = JLabel("00:00", SwingConstants.CENTER)
    internal val presenter: TimerContract.ITimerPresenter

    init {
        presenter = TimerPresenter(DEFAULT_MINUTES, this)
    }

    override fun updateTime(time: String) {
        timeDisplay.text = time
    }

    fun setupSwing() {
        val frame = JFrame()
        val start = JButton("Start Timer")
        start.setBounds(150, 100, 100, 40)
        start.addActionListener { presenter.startTimer() }
        val stop = JButton("Stop Timer")
        stop.addActionListener { presenter.pauseTimer() }
        stop.setBounds(150, 150, 100, 40)
        val reset = JButton("Reset Timer")
        val minuteBox = JTextPane()
        val doc = minuteBox.styledDocument
        val center = SimpleAttributeSet()
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER)
        doc.setParagraphAttributes(0, doc.length, center, false)
        minuteBox.setBounds(150, 275, 100, 20)

        reset.setBounds(140, 200, 120, 40)
        reset.addActionListener {
            if (minuteBox.text == null) {
                presenter.resetTimer(DEFAULT_MINUTES)
            } else {
                presenter.resetTimer(Integer.valueOf(minuteBox.text)!!)
            }
        }

        timeDisplay = JLabel("00:00", SwingConstants.CENTER)
        timeDisplay.setBounds(150, 240, 100, 40)

        frame.add(timeDisplay)
        frame.add(start)
        frame.add(stop)
        frame.add(minuteBox)
        frame.add(reset)
        frame.setSize(400, 500)
        frame.layout = null
        frame.isVisible = true
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }
}

val DEFAULT_MINUTES = 3
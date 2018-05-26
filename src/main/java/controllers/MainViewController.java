package controllers;

import io.RandomWordsReader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import models.Utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controls visual components,
 * Process data given to and given by user
 *
 * @author Rustem Azimov
 */
public class MainViewController {

	//Visual Components
	@FXML private TextArea randomTextArea;
	@FXML private Spinner<Integer> randomWordCountSpinner;
	@FXML private Button button;
	@FXML private Text elapsedTimeText;
	@FXML private TextArea inputTextArea;

	/**
	 * Structure that has 2 cases for button.
	 * If user click "Start" it's going to be turned to "Stop" and vice versa
	 */
	private enum ButtonStatus {
		START,
		STOP
	}

	//Variable that saves button state (Start or Stop)
	private ButtonStatus buttonStatus = ButtonStatus.START;

	//Variables keeps Time in mind
	private byte
				minutes,
				seconds,
				milliseconds;

	private String[] randomWords;

	//Initialize Time Features for measuring elapsed time
	Timer timer = new Timer();
	TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			milliseconds++;
			updateTime();
		}
	};

	//Input and Output text
	String
			randomText,
			inputText;

	/**
	 * It's called when the view is
	 * first loaded
	 */
	@FXML private void initialize() {
		/*
		 * Prepare "services" for
		 * being able to
		 *      1. generate random words,
		 *      2. check user answers
		 */
		RandomWordsReader.getInstance();
		Utils.getInstance();

		//Prepare word count spinner for using by user
		randomWordCountSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Short.MAX_VALUE));
	}

	/**
	 * It's called when user clicks on the button
	 */
	@FXML private void handleButtonAction() {
		switch(buttonStatus) {
			case START:
				reset();

				/*
				 *  Generate random words,
				 *  Keep them in mind,
				 *  Show to user
				 */
				this.randomWords = RandomWordsReader
						.getInstance()
						.findRandomWordsText(randomWordCountSpinner.getValue());
				randomText = toString(this.randomWords);

				randomTextArea.setText(randomText);
				timer.schedule(timerTask, 1);

				button.setText("Stop");
				buttonStatus = ButtonStatus.STOP;

				break;

			case STOP:
				Alert dialog = new Alert(Alert.AlertType.INFORMATION);
				dialog.setTitle("Your Results");
				dialog.setHeaderText(null);
				dialog.setContentText(
						String.format(
								"Score: %f (answers per milliseconds)",
								((double) Utils
										.getInstance()
										.findAnswersScoreCount(
												inputTextArea.getText(),
												randomWords
										)
										/ seconds)
						));
				timer.cancel();

				button.setText("Start");
				buttonStatus = ButtonStatus.STOP;
		}


	}

	/**
	 * The method prepares visual components for the task again
	 */
	private void reset() {
		//Clear the texts
		randomTextArea.setText("");
		inputTextArea.setText("");

		//Reset Time
		minutes = 0;
		seconds = 0;
		milliseconds = 0;
		elapsedTimeText.setText("00:00:000");
	}

	private void updateTime() {
		if (milliseconds  == 1000)
		{
			seconds++;
			milliseconds = 0;
		}
		if (seconds == 60)
		{
			minutes++;
			seconds = 0;
		}
		elapsedTimeText.setText(String.format("%d:%d:%d",
												minutes,
												seconds,
												milliseconds));
	}
	private String toString(String[] arr) {
		StringBuilder builder = new StringBuilder(5 * arr.length);
		for (String item :  arr) {
			builder.append(item + " ");
		}
		return builder.toString();
	}
}

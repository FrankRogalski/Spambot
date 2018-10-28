package controller;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class MainWindowController {
	@FXML
	private TextField text, speed;

	@FXML
	private Button toggle;

	@FXML
	private CheckBox enter;

	private Timeline tl;
	private Robot bot;

	public MainWindowController() {
		try {
			bot = new Robot();
		} catch (Exception ex) {
		}
	}

	@FXML
	public void spamText() {
		if (tl == null || tl.getStatus() != Status.RUNNING) {
			toggle.setText("Ausschalten");
			try {
				double speed = 1000d / Double.parseDouble(this.speed.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text.getText()), null);
				tl = new Timeline(new KeyFrame(Duration.millis(speed), e -> {
					spam();
				}));
				tl.setCycleCount(Timeline.INDEFINITE);
				tl.play();
			} catch (Exception ex) {
			}
		} else {
			toggle.setText("Anschalten");
			tl.stop();
		}
	}

	private void spam() {
		if (!text.isFocused() && !speed.isFocused()) {
			bot.keyPress(KeyEvent.VK_CONTROL);
			bot.keyPress(KeyEvent.VK_V);
			bot.keyRelease(KeyEvent.VK_V);
			bot.keyRelease(KeyEvent.VK_CONTROL);
			if (enter.isSelected()) {
				bot.keyPress(KeyEvent.VK_ENTER);
				bot.keyRelease(KeyEvent.VK_ENTER);
			}
		}
	}
}

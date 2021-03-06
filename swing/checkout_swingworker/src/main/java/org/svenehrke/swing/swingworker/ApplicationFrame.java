package org.svenehrke.swing.swingworker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationFrame {
	public JFrame newComponent() {
		final JFrame result = new JFrame("Hello Swing");
		result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("Click Me");

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AnswerWorker(result).execute();
			}
		};

		button.addActionListener(actionListener);

		result.add(button, BorderLayout.SOUTH);
		result.setSize(300, 100);
		result.setVisible(true);
		return result;
	}
}

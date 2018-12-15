package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.UI.AppWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {

	protected JButton purchaseButton;
	protected JButton drawButton;
	protected JButton playCardButton;
	protected JButton improveButton;
	protected JButton rollDice;
	protected JButton endTurn;
	protected JButton resumeButton;
	protected JButton pauseButton;

    private int offset = 100;
    private int buttonHeight = 35;

    protected JButton saveButton;
    
    private CommunicationController communicationController = CommunicationController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();

	public ButtonPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        setOpaque(false);
        purchaseButton = new JButton("Buy Property");
        //purchaseButton = new CustomButton("Buy Property");
        drawButton = new JButton("Draw card");
        playCardButton = new JButton("Play card");
        improveButton = new JButton("Improve Property");
        rollDice = new JButton("Roll Dice");
        endTurn = new JButton("End Turn");
        resumeButton = new JButton("Resume");
        pauseButton = new JButton("Pause");
		saveButton = new JButton("Save");


        // THESE ARE WORTHLESS, BOXLAYOUT DOESN'T CARE!
//        purchaseButton.setBounds(150, offset, 100, 30);
//        drawButton.setBounds(150, offset + buttonHeight, 100, 30);
//        playCardButton.setBounds(150, offset + 2*buttonHeight, 100, 30);
//        improveButton.setBounds(150, offset + 3*buttonHeight, 100, 30);
//        rollDice.setBounds(150, offset + 4*buttonHeight, 100, 30);
//        endTurn.setBounds(150, offset + 5*buttonHeight, 100, 30);
//        resumeButton.setBounds(150, offset + 6*buttonHeight, 100, 30);
//        pauseButton.setBounds(150, offset + 7*buttonHeight, 100, 30);

        panel.add(rollDice);
        panel.add(endTurn);
        panel.add(purchaseButton, BorderLayout.CENTER);
        panel.add(drawButton);
        panel.add(playCardButton);
        panel.add(improveButton);
        panel.add(resumeButton);
        panel.add(pauseButton);

        this.setMaximumSize(panel.getMaximumSize());

        this.add(panel);

		purchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("purchase");
				communicationController.sendClientMessage("purchase");
				purchaseButton.setEnabled(false);
				;
			}
		});

		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("drawCard");
				communicationController.sendClientMessage("card/draw");
				drawButton.setEnabled(false);
			}
		});

		playCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//communicationController.sendClientMessage("playCard");
				playCardButton.setEnabled(false);
			}
		});

		improveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//communicationController.sendClientMessage("improveProperty");
				improveButton.setEnabled(false);
			}
		});

		resumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Game resumed");
				communicationController.sendClientMessage("resume");
				pauseButton.setEnabled(true);
				resumeButton.setEnabled(false);
			}
		});

		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Game paused");
				communicationController.sendClientMessage("pause");
				pauseButton.setEnabled(false);
				resumeButton.setEnabled(true);
			}
		});

		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!gameEngine.getRoll3())
					gameEngine.rollDice();
				else
					gameEngine.roll3Dice();
				communicationController.sendClientMessage("dice/" + gameEngine.getLastDiceValues());
				rollDice.setEnabled(false);
			}
		});

		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				communicationController.sendClientMessage("player/next");
				endTurn.setEnabled(false);
			}
		});


		purchaseButton.setEnabled(false);
		endTurn.setEnabled(false);
		drawButton.setEnabled(false);
		playCardButton.setEnabled(false);
		improveButton.setEnabled(false);
		rollDice.setEnabled(false);
		resumeButton.setEnabled(false);
		pauseButton.setEnabled(true);
		this.setVisible(true);

	}
}

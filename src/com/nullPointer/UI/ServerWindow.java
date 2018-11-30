package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Observer;
import com.nullPointer.Domain.Server.ServerInfo;
import com.nullPointer.Utils.ColorSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ServerWindow extends JPanel implements Observer {
    private JButton startGame;
    private JButton addPlayer;
    private JButton quitServer;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();
    private Navigator navigator = Navigator.getInstance();
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private JPanel playerPanel;
    private List<ClientDisplay> clientDisplayList;
    private List<PlayerDisplay> playerDisplayList;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Image background;
    private File backgroundSrc = new File("./assets/background2.jpg");

    public ServerWindow() {

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(new JLabel("Server Screen"));
        this.add(buttonPanel);
        addButtons(buttonPanel);

        gameEngine.subscribe(this);

        try {
            background = ImageIO.read(backgroundSrc);
            background = background.getScaledInstance(
                    screenSize.width,
                    screenSize.height,
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        createClientDisplay();
        createPlayerDisplay();
        this.add(scrollPane);

    }

    private void addButtons(JPanel panel) {

        startGame = new CustomButton("Start Game");
        startGame.setToolTipText("Start the game ");
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startServer();
                //navigator.gameScreen();
            }
        });
        panel.add(startGame);

        addPlayer = new CustomButton("Add player");
        addPlayer.setToolTipText("add new player ");
        addPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player player = new Player("Test", serverInfo.getClientID());
                communicationController.sendClientMessage(player);
                //navigator.gameScreen();
            }
        });
        panel.add(addPlayer);

        quitServer = new CustomButton("Quit Server ");
        quitServer.setToolTipText("Quit from the server");
        quitServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.removeClient();
                navigator.menuScreen();
            }
        });
        panel.add(quitServer);

    }

    private void startServer() {
        communicationController.sendClientMessage("game/start");
    }

    public List<ClientDisplay> createClientDisplay() {
        List<Integer> clientList = serverInfo.getClientList();
        clientDisplayList = new ArrayList<>();
        for (int i = 0; i < clientList.size(); i++) {
            ClientDisplay clientDisplay = new ClientDisplay("Computer " + (i + 1), new Point(200, i * 200));
            clientDisplayList.add(clientDisplay);
        }
        return clientDisplayList;
    }

    public void addClient() {
        createClientDisplay();
    }

    public void createPlayerDisplay() {
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBounds(600, 100, 120, 600);
        playerPanel.setPreferredSize(new Dimension(120, 600));
        playerPanel.setBackground(ColorSet.WHITE);
        scrollPane = new JScrollPane(playerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(600, 100, 120, 300);
        scrollPane.setBackground(ColorSet.BLACK);
        this.add(scrollPane);
        scrollPane.validate();
    }

    public void addPlayer() {
        ArrayList<Player> pList = gameEngine.getPlayerController().getPlayers();
        CustomButton newButton = new CustomButton(pList.get(pList.size() - 1).getName());
        newButton.setPreferredSize(new Dimension(100, 100));

        playerPanel.add(newButton);
        playerPanel.validate();
    }


    @Override
    public void onEvent(String message) {
        if (message.equals("newClient")) {
            this.addClient();
            repaint();
        } else if (message.equals("newPlayer")) {
            addPlayer();
            //repaint();
        }
    }

    public void paint(Graphics g) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        super.paint(g);
        //g.drawImage(background, 0, 0, null);
        clientDisplayList.forEach(clientDisplay -> clientDisplay.paint(g));
        buttonPanel.setLocation((screenSize.width - buttonPanel.getWidth()) / 2, 300);
        scrollPane.setLocation((screenSize.width - buttonPanel.getWidth()) / 4 * 3, 300);
    }
}

class ClientDisplay {

    String clientName;
    Point position;
    int width = 300;
    int height = 100;

    ClientDisplay(String name, Point position) {
        clientName = name;
        this.position = position;
    }

    public void paint(Graphics g) {
        Color color = new Color(255, 255, 255);
        g.setColor(color);
        g.fillRect(position.x, position.y, width, height);

        color = new Color(0, 0, 0);
        g.setColor(color);
        g.drawString(clientName, position.x + 20, position.y + height / 2);


    }
}

class PlayerDisplay {

    String clientName;
    Point position;
    int width = 300;
    int height = 100;

    PlayerDisplay(String name, Point position) {
        clientName = name;
        this.position = position;
    }

    public void paint(Graphics g) {
        Color color = new Color(255, 255, 255);
        g.setColor(color);
        g.fillRect(position.x, position.y, width, height);

        color = new Color(0, 0, 0);
        g.setColor(color);
        g.drawString(clientName, position.x + 20, position.y + height / 2);

    }
}
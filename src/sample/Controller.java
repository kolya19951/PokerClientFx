package sample;

import Client.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import javax.swing.*;


public class Controller {

    @FXML
    public Button button;
    @FXML
    private TextField login;
    @FXML
    private Pane panel1, panel2;
    @FXML
    public Label name0, name1, name2, name3, name4, name5;
    public Label[] names = new Label[]{name0, name1, name2, name3, name4, name5};
    @FXML
    public Label bankroll0, bankroll1, bankroll2, bankroll3, bankroll4, bankroll5;
    public Label[] bankrolls = new Label[]{bankroll0, bankroll1, bankroll2, bankroll3, bankroll4, bankroll5};
    @FXML
    private Label bet0, bet1, bet2, bet3, bet4, bet5;
    public Label[] bets = new Label[]{bet0, bet1, bet2, bet3, bet4, bet5};
    @FXML
    private ImageView card1_0, card2_0, card1_1, card2_1, card1_2, card2_2, card1_3, card2_3, card1_4, card2_4, card1_5, card2_5;
    public ImageView[] cards = new ImageView[]{card1_0, card2_0, card1_1, card2_1, card1_2, card2_2, card1_3, card2_3, card1_4, card2_4, card1_5, card2_5};
    @FXML
    public Label bank;
    @FXML
    private static ImageView flop1, flop2, flop3, turn, river;
    public static ImageView[] flops = new ImageView[]{flop1, flop2, flop3};
    @FXML
    private Button checkButton, foldButton, raiseButton;
    private Command command;


    @FXML
    public void initialize() {
        for (int i = 0; i < 6; i++) {
            names[i] = new Label();
            bankrolls[i] = new Label();
            bets[i] = new Label();
            cards[i * 2] = new ImageView();
            cards[i * 2 + 1] = new ImageView();
        }
        for (int i = 0; i < 3; i++) {
            flops[i] = new ImageView();
        }
        command = new Command();
        Thread com = new Thread(command);
        command.AddController(this);
        com.start();
        cards[2].setImage(new Image("img/cards/30.png"));
        panel2.setVisible(false);
        panel1.setLayoutX(0);
        panel1.setLayoutY(0);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.client.SendUTF(login.getText());
                panel1.setVisible(false);
                panel2.setLayoutX(0);
                panel2.setLayoutY(0);
                panel2.setVisible(true);
            }
        });
        checkButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.client.SendInt(0);
                setButtonsDisable();
            }
        });
        foldButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.client.SendInt(-1);
                setButtonsDisable();
            }
        });
        raiseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.client.SendInt(100);
                setButtonsDisable();
            }
        });
    }

    public static void ChangeForm() {
        System.out.println("aaa");
    }

    public void gameStartInfo() {
        for (int i = 0; i < 6; i++) {
            int position = Main.client.ReadInt();
            String login = Main.client.ReadUTF();
            int bankroll = Main.client.ReadInt();
            names[position].setText(login);
            bankrolls[position].setText(Integer.toString(bankroll));
        }
    }


    public void setButtonsEnable() {
        checkButton.setDisable(false);
        raiseButton.setDisable(false);
        foldButton.setDisable(false);
    }

    public void setButtonsDisable() {
        checkButton.setDisable(true);
        raiseButton.setDisable(true);
        foldButton.setDisable(true);

    }

}
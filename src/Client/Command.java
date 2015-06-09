package Client;


import javafx.scene.image.*;
import sample.Controller;
import sample.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;

/**
 * Created by ���� on 26.05.2015.
 */
public class Command implements Runnable{
    private static String command;
    private String card_1, card_2, login, field;
    private int position, bankroll;
    private Controller controller;

    public void run(){
        WaitCommand();
    }
    /*public Command(Client client2, Controller controller) {
        Main.client = client2;
        this.controller = controller;
    }*/

    public void AddController(Controller controller1){
        this.controller = controller1;
    }


    public void Action() {
        if (command.equals("game start info"))
            gameStartInfo();
        if (command.equals("you hand"))
            youHand();
        if (command.equals("change"))
            Change();
        if (command.equals("flop"))
            Flop();
        if (command.equals("turn"))
            Turn();
        if (command.equals("river"))
            River();
        if (command.equals("bank"))
            Bank();
        if (command.equals("small blind"))
            smallBlind();
        if (command.equals("big blind"))
            bigBlind();
        if (command.equals("You turn"))
            youTurn();
    }

    public void WaitCommand() {
        System.out.println("w8");
        command = Main.client.ReadUTF();
        Action();
    }

    private void gameStartInfo() {
        for (int i = 0; i < 6; i++) {
            position = Main.client.ReadInt();
            login = Main.client.ReadUTF();
            bankroll = Main.client.ReadInt();
            controller.names[position].setText(login);
            controller.bankrolls[position].setText(Integer.toString(bankroll));
        }
    }

    private void youHand() {
        card_1 = Main.client.ReadUTF();
        card_2 = Main.client.ReadUTF();
    }

    private void smallBlind() {
    }

    private void bigBlind() {
    }

    private void Change() {
        position = Main.client.ReadInt();
        field = Main.client.ReadUTF();
        if (field.equals("bet"))
            controller.bets[position].setText(String.valueOf(Main.client.ReadInt()));
        if (field.equals("bankroll"))
            controller.bankrolls[position].setText(String.valueOf(Main.client.ReadInt()));
        if (field.equals("bank"))
            controller.bank.setText(String.valueOf(Main.client.ReadInt()));
        if (field.equals("hand")) {
            controller.cards[position * 2].setImage(new javafx.scene.image.Image(getCard()));
            controller.cards[position * 2 + 1].setImage(new javafx.scene.image.Image(getCard()));
        }
    }

    private void Flop() {
        for (int i = 0; i < 3; i++) {
            controller.flops[i].setImage(new javafx.scene.image.Image(getCard()));
        }
    }

    private String getCard() {
        card_1 = Main.client.ReadUTF();
        if (card_1.equals("null"))
            return "img/cards/back.png";
        return "img/cards/" + card_1 + ".png";
    }

    private void Bank() {
        String bank;
        bank = new String(String.valueOf(Main.client.ReadInt()));
        controller.bank.setText(bank);
    }

    private void youTurn() {
        controller.setButtonsEnable();
    }

    private void Turn() {
        //controller.turn.setImage(new javafx.scene.image.Image(getCard()));
    }

    private void River() {
       // controller.river.setImage(new javafx.scene.image.Image(getCard()));
    }
}



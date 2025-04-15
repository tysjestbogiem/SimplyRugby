package simpleRugby.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * SkillDevelopmentPanel is the GUI where coaches can select a player 
 * and (in the future) manage skill development.
 * It includes a JComboBox for choosing a player.
 */
public class SkillDevelopmentPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JComboBox<String> cmbPlayers;
    private JLabel lblChoosePlayer;

    public SkillDevelopmentPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        JLabel lblSkillsDev = new JLabel("Skill Development");
        lblSkillsDev.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblSkillsDev.setBounds(50, 100, 200, 50);
        add(lblSkillsDev);

        lblChoosePlayer = new JLabel("Choose Player:");
        lblChoosePlayer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblChoosePlayer.setBounds(50, 147, 200, 25);
        add(lblChoosePlayer);

        cmbPlayers = new JComboBox<>();
        cmbPlayers.setBounds(50, 182, 279, 25);
        add(cmbPlayers);
    }

    // fills combo box with player names
    public void populateCmb(List<Object[]> players) {
        cmbPlayers.removeAllItems(); // clear existing items

        for (Object[] row : players) {
            String fullName = row[0] + " " + row[1];
            cmbPlayers.addItem(fullName);
        }
    }


    public String getSelectedPlayer() {
        return (String) cmbPlayers.getSelectedItem();
    }
}

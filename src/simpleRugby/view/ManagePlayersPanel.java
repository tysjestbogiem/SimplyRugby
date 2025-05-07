package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import simpleRugby.controler.ManagePlayersController;
import simpleRugby.model.ManagePlayersDAO;
import simpleRugby.model.Player;
import simpleRugby.model.SessionManager;
import simpleRugby.model.SquadDAO;

/**
 * ManagePlayersPanel is the GUI panel where a coach can view list of players.
 * 
 * This panel is part of CoachGUI layout and gets filled with player data by 
 * ManagePlayersPanelController. 
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.*;
import java.util.List;

public class ManagePlayersPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private Object currentValue;
    private ManagePlayersController myManagePlayersController;

    public ManagePlayersPanel() {

        setLayout(new BorderLayout()); // main layout
        setBackground(Color.LIGHT_GRAY);

        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(50, 50, 50, 50)); 
        contentPanel.setBackground(Color.LIGHT_GRAY);

        // top panel for labels
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 0, 20)); 
        topPanel.setBackground(Color.LIGHT_GRAY);
        
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        JLabel lblTeam = new JLabel();
        lblTeam.setFont(new Font("SansSerif", Font.BOLD, 16));
        String teamName = SquadDAO.getSquadNameByCoach(SessionManager.getUserId());
        lblTeam.setText("Team: " + teamName);
        topPanel.add(lblTeam);

        JLabel lblPlayers = new JLabel("Players");
        lblPlayers.setFont(new Font("SansSerif", Font.PLAIN, 16));
        topPanel.add(lblPlayers);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Table setup
        model = new DefaultTableModel();
        String[] col = { "ID", "Name", "Surname", "Team", "Position" };
        model.setColumnIdentifiers(col);

        table = new JTable(model) {
        	@Override
            public boolean isCellEditable(int row, int column) {
                
                return column == 4; 
            }
        };
        //table.removeColumn(table.getColumnModel().getColumn(0));
        

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());

                if (column == 4 && row >= 0) {
                    String currentValue = (String) model.getValueAt(row, column);

                    String[] positions = {
                        "Full Back", "Wing", "Centre", "Fly half", "Scrum half",
                        "Hooker", "Prop", "2nd row", "Back row"
                    };

                    JComboBox<String> comboBox = new JComboBox<>(positions);
                    comboBox.setSelectedItem(currentValue);

                    int result = JOptionPane.showConfirmDialog(
                        null,
                        comboBox,
                        "Change player position",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        String selectedPosition = (String) comboBox.getSelectedItem();
                        int playerId = getPlayerIdByRow(row); 

                        boolean updated = myManagePlayersController.positionChange(playerId, selectedPosition);

                        if (updated) {
                            model.setValueAt(selectedPosition, row, column);
                            JOptionPane.showMessageDialog(null, "Position updated.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Update failed.");
                        }
                    }
                }
            }
        });



        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); // table sorting
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        table.setFont(new Font("SansSerif", Font.PLAIN, 14)); 
        table.setRowHeight(20); 
        JScrollPane scrollPane = new JScrollPane(table);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // add content panel to main panel
        add(contentPanel, BorderLayout.CENTER);
    }
    
    public void setController(ManagePlayersController controller) {
        this.myManagePlayersController = controller;
    }

	public void populateTable(List<Player> players) {
       // model.setRowCount(0); // clear table first
        for (Player player : players) {
            model.addRow(new Object[]{
            	player.getMemberId(),
            	player.getFirstName(),
            	player.getSurname(),
            	player.getTeamName(),
            	player.getPosition()
            });
        }
    }
    
    private int getPlayerIdByRow(int viewRow) {
        int modelRow = table.convertRowIndexToModel(viewRow);
        Object playerId = model.getValueAt(modelRow, 0); 
        return Integer.parseInt(playerId.toString());
    }

}


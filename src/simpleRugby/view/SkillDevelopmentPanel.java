package simpleRugby.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simpleRugby.controler.SkillDevelopmentController;
import simpleRugby.model.Player;
import simpleRugby.model.Skill;

public class SkillDevelopmentPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cmbPlayers;
    private JTable table;
    private Border blackBorder;
    private SkillDevelopmentController mySkillDevelopmentController;
    private JRadioButton rbtStandard_1;
    private JRadioButton rbtStandard_2;
    private JRadioButton rbtStandard_3;
    private JRadioButton rbtStandard_4;
    private JRadioButton rbtStandard_5;
    private JTextArea txtCommentPassing;
    


    public SkillDevelopmentPanel() {
    	
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.LIGHT_GRAY);

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());  
        contentPanel.setBorder(new EmptyBorder(50, 50, 20, 20));
        contentPanel.setBackground(Color.LIGHT_GRAY);
        add(contentPanel, BorderLayout.CENTER);

        // === Top Panel (form area) ===
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);

        
        GridBagLayout gbl_topPanel = new GridBagLayout();
        gbl_topPanel.columnWidths = new int[]{454, 245, 0};
        gbl_topPanel.rowHeights = new int[]{30, 30, 30, 0};
        gbl_topPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        topPanel.setLayout(gbl_topPanel);
                                
                                        JLabel lblTitle = new JLabel("Skill Development");
                                        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
                                        GridBagConstraints gbc_lblTitle = new GridBagConstraints();
                                        gbc_lblTitle.fill = GridBagConstraints.BOTH;
                                        gbc_lblTitle.insets = new Insets(0, 0, 20, 10);
                                        gbc_lblTitle.gridx = 0;
                                        gbc_lblTitle.gridy = 0;
                                        topPanel.add(lblTitle, gbc_lblTitle);
                        
                                JLabel lblPlayers = new JLabel("Team name");
                                lblPlayers.setFont(new Font("SansSerif", Font.PLAIN, 16));
                                GridBagConstraints gbc_lblPlayers = new GridBagConstraints();
                                gbc_lblPlayers.fill = GridBagConstraints.BOTH;
                                gbc_lblPlayers.insets = new Insets(0, 0, 20, 10);
                                gbc_lblPlayers.gridx = 0;
                                gbc_lblPlayers.gridy = 1;
                                topPanel.add(lblPlayers, gbc_lblPlayers);
                
                        JLabel lblTrainingDate = new JLabel("Training date:");
                        lblTrainingDate.setFont(new Font("SansSerif", Font.PLAIN, 16));
                        GridBagConstraints gbc_lblTrainingDate = new GridBagConstraints();
                        gbc_lblTrainingDate.fill = GridBagConstraints.BOTH;
                        gbc_lblTrainingDate.insets = new Insets(0, 0, 5, 0);
                        gbc_lblTrainingDate.gridx = 1;
                        gbc_lblTrainingDate.gridy = 1;
                        topPanel.add(lblTrainingDate, gbc_lblTrainingDate);

        
                
                        cmbPlayers = new JComboBox<>();
                        cmbPlayers.setPreferredSize(new Dimension(300, 30));
                        GridBagConstraints gbc_cmbPlayers = new GridBagConstraints();
                        gbc_cmbPlayers.fill = GridBagConstraints.BOTH;
                        gbc_cmbPlayers.insets = new Insets(0, 0, 0, 5);
                        gbc_cmbPlayers.gridx = 0;
                        gbc_cmbPlayers.gridy = 2;
                        topPanel.add(cmbPlayers, gbc_cmbPlayers);
        
                JDateChooser trainingDate = new JDateChooser();
                trainingDate.getCalendarButton().setBounds(200, 0, 45, 30);
                trainingDate.setDateFormatString("dd-MM-yyyy");
                GridBagConstraints gbc_trainingDate = new GridBagConstraints();
                gbc_trainingDate.fill = GridBagConstraints.BOTH;
                gbc_trainingDate.gridx = 1;
                gbc_trainingDate.gridy = 2;
                topPanel.add(trainingDate, gbc_trainingDate);
                trainingDate.setLayout(null);


                GridBagConstraints gbc_topPanel = new GridBagConstraints();
                gbc_topPanel.gridx = 0;
                gbc_topPanel.gridy = 0;
                gbc_topPanel.fill = GridBagConstraints.HORIZONTAL;
                gbc_topPanel.insets = new Insets(0, 0, 10, 0);
                contentPanel.add(topPanel, gbc_topPanel);

                // === Table Panel ===
                JPanel tablePanel = new JPanel();
                tablePanel.setLayout(new GridBagLayout());
                tablePanel.setBackground(Color.LIGHT_GRAY);

                
        
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        
        // GridBagLayout settings
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 150, 150, 30, 30, 30, 30, 30, 250, 0}; 
        gbl_panel.rowHeights = new int[]{40, 25, 40, 40, 40, 40, 40, 40, 40, 40, 50, 40, 40, 40, 40}; 
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        tablePanel.setLayout(gbl_panel);
        
        // ===== HEADER =====
        
        JLabel lblCategory = new JLabel("Category", SwingConstants.CENTER);
        lblCategory.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblCategory.setBorder(blackBorder);
        GridBagConstraints gbc_lblCategory = new GridBagConstraints();
        gbc_lblCategory.fill = GridBagConstraints.BOTH;
        gbc_lblCategory.insets = new Insets(1, 1, 1, 1);
        gbc_lblCategory.gridheight = 2;
        gbc_lblCategory.gridx = 1;
        gbc_lblCategory.gridy = 0;
        tablePanel.add(lblCategory, gbc_lblCategory);
        
        JLabel lblPassing = new JLabel("Passing", SwingConstants.CENTER);
        lblPassing.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPassing.setBorder(blackBorder);
        GridBagConstraints gbc_lblPassing = new GridBagConstraints();
        gbc_lblPassing.fill = GridBagConstraints.BOTH;
        gbc_lblPassing.insets = new Insets(1, 1, 1, 1);
        gbc_lblPassing.gridheight = 3;
        gbc_lblPassing.gridx = 1;
        gbc_lblPassing.gridy = 2;
        tablePanel.add(lblPassing, gbc_lblPassing);
        
        JLabel lblTackling = new JLabel("Tackling", SwingConstants.CENTER);
        lblTackling.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTackling.setBorder(blackBorder);
        GridBagConstraints gbc_lblTackling = new GridBagConstraints();
        gbc_lblTackling.fill = GridBagConstraints.BOTH;
        gbc_lblTackling.insets = new Insets(1, 1, 1, 1);
        gbc_lblTackling.gridheight = 4;
        gbc_lblTackling.gridx = 1;
        gbc_lblTackling.gridy = 5;
        tablePanel.add(lblTackling, gbc_lblTackling);
        
        JLabel lblKicking = new JLabel("Kicking", SwingConstants.CENTER);
        lblKicking.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblKicking.setBorder(blackBorder);
        GridBagConstraints gbc_lblKicking = new GridBagConstraints();
        gbc_lblKicking.fill = GridBagConstraints.BOTH;
        gbc_lblKicking.insets = new Insets(1, 1, 1, 1);
        gbc_lblKicking.gridheight = 4;
        gbc_lblKicking.gridx = 1;
        gbc_lblKicking.gridy = 9;
        tablePanel.add(lblKicking, gbc_lblKicking);
        
        JLabel lblSkill = new JLabel("Skill", SwingConstants.CENTER);
        lblSkill.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblSkill.setBorder(blackBorder);
        GridBagConstraints gbc_lblSkill = new GridBagConstraints();
        gbc_lblSkill.fill = GridBagConstraints.BOTH;
        gbc_lblSkill.insets = new Insets(1, 1, 1, 1);
        gbc_lblSkill.gridheight = 2;
        gbc_lblSkill.gridx = 2;
        gbc_lblSkill.gridy = 0;
        tablePanel.add(lblSkill, gbc_lblSkill);
        
        JLabel lblSkillLevel = new JLabel("Skill Level", SwingConstants.CENTER);
        lblSkillLevel.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblSkillLevel.setBorder(blackBorder);
        GridBagConstraints gbc_lblSkillLevel = new GridBagConstraints();
        gbc_lblSkillLevel.fill = GridBagConstraints.BOTH;
        gbc_lblSkillLevel.insets = new Insets(1, 1, 1, 1);
        gbc_lblSkillLevel.gridwidth = 5;
        gbc_lblSkillLevel.gridx = 3;
        gbc_lblSkillLevel.gridy = 0;
        tablePanel.add(lblSkillLevel, gbc_lblSkillLevel);
        
        JLabel lblComment = new JLabel("Comment", SwingConstants.CENTER);
        lblComment.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblComment.setBorder(blackBorder);
        GridBagConstraints gbc_lblComment = new GridBagConstraints();
        gbc_lblComment.fill = GridBagConstraints.BOTH;
        gbc_lblComment.insets = new Insets(1, 1, 1, 1);
        gbc_lblComment.gridheight = 2;
        gbc_lblComment.gridx = 8;
        gbc_lblComment.gridy = 0;
        tablePanel.add(lblComment, gbc_lblComment);
        
        txtCommentPassing = new JTextArea();
        txtCommentPassing.setBorder(blackBorder);
        txtCommentPassing.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentPassing.setLineWrap(true);
        txtCommentPassing.setWrapStyleWord(true);
        
        JScrollPane scrollCommentPassing = new JScrollPane(txtCommentPassing);
        scrollCommentPassing.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentPassing.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentPassing = new GridBagConstraints();
        gbc_txtCommentPassing.fill = GridBagConstraints.BOTH;
        gbc_txtCommentPassing.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentPassing.gridheight = 3;
        gbc_txtCommentPassing.gridx = 8;
        gbc_txtCommentPassing.gridy = 2;
        tablePanel.add(scrollCommentPassing, gbc_txtCommentPassing);
        
        JTextArea txtCommentTackling = new JTextArea();
        txtCommentTackling.setBorder(blackBorder);
        txtCommentTackling.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentTackling.setLineWrap(true);
        txtCommentTackling.setWrapStyleWord(true);
        
        JScrollPane scrollCommentTackling = new JScrollPane(txtCommentTackling);
        scrollCommentTackling.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentTackling.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentTackling = new GridBagConstraints();
        gbc_txtCommentTackling.fill = GridBagConstraints.BOTH;
        gbc_txtCommentTackling.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentTackling.gridheight = 4;
        gbc_txtCommentTackling.gridx = 8;
        gbc_txtCommentTackling.gridy = 5;
        tablePanel.add(scrollCommentTackling, gbc_txtCommentTackling);
        
        JTextArea txtCommentKicking = new JTextArea();
        txtCommentKicking.setBorder(blackBorder);
        txtCommentKicking.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentKicking.setLineWrap(true);
        txtCommentKicking.setWrapStyleWord(true);
        
        JScrollPane scrollCommentKicking = new JScrollPane(txtCommentKicking);
        scrollCommentKicking.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // vertical scrollbar
        scrollCommentKicking.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentKicking = new GridBagConstraints();
        gbc_txtCommentKicking.fill = GridBagConstraints.BOTH;
        gbc_txtCommentKicking.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentKicking.gridheight = 4;
        gbc_txtCommentKicking.gridx = 8;
        gbc_txtCommentKicking.gridy = 9;
        tablePanel.add(scrollCommentKicking, gbc_txtCommentKicking);
        
        // SKILL LEVEL NUMBERS 
        // standard radio button grouping 
        
        JLabel lblOne = new JLabel("1", SwingConstants.CENTER);
        lblOne.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblOne.setBorder(blackBorder);
        GridBagConstraints gbc_lblOne = new GridBagConstraints();
        gbc_lblOne.fill = GridBagConstraints.BOTH;
        gbc_lblOne.insets = new Insets(1, 1, 1, 1);
        gbc_lblOne.gridx = 3;
        gbc_lblOne.gridy = 1;
        tablePanel.add(lblOne, gbc_lblOne);
        
        
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        JLabel lblTwo = new JLabel("2", SwingConstants.CENTER);
        lblTwo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTwo.setBorder(blackBorder);
        GridBagConstraints gbc_lblTwo = new GridBagConstraints();
        gbc_lblTwo.fill = GridBagConstraints.BOTH;
        gbc_lblTwo.insets = new Insets(1, 1, 1, 1);
        gbc_lblTwo.gridx = 4;
        gbc_lblTwo.gridy = 1;
        tablePanel.add(lblTwo, gbc_lblTwo);
        
        JLabel lblThree = new JLabel("3", SwingConstants.CENTER);
        lblThree.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblThree.setBorder(blackBorder);
        GridBagConstraints gbc_lblThree = new GridBagConstraints();
        gbc_lblThree.fill = GridBagConstraints.BOTH;
        gbc_lblThree.insets = new Insets(1, 1, 1, 1);
        gbc_lblThree.gridx = 5;
        gbc_lblThree.gridy = 1;
        tablePanel.add(lblThree, gbc_lblThree);
        
        JLabel lblFour = new JLabel("4", SwingConstants.CENTER);
        lblFour.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblFour.setBorder(blackBorder);
        GridBagConstraints gbc_lblFour = new GridBagConstraints();
        gbc_lblFour.fill = GridBagConstraints.BOTH;
        gbc_lblFour.insets = new Insets(1, 1, 1, 1);
        gbc_lblFour.gridx = 6;
        gbc_lblFour.gridy = 1;
        tablePanel.add(lblFour, gbc_lblFour);
        
        JLabel lblFive = new JLabel("5", SwingConstants.CENTER);
        lblFive.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblFive.setBorder(blackBorder);
        GridBagConstraints gbc_lblFive = new GridBagConstraints();
        gbc_lblFive.fill = GridBagConstraints.BOTH;
        gbc_lblFive.insets = new Insets(1, 1, 1, 1);
        gbc_lblFive.gridx = 7;
        gbc_lblFive.gridy = 1;
        tablePanel.add(lblFive, gbc_lblFive);
        
        
        // radio buttons for standard
        rbtStandard_1 = new JRadioButton();
        rbtStandard_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(rbtStandard_1.isSelected()) {
        			mySkillDevelopmentController.saveSkill();
        		}
        	}
        });
        rbtStandard_1.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_rbtStandard_1 = new GridBagConstraints();
        gbc_rbtStandard_1 .fill = GridBagConstraints.BOTH;
        gbc_rbtStandard_1.insets = new Insets(1, 1, 1, 1);
        gbc_rbtStandard_1 .gridx = 3;
        gbc_rbtStandard_1 .gridy = 2;
        gbc_rbtStandard_1.anchor = GridBagConstraints.CENTER; 
        gbc_rbtStandard_1.fill = GridBagConstraints.NONE; 
        tablePanel.add(rbtStandard_1 , gbc_rbtStandard_1 );
        
        rbtStandard_2 = new JRadioButton();
        rbtStandard_2.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_rbtStandard_2 = new GridBagConstraints();
        gbc_rbtStandard_2 .fill = GridBagConstraints.BOTH;
        gbc_rbtStandard_2.insets = new Insets(1, 1, 1, 1);
        gbc_rbtStandard_2 .gridx = 4;
        gbc_rbtStandard_2 .gridy = 2;
        gbc_rbtStandard_2.anchor = GridBagConstraints.CENTER; 
        gbc_rbtStandard_2.fill = GridBagConstraints.NONE; 
        tablePanel.add(rbtStandard_2 , gbc_rbtStandard_2 );
        
        rbtStandard_3 = new JRadioButton();
        rbtStandard_3.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_rbtStandard_3 = new GridBagConstraints();
        gbc_rbtStandard_3 .fill = GridBagConstraints.BOTH;
        gbc_rbtStandard_3.insets = new Insets(1, 1, 1, 1);
        gbc_rbtStandard_3.gridx = 5;
        gbc_rbtStandard_3.gridy = 2;
        gbc_rbtStandard_3.anchor = GridBagConstraints.CENTER; 
        gbc_rbtStandard_3.fill = GridBagConstraints.NONE; 
        tablePanel.add(rbtStandard_3 , gbc_rbtStandard_3);
        
        rbtStandard_4 = new JRadioButton();
        rbtStandard_4.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_rbtStandard_4 = new GridBagConstraints();
        gbc_rbtStandard_4 .fill = GridBagConstraints.BOTH;
        gbc_rbtStandard_4.insets = new Insets(1, 1, 1, 1);
        gbc_rbtStandard_4.gridx = 6;
        gbc_rbtStandard_4.gridy = 2;
        gbc_rbtStandard_4.anchor = GridBagConstraints.CENTER; 
        gbc_rbtStandard_4.fill = GridBagConstraints.NONE; 
        tablePanel.add(rbtStandard_4 , gbc_rbtStandard_4);
        
        rbtStandard_5 = new JRadioButton();
        rbtStandard_5.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_rbtStandard_5 = new GridBagConstraints();
        gbc_rbtStandard_5 .fill = GridBagConstraints.BOTH;
        gbc_rbtStandard_5.insets = new Insets(1, 1, 1, 1);
        gbc_rbtStandard_5.gridx = 7;
        gbc_rbtStandard_5.gridy = 2;
        gbc_rbtStandard_5.anchor = GridBagConstraints.CENTER; 
        gbc_rbtStandard_5.fill = GridBagConstraints.NONE; 
        tablePanel.add(rbtStandard_5 , gbc_rbtStandard_5);
        
        ButtonGroup standardGroup = new ButtonGroup();
        standardGroup.add(rbtStandard_1);
        standardGroup.add(rbtStandard_2);
        standardGroup.add(rbtStandard_3);
        standardGroup.add(rbtStandard_4);
        standardGroup.add(rbtStandard_5);
        
        // radiobuttons for spin
        ButtonGroup spinGroup = new ButtonGroup();

        // loop to create 5 radio buttons for Spin
        for (int i = 0; i < 5; i++) {
            JRadioButton rbtSpin = new JRadioButton();
            rbtSpin.setBackground(Color.LIGHT_GRAY);
            rbtSpin.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            	}
            });
            

            spinGroup.add(rbtSpin);

            GridBagConstraints gbc_rbtSpin = new GridBagConstraints();
            gbc_rbtSpin.insets = new Insets(1, 1, 1, 1);
            gbc_rbtSpin.gridx = 3 + i;  
            gbc_rbtSpin.gridy = 3;      
            gbc_rbtSpin.anchor = GridBagConstraints.CENTER;
            gbc_rbtSpin.fill = GridBagConstraints.NONE;

            tablePanel.add(rbtSpin, gbc_rbtSpin);
        }

        
        // === SKILLS NAMES ===
        JLabel lblStandard = new JLabel("Standard", SwingConstants.CENTER);
        lblStandard.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblStandard.setBorder(blackBorder);
        GridBagConstraints gbc_lblStandard = new GridBagConstraints();
        gbc_lblStandard.fill = GridBagConstraints.BOTH;
        gbc_lblStandard.insets = new Insets(1, 1, 1, 1);
        gbc_lblStandard.gridx = 2;
        gbc_lblStandard.gridy = 2;
        tablePanel.add(lblStandard, gbc_lblStandard);
        
        JLabel lblSpin = new JLabel("Spin", SwingConstants.CENTER);
        lblSpin.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSpin.setBorder(blackBorder);
        GridBagConstraints gbc_lblSpin = new GridBagConstraints();
        gbc_lblSpin.fill = GridBagConstraints.BOTH;
        gbc_lblSpin.insets = new Insets(1, 1, 1, 1);
        gbc_lblSpin.gridx = 2;
        gbc_lblSpin.gridy = 3;
        tablePanel.add(lblSpin, gbc_lblSpin);
        
        JLabel lblPop = new JLabel("Pop", SwingConstants.CENTER);
        lblPop.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPop.setBorder(blackBorder);
        GridBagConstraints gbc_lblPop = new GridBagConstraints();
        gbc_lblPop.fill = GridBagConstraints.BOTH;
        gbc_lblPop.insets = new Insets(1, 1, 1, 1);
        gbc_lblPop.gridx = 2;
        gbc_lblPop.gridy = 4;
        tablePanel.add(lblPop, gbc_lblPop);
        
        JLabel lblFront = new JLabel("Front", SwingConstants.CENTER);
        lblFront.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblFront.setBorder(blackBorder);
        GridBagConstraints gbc_lblFront = new GridBagConstraints();
        gbc_lblFront.fill = GridBagConstraints.BOTH;
        gbc_lblFront.insets = new Insets(1, 1, 1, 1);
        gbc_lblFront.gridx = 2;
        gbc_lblFront.gridy = 5;
        tablePanel.add(lblFront, gbc_lblFront);
        
        JLabel lblRear = new JLabel("Rear", SwingConstants.CENTER);
        lblRear.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblRear.setBorder(blackBorder);
        GridBagConstraints gbc_lblRear = new GridBagConstraints();
        gbc_lblRear.fill = GridBagConstraints.BOTH;
        gbc_lblRear.insets = new Insets(1, 1, 1, 1);
        gbc_lblRear.gridx = 2;
        gbc_lblRear.gridy = 6;
        tablePanel.add(lblRear, gbc_lblRear);
        
        JLabel lblSide = new JLabel("Side", SwingConstants.CENTER);
        lblSide.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSide.setBorder(blackBorder);
        GridBagConstraints gbc_lblSide = new GridBagConstraints();
        gbc_lblSide.fill = GridBagConstraints.BOTH;
        gbc_lblSide.insets = new Insets(1, 1, 1, 1);
        gbc_lblSide.gridx = 2;
        gbc_lblSide.gridy = 7;
        tablePanel.add(lblSide, gbc_lblSide);
        
        JLabel lblScrabble = new JLabel("Scrabble", SwingConstants.CENTER);
        lblScrabble.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblScrabble.setBorder(blackBorder);
        GridBagConstraints gbc_lblScrabble = new GridBagConstraints();
        gbc_lblScrabble.fill = GridBagConstraints.BOTH;
        gbc_lblScrabble.insets = new Insets(1, 1, 1, 1);
        gbc_lblScrabble.gridx = 2;
        gbc_lblScrabble.gridy = 8;
        tablePanel.add(lblScrabble, gbc_lblScrabble);
        
        JLabel lblDrop = new JLabel("Drop", SwingConstants.CENTER);
        lblDrop.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDrop.setBorder(blackBorder);
        GridBagConstraints gbc_lblDrop = new GridBagConstraints();
        gbc_lblDrop.fill = GridBagConstraints.BOTH;
        gbc_lblDrop.insets = new Insets(1, 1, 1, 1);
        gbc_lblDrop.gridx = 2;
        gbc_lblDrop.gridy = 9;
        tablePanel.add(lblDrop, gbc_lblDrop);
        
        JLabel lblPunt = new JLabel("Punt", SwingConstants.CENTER);
        lblPunt.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPunt.setBorder(blackBorder);
        GridBagConstraints gbc_lblPunt = new GridBagConstraints();
        gbc_lblPunt.fill = GridBagConstraints.BOTH;
        gbc_lblPunt.insets = new Insets(1, 1, 1, 1);
        gbc_lblPunt.gridx = 2;
        gbc_lblPunt.gridy = 10;
        tablePanel.add(lblPunt, gbc_lblPunt);
        
        JLabel lblGrubber = new JLabel("Grubber", SwingConstants.CENTER);
        lblGrubber.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblGrubber.setBorder(blackBorder);
        GridBagConstraints gbc_lblGrubber = new GridBagConstraints();
        gbc_lblGrubber.fill = GridBagConstraints.BOTH;
        gbc_lblGrubber.insets = new Insets(1, 1, 1, 1);
        gbc_lblGrubber.gridx = 2;
        gbc_lblGrubber.gridy = 11;
        tablePanel.add(lblGrubber, gbc_lblGrubber);
        
        JLabel lblGoal = new JLabel("Goal", SwingConstants.CENTER);
        lblGoal.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblGoal.setBorder(blackBorder);
        GridBagConstraints gbc_lblGoal = new GridBagConstraints();
        gbc_lblGoal.fill = GridBagConstraints.BOTH;
        gbc_lblGoal.insets = new Insets(1, 1, 1, 1);
        gbc_lblGoal.gridx = 2;
        gbc_lblGoal.gridy = 12;
        tablePanel.add(lblGoal, gbc_lblGoal);
        
        
        GridBagConstraints gbc_tablePanel = new GridBagConstraints();
        gbc_tablePanel.gridx = 0;
        gbc_tablePanel.gridy = 1;
        gbc_tablePanel.fill = GridBagConstraints.BOTH;
        gbc_tablePanel.weightx = 0.7;
        gbc_tablePanel.weighty = 1.0;
        contentPanel.add(tablePanel, gbc_tablePanel);
        
        
        // right panel for displaying what coach wrote
        JPanel displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(400, 0)); 
        displayPanel.setBackground(Color.LIGHT_GRAY);
        displayPanel.setLayout(new BorderLayout(10, 10));
        displayPanel.setBorder(new EmptyBorder(0, 0, 150, 50));

        JLabel lblSummary = new JLabel("Summary", SwingConstants.CENTER);
        lblSummary.setFont(new Font("SansSerif", Font.BOLD, 16));

        JTextArea txtSummary = new JTextArea();
        txtSummary.setLineWrap(true);
        txtSummary.setWrapStyleWord(true);
        txtSummary.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtSummary.setEditable(false);
        txtSummary.setBackground(new Color(240, 240, 240));
        txtSummary.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        displayPanel.add(lblSummary, BorderLayout.NORTH);
        displayPanel.add(new JScrollPane(txtSummary), BorderLayout.CENTER);

        GridBagConstraints gbc_displayPanel = new GridBagConstraints();
        gbc_displayPanel.gridx = 1; 
        gbc_displayPanel.gridy = 1; 
        gbc_displayPanel.fill = GridBagConstraints.BOTH;
        gbc_displayPanel.weightx = 0.3; 
        gbc_displayPanel.insets = new Insets(0, 10, 0, 0); 
        contentPanel.add(displayPanel, gbc_displayPanel);
        
        JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("SansSerif", Font.BOLD, 14));

   
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mySkillDevelopmentController.saveSkill();

            }
        });

        
        displayPanel.add(btnSave, BorderLayout.SOUTH);


        
    }


    // fill ComboBox with Players
    public void populateCmb(List<Player> players) {
        cmbPlayers.removeAllItems();
        cmbPlayers.addItem("Select Player");
        for (Player row : players) {
            String fullName = row.getFirstName() + " " + row.getSurname();
            cmbPlayers.addItem(fullName);
        }
    }
    
    
    public void getDate() {
    	
    }
    

    public String getSelectedPlayer() {
        return (String) cmbPlayers.getSelectedItem();
    }


	


	

}




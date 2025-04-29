package simpleRugby.view;

import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;





import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import simpleRugby.controler.SkillDevelopmentController;
import simpleRugby.model.Player;
import simpleRugby.model.SessionManager;
import simpleRugby.model.Skill;
import simpleRugby.model.Squad;
import simpleRugby.model.SquadDAO;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

public class SkillDevelopmentPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private SkillDevelopmentController mySkillDevelopmentController;
//    private List<ButtonGroup> allButtonGroups = new ArrayList<>();
//    private List<JTextArea> allCommentAreas = new ArrayList<>();

    private JComboBox<Player> cmbPlayers;
    private JTable table;
    private JRadioButton rbtStandard_1;
    private JRadioButton rbtStandard_2;
    private JRadioButton rbtStandard_3;
    private JRadioButton rbtStandard_4;
    private JRadioButton rbtStandard_5;
    private JTextArea txtCommentPassing;
    private JTextArea txtSummary = new JTextArea();
    private JDateChooser trainingDate;
    private List<JRadioButton> spinButtons = new ArrayList<>();
    private List<JRadioButton> popButtons = new ArrayList<>();
    private List<JRadioButton> frontButtons = new ArrayList<>();
    private List<JRadioButton> rearButtons = new ArrayList<>();
    private List<JRadioButton> sideButtons = new ArrayList<>();
    private List<JRadioButton> scrabbleButtons = new ArrayList<>();
    private List<JRadioButton> dropButtons = new ArrayList<>();
    private List<JRadioButton> puntButtons = new ArrayList<>();
    private List<JRadioButton> grubberButtons = new ArrayList<>();
    private List<JRadioButton> goalButtons = new ArrayList<>();
	private JTextArea txtCommentStandard;
	private JTextArea txtCommentSpin;
	private JTextArea txtCommentPop;
	private JTextArea txtCommentFront;
	private JTextArea txtCommentRear;
	private JTextArea txtCommentSide;
	private JTextArea txtCommentScrabble;
	private JTextArea txtCommentDrop;
	private JTextArea txtCommentPunt;
	private JTextArea txtCommentGrubber;
	private JTextArea txtCommentGoal;
	private ButtonGroup standardGroup;
	private ButtonGroup spinGroup;
	private ButtonGroup popGroup;
	private ButtonGroup frontGroup;
	private ButtonGroup rearGroup;
	private ButtonGroup sideGroup;
	private ButtonGroup scrabbleGroup;
	private ButtonGroup dropGroup;
	private ButtonGroup puntGroup;
	private ButtonGroup grubberGroup;
	private ButtonGroup goalGroup;



	public void setMySkillDevelopmentController(SkillDevelopmentController mySkillDevelopmentController) {
		this.mySkillDevelopmentController = mySkillDevelopmentController;
	}

    public SkillDevelopmentPanel() {
    	
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.LIGHT_GRAY);

        // Content panel
        JPanel contentPanel = new JPanel();
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0};
        contentPanel.setLayout(gbl_contentPanel);  
        contentPanel.setBorder(new EmptyBorder(50, 50, 20, 20));
        contentPanel.setBackground(Color.LIGHT_GRAY);
        add(contentPanel, BorderLayout.CENTER);

        // === Top Panel (form area) ===
        JPanel topPanel = new JPanel();
        GridBagLayout gbl_topPanel = new GridBagLayout();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);
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
                        
                                JLabel lblPlayers = new JLabel();
                                lblPlayers.setFont(new Font("SansSerif", Font.PLAIN, 16));
                                String teamName = Squad.getSquadName();
                                lblPlayers.setText("Team: " + teamName);



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
                        cmbPlayers.setFont(new Font("SansSerif", Font.PLAIN, 14)); 
                        cmbPlayers.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Player selectedPlayer = (Player) cmbPlayers.getSelectedItem();
                                if (selectedPlayer != null && mySkillDevelopmentController != null) {
                                    mySkillDevelopmentController.updateSummary("Player", selectedPlayer.getFullName());
                                }
                            }
                        });

                        cmbPlayers.setPreferredSize(new Dimension(200, 30));
                        GridBagConstraints gbc_cmbPlayers = new GridBagConstraints();
                        gbc_cmbPlayers.fill = GridBagConstraints.BOTH;
                        gbc_cmbPlayers.insets = new Insets(0, 0, 0, 5);
                        gbc_cmbPlayers.gridx = 0;
                        gbc_cmbPlayers.gridy = 2;
                        topPanel.add(cmbPlayers, gbc_cmbPlayers);
                        

                        trainingDate = new JDateChooser();
                        trainingDate.setDate(new Date()); 
                        trainingDate.setDateFormatString("dd-MM-yyyy");

                       
                        trainingDate.getDateEditor().addPropertyChangeListener("date", evt -> {
                            if (trainingDate.getDate() != null) {
                                String dateStr = new SimpleDateFormat("dd-MM-yyyy").format(trainingDate.getDate());
                                System.out.println("Selected Date: " + dateStr);
                                mySkillDevelopmentController.updateSummary("Training Date", dateStr);
                            }
                        });              
  

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
                gbc_topPanel.insets = new Insets(0, 0, 10, 5);
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
        
        
        // <<<<<<<<<<<<<<< COMMENTS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 
        
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
        
        // STANDARD
        txtCommentStandard = new JTextArea();
        txtCommentStandard.setBorder(blackBorder);
        txtCommentStandard.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentStandard.setLineWrap(true);
        txtCommentStandard.setWrapStyleWord(true);
        
        JScrollPane scrollCommentStandard = new JScrollPane(txtCommentStandard);
        scrollCommentStandard.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentStandard.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentStandard = new GridBagConstraints();
        gbc_txtCommentStandard.fill = GridBagConstraints.BOTH;
        gbc_txtCommentStandard.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentStandard.gridheight = 1;
        gbc_txtCommentStandard.gridx = 8;
        gbc_txtCommentStandard.gridy = 2;
        tablePanel.add(scrollCommentStandard, gbc_txtCommentStandard);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // SPIN
        txtCommentSpin = new JTextArea();
        txtCommentSpin.setBorder(blackBorder);
        txtCommentSpin.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentSpin.setLineWrap(true);
        txtCommentSpin.setWrapStyleWord(true);
        
        JScrollPane scrollCommentSpin = new JScrollPane(txtCommentSpin);
        scrollCommentSpin.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentSpin.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentSpin = new GridBagConstraints();
        gbc_txtCommentSpin.fill = GridBagConstraints.BOTH;
        gbc_txtCommentSpin.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentSpin.gridheight = 1;
        gbc_txtCommentSpin.gridx = 8;
        gbc_txtCommentSpin.gridy = 3;
        tablePanel.add(scrollCommentSpin, gbc_txtCommentSpin);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // POP
        txtCommentPop = new JTextArea();
        txtCommentPop.setBorder(blackBorder);
        txtCommentPop.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentPop.setLineWrap(true);
        txtCommentPop.setWrapStyleWord(true);
        
        JScrollPane scrollCommentPop = new JScrollPane(txtCommentPop);
        scrollCommentPop.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentPop.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentPop = new GridBagConstraints();
        gbc_txtCommentPop.fill = GridBagConstraints.BOTH;
        gbc_txtCommentPop.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentPop.gridheight = 1;
        gbc_txtCommentPop.gridx = 8;
        gbc_txtCommentPop.gridy = 4;
        tablePanel.add(scrollCommentPop, gbc_txtCommentPop);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // FRONT
        txtCommentFront = new JTextArea();
        txtCommentFront.setBorder(blackBorder);
        txtCommentFront.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentFront.setLineWrap(true);
        txtCommentFront.setWrapStyleWord(true);
        
        JScrollPane scrollCommentFront = new JScrollPane(txtCommentFront);
        scrollCommentFront.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentFront.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentFront = new GridBagConstraints();
        gbc_txtCommentFront.fill = GridBagConstraints.BOTH;
        gbc_txtCommentFront.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentFront.gridheight = 1;
        gbc_txtCommentFront.gridx = 8;
        gbc_txtCommentFront.gridy = 5;
        tablePanel.add(scrollCommentFront, gbc_txtCommentFront);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // REAR
        txtCommentRear = new JTextArea();
        txtCommentRear.setBorder(blackBorder);
        txtCommentRear.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentRear.setLineWrap(true);
        txtCommentRear.setWrapStyleWord(true);
        
        JScrollPane scrollCommentRear = new JScrollPane(txtCommentRear);
        scrollCommentRear.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentRear.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentRear = new GridBagConstraints();
        gbc_txtCommentRear.fill = GridBagConstraints.BOTH;
        gbc_txtCommentRear.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentRear.gridheight = 1;
        gbc_txtCommentRear.gridx = 8;
        gbc_txtCommentRear.gridy = 6;
        tablePanel.add(scrollCommentRear, gbc_txtCommentRear);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // SIDE
        txtCommentSide = new JTextArea();
        txtCommentSide.setBorder(blackBorder);
        txtCommentSide.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentSide.setLineWrap(true);
        txtCommentSide.setWrapStyleWord(true);
        
        JScrollPane scrollCommentSide = new JScrollPane(txtCommentSide);
        scrollCommentSide.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentSide.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentSide = new GridBagConstraints();
        gbc_txtCommentSide.fill = GridBagConstraints.BOTH;
        gbc_txtCommentSide.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentSide.gridheight = 1;
        gbc_txtCommentSide.gridx = 8;
        gbc_txtCommentSide.gridy = 7;
        tablePanel.add(scrollCommentSide, gbc_txtCommentSide);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // SCRABBLE
        txtCommentScrabble = new JTextArea();
        txtCommentScrabble.setBorder(blackBorder);
        txtCommentScrabble.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentScrabble.setLineWrap(true);
        txtCommentScrabble.setWrapStyleWord(true);
        
        JScrollPane scrollCommentScrabble = new JScrollPane(txtCommentScrabble);
        scrollCommentScrabble.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentScrabble.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentScrabble = new GridBagConstraints();
        gbc_txtCommentScrabble.fill = GridBagConstraints.BOTH;
        gbc_txtCommentScrabble.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentScrabble.gridheight = 1;
        gbc_txtCommentScrabble.gridx = 8;
        gbc_txtCommentScrabble.gridy = 8;
        tablePanel.add(scrollCommentScrabble, gbc_txtCommentScrabble);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // DROP
        txtCommentDrop = new JTextArea();
        txtCommentDrop.setBorder(blackBorder);
        txtCommentDrop.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentDrop.setLineWrap(true);
        txtCommentDrop.setWrapStyleWord(true);
        
        JScrollPane scrollCommentDrop = new JScrollPane(txtCommentDrop);
        scrollCommentDrop.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentDrop.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentDrop = new GridBagConstraints();
        gbc_txtCommentDrop.fill = GridBagConstraints.BOTH;
        gbc_txtCommentDrop.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentDrop.gridheight = 1;
        gbc_txtCommentDrop.gridx = 8;
        gbc_txtCommentDrop.gridy = 9;
        tablePanel.add(scrollCommentDrop, gbc_txtCommentDrop);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // PUNT
        txtCommentPunt = new JTextArea();
        txtCommentPunt.setBorder(blackBorder);
        txtCommentPunt.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentPunt.setLineWrap(true);
        txtCommentPunt.setWrapStyleWord(true);
        
        JScrollPane scrollCommentPunt = new JScrollPane(txtCommentPunt);
        scrollCommentPunt.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentPunt.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentPunt = new GridBagConstraints();
        gbc_txtCommentPunt.fill = GridBagConstraints.BOTH;
        gbc_txtCommentPunt.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentPunt.gridheight = 1;
        gbc_txtCommentPunt.gridx = 8;
        gbc_txtCommentPunt.gridy = 10;
        tablePanel.add(scrollCommentPunt, gbc_txtCommentPunt);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // GRUBBER
        txtCommentGrubber = new JTextArea();
        txtCommentGrubber.setBorder(blackBorder);
        txtCommentGrubber.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentGrubber.setLineWrap(true);
        txtCommentGrubber.setWrapStyleWord(true);
        
        JScrollPane scrollCommentGrubber = new JScrollPane(txtCommentGrubber);
        scrollCommentGrubber.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentGrubber.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentGrubber = new GridBagConstraints();
        gbc_txtCommentGrubber.fill = GridBagConstraints.BOTH;
        gbc_txtCommentGrubber.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentGrubber.gridheight = 1;
        gbc_txtCommentGrubber.gridx = 8;
        gbc_txtCommentGrubber.gridy = 11;
        tablePanel.add(scrollCommentGrubber, gbc_txtCommentGrubber);
        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        // GOAL
        txtCommentGoal = new JTextArea();
        txtCommentGoal.setBorder(blackBorder);
        txtCommentGoal.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCommentGoal.setLineWrap(true);
        txtCommentGoal.setWrapStyleWord(true);
        
        JScrollPane scrollCommentGoal = new JScrollPane(txtCommentGoal);
        scrollCommentGoal.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollCommentGoal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        
        GridBagConstraints gbc_txtCommentGoal = new GridBagConstraints();
        gbc_txtCommentGoal.fill = GridBagConstraints.BOTH;
        gbc_txtCommentGoal.insets = new Insets(1, 1, 1, 1);
        gbc_txtCommentGoal.gridheight = 1;
        gbc_txtCommentGoal.gridx = 8;
        gbc_txtCommentGoal.gridy = 12;
        tablePanel.add(scrollCommentGoal, gbc_txtCommentGoal);

        
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        

        
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
                int selectedStandard = 1;
                if (mySkillDevelopmentController != null) {
                    mySkillDevelopmentController.updateSummary("Standard", String.valueOf(selectedStandard));
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
        rbtStandard_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedStandard = 2;
                if (mySkillDevelopmentController != null) {
                    mySkillDevelopmentController.updateSummary("Standard", String.valueOf(selectedStandard));
                }
            }
        });
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
        rbtStandard_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedStandard = 3;
                if (mySkillDevelopmentController != null) {
                    mySkillDevelopmentController.updateSummary("Standard", String.valueOf(selectedStandard));
                }
            }
        });
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
        rbtStandard_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedStandard = 4;
                if (mySkillDevelopmentController != null) {
                    mySkillDevelopmentController.updateSummary("Standard", String.valueOf(selectedStandard));
                }
            }
        });
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
        rbtStandard_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedStandard = 5;
                if (mySkillDevelopmentController != null) {
                    mySkillDevelopmentController.updateSummary("Standard", String.valueOf(selectedStandard));
                }
            }
        });
        rbtStandard_5.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_rbtStandard_5 = new GridBagConstraints();
        gbc_rbtStandard_5 .fill = GridBagConstraints.BOTH;
        gbc_rbtStandard_5.insets = new Insets(1, 1, 1, 1);
        gbc_rbtStandard_5.gridx = 7;
        gbc_rbtStandard_5.gridy = 2;
        gbc_rbtStandard_5.anchor = GridBagConstraints.CENTER; 
        gbc_rbtStandard_5.fill = GridBagConstraints.NONE; 
        tablePanel.add(rbtStandard_5 , gbc_rbtStandard_5);
        
        standardGroup = new ButtonGroup();
        standardGroup.add(rbtStandard_1);
        standardGroup.add(rbtStandard_2);
        standardGroup.add(rbtStandard_3);
        standardGroup.add(rbtStandard_4);
        standardGroup.add(rbtStandard_5);
        
        // radiobuttons for spin
        spinGroup = new ButtonGroup();

        // loop to create 5 radio buttons for Spin
        for (int i = 0; i < 5; i++) {
        	int selectedSpin = i + 1;
            JRadioButton rbtSpin = new JRadioButton();
            rbtSpin.setBackground(Color.LIGHT_GRAY);
            
            rbtSpin.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		if (mySkillDevelopmentController != null) {
                        mySkillDevelopmentController.updateSummary("Spin", String.valueOf(selectedSpin));
                    }
            	}
            });
            

            spinGroup.add(rbtSpin);
            spinButtons.add(rbtSpin);

            GridBagConstraints gbc_rbtSpin = new GridBagConstraints();
            gbc_rbtSpin.insets = new Insets(1, 1, 1, 1);
            gbc_rbtSpin.gridx = 3 + i;  
            gbc_rbtSpin.gridy = 3;      
            gbc_rbtSpin.anchor = GridBagConstraints.CENTER;
            gbc_rbtSpin.fill = GridBagConstraints.NONE;

            tablePanel.add(rbtSpin, gbc_rbtSpin);
        }
        
        
        // radiobuttons for pop
        popGroup = new ButtonGroup();

        // loop to create 5 radio buttons for Spin
        for (int i = 0; i < 5; i++) {
        	int selectedPop = i + 1;
            JRadioButton rbtPop = new JRadioButton();
            rbtPop.setBackground(Color.LIGHT_GRAY);
            
            rbtPop.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		if (mySkillDevelopmentController != null) {
                        mySkillDevelopmentController.updateSummary("Pop", String.valueOf(selectedPop));
                    }
            	}
            });
            
            popGroup.add(rbtPop);
            popButtons.add(rbtPop);

            GridBagConstraints gbc_rbtPop = new GridBagConstraints();
            gbc_rbtPop.insets = new Insets(1, 1, 1, 1);
            gbc_rbtPop.gridx = 3 + i;  
            gbc_rbtPop.gridy = 4;      
            gbc_rbtPop.anchor = GridBagConstraints.CENTER;
            gbc_rbtPop.fill = GridBagConstraints.NONE;

            tablePanel.add(rbtPop, gbc_rbtPop);
        }
        
     // radiobuttons for spin
        frontGroup = new ButtonGroup();

        // loop to create 5 radio buttons for Spin
        for (int i = 0; i < 5; i++) {
        	int selectedFront = i + 1;
            JRadioButton rbtFront = new JRadioButton();
            rbtFront.setBackground(Color.LIGHT_GRAY);
            
            rbtFront.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		if (mySkillDevelopmentController != null) {
                        mySkillDevelopmentController.updateSummary("Front", String.valueOf(selectedFront));
                    }
            	}
            });
            
            frontGroup.add(rbtFront);
            frontButtons.add(rbtFront);

            GridBagConstraints gbc_rbtFront = new GridBagConstraints();
            gbc_rbtFront.insets = new Insets(1, 1, 1, 1);
            gbc_rbtFront.gridx = 3 + i;  
            gbc_rbtFront.gridy = 5;      
            gbc_rbtFront.anchor = GridBagConstraints.CENTER;
            gbc_rbtFront.fill = GridBagConstraints.NONE;

            tablePanel.add(rbtFront, gbc_rbtFront);
        }
        
        // radiobuttons for rear >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        rearGroup = new ButtonGroup();

        // loop to create 5 radio buttons for Spin
        for (int i = 0; i < 5; i++) {
        	int selectedRear = i + 1;
            JRadioButton rbtRear = new JRadioButton();
            rbtRear.setBackground(Color.LIGHT_GRAY);
            
            rbtRear.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		if (mySkillDevelopmentController != null) {
                        mySkillDevelopmentController.updateSummary("Rear", String.valueOf(selectedRear));
                    }
            	}
            });
            
            rearGroup.add(rbtRear);
            rearButtons.add(rbtRear);

            GridBagConstraints gbc_rbtRear = new GridBagConstraints();
            gbc_rbtRear.insets = new Insets(1, 1, 1, 1);
            gbc_rbtRear.gridx = 3 + i;  
            gbc_rbtRear.gridy = 6;      
            gbc_rbtRear.anchor = GridBagConstraints.CENTER;
            gbc_rbtRear.fill = GridBagConstraints.NONE;

            tablePanel.add(rbtRear, gbc_rbtRear);
        }
        
        // radio buttons for side >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        sideGroup = new ButtonGroup();

        // loop to create 5 radio buttons for Spin
        for (int i = 0; i < 5; i++) {
        	int selectedSide = i + 1;
            JRadioButton rbtSide = new JRadioButton();
            rbtSide.setBackground(Color.LIGHT_GRAY);
            
            rbtSide.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		if (mySkillDevelopmentController != null) {
                        mySkillDevelopmentController.updateSummary("Side", String.valueOf(selectedSide));
                    }
            	}
            });
            
            sideGroup.add(rbtSide);
            sideButtons.add(rbtSide);

            GridBagConstraints gbc_rbtSide = new GridBagConstraints();
            gbc_rbtSide.insets = new Insets(1, 1, 1, 1);
            gbc_rbtSide.gridx = 3 + i;  
            gbc_rbtSide.gridy = 7;      
            gbc_rbtSide.anchor = GridBagConstraints.CENTER;
            gbc_rbtSide.fill = GridBagConstraints.NONE;

            tablePanel.add(rbtSide, gbc_rbtSide);
        }
        
        	// radio buttons for scrabble >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
           scrabbleGroup = new ButtonGroup();

           // loop to create 5 radio buttons for Spin
           for (int i = 0; i < 5; i++) {
           	int selectedScrabble = i + 1;
               JRadioButton rbtScrabble = new JRadioButton();
               rbtScrabble.setBackground(Color.LIGHT_GRAY);
               
               rbtScrabble.addActionListener(new ActionListener() {
               	public void actionPerformed(ActionEvent e) {
               		if (mySkillDevelopmentController != null) {
                           mySkillDevelopmentController.updateSummary("Side", String.valueOf(selectedScrabble));
                       }
               	}
               });
               
               scrabbleGroup.add(rbtScrabble);
               scrabbleButtons.add(rbtScrabble);

               GridBagConstraints gbc_rbtScrabble = new GridBagConstraints();
               gbc_rbtScrabble.insets = new Insets(1, 1, 1, 1);
               gbc_rbtScrabble.gridx = 3 + i;  
               gbc_rbtScrabble.gridy = 8;      
               gbc_rbtScrabble.anchor = GridBagConstraints.CENTER;
               gbc_rbtScrabble.fill = GridBagConstraints.NONE;

               tablePanel.add(rbtScrabble, gbc_rbtScrabble);
           }
           
           // radio buttons for drop >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
           dropGroup = new ButtonGroup();

           // loop to create 5 radio buttons for Spin
           for (int i = 0; i < 5; i++) {
           	int selectedDrop = i + 1;
               JRadioButton rbtDrop = new JRadioButton();
               rbtDrop.setBackground(Color.LIGHT_GRAY);
               
               rbtDrop.addActionListener(new ActionListener() {
               	public void actionPerformed(ActionEvent e) {
               		if (mySkillDevelopmentController != null) {
                           mySkillDevelopmentController.updateSummary("Drop", String.valueOf(selectedDrop));
                       }
               	}
               });
               
               dropGroup.add(rbtDrop);
               dropButtons.add(rbtDrop);

               GridBagConstraints gbc_rbtDrop = new GridBagConstraints();
               gbc_rbtDrop.insets = new Insets(1, 1, 1, 1);
               gbc_rbtDrop.gridx = 3 + i;  
               gbc_rbtDrop.gridy = 9;      
               gbc_rbtDrop.anchor = GridBagConstraints.CENTER;
               gbc_rbtDrop.fill = GridBagConstraints.NONE;

               tablePanel.add(rbtDrop, gbc_rbtDrop);
           }
           
        // radio buttons for drop >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
           puntGroup = new ButtonGroup();

           // loop to create 5 radio buttons for Spin
           for (int i = 0; i < 5; i++) {
           	int selectedPunt = i + 1;
               JRadioButton rbtPunt = new JRadioButton();
               rbtPunt.setBackground(Color.LIGHT_GRAY);
               
               rbtPunt.addActionListener(new ActionListener() {
               	public void actionPerformed(ActionEvent e) {
               		if (mySkillDevelopmentController != null) {
                           mySkillDevelopmentController.updateSummary("Punt", String.valueOf(selectedPunt));
                       }
               	}
               });
               
               puntGroup.add(rbtPunt);
               puntButtons.add(rbtPunt);

               GridBagConstraints gbc_rbtPunt = new GridBagConstraints();
               gbc_rbtPunt.insets = new Insets(1, 1, 1, 1);
               gbc_rbtPunt.gridx = 3 + i;  
               gbc_rbtPunt.gridy = 10;      
               gbc_rbtPunt.anchor = GridBagConstraints.CENTER;
               gbc_rbtPunt.fill = GridBagConstraints.NONE;

               tablePanel.add(rbtPunt, gbc_rbtPunt);
           }
           
        // radio buttons for drop >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
           grubberGroup = new ButtonGroup();

           // loop to create 5 radio buttons for Spin
           for (int i = 0; i < 5; i++) {
           	int selectedGrubber = i + 1;
               JRadioButton rbtGrubber = new JRadioButton();
               rbtGrubber.setBackground(Color.LIGHT_GRAY);
               
               rbtGrubber.addActionListener(new ActionListener() {
               	public void actionPerformed(ActionEvent e) {
               		if (mySkillDevelopmentController != null) {
                           mySkillDevelopmentController.updateSummary("Grubber", String.valueOf(selectedGrubber));
                       }
               	}
               });
               
               grubberGroup.add(rbtGrubber);
               grubberButtons.add(rbtGrubber);

               GridBagConstraints gbc_rbtGrubber = new GridBagConstraints();
               gbc_rbtGrubber.insets = new Insets(1, 1, 1, 1);
               gbc_rbtGrubber.gridx = 3 + i;  
               gbc_rbtGrubber.gridy = 11;      
               gbc_rbtGrubber.anchor = GridBagConstraints.CENTER;
               gbc_rbtGrubber.fill = GridBagConstraints.NONE;

               tablePanel.add(rbtGrubber, gbc_rbtGrubber);
           }
           
        // radio buttons for drop >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
           goalGroup = new ButtonGroup();

           // loop to create 5 radio buttons for Spin
           for (int i = 0; i < 5; i++) {
           	int selectedGoal = i + 1;
               JRadioButton rbtGoal = new JRadioButton();
               rbtGoal.setBackground(Color.LIGHT_GRAY);
               
               rbtGoal.addActionListener(new ActionListener() {
               	public void actionPerformed(ActionEvent e) {
               		if (mySkillDevelopmentController != null) {
                           mySkillDevelopmentController.updateSummary("Goal", String.valueOf(selectedGoal));
                       }
               	}
               });
               
               goalGroup.add(rbtGoal);
               goalButtons.add(rbtGoal);

               GridBagConstraints gbc_rbtGoal = new GridBagConstraints();
               gbc_rbtGoal.insets = new Insets(1, 1, 1, 1);
               gbc_rbtGoal.gridx = 3 + i;  
               gbc_rbtGoal.gridy = 12;      
               gbc_rbtGoal.anchor = GridBagConstraints.CENTER;
               gbc_rbtGoal.fill = GridBagConstraints.NONE;

               tablePanel.add(rbtGoal, gbc_rbtGoal);
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
        gbc_tablePanel.insets = new Insets(0, 0, 0, 5);
        gbc_tablePanel.gridx = 0;
        gbc_tablePanel.gridy = 2;
        gbc_tablePanel.fill = GridBagConstraints.BOTH;
        gbc_tablePanel.weightx = 0.7;
        gbc_tablePanel.weighty = 1.0;
        contentPanel.add(tablePanel, gbc_tablePanel);
        
        
        // right panel for displaying what coach wrote
        JPanel displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(400, 500)); 
        displayPanel.setBackground(Color.LIGHT_GRAY);
        displayPanel.setLayout(new BorderLayout(10, 10));
        displayPanel.setBorder(new EmptyBorder(0, 0, 150, 50));

        JLabel lblSummary = new JLabel("Summary", SwingConstants.CENTER);
        lblSummary.setFont(new Font("SansSerif", Font.BOLD, 16));

        getTxtSummary().setLineWrap(true);
        getTxtSummary().setWrapStyleWord(true);
        getTxtSummary().setFont(new Font("SansSerif", Font.PLAIN, 14));
        getTxtSummary().setEditable(false);
        getTxtSummary().setBackground(new Color(240, 240, 240));
        getTxtSummary().setBorder(BorderFactory.createLineBorder(Color.BLACK));

        displayPanel.add(lblSummary, BorderLayout.NORTH);
        displayPanel.add(new JScrollPane(getTxtSummary()), BorderLayout.CENTER);

        GridBagConstraints gbc_displayPanel = new GridBagConstraints();
        gbc_displayPanel.gridx = 1; 
        gbc_displayPanel.gridy = 2; 
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

                clearForm(); 
                JOptionPane.showMessageDialog(SkillDevelopmentPanel.this, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // <<< NEW
                txtSummary.setText("");
            }
        });


        
        displayPanel.add(btnSave, BorderLayout.SOUTH);


        
    }
    
    public void setSkillDevelopmentController(SkillDevelopmentController controller) {
        this.mySkillDevelopmentController = controller;
    }


    public void updateSummary() {
		Player selectedPlayer = (Player) cmbPlayers.getSelectedItem();
	}


	// fill cmb
    public void populateCmb(List<Player> players) {
        cmbPlayers.removeAllItems();

        for (Player player : players) {
            cmbPlayers.addItem(player); // add whole player object
        }
    }
    
    public int getSelectedPlayerId() {
        Player selectedPlayer = (Player) cmbPlayers.getSelectedItem();

        if (selectedPlayer != null) {
            int playerId = selectedPlayer.getMemberId();
            System.out.println("Selected Player ID: " + playerId);
            return playerId;
        }

        System.out.println("No player selected.");
        return -1;
    }


    public int getLevelForStandard() {
        if (rbtStandard_1.isSelected()) {
        	return 1;
        } else if (rbtStandard_2.isSelected()) {
        	return 2;
        } else if (rbtStandard_3.isSelected()) {
        	return 3;
        } else if (rbtStandard_4.isSelected()) {
        	return 4;
        } else if (rbtStandard_5.isSelected()) {
        	return 5;
        } else {
        	return 0;
        }
    }
    
    
    public int getLevelForSpin() {
        for (int i = 0; i < spinButtons.size(); i++) {
            if (spinButtons.get(i).isSelected()) {
                return i + 1; 
            }
        }
        return 0;
    }

    
    public int getLevelForPop() {
        for (int i = 0; i < popButtons.size(); i++) {
            if (popButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    public int getLevelForFront() {
        for (int i = 0; i < frontButtons.size(); i++) {
            if (frontButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    public int getLevelForRear() {
        for (int i = 0; i < rearButtons.size(); i++) {
            if (rearButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    public int getLevelForSide() {
        for (int i = 0; i < sideButtons.size(); i++) {
            if (sideButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    public int getLevelForScrabble() {
        for (int i = 0; i < scrabbleButtons.size(); i++) {
            if (scrabbleButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    public int getLevelForDrop() {
        for (int i = 0; i < dropButtons.size(); i++) {
            if (dropButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    public int getLevelForPunt() {
        for (int i = 0; i < puntButtons.size(); i++) {
            if (puntButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    public int getLevelForGrubber() {
        for (int i = 0; i < grubberButtons.size(); i++) {
            if (grubberButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    public int getLevelForGoal() {
        for (int i = 0; i < goalButtons.size(); i++) {
            if (goalButtons.get(i).isSelected()) {
                return i + 1;
            }
        }
        return 0; // no level selected
    }

    
    public Date getTrainingDate() {
        return trainingDate.getDate();
    }
    
    
    public String getPassingComment() {
        return txtCommentPassing.getText();
    }
  

    public String getSelectedPlayer() {
        return (String) cmbPlayers.getSelectedItem();
    }



	public JTextArea getTxtSummary() {
		return txtSummary;
	}


	public void setTxtSummary(JTextArea txtSummary) {
		this.txtSummary = txtSummary;
	}




	// Standard
	public String getTxtCommentStandard() {
	    return txtCommentStandard.getText().trim();
	}

	public void setTxtCommentStandard(String txtCommentStandard) {
	    this.txtCommentStandard.setText(txtCommentStandard);
	}

	// Spin
	public String getTxtCommentSpin() {
	    return txtCommentSpin.getText().trim();
	}

	public void setTxtCommentSpin(String txtCommentSpin) {
	    this.txtCommentSpin.setText(txtCommentSpin);
	}

	// Pop
	public String getTxtCommentPop() {
	    return txtCommentPop.getText().trim();
	}

	public void setTxtCommentPop(String txtCommentPop) {
	    this.txtCommentPop.setText(txtCommentPop);
	}

	// Front
	public String getTxtCommentFront() {
	    return txtCommentFront.getText().trim();
	}

	public void setTxtCommentFront(String txtCommentFront) {
	    this.txtCommentFront.setText(txtCommentFront);
	}

	// Rear
	public String getTxtCommentRear() {
	    return txtCommentRear.getText().trim();
	}

	public void setTxtCommentRear(String txtCommentRear) {
	    this.txtCommentRear.setText(txtCommentRear);
	}

	// Side
	public String getTxtCommentSide() {
	    return txtCommentSide.getText().trim();
	}

	public void setTxtCommentSide(String txtCommentSide) {
	    this.txtCommentSide.setText(txtCommentSide);
	}

	// Scrabble
	public String getTxtCommentScrabble() {
	    return txtCommentScrabble.getText().trim();
	}

	public void setTxtCommentScrabble(String txtCommentScrabble) {
	    this.txtCommentScrabble.setText(txtCommentScrabble);
	}

	// Drop
	public String getTxtCommentDrop() {
	    return txtCommentDrop.getText().trim();
	}

	public void setTxtCommentDrop(String txtCommentDrop) {
	    this.txtCommentDrop.setText(txtCommentDrop);
	}

	// Punt
	public String getTxtCommentPunt() {
	    return txtCommentPunt.getText().trim();
	}

	public void setTxtCommentPunt(String txtCommentPunt) {
	    this.txtCommentPunt.setText(txtCommentPunt);
	}

	// Grubber
	public String getTxtCommentGrubber() {
	    return txtCommentGrubber.getText().trim();
	}

	public void setTxtCommentGrubber(String txtCommentGrubber) {
	    this.txtCommentGrubber.setText(txtCommentGrubber);
	}

	// Goal
	public String getTxtCommentGoal() {
	    return txtCommentGoal.getText().trim();
	}

	public void setTxtCommentGoal(String txtCommentGoal) {
	    this.txtCommentGoal.setText(txtCommentGoal);
	}


	
	public void clearForm() {
	    // clear text areas
	    txtCommentStandard.setText("");
	    txtCommentSpin.setText("");
	    txtCommentPop.setText("");
	    txtCommentFront.setText("");
	    txtCommentRear.setText("");
	    txtCommentSide.setText("");
	    txtCommentScrabble.setText("");
	    txtCommentDrop.setText("");
	    txtCommentPunt.setText("");
	    txtCommentGrubber.setText("");
	    txtCommentGoal.setText("");


	    // reset training date
	    trainingDate.setDate(new Date());

	    // radio button groups, if button is not marked (null) dont clear it
	    if (standardGroup != null) standardGroup.clearSelection();
	    if (spinGroup != null) spinGroup.clearSelection();
	    if (popGroup != null) popGroup.clearSelection();
	    if (frontGroup != null) frontGroup.clearSelection();
	    if (rearGroup != null) rearGroup.clearSelection();
	    if (sideGroup != null) sideGroup.clearSelection();
	    if (scrabbleGroup != null) scrabbleGroup.clearSelection();
	    if (dropGroup != null) dropGroup.clearSelection();
	    if (puntGroup != null) puntGroup.clearSelection();
	    if (grubberGroup != null) grubberGroup.clearSelection();
	    if (goalGroup != null) goalGroup.clearSelection();


	}



	

}




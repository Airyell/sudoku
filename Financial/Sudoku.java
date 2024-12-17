package Financial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Sudoku {
	private int points = 0; // records score
    public Sudoku() {


	        // Create the JFrame
	        JFrame frame = new JFrame("Sudoku Game");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);

	        // Create the main JPanel
	        JPanel primary = new JPanel();
	        primary.setLayout(null); 
	        Color c1 = new Color(245, 225, 225); // Background color for the main panel
	        primary.setBackground(c1);
	        primary.setPreferredSize(new Dimension(640, 795));
	        frame.add(primary);
	        frame.pack();
	        frame.setVisible(true);
	       


	        // Title JLabel
	        JLabel title = new JLabel("Sudoku Game", JLabel.CENTER);
	        Font font1 = new Font("Serif", Font.PLAIN, 30);
	        title.setFont(font1);

	        // Title JPanel
	        JPanel tit = new JPanel();
	        tit.setLayout(new BorderLayout());
	        tit.setBounds(200, 10, 200, 50);
	        Color c2 = new Color(245, 189, 215);
	        tit.setBackground(c2);
	        tit.add(title, BorderLayout.CENTER); 
	        primary.add(tit);

	        //second panel below the title
	       JPanel panel1 = new JPanel();
	        panel1.setLayout(new BorderLayout());
	        panel1.setBounds(95, 80, 408, 408);
	        panel1.setBackground(c1);
	        

	        board grid1 = new board();
	        primary.add(panel1, BorderLayout.CENTER);
	        panel1.add(grid1,BorderLayout.CENTER);
	        grid1.setBackground(Color.white);
	        grid1.setPreferredSize(new Dimension(500, 500));
	        
	        //enter row
	        JLabel enterRowLabel = new JLabel("Enter Row");
	        JTextField enterRow = new JTextField(10);
	        primary.add(enterRowLabel);
	        primary.add(enterRow);
	        Font font3 = new Font(null, Font.BOLD, 15);
	        enterRow.setBounds(50, 500, 80, 20);
	        enterRowLabel.setBounds(50, 500, 500, 50);
	        enterRowLabel.setFont(font3);
	        
	        
	        //enter column
	        JLabel enterColLabel = new JLabel("Enter Column");
	        JTextField enterCol = new JTextField(10);
	        primary.add(enterColLabel);
	        primary.add(enterCol);
	        enterCol.setBounds(50, 550, 80, 20);
	        enterColLabel.setBounds(50, 550, 500, 50);
	        enterColLabel.setFont(font3);
	        
	        //Enter Value
	        JLabel enterValLabel = new JLabel("Enter Value");
	        JTextField enterVal = new JTextField(10);
	        primary.add(enterValLabel);
	        primary.add(enterVal);
	        enterVal.setBounds(50, 600, 80, 20);
	        enterValLabel.setBounds(50, 600, 500, 50);
	        enterValLabel.setFont(font3);
	        
	      //score
	        JLabel score = new JLabel();
	        score.setText("Score:" + points);
	        primary.add(score);
	        score.setBounds(280, 550, 200, 30);
	        Font font2 = new Font(null, Font.BOLD, 15);
	        score.setFont(font2);
	        
	        //Enter Button
	        JButton enter = new JButton("ENTER<3");
	        primary.add(enter);
	        enter.setBounds(40, 650, 100, 45);
	        enter.setBackground(c2);
	        enter.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    int row = Integer.parseInt(enterRow.getText()) - 1; // Convert to 0-indexed
	                    int col = Integer.parseInt(enterCol.getText()) - 1; // Convert to 0-indexed
	                    int value = Integer.parseInt(enterVal.getText());


	                    // Validate input
	                    if (row < 0 || row >= 9 || col < 0 || col >= 9 || value < 1 || value > 9) {
	                        JOptionPane.showMessageDialog(frame, "Invalid input. Enter values between 1-9.");
	                        return;
	                    }

	                    // Check if the cell is fixed
	                    if (grid1.isFixedCell(row, col)) {
	                        JOptionPane.showMessageDialog(frame, "Cannot change the value of a fixed cell.");
	                    } else if (grid1.invalidMove(grid1.getBorder(), row, col, value)) {
	                        JOptionPane.showMessageDialog(frame, "Invalid move! Duplicate number found.");
	                    } else if (grid1.isValidMove(row, col, value)) {
	                        boolean success = grid1.updateBoard(row, col, value);
	                        if (success) {
	                            JOptionPane.showMessageDialog(frame, "Move accepted!");
	                            points += 10;
	                            score.setText("Score: " + points);
	                            if (grid1.isSolved()) {
	                                JOptionPane.showMessageDialog(frame, "Congratulations! You solved the puzzle!");
	                                enter.setEnabled(false);
	                            }
	                        }
	                    } else {
	                    	
	                        JOptionPane.showMessageDialog(frame, "Wrong move! Try again.");
	                    }
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(frame, "Please enter valid integers.");
	                }
	            }
	        });


	      //Name
	        JLabel name = new JLabel();
	        primary.add(name);
	        name.setBounds(280, 500, 200, 30);
	        name.setFont(font2);
	        name.setText("Name:");
	      //Enter name
	        JFrame frame1 = new JFrame();
	        frame1.setTitle("WELCOME!!!");
	        final int FRAME_WIDTH = 300;
	        final int FRAME_HEIGHT = 100;
	        frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	        frame1.setLayout(null);
	        JLabel entername = new JLabel("Enter name:");
	        entername.setBounds(20, 20, 80, 20);
	        frame1.add(entername);
	        JTextField entername1 = new JTextField(10);
	        entername1.setBounds(100, 20, 100, 20);
	        frame1.add(entername1);
	        JButton ok = new JButton("ok");
	        ok.setBounds(200, 20, 50, 20);
	        frame1.add(ok);
	        ok.setBackground(c2);
	        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame1.setVisible(true);
	        ok.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				name.setText("Name:" + entername1.getText());
				frame1.dispose();
					
					
				}
	        	
	        }
	        		
	        		);
	      
	        //leader board
	        JButton leader = new JButton("Go!");
	        primary.add(leader);
	        leader.setBounds(400, 630, 100, 30);
	        leader.setBackground(c2);
	        JLabel board = new JLabel("Go to Leader Board");
	        primary.add(board);
	        board.setBounds(380, 600, 200, 30);
	        board.setFont(font2);
	        leader.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	JFrame frame2 = new JFrame();
	            	final int w = 300;
	            	final int h = 400;
	            	frame2.setSize(w, h);
	            	frame2.setTitle("Welcome to Leaderboard");
	            	frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            	frame2.setLayout(null); 
	            	// Panel
	            	Color c4 = new Color(255, 240, 248);
	            	JPanel last = new JPanel();
	            	last.setBackground(c4);
	            	last.setBounds(0, 0, w, h); 
	            	last.setLayout(null);
	            	// LEADERBOARD Label
	            	JLabel hello = new JLabel("LEADERBOARD");
	            	Font font3 = new Font("Serif", Font.PLAIN, 25);
	            	hello.setFont(font3);
	            	hello.setBounds(50, 10, 200, 50);
	            	// Rank Label
	            	JLabel rank = new JLabel();
	            	Font font4 = new Font("Serif", Font.PLAIN, 15);
	            	int ranknum = 1;
	            	rank.setText("RANK: " + ranknum);
	            	rank.setFont(font4);
	            	rank.setBounds(30, 100, 200, 30);
	            	// Name Label
	            	JLabel name1 = new JLabel();
	            	name1.setText("NAME: " + entername1.getText());
	            	name1.setFont(font4);
	            	name1.setBounds(30, 150, 200, 30);
	            	// Score Label
	            	JLabel score2 = new JLabel();
	            	score2.setText("SCORE: " + points);
	            	score2.setFont(font4);
	            	score2.setBounds(30, 200, 200, 30);
	            	// Thank You Label
	            	JLabel thank = new JLabel("THANK YOU!!!");
	            	thank.setFont(font3);
	            	thank.setBounds(50, 300, 200, 50);
	            	// Add to panel
	            	last.add(hello);
	            	last.add(rank);
	            	last.add(name1);
	            	last.add(score2);
	            	last.add(thank);
	            	// Add panel to frame
	            	frame2.add(last);

	            	frame2.setVisible(true);

	            }
	        });

	        
	        
	       
	        
	    }

	    public static void main(String[] args) {
	        new Sudoku();
	    }

	}

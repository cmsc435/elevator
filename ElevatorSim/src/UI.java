import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.Canvas;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import javax.swing.JTextField;


public class UI extends JFrame {

	private JPanel contentPane;
	static ElevatorController control;
	static Elevator left;
	static Elevator right;
	public static JTextField e1_text;
	public static JTextField e2_text;

	/** left elevator is e1, right elevator is e2 in regards to button naming and association */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		control = new ElevatorController();
		left = control.left;
		right = control.right;
		System.out.println("about to eventqueue invokelater");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("inside eventqueue runnable");
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel four_one_inside = new JPanel();
		four_one_inside.setBorder(new LineBorder(new Color(0, 0, 0)));
		four_one_inside.setBounds(115, 149, 58, 39);
		contentPane.add(four_one_inside);
		four_one_inside.setLayout(null);

		JLabel e1_f4_down = new JLabel("v");
		e1_f4_down.setBounds(25, 11, 7, 16);
		e1_f4_down.setHorizontalAlignment(SwingConstants.CENTER);
		four_one_inside.add(e1_f4_down);

		JPanel four_two_inside = new JPanel();
		four_two_inside.setBorder(new LineBorder(new Color(0, 0, 0)));
		four_two_inside.setBounds(463, 149, 58, 39);
		contentPane.add(four_two_inside);
		four_two_inside.setLayout(null);

		JLabel e2_f4_down = new JLabel("v");
		e2_f4_down.setHorizontalAlignment(SwingConstants.CENTER);
		e2_f4_down.setBounds(25, 8, 7, 16);
		four_two_inside.add(e2_f4_down);

		JPanel three_one_inside = new JPanel();
		three_one_inside.setBorder(new LineBorder(new Color(0, 0, 0)));
		three_one_inside.setBounds(115, 200, 58, 76);
		contentPane.add(three_one_inside);
		three_one_inside.setLayout(null);

		JLabel e1_f3_up = new JLabel("^");
		e1_f3_up.setBounds(22, 5, 8, 16);
		three_one_inside.add(e1_f3_up);

		Canvas canvas = new Canvas();
		canvas.setBounds(35, 13, 0, 0);
		three_one_inside.add(canvas);

		JLabel e1_f3_down = new JLabel("v");
		e1_f3_down.setHorizontalAlignment(SwingConstants.CENTER);
		e1_f3_down.setBounds(23, 54, 7, 16);
		three_one_inside.add(e1_f3_down);

		JPanel three_two_inside = new JPanel();
		three_two_inside.setBorder(new LineBorder(new Color(0, 0, 0)));
		three_two_inside.setBounds(463, 200, 58, 76);
		contentPane.add(three_two_inside);
		three_two_inside.setLayout(null);

		JLabel e2_f3_up = new JLabel("^");
		e2_f3_up.setBounds(25, 5, 8, 16);
		three_two_inside.add(e2_f3_up);

		JLabel e2_f3_down = new JLabel("v");
		e2_f3_down.setHorizontalAlignment(SwingConstants.CENTER);
		e2_f3_down.setBounds(25, 54, 7, 16);
		three_two_inside.add(e2_f3_down);

		JPanel two_one_inside = new JPanel();
		two_one_inside.setBorder(new LineBorder(new Color(0, 0, 0)));
		two_one_inside.setBounds(115, 288, 58, 76);
		contentPane.add(two_one_inside);
		two_one_inside.setLayout(null);

		JLabel e1_f2_up = new JLabel("^");
		e1_f2_up.setBounds(25, 5, 8, 16);
		two_one_inside.add(e1_f2_up);

		JLabel e1_f2_down = new JLabel("v");
		e1_f2_down.setHorizontalAlignment(SwingConstants.CENTER);
		e1_f2_down.setBounds(25, 54, 7, 16);
		two_one_inside.add(e1_f2_down);

		JPanel two_two_inside = new JPanel();
		two_two_inside.setBorder(new LineBorder(new Color(0, 0, 0)));
		two_two_inside.setBounds(463, 288, 58, 76);
		contentPane.add(two_two_inside);
		two_two_inside.setLayout(null);

		JLabel e2_f2_up = new JLabel("^");
		e2_f2_up.setBounds(25, 5, 8, 16);
		two_two_inside.add(e2_f2_up);

		JLabel e2_f2_down = new JLabel("v");
		e2_f2_down.setHorizontalAlignment(SwingConstants.CENTER);
		e2_f2_down.setBounds(25, 54, 7, 16);
		two_two_inside.add(e2_f2_down);

		JPanel e1_inside_panel = new JPanel();
		e1_inside_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		e1_inside_panel.setBounds(60, 63, 165, 74);
		contentPane.add(e1_inside_panel);

		JButton e1_1_button = new JButton("1");
		e1_1_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Elevator.Direction dir = left.direction;
				if (left.currentLevel > 1) {
					dir = Elevator.Direction.DOWN;
				}
				
				Request request = new Request(1, Request.Status.DROPOFF, dir, left);
				// hardcoded based on button name
				control.addDropRequest(request);
				// call special dropoff request method to send to specific elevator
				System.out.println("left elevator " + left.elevatorid + ": dropoff on 1st floor requested");
			}
		});
		e1_inside_panel.add(e1_1_button);

		JButton e1_2_button = new JButton("2");
		e1_2_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Elevator.Direction dir = left.direction;
				if (left.currentLevel > 2) {
					dir = Elevator.Direction.DOWN;
				}
				else if (left.currentLevel < 2) {
					dir = Elevator.Direction.UP;
				}
				
				Request request = new Request(2, Request.Status.DROPOFF, dir, left);
				// hardcoded based on button name
				control.addDropRequest(request);

				// call special dropoff request method to send to specific elevator
				System.out.println("left elevator " + left.elevatorid + ": dropoff on 2nd floor requested");
			}
		});
		e1_inside_panel.add(e1_2_button);

		JButton e1_3_button = new JButton("3");
		e1_3_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Elevator.Direction dir = left.direction;
				if (left.currentLevel > 3) {
					dir = Elevator.Direction.DOWN;
				}
				else if (left.currentLevel < 3) {
					dir = Elevator.Direction.UP;
				}
				
				Request request = new Request(3, Request.Status.DROPOFF, dir, left);
				// hardcoded based on button name
				control.addDropRequest(request);
				// call special dropoff request method to send to specific elevator
				System.out.println("left elevator " + left.elevatorid + ": dropoff on 3rd floor requested");
			}
		});
		e1_inside_panel.add(e1_3_button);

		JButton e1_4_button = new JButton("4");
		e1_4_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Elevator.Direction dir = left.direction;
				if (left.currentLevel < 4) {
					dir = Elevator.Direction.UP;
				}
				
				Request request = new Request(4, Request.Status.DROPOFF, dir, left);
				// hardcoded based on button name
				control.addDropRequest(request);
				// call special dropoff request method to send to specific elevator
				System.out.println("left elevator " + left.elevatorid + ": dropoff on 4th floor requested");
			}
		});
		e1_inside_panel.add(e1_4_button);

		JPanel e2_inside_panel = new JPanel();
		e2_inside_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		e2_inside_panel.setBounds(408, 63, 165, 74);
		contentPane.add(e2_inside_panel);

		JButton e2_1_button = new JButton("1");
		e2_1_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Elevator.Direction dir = right.direction;
				if (right.currentLevel > 1) {
					dir = Elevator.Direction.DOWN;
				}
				
				Request request = new Request(1, Request.Status.DROPOFF, dir, right);
				// hardcoded based on button name
				control.addDropRequest(request);
				// call special dropoff request method to send to specific elevator
				System.out.println("right elevator " + right.elevatorid + ": dropoff on 1st floor requested");
			}
		});
		e2_inside_panel.add(e2_1_button);

		JButton e2_2_button = new JButton("2");
		e2_2_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Elevator.Direction dir = right.direction;
				if (right.currentLevel > 2) {
					dir = Elevator.Direction.DOWN;
				}
				else if (right.currentLevel < 2) {
					dir = Elevator.Direction.UP;
				}
				
				Request request = new Request(2, Request.Status.DROPOFF, dir, right);
				// hardcoded based on button name
				control.addDropRequest(request);
				// call special dropoff request method to send to specific elevator
				System.out.println("right elevator " + right.elevatorid + ": dropoff on 2nd floor requested");
			}
		});
		e2_inside_panel.add(e2_2_button);

		JButton e2_3_button = new JButton("3");
		e2_3_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Elevator.Direction dir = right.direction;
				if (right.currentLevel > 3) {
					dir = Elevator.Direction.DOWN;
				}
				else if (right.currentLevel < 3) {
					dir = Elevator.Direction.UP;
				}
				
				Request request = new Request(3, Request.Status.DROPOFF, dir, right);
				// hardcoded based on button name
				control.addDropRequest(request);
				// call special dropoff request method to send to specific elevator
				System.out.println("right elevator " + right.elevatorid + ": dropoff on 3rd floor requested");
			}
		});
		e2_inside_panel.add(e2_3_button);

		JButton e2_4_button = new JButton("4");
		e2_4_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Elevator.Direction dir = right.direction;
				if (right.currentLevel < 4) {
					dir = Elevator.Direction.UP;
				}
				
				Request request = new Request(4, Request.Status.DROPOFF, dir, right);
				// hardcoded based on button name and current elevator position for directional info
				control.addDropRequest(request);
				// call special dropoff request method to send to specific elevator
				System.out.println("right elevator " + right.elevatorid + ": dropoff on 4th floor requested");
			}
		});
		e2_inside_panel.add(e2_4_button);

		JLabel e1_label = new JLabel("Elevator 1");
		e1_label.setBackground(Color.DARK_GRAY);
		e1_label.setHorizontalAlignment(SwingConstants.CENTER);
		e1_label.setBounds(60, 11, 165, 39);
		contentPane.add(e1_label);

		JLabel e2_label = new JLabel("Elevator 2");
		e2_label.setHorizontalAlignment(SwingConstants.CENTER);
		e2_label.setBounds(408, 12, 165, 39);
		contentPane.add(e2_label);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(258, 149, 119, 39);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton f4_down_button = new JButton("DOWN");
		f4_down_button.setBounds(17, 6, 85, 29);
		f4_down_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				System.out.println("floor 4 up button pressed");
				Request request = new Request(4, Elevator.Direction.DOWN, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				System.out.println("request added");
				// push request to elevatorcontroller to be delegated to elevators

			}

		});
		panel.add(f4_down_button);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(115, 376, 58, 39);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel e1_f1_up = new JLabel("^");
		e1_f1_up.setBounds(25, 11, 8, 16);
		panel_1.add(e1_f1_up);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(463, 376, 58, 39);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel e2_f1_up = new JLabel("^");
		e2_f1_up.setBounds(25, 11, 8, 16);
		panel_2.add(e2_f1_up);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(258, 200, 119, 76);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JButton f3_up_button = new JButton("UP");
		f3_up_button.setBounds(22, 5, 75, 29);
		f3_up_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				System.out.println("floor 3 up button pressed");
				Request request = new Request(3, Elevator.Direction.UP, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				System.out.println("request added");
				// push request to elevatorcontroller to be delegated to elevators
			}

		});
		panel_3.add(f3_up_button);

		JButton f3_down_button = new JButton("DOWN");
		f3_down_button.setBounds(17, 39, 85, 29);
		f3_down_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				System.out.println("floor 3 down button");
				Request request = new Request(3, Elevator.Direction.DOWN, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				System.out.println("request added");
				// push request to elevatorcontroller to be delegated to elevators

			}

		});
		panel_3.add(f3_down_button);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(258, 288, 119, 76);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JButton f2_up_button = new JButton("UP");
		f2_up_button.setBounds(22, 5, 75, 29);
		f2_up_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				System.out.println("floor 2 up button pressed");
				Request request = new Request(2, Elevator.Direction.UP, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				System.out.println("request added");
				// push request to elevatorcontroller to be delegated to elevators

			}

		});
		panel_4.add(f2_up_button);

		JButton f2_down_button = new JButton("DOWN");
		f2_down_button.setBounds(17, 39, 85, 29);
		f2_down_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				System.out.println("floor 2 down button pressed");
				Request request = new Request(2, Elevator.Direction.DOWN, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				System.out.println("request added");
				// push request to elevatorcontroller to be delegated to elevators

			}

		});
		panel_4.add(f2_down_button);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(258, 376, 119, 39);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JButton f1_up_button = new JButton("UP");
		f1_up_button.setBounds(22, 5, 75, 29);
		f1_up_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				System.out.println("floor 1 up button pressed");
				Request request = new Request(1, Elevator.Direction.UP, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				System.out.println("request added");
				// push request to elevatorcontroller to be delegated to elevators

			}

		});
		panel_5.add(f1_up_button);

		// set initial positions for elevator animation text boxes
		e2_text = new JTextField();
		e2_text.setText("G");
		e2_text.setHorizontalAlignment(SwingConstants.CENTER);
		e2_text.setEditable(false);
		e2_text.setColumns(5);
		e2_text.setBounds(542, 360, 47, 55);
		contentPane.add(e2_text);

		e1_text = new JTextField();
		e1_text.setBounds(26, 360, 47, 55);
		contentPane.add(e1_text);
		e1_text.setHorizontalAlignment(SwingConstants.CENTER);
		e1_text.setEditable(false);
		e1_text.setText("G");
		e1_text.setColumns(5);



	}

	// Used to change the floor displayed to the user in the GUI
	static void changeFloorDisplay(JTextField tf, int i){

		switch(i){

		case 1:
			// change text box's position to ground position in GUI
			if (tf == e1_text)
				e1_text.setBounds(26, 360, 47, 55);
			else
				e2_text.setBounds(542, 360, 47, 55);
			tf.setText("G");
			break;
		case 2:
			// change text box's position to 2nd floor in GUI
			if (tf == e1_text)
				e1_text.setBounds(26, 299, 47, 55);
			else
				e2_text.setBounds(542, 299, 47, 55);
			tf.setText("2");
			break;
			
		case 3:
			// change text box's position to 3rd floor in GUI
			if (tf == e1_text)
				e1_text.setBounds(26, 211, 47, 55);
			else
				e2_text.setBounds(542, 211, 47, 55);
			tf.setText("3");
			break;
			
		case 4:
			// change text box's position to 4th floor in GUI
			if (tf == e1_text)
				e1_text.setBounds(26, 139, 47, 55);
			else
				e2_text.setBounds(542, 139, 47, 55);
			tf.setText("4");
			break;
			
		default:
			break;
		}


	}
}
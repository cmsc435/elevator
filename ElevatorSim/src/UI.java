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


public class UI extends JFrame {

	private JPanel contentPane;
	static ElevatorController control;
	static Elevator left;
	static Elevator right;
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
		setBounds(100, 100, 537, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel four_one_inside = new JPanel();
		four_one_inside.setBounds(125, 149, 58, 39);
		contentPane.add(four_one_inside);
		four_one_inside.setLayout(null);

		JLabel e1_f4_down = new JLabel("v");
		e1_f4_down.setBounds(25, 11, 7, 16);
		e1_f4_down.setHorizontalAlignment(SwingConstants.CENTER);
		four_one_inside.add(e1_f4_down);

		JPanel four_two_inside = new JPanel();
		four_two_inside.setBounds(353, 149, 58, 39);
		contentPane.add(four_two_inside);
		four_two_inside.setLayout(null);

		JLabel e2_f4_down = new JLabel("v");
		e2_f4_down.setHorizontalAlignment(SwingConstants.CENTER);
		e2_f4_down.setBounds(25, 8, 7, 16);
		four_two_inside.add(e2_f4_down);

		JPanel three_one_inside = new JPanel();
		three_one_inside.setBounds(125, 200, 58, 76);
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
		three_two_inside.setBounds(353, 200, 58, 76);
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
		two_one_inside.setBounds(125, 288, 58, 76);
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
		two_two_inside.setBounds(353, 288, 58, 76);
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
		e1_inside_panel.setBounds(72, 63, 165, 74);
		contentPane.add(e1_inside_panel);

		JButton e1_1_button = new JButton("1");
		e1_1_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for inside buttons not outside
				Elevator.Direction dir = left.direction;
				if (left.currentLevel > 1) {
					dir = Elevator.Direction.DOWN;
				}
				Request request = new Request(1, dir, Request.Status.DROPOFF);
				// hardcoded based on button name
				try {
					control.addDropRequest(request);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				// push request to elevatorcontroller to be delegated to elevators
			}
		});
		e1_inside_panel.add(e1_1_button);

		JButton e1_2_button = new JButton("2");
		e1_2_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for inside buttons not outside
				Elevator.Direction dir = left.direction;
				if (left.currentLevel > 2) {
					dir = Elevator.Direction.DOWN;
				}
				else if (left.currentLevel < 2) {
					dir = Elevator.Direction.UP;
				}
				Request request = new Request(2, dir, Request.Status.DROPOFF);
				// hardcoded based on button name
				try {
					control.addDropRequest(request);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				// push request to elevatorcontroller to be delegated to elevators
			}
		});
		e1_inside_panel.add(e1_2_button);

		JButton e1_3_button = new JButton("3");
		e1_3_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for inside buttons not outside
				Elevator.Direction dir = left.direction;
				if (left.currentLevel > 3) {
					dir = Elevator.Direction.DOWN;
				}
				else if (left.currentLevel < 3) {
					dir = Elevator.Direction.UP;
				}
				Request request = new Request(3, dir, Request.Status.DROPOFF);
				// hardcoded based on button name
				try {
					control.addDropRequest(request);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				// push request to elevatorcontroller to be delegated to elevators
			}
		});
		e1_inside_panel.add(e1_3_button);

		JButton e1_4_button = new JButton("4");
		e1_4_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for inside buttons not outside
				Elevator.Direction dir = left.direction;
				if (left.currentLevel < 4) {
					dir = Elevator.Direction.UP;
				}
				Request request = new Request(4, dir, Request.Status.DROPOFF);
				// hardcoded based on button name
				try {
					control.addDropRequest(request);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				// push request to elevatorcontroller to be delegated to elevators
			}
		});
		e1_inside_panel.add(e1_4_button);

		JPanel e2_inside_panel = new JPanel();
		e2_inside_panel.setBounds(298, 63, 165, 74);
		contentPane.add(e2_inside_panel);

		JButton e2_1_button = new JButton("1");
		e2_1_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for inside buttons not outside
				Elevator.Direction dir = right.direction;
				if (right.currentLevel > 1) {
					dir = Elevator.Direction.DOWN;
				}
				Request request = new Request(1, dir, Request.Status.DROPOFF);
				// hardcoded based on button name
				try {
					control.addDropRequest(request);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				// push request to elevatorcontroller to be delegated to elevators
			}
		});
		e2_inside_panel.add(e2_1_button);

		JButton e2_2_button = new JButton("2");
		e2_2_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for inside buttons not outside
				Elevator.Direction dir = right.direction;
				if (right.currentLevel > 2) {
					dir = Elevator.Direction.DOWN;
				}
				else if (right.currentLevel < 2) {
					dir = Elevator.Direction.UP;
				}
				Request request = new Request(2, dir, Request.Status.DROPOFF);
				// hardcoded based on button name
				try {
					control.addDropRequest(request);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				// push request to elevatorcontroller to be delegated to elevators
			}
		});
		e2_inside_panel.add(e2_2_button);

		JButton e2_3_button = new JButton("3");
		e2_3_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for inside buttons not outside
				Elevator.Direction dir = right.direction;
				if (right.currentLevel > 3) {
					dir = Elevator.Direction.DOWN;
				}
				else if (right.currentLevel < 3) {
					dir = Elevator.Direction.UP;
				}
				Request request = new Request(3, dir, Request.Status.DROPOFF);
				// hardcoded based on button name
				try {
					control.addDropRequest(request);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				// push request to elevatorcontroller to be delegated to elevators
			}
		});
		e2_inside_panel.add(e2_3_button);

		JButton e2_4_button = new JButton("4");
		e2_4_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for inside buttons not outside
				Elevator.Direction dir = right.direction;
				if (right.currentLevel < 4) {
					dir = Elevator.Direction.UP;
				}
				Request request = new Request(4, dir, Request.Status.DROPOFF);
				// hardcoded based on button name and current elevator position for directional info
				try {
					control.addDropRequest(request);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				// push request to elevatorcontroller to be delegated to elevators
			}
		});
		e2_inside_panel.add(e2_4_button);

		JLabel e1_label = new JLabel("Elevator 1");
		e1_label.setBackground(Color.DARK_GRAY);
		e1_label.setHorizontalAlignment(SwingConstants.CENTER);
		e1_label.setBounds(72, 11, 165, 39);
		contentPane.add(e1_label);

		JLabel e2_label = new JLabel("Elevator 2");
		e2_label.setHorizontalAlignment(SwingConstants.CENTER);
		e2_label.setBounds(298, 11, 165, 39);
		contentPane.add(e2_label);

		JPanel panel = new JPanel();
		panel.setBounds(207, 149, 119, 39);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton f4_down_button = new JButton("DOWN");
		f4_down_button.setBounds(17, 6, 85, 29);
		f4_down_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				Request request = new Request(4, Elevator.Direction.DOWN, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				// push request to elevatorcontroller to be delegated to elevators
				
			}
			
		});
		panel.add(f4_down_button);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(125, 376, 58, 39);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel e1_f1_up = new JLabel("^");
		e1_f1_up.setBounds(25, 11, 8, 16);
		panel_1.add(e1_f1_up);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(353, 376, 58, 39);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel e2_f1_up = new JLabel("^");
		e2_f1_up.setBounds(25, 11, 8, 16);
		panel_2.add(e2_f1_up);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(207, 200, 119, 76);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JButton f3_up_button = new JButton("UP");
		f3_up_button.setBounds(22, 5, 75, 29);
		f3_up_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				Request request = new Request(3, Elevator.Direction.UP, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
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
				Request request = new Request(3, Elevator.Direction.DOWN, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				// push request to elevatorcontroller to be delegated to elevators
				
			}
			
		});
		panel_3.add(f3_down_button);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(207, 288, 119, 76);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JButton f2_up_button = new JButton("UP");
		f2_up_button.setBounds(22, 5, 75, 29);
		f2_up_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				Request request = new Request(2, Elevator.Direction.UP, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
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
				Request request = new Request(2, Elevator.Direction.DOWN, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				// push request to elevatorcontroller to be delegated to elevators
				
			}
			
		});
		panel_4.add(f2_down_button);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(207, 376, 119, 39);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JButton f1_up_button = new JButton("UP");
		f1_up_button.setBounds(22, 5, 75, 29);
		f1_up_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for outside buttons not inside
				Request request = new Request(1, Elevator.Direction.UP, Request.Status.PICKUP);
				// hardcoded based on button name
				control.addRequest(request);
				// push request to elevatorcontroller to be delegated to elevators
				
			}
			
		});
		panel_5.add(f1_up_button);


	}
}
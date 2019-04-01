package myJava.neulii.Game;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JToggleButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MarketWindow {
	
	private JDialog window;
	private MaterialManager mm;
	private JFrame parentWindow;
	private JTextField coalMoneyValue;
	private JTextField ironOreMoneyValue;
	private JTextField rawIronMoneyValue;
	private JTextField moneyTotal;
	private JSpinner coalSpinner;
	private JSpinner ironOreSpinner;
	private JSpinner rawIronSpinner;
	private JButton resetValues;
	private JButton selling;
	
	public MarketWindow(MaterialManager mm, JFrame parentWindow) {
		
		this.mm = mm;
		this.parentWindow = parentWindow;
		
		
		window = new JDialog(parentWindow, "Market", true);
		
		
		window.setSize(463,453);
		window.setLocationRelativeTo(parentWindow);
		window.setResizable(false);
		
		window.getContentPane().setLayout(null);
		
		coalSpinner = new JSpinner();
		coalSpinner.setBounds(103, 111, 40, 34);
		window.getContentPane().add(coalSpinner);
		//coalSpinner.setModel(new SpinnerNumberModel(0, 0, mm.getcoal(), 1));
		setSpinnerNotEditabl(coalSpinner);
		
		coalSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int newValue = (int)coalSpinner.getValue();
				
				if((int)coalSpinner.getValue() > mm.getcoal()) {
					coalSpinner.setValue(newValue-1);	
				}
				
			}
		});
		
		
		
		
		
		ironOreSpinner = new JSpinner();
		ironOreSpinner.setBounds(200, 111, 40, 34);
		window.getContentPane().add(ironOreSpinner);
		//ironOreSpinner.setModel(new SpinnerNumberModel(0, 0, mm.getIronOre(),1));
		setSpinnerNotEditabl(ironOreSpinner);
		
		
		
		rawIronSpinner = new JSpinner();
		rawIronSpinner.setBounds(296, 111, 40, 34);
		window.getContentPane().add(rawIronSpinner);
		rawIronSpinner.setModel(new SpinnerNumberModel(0, 0, mm.getRawIron(),1));
		setSpinnerNotEditabl(rawIronSpinner);
		
		
		
		JLabel lblKohle = new JLabel("Kohle");
		lblKohle.setHorizontalAlignment(SwingConstants.CENTER);
		lblKohle.setBounds(78, 78, 91, 14);
		window.getContentPane().add(lblKohle);
		
		JLabel lblEisenerz = new JLabel("Eisenerz");
		lblEisenerz.setHorizontalAlignment(SwingConstants.CENTER);
		lblEisenerz.setBounds(179, 78, 83, 14);
		window.getContentPane().add(lblEisenerz);
		
		JLabel lblRoheisen = new JLabel("Roheisen");
		lblRoheisen.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoheisen.setBounds(272, 78, 89, 14);
		window.getContentPane().add(lblRoheisen);
		
		JLabel lblVerkauf = new JLabel("Verkauf:");
		lblVerkauf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVerkauf.setBounds(81, 21, 80, 34);
		window.getContentPane().add(lblVerkauf);
		
		resetValues = new JButton("Zur\u00FCcksetzen");
		resetValues.setBounds(80, 310, 123, 34);
		window.getContentPane().add(resetValues);
		
		selling = new JButton("Verkaufen");
		selling.setBounds(241, 310, 123, 34);
		window.getContentPane().add(selling);
		
		coalMoneyValue = new JTextField();
		coalMoneyValue.setHorizontalAlignment(SwingConstants.CENTER);
		coalMoneyValue.setText("0");
		coalMoneyValue.setBounds(78, 172, 86, 20);
		window.getContentPane().add(coalMoneyValue);
		coalMoneyValue.setColumns(10);
		
		ironOreMoneyValue = new JTextField();
		ironOreMoneyValue.setHorizontalAlignment(SwingConstants.CENTER);
		ironOreMoneyValue.setText("0");
		ironOreMoneyValue.setColumns(10);
		ironOreMoneyValue.setBounds(179, 172, 86, 20);
		window.getContentPane().add(ironOreMoneyValue);
		
		rawIronMoneyValue = new JTextField();
		rawIronMoneyValue.setHorizontalAlignment(SwingConstants.CENTER);
		rawIronMoneyValue.setText("0");
		rawIronMoneyValue.setColumns(10);
		rawIronMoneyValue.setBounds(275, 172, 86, 20);
		window.getContentPane().add(rawIronMoneyValue);
		
		moneyTotal = new JTextField();
		moneyTotal.setHorizontalAlignment(SwingConstants.CENTER);
		moneyTotal.setText("0");
		moneyTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		moneyTotal.setColumns(10);
		moneyTotal.setBounds(275, 250, 86, 20);
		window.getContentPane().add(moneyTotal);
		
		JLabel lblVerkaufswert = new JLabel("Verkaufswert");
		lblVerkaufswert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVerkaufswert.setBounds(272, 205, 136, 34);
		window.getContentPane().add(lblVerkaufswert);
		
		//updateValues();
		
		
		
	}

	public void updateValues() {
		
		coalSpinner.setModel(new SpinnerNumberModel((Number) coalSpinner.getValue(), 0, mm.getcoal(), 1));
		setSpinnerNotEditabl(coalSpinner);
		
		
		System.out.println(mm.getcoal());
		
		ironOreSpinner.setModel(new SpinnerNumberModel(0, 0, mm.getIronOre(),1));
		setSpinnerNotEditabl(ironOreSpinner);
		
		rawIronSpinner.setModel(new SpinnerNumberModel(0, 0, mm.getRawIron(),1));
		setSpinnerNotEditabl(rawIronSpinner);
		
		
	}
	
	public void setSpinnerNotEditabl(JSpinner spinner) {
		((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
		
	}

	public void show() {
		window.setVisible(true);
		
		
	}
}

package myJava.neulii.Game;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private int sumOfCoal;
	private int sumOfRawIron;
	private int sumOfIronOre;
	
	public MarketWindow(MaterialManager mm, JFrame parentWindow) {
		
		this.mm = mm;
		this.parentWindow = parentWindow;
		
		
		window = new JDialog(parentWindow, "Market", true);
		
		
		window.setSize(463,453);
		
		window.setLocationRelativeTo(parentWindow);
		window.setResizable(false);
		
		window.getContentPane().setLayout(null);
		
		coalSpinner = new JSpinner();
		coalSpinner.setBounds(96, 111, 55, 34);
		window.getContentPane().add(coalSpinner);
		setSpinnerNotEditabl(coalSpinner);
		
		coalSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int newValue = (int)coalSpinner.getValue();
				
				if((int)coalSpinner.getValue() > mm.getcoal()) {
					coalSpinner.setValue(newValue-1);	
				}
				
				if((int)coalSpinner.getValue()<=0) {
					coalSpinner.setValue(0);
				}
				
				sumOfCoal = mm.getCoalWorth() * (int)coalSpinner.getValue();
				coalMoneyValue.setText(Integer.toString(sumOfCoal));
				
				moneyTotal.setText(Integer.toString(sumOfIronOre + sumOfCoal + sumOfRawIron));
				
			}
		});
				
		ironOreSpinner = new JSpinner();
		ironOreSpinner.setBounds(193, 111, 55, 34);
		window.getContentPane().add(ironOreSpinner);
		setSpinnerNotEditabl(ironOreSpinner);
		
		ironOreSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int newValue = (int)ironOreSpinner.getValue();
				
				if((int)ironOreSpinner.getValue() > mm.getIronOre()){
					ironOreSpinner.setValue(newValue-1);	
				}
				
				if((int)ironOreSpinner.getValue()<=0) {
					ironOreSpinner.setValue(0);
				}
				sumOfIronOre = mm.getironOreWorth() * (int)ironOreSpinner.getValue();
				ironOreMoneyValue.setText(Integer.toString(sumOfIronOre));
				
				moneyTotal.setText(Integer.toString(sumOfIronOre + sumOfCoal + sumOfRawIron));
				
			}
		});

		rawIronSpinner = new JSpinner();
		rawIronSpinner.setBounds(289, 111, 55, 34);
		window.getContentPane().add(rawIronSpinner);
		setSpinnerNotEditabl(rawIronSpinner);
		
		rawIronSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int newValue = (int)rawIronSpinner.getValue();
				
				if((int)rawIronSpinner.getValue()> mm.getRawIron()) {
					rawIronSpinner.setValue(newValue-1);
				}
				
				if((int) rawIronSpinner.getValue()<=0) {
					rawIronSpinner.setValue(0);
				}
				sumOfRawIron = mm.getRawIronWorth() * (int)rawIronSpinner.getValue();
				rawIronMoneyValue.setText(Integer.toString(sumOfRawIron));
				
				moneyTotal.setText(Integer.toString(sumOfIronOre + sumOfCoal + sumOfRawIron));
			}
		});
		
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
		resetValues.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ironOreSpinner.setValue(0);
				rawIronSpinner.setValue(0);
				coalSpinner.setValue(0);
				
			}
		});
		
		
		selling = new JButton("Verkaufen");
		selling.setBounds(241, 310, 123, 34);
		window.getContentPane().add(selling);
		
		selling.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mm.subCoal((int)coalSpinner.getValue());
				mm.addMoney(sumOfCoal);
				coalSpinner.setValue(0);
				
				
				mm.subIronOre((int)ironOreSpinner.getValue());
				mm.addMoney(sumOfIronOre);
				ironOreSpinner.setValue(0);
				
				mm.subRawIron((int)rawIronSpinner.getValue());
				mm.addMoney(sumOfRawIron);
				rawIronSpinner.setValue(0);
				
				
			}
		});
		
		coalMoneyValue = new JTextField();
		coalMoneyValue.setHorizontalAlignment(SwingConstants.CENTER);
		coalMoneyValue.setText("0");
		coalMoneyValue.setBounds(80, 172, 86, 20);
		window.getContentPane().add(coalMoneyValue);
		coalMoneyValue.setColumns(10);
		coalMoneyValue.setEditable(false);
		
		ironOreMoneyValue = new JTextField();
		ironOreMoneyValue.setHorizontalAlignment(SwingConstants.CENTER);
		ironOreMoneyValue.setText("0");
		ironOreMoneyValue.setColumns(10);
		ironOreMoneyValue.setBounds(177, 172, 86, 20);
		window.getContentPane().add(ironOreMoneyValue);
		ironOreMoneyValue.setEditable(false);
		
		rawIronMoneyValue = new JTextField();
		rawIronMoneyValue.setHorizontalAlignment(SwingConstants.CENTER);
		rawIronMoneyValue.setText("0");
		rawIronMoneyValue.setColumns(10);
		rawIronMoneyValue.setBounds(273, 172, 86, 20);
		window.getContentPane().add(rawIronMoneyValue);
		rawIronMoneyValue.setEditable(false);
		
		moneyTotal = new JTextField();
		moneyTotal.setHorizontalAlignment(SwingConstants.CENTER);
		moneyTotal.setText("0");
		moneyTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		moneyTotal.setColumns(10);
		moneyTotal.setBounds(275, 250, 86, 20);
		window.getContentPane().add(moneyTotal);
		moneyTotal.setEditable(false);
		
		JLabel lblVerkaufswert = new JLabel("Verkaufswert");
		lblVerkaufswert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVerkaufswert.setBounds(272, 205, 136, 34);
		window.getContentPane().add(lblVerkaufswert);

	}
	
	public void setSpinnerNotEditabl(JSpinner spinner) {
		((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
		
	}

	public void show() {
		window.setVisible(true);
	
	}
}

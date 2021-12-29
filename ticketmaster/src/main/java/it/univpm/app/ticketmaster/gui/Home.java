package it.univpm.app.ticketmaster.gui;

import java.util.Vector;


import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import it.univpm.app.ticketmaster.filter.EventsFilter;

public class Home extends JFrame
{	
	JComboBox<String> statesBox;
	JComboBox<String> citiesBox;
	JComboBox<String> segmentsBox;
	JComboBox<String> genresBox;
	
	JButton searchButton;
	JButton exitButton;


	public Home()
	{
		this.setTitle("Filtraggio Eventi");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		//this.pack();
		

		statesBox = new JComboBox<String>();
		citiesBox = new JComboBox<String>();
		segmentsBox = new JComboBox<String>();
		genresBox = new JComboBox<String>();		
		
		loadBox();
		statesBox.setBounds(450, 310, 250, 30);
		//statesBox.setAlignmentY(50);
		
		this.add(statesBox);
		this.add(citiesBox);
		this.add(segmentsBox);
		this.add(genresBox);
		
		
		searchButton = new JButton("Search");
		exitButton = new JButton("Exit");
		this.add(searchButton);
		this.add(exitButton);
		
		
		exitButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				System.out.println("Vorrei uscire");
			}
		});
		
		
		
		searchButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				System.out.println("Vorrei cercare");
			}
		});
	
		
		this.setVisible(true);
	}
	
	
	
	
	public void loadBox(JComboBox<String> box, Vector<String> list)
	{
		box.addItem("-ALL-");
		
		for(int i = 0; i<list.size(); i++)
		{
			box.addItem(list.get(i));
		}
	}
	
	public void loadBox()
	{
		loadBox(statesBox, EventsFilter.getStates());
		loadBox(citiesBox, EventsFilter.getCities());
		loadBox(segmentsBox, EventsFilter.getSegments());
		loadBox(genresBox, EventsFilter.getGenres());
	}


}

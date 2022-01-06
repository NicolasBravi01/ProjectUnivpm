package it.univpm.app.ticketmaster.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;
import it.univpm.app.ticketmaster.model.Event;

public class Result extends JFrame
{
	JScrollPane scrollRisultato;
	JTextArea risultato;
	
	JButton backButton;
	JButton exitButton;
	
	
	Home home;
	
	
	public Result(Home home, Vector<Event> events)
	{
		this.home = home;
		
		/*this.setTitle("Filtraggio Eventi");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		
		
		risultato = new JTextArea(events.toString());		 
		 
		scrollRisultato = new JScrollPane(risultato);
		
		risultato.setCaretPosition(0);
		//risultato.setMargin(new Insets(0, 50, 0, 0));
		scrollRisultato.setBounds(60, 50, 800, 550);
		scrollRisultato.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.getContentPane().add(scrollRisultato);
		
		
		//this.add(scrollRisultato);
		
		this.setVisible(true);
		*/
		
		JTextArea risultato = new JTextArea();
		JScrollPane scrollRisultato = new JScrollPane(risultato);
				
		
		this.setSize(1280, 730);
		this.setTitle("Result events");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		//this.setLocationRelativeTo(null);
		this.setResizable(true);
		//this.setUndecorated(true);
		//this.getContentPane().setBackground(new Color(30, 30, 30));
		//this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.setVisible(true);
		
		
		
		
		
		//risultato.setBackground(new Color(50, 50, 50));
		risultato.setEditable(false);
		risultato.setText(events.toString());
		risultato.setCaretPosition(0);
		//risultato.setForeground(Color.WHITE);
		//risultato.setFont(new Font("Arial", Font.PLAIN, 20));
		risultato.setMargin(new Insets(0, 50, 0, 0));
		scrollRisultato.setBounds(60, 50, 1150, 550);
		this.add(scrollRisultato);
		
		
		
		
		
		
		
		/*
		 * BUTTON
		 */
		backButton = new JButton("Back");
		exitButton = new JButton("Exit");
		
		backButton.setBounds(920, 624, 120, 60);
		exitButton.setBounds(1090, 624, 120, 60);
		
		this.add(backButton);
		this.add(exitButton);
		
		
		
		//aggiunge ActionListener dei bottoni
		addButtonsMouseListener();
		
		
		this.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	public void addButtonsMouseListener()
	{
		addExitButtonMouseListener();
		addBackButtonMouseListener();
	}
	
	
	
	
	public void addExitButtonMouseListener()
	{
		exitButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				System.exit(NORMAL);
			}
		});
	}
	
	
	public void addBackButtonMouseListener()
	{
		backButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				close();
				home.visible(true);			
			}
		});
	}
	
	
	
	/*
	 * Metodo che permette di modificare la visibilità della finestra, fatto
	 * perchè all'interno dell'actionListener, l'oggetto this non è accessibile
	 */
	public void close()
	{
		this.dispose();
	}
	
	
}

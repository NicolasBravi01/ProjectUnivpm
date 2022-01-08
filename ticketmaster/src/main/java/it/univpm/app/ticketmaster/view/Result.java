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
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.JSONHandler.JSONBuilder;

public class Result extends JFrame
{
	JScrollPane scrollRisultato;
	JTextArea risultato;
	
	JButton backButton;
	JButton exitButton;

	Home home;
	
	
	public Result(Home home, Filter filter, Vector<Event> events)
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
		this.setResizable(false);
		//this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(120, 120, 120));
		//this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.setVisible(true);
		
		
		String text = eventsToString(filter, events);
		//String text = jB.getJSONObjectEvents(events).toJSONString();
		
		risultato.setBackground(new Color(120, 120, 120));
		risultato.setEditable(false);
		risultato.setText(text);
		risultato.setCaretPosition(0);
		risultato.setForeground(Color.BLACK);
		risultato.setFont(new Font("Calibri", Font.PLAIN, 18));
		risultato.setMargin(new Insets(0, 50, 0, 0));
		scrollRisultato.setBounds(0, 0, 1265, 600);
		this.add(scrollRisultato);
		
		
		
		
		
		
		
		/*
		 * BUTTON
		 */
		backButton = new JButton("BACK");
		exitButton = new JButton("EXIT");
		
		backButton.setBounds(920, 615, 120, 60);
		exitButton.setBounds(1090, 615, 120, 60);
		
		setButtonsFont(new Font("Calibri", Font.BOLD, 25));
		
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
	
	
	
	
	private String eventsToString(Vector<Event> events)
	{
		String text = events.toString();
		
		text = text.substring(1, text.length()-1);		
		text = text.replaceAll(", Name:", "Name:");
		
		/*
		for(int i = 0; i > events.size(); i++)
		{
			text += (i+1) + ":\n" + events.get(i).toString();
			text += System.lineSeparator() + System.lineSeparator();
		}
		
		text += "number events: " + events.size();*/		
		
		return text;
	}
	
	private String eventsToString(Filter filter, Vector<Event> events)
	{
		JSONBuilder jB = new JSONBuilder();
		
		String text = jB.getJSONObjectAllStats(filter, events).toJSONString();
		text = text.substring(11);
		text = text.replace('{', '\n');
		text = text.replace('}', '\n');
		text = text.replace(",\"", "\n\"");
		text = text.replace("perspectives\":", System.lineSeparator());
		text = text.replace("\":", ": ");
		text = text.replace("\"", "");
		
		text = text.replace("cities", "CITIES");
		text = text.replace("states", "STATES");
		text = text.replace("segments", "SEGMENTS");
		text = text.replace("genres", "GENRES");
		text = text.replace("max", "Max");
		text = text.replace("min", "Min");
		
		text += System.lineSeparator() + System.lineSeparator() + System.lineSeparator() + System.lineSeparator() + System.lineSeparator();		
		
		for(int i = 0; i < events.size(); i++)
		{
			text += (i+1) + ":\n" + events.get(i).toString();
			text += System.lineSeparator() + System.lineSeparator();
		}
		
		text += "number events: " + events.size();
		text += System.lineSeparator() + System.lineSeparator();		
		
		return text;
	}
	
	
	/*
	 * Metodo che permette di modificare la visibilità della finestra, fatto
	 * perchè all'interno dell'actionListener, l'oggetto this non è accessibile
	 */
	public void close()
	{
		this.dispose();
	}
	
	
	
	public void setButtonsFont(Font font)
	{
		backButton.setFont(font);
		exitButton.setFont(font);
	}
	
	
}

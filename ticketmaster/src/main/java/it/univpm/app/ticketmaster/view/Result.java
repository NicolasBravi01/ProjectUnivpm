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
	
	JButton statsEventsButton;
	JButton backButton;
	JButton exitButton;

	Home home;
	
	
	String eventsText;
	String statsText;
	
	boolean areEventsShown = true;
	
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
		
		risultato = new JTextArea();
		scrollRisultato = new JScrollPane(risultato);
				
		
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
		
		
		
		/*
		 * BUTTON
		 */
		statsEventsButton = new JButton("SHOW STATS");
		backButton = new JButton("BACK");
		exitButton = new JButton("EXIT");
		
		statsEventsButton.setBounds(50, 615, 220, 60);
		backButton.setBounds(920, 615, 120, 60);
		exitButton.setBounds(1090, 615, 120, 60);
		
		setButtonsFont(new Font("Calibri", Font.BOLD, 25));
		

		this.add(statsEventsButton);
		this.add(backButton);
		this.add(exitButton);
			
		
		//aggiunge ActionListener dei bottoni
		addButtonsMouseListener();
		
		
		
		
		
		
		
		this.eventsText = readEventsText(events);
		this.statsText = readStatsText(filter, events);
		
		
		risultato.setBackground(new Color(120, 120, 120));
		risultato.setEditable(false);
		
		//risultato.setText(eventsText);
		
		risultato.setCaretPosition(0);
		risultato.setForeground(Color.BLACK);
		risultato.setFont(new Font("Calibri", Font.PLAIN, 18));
		risultato.setMargin(new Insets(0, 50, 0, 0));
		scrollRisultato.setBounds(0, 0, 1265, 600);
		this.add(scrollRisultato);
		
		loadEvents();
		
		
		
		
		
		
		
		
		
		
		this.setVisible(true);
	}
	
	
	
	
	
	private String readEventsText(Vector<Event> events)
	{
		String text = System.lineSeparator() + "-------- LIST EVENTS --------" + System.lineSeparator() + System.lineSeparator();
		
		for(int i = 0; i < events.size(); i++)
		{
			text += (i+1) + ":\n" + events.get(i).toString();
			text += System.lineSeparator() + System.lineSeparator();
		}
		
		text += "number events: " + events.size();
		text += System.lineSeparator() + System.lineSeparator();		
		
		return text;		
	}
	
	
	private String readStatsText(Filter filter, Vector<Event> events)
	{
		JSONBuilder jB = new JSONBuilder();
		
		String titleGeneral = System.lineSeparator() + "-------- GENERAL STATS --------" + System.lineSeparator();
		
		String text = jB.getJSONObjectAllStats(filter, events).toJSONString();
		text = text.substring(11);
		text = text.replace('{', '\n');
		text = text.replace('}', '\n');
		text = text.replace(",\"", "\n\"");
		text = text.replace("perspectives\":", System.lineSeparator()+System.lineSeparator());
		text = text.replace("\":", ": ");
		text = text.replace("\"", "");
		
		String titlePerspectives = System.lineSeparator() + System.lineSeparator() + "-------- STATS PERSPECTIVES --------";
		titlePerspectives += System.lineSeparator()+System.lineSeparator()+System.lineSeparator()+System.lineSeparator();
		
		text = text.replace("cities:", titlePerspectives + "--- CITIES ---" + System.lineSeparator());
		text = text.replace("states:", System.lineSeparator()+System.lineSeparator() + "--- STATES ---" + System.lineSeparator());
		text = text.replace("segments:", System.lineSeparator()+System.lineSeparator() + "--- SEGMENTS ---" + System.lineSeparator());
		text = text.replace("genres:", System.lineSeparator()+System.lineSeparator() + "--- GENRES ---" + System.lineSeparator());
		text = text.replace("max", "Max");
		text = text.replace("min", "Min");	
				
		text = titleGeneral + text;
		
		return text;		
	}
	
	
	
	
	
	
	public void addButtonsMouseListener()
	{
		addStatsEventsButtonMouseListener();
		addExitButtonMouseListener();
		addBackButtonMouseListener();
	}
	
	
	
	public void addStatsEventsButtonMouseListener()
	{
		statsEventsButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(areEventsShown)
				{
					loadStats();
				}
				else
				{
					loadEvents();
				}
				
				areEventsShown = !areEventsShown;
			}
		});
	}
	
	private void loadStats()
	{
		this.risultato.setText(statsText);
		statsEventsButton.setText("SHOW EVENTS");
		risultato.setCaretPosition(0);
	}
	
	private void loadEvents()
	{
		this.risultato.setText(eventsText);
		statsEventsButton.setText("SHOW STATS");
		this.risultato.setCaretPosition(0);
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
		statsEventsButton.setFont(font);
		backButton.setFont(font);
		exitButton.setFont(font);
	}
	
	
}

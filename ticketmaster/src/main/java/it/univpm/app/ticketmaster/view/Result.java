package it.univpm.app.ticketmaster.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;

/**
 * Classe che gestisce la visualizzazione della finestra che mostra la lista di eventi e le statistiche
 * 
 * @author sup3r
 * @autor NicolasBravi01
 */
@SuppressWarnings("serial")
public class Result extends JFrame
{
	/**
	 * Area per la visualizzazione di un testo
	 */
	JTextArea result = new JTextArea();
	
	/**
	 * Scroll per scorrere un testo
	 */
	JScrollPane scrollResult = new JScrollPane(result);
	
	/*
	 * Bottoni Show Stats/Events, Back e Exit
	 */
	JButton statsEventsButton = new JButton("SHOW STATS");
	JButton backButton = new JButton("BACK");
	JButton exitButton = new JButton("EXIT");

	/**
	 * Oggetto Home, per poter tornare indietro senza perdere le informazioni
	 */
	Home home;	
	
	String eventsText;
	String statsText;
	
	/**
	 * Variabile settata a true se si stanno visualizzando gli eventi, false se si stanno visualizzando le statistiche
	 */
	boolean areEventsShown = true;
	
	

	/**
	 *  Costruttore della finestra dei risultati con cui è possibile visualizzare gli eventi e le statistiche relative
	 *  ai filtri settati dall'utente nella finestra precedente
	 * 
	 * @param home
	 * @param filter 
	 * @param events
	 */
	public Result(Home home, Filter filter, Vector<Event> events, JSONObject JSONObjectAllStats)
	{
		/*
		 * L'oggetto Home viene memorizzato all'interno di questa classe per avere la possibilità di tornare alla
		 * finestra precedente senza perdere i filtri che l'utente ha settato
		 */
		this.home = home;				
		
		/*
		 * Impostazioni di settaggio della finestra e dell'icona
		 */
		this.setSize(1280, 730);
		this.setTitle("Result events");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(133,173,225));
		
		ImageIcon icon = new ImageIcon("img\\Ticketmaster-Emblem.png");
		this.setIconImage(icon.getImage());
		
		
		
		
		
		/*
		 * BUTTONS
		 */
		
		statsEventsButton.setBounds(50, 615, 220, 60);
		backButton.setBounds(920, 615, 120, 60);
		exitButton.setBounds(1090, 615, 120, 60);
		
		setButtonsFont(new Font("Calibri", Font.BOLD, 25));
		
		this.add(statsEventsButton);
		this.add(backButton);
		this.add(exitButton);			
		
		addButtonsMouseListener();
		
		
		
		
		
		/*
 		 * TEXTAREA e SCROLL
		 */
		
		this.eventsText = readEventsText(events);
		this.statsText = readStatsText(JSONObjectAllStats);
		
		result.setBackground(new Color(133,173,225));		
		result.setEditable(false);
		result.setCaretPosition(0);
		result.setForeground(Color.BLACK);
		result.setFont(new Font("Calibri", Font.PLAIN, 18));
		result.setMargin(new Insets(0, 50, 0, 0));
		scrollResult.setBounds(0, 0, 1265, 600);		
		
		loadEvents();

		this.add(scrollResult);

		
		this.setVisible(true);
	}
	
	
	
	
	
	/**
	 * Metodo che converte una lista di eventi in una stringa che viene leggermente modificata per essere resa
	 * esteticamente più gradevole da leggere.
	 * 
	 * @param events Lista di tutti gli eventi
	 * 
	 * @return text Stringa contenente il testo da visualizzare nella finestra degli eventi
	 */
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
	
	
	
	
	
	/**
	 * Metodo che converte il JSON contenente le statistiche relative ad un gruppo di eventi in una stringa
	 * che viene leggermente modificata per essere resa esteticamente più gradevole da leggere.
	 * 
	 * @param JSONStats JSONObject delle statistiche
	 * 
	 * @return text Stringa contenente il testo da visualizzare nella finestra degli stati
	 */
	private String readStatsText(JSONObject JSONStats)
	{
		String titleGeneral = System.lineSeparator() + "-------- GENERAL STATS --------" + System.lineSeparator();
		
		String text = JSONStats.toJSONString();
		
		text = text.substring(11);
		text = text.replace('{', '\n');
		text = text.replace('}', '\n');
		text = text.replace(",\"", "\n\"");
		text = text.replace("perspectives\":", System.lineSeparator()+System.lineSeparator());
		text = text.replace("\":", ": ");
		text = text.replace("\"", "");
		
		String titlePerspectives = System.lineSeparator() + System.lineSeparator() + "-------- STATS PERSPECTIVES --------";
		titlePerspectives += System.lineSeparator()+System.lineSeparator()+System.lineSeparator();
		
		text = text.replace("cities:", titlePerspectives + "--- CITIES ---" + System.lineSeparator());
		text = text.replace("states:", System.lineSeparator()+System.lineSeparator() + "--- STATES ---" + System.lineSeparator());
		text = text.replace("segments:", System.lineSeparator()+System.lineSeparator() + "--- SEGMENTS ---" + System.lineSeparator());
		text = text.replace("genres:", System.lineSeparator()+System.lineSeparator() + "--- GENRES ---" + System.lineSeparator());
		text = text.replace("max", "Max");
		text = text.replace("min", "Min");	
				
		text = titleGeneral + text;
		
		return text;		
	}
	
	
	
	
	
	/**
	 * Metodo che aggiunge i MouseLister dei bottoni Show stats/Show events, Back e Exit
	 */
	public void addButtonsMouseListener()
	{
		addStatsEventsButtonMouseListener();
		addExitButtonMouseListener();
		addBackButtonMouseListener();
	}
	
	
	/**
	 * Metodo che aggiunge il MouseListener del bottone Show stats/Show events.
	 * Se il bottone viene premuto viene alternata la visualizzazioni degli eventi con quella delle
	 * statistiche o viceversa
	 */
	public void addStatsEventsButtonMouseListener()
	{
		statsEventsButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(areEventsShown)
					loadStats();
				else
					loadEvents();
				
				areEventsShown = !areEventsShown;
			}
		});
	}
	
	/**
	 * Metodo che fa visualizzare nell'area di testo le statistiche
	 */
	private void loadStats()
	{
		this.result.setText(statsText);
		statsEventsButton.setText("SHOW EVENTS");
		result.setCaretPosition(0);
	}
	
	/**
	 * Metodo che fa visualizzare nell'area di testo gli eventi
	 */
	private void loadEvents()
	{
		this.result.setText(eventsText);
		statsEventsButton.setText("SHOW STATS");
		this.result.setCaretPosition(0);
	}
	
	
	/**
	 * Metodo che aggiunge il MouseListener del bottone Exit.
	 * Se il bottone viene premuto, termina il debug
	 */
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
	
	
	/**
	 * Metodo che aggiunge il MouseListener del bottone Back.
	 * Se il bottone viene premuto, viene chiusa la finestra Result e viene rimostrata la finestra precedente 
	 */
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
	
	
	
	
	/**
	 * Metodo che chiude la finestra Result 
	 */
	public void close()
	{
		this.dispose();
	}
	
	
	
	
	/**
	 * Metodo che setta il font passato come parametro per i bottoni Show Stats/Events, Back e Exit
	 * 
	 * @param font Font
	 */
	public void setButtonsFont(Font font)
	{
		statsEventsButton.setFont(font);
		backButton.setFont(font);
		exitButton.setFont(font);
	}
	
	
}

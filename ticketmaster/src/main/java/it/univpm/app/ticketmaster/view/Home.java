package it.univpm.app.ticketmaster.view;

import java.util.Vector;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;

//import org.jdesktop.swingx.JXDatePicker;



import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;

public class Home extends JFrame
{	

	Vector<String> states = new Vector<String>();
	Vector<String> cities = new Vector<String>();
	String segment = "";
	Vector<String> genres = new Vector<String>();
	
	JComboBox<String> statesBox;
	JComboBox<String> citiesBox;
	JComboBox<String> segmentsBox;
	JComboBox<String> genresBox;
	
	
	JButton searchButton;
	JButton exitButton;
	

	JLabel statesLabel;
	JLabel citiesLabel;
	JLabel segmentLabel;
	JLabel genresLabel;
	
	
	JXDatePicker fromDatePicker;
	JXDatePicker toDatePicker;


	public Home()
	{
		this.setTitle("Filtraggio Eventi");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 500);
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		
		this.getContentPane().setBackground(new Color(180, 200, 180));
		
		
		//this.pack();
		

		
		
		/*
		 * 	LABEL
		 */		
		statesLabel = new JLabel();
		citiesLabel = new JLabel();
		segmentLabel = new JLabel();
		genresLabel = new JLabel();
		
		loadLabels();


		statesLabel.setBounds(50, 450, 95, 60);
		
		
		this.add(statesLabel);
		this.add(citiesLabel);
		this.add(segmentLabel);
		this.add(genresLabel);
		
		
		/* 
		 *	COMBOBOX 
		 */
		statesBox = new JComboBox<String>();
		citiesBox = new JComboBox<String>();
		segmentsBox = new JComboBox<String>();
		genresBox = new JComboBox<String>();		
		
		loadBox();
		statesBox.setBounds(450, 310, 250, 30);
		//statesBox.setAlignmentY(50);
		
		//aggiunge ActionListener delle comboBox per filtrare
		addComboBoxesActionListener();
		
		this.add(statesBox);
		this.add(citiesBox);
		this.add(segmentsBox);
		this.add(genresBox);
		
		
		
		/*
		 * BUTTON
		 */
		searchButton = new JButton("Search");
		exitButton = new JButton("Exit");
		
		//searchButton.setBounds(100,100,200,15);
		this.add(searchButton);
		this.add(exitButton);
		
		
		//aggiunge ActionListener dei bottoni
		addButtonsMouseListener();
		
		
		
		/*
		 * CALENDARIO
		 */
		fromDatePicker = new JXDatePicker();
		fromDatePicker.setDate(Calendar.getInstance().getTime());
		fromDatePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
        
		toDatePicker = new JXDatePicker();
		toDatePicker.setDate(Calendar.getInstance().getTime());
		toDatePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
				
		this.add(fromDatePicker);
		this.add(toDatePicker);
		
		LocalDate from = getLocalDate(fromDatePicker.getDate());
		

		/*
		 * PANEL
		 */
		
		/*JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		JPanel pan4 = new JPanel();
		JPanel pan5 = new JPanel();
		
		pan1.setBackground(Color.red);
		pan1.setBounds(0, 0, 100, 100);	

		pan2.setBackground(Color.green);
		pan2.setBounds(100, 0, 50, 50);		

		pan3.setBackground(Color.blue);
		pan3.setBounds(100, 50, 50, 50);
		
		pan1.setVisible(true);
		pan2.setVisible(true);
		pan3.setVisible(true);
		
		pan1.add(statesLabel);
		//pan1.setLayout(null);
		
		this.add(pan1);
		this.add(pan2);
		this.add(pan3);
		*/
		
		
		
		
		
		
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

	
	public void addStateFilter(String state)
	{
		//aggiungi
		if(!this.states.contains(state))			
			this.states.add(state);		
		//rimuovi
		else
			this.states.remove(state);			
	}
	
	
	
	/*
	 * Per filtrare gli eventi per tutti gli stati, invece di scriverli tutti è sufficiente
	 * non metterne nessuno, sottointendendo che non bisogna filtrare gli eventi per stati
	 * 
	 * @see FilterImpl.isIncludedState(String) 
	 */
	public void addAllStatesFilter()
	{
		this.states.removeAllElements();
		this.statesLabel.setText("All States");
	}
	
	
	public void addCityFilter(String city)
	{
		if(!this.cities.contains(city))
			this.cities.add(city);
		else
			this.cities.remove(city);	
	}
	
	/*
	 * Per filtrare gli eventi per tutte le città, invece di scriverli tutti è sufficiente
	 * non metterne nessuno, sottointendendo che non bisogna filtrare gli eventi per città
	 * 
	 * @see FilterImpl.isIncludedCity(String) 
	 */
	public void addAllCitiesFilter()
	{
		this.cities.removeAllElements();
	}
	
	
	
	public void addGenreFilter(String genre)
	{
		if(!this.genres.contains(genre))
			this.genres.add(genre);
		else
			this.genres.remove(genre);
	}
	
	/*
	 * Per filtrare gli eventi per tutti i generi, invece di scriverli tutti è sufficiente
	 * non metterne nessuno, sottointendendo che non bisogna filtrare gli eventi per generi
	 * 
	 * @see FilterImpl.isIncludedGenre(String) 
	 */
	public void addAllGenresFilter()
	{
		this.genres.removeAllElements();
	}
	
	
	
	public void setSegmentFilter(String segment)
	{
		if(!this.segment.equals(segment))
			this.segment = segment;
		else
			this.segment = "";
	}
	
	/*
	 * Per filtrare gli eventi per tutti gli stati, invece di scriverli tutti è sufficiente
	 * non metterne nessuno, sottointendendo che non bisogna filtrare gli eventi per stati
	 * 
	 * @see FilterImpl.isIncludedSegment(String) 
	 */
	public void addAllSegmentsFilter()
	{
		this.segment = "";
	}
	
	
	
	public void loadStatesLabel()
	{
		String text;
		
		if(this.states.size() == 0)
		{
			text = "All states";
		}
		else
		{
			text = this.states.toString();
			text = text.substring(1, text.length() - 1);
		}
		
		this.statesLabel.setText(text);
	}
	
	
	public void loadCitiesLabel()
	{
		String text;
		
		if(this.cities.size() == 0)
		{
			text = "All cities";
		}
		else
		{
			text = this.cities.toString();
			text = text.substring(1, text.length() - 1);
		}		
		
		this.citiesLabel.setText(text);
	}
	
	
	public void loadSegmentLabel()
	{
		String text;
		
		if(this.segment.isEmpty())
		{
			text = "All segments";
		}
		else
		{
			text = this.segment;
		}		
		
		this.segmentLabel.setText(text);
	}
	

	public void loadGenresLabel()
	{
		String text;
		
		if(this.genres.size() == 0)
		{
			text = "All genres";
		}
		else
		{
			text = this.genres.toString();
			text = text.substring(1, text.length() - 1);
		}		
		
		this.genresLabel.setText(text);
	}
	
	
	
	public void loadLabels()
	{
		loadStatesLabel();
		loadCitiesLabel();
		loadSegmentLabel();
		loadGenresLabel();
	}
	
	
	
	public void addComboBoxesActionListener()
	{
		addStatesBoxActionListener();
		addCitiesBoxActionListener();
		addSegmentsBoxActionListener();
		addGenresBoxActionListener();
	}
	
	
	public void addStatesBoxActionListener()
	{
		this.statesBox.addActionListener (new ActionListener () 
		{
			@Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(statesBox.getSelectedIndex() == 0)
		    		addAllStatesFilter();
		    	else
		    		addStateFilter(statesBox.getSelectedItem().toString());
		    	
		    	loadStatesLabel();
		    }
		});
	}
	
	
	public void addCitiesBoxActionListener()
	{
		this.citiesBox.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(citiesBox.getSelectedIndex() == 0)
		    		addAllCitiesFilter();
		    	else
		    		addCityFilter(citiesBox.getSelectedItem().toString());
		    	
		    	loadCitiesLabel();
		    }
		});
	}
	
	
	public void addSegmentsBoxActionListener()
	{		
		this.segmentsBox.addActionListener(new ActionListener() 
		{
			@Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(segmentsBox.getSelectedIndex() == 0)
		    		addAllSegmentsFilter();
		    	else
		    		setSegmentFilter(segmentsBox.getSelectedItem().toString());
		    	
		    	loadSegmentLabel();
		    }
		});
	}

	
	public void addGenresBoxActionListener()
	{
		this.genresBox.addActionListener(new ActionListener() 
		{
			@Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(genresBox.getSelectedIndex() == 0)
		    		addAllGenresFilter();
		    	else
		    		addGenreFilter(genresBox.getSelectedItem().toString());
		    	
		    	loadGenresLabel();
		    }
		});  
		
	}
	
	
	
	
	
	public void addButtonsMouseListener()
	{
		addExitButtonMouseListener();
		addSearchButtonMouseListener();
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
	
	
	public void addSearchButtonMouseListener()
	{
		searchButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				System.out.println("Vorrei cercare");
				//Effettuare chiamata
				
				FilterImpl filter = readFilter();
				
				/*
				 * 	Controlla filtri, se è tutto ok allora richiedi eventi
				 */
				
				//eventi filtrati
				
				Hide();
				new Result(getThis(), EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents()));
				
			}
		});
	}
	
	
	public void Hide()
	{
		this.setVisible(false);
	}
	
	
	public Home getThis()
	{
		return this;
	}
	
	public FilterImpl readFilter()
	{
		FilterImpl filter = new FilterImpl();

		filter.setStates(this.states);
		filter.setCities(this.cities);
		filter.setSegment(this.segment);
		filter.setGenres(this.genres);
		filter.setStartDate(getLocalDate(this.fromDatePicker.getDate()));
		filter.setEndDate(getLocalDate(this.toDatePicker.getDate()));
		
		return filter;
	}
	
	
	public LocalDate getLocalDate(Date date)
	{
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	
	
}

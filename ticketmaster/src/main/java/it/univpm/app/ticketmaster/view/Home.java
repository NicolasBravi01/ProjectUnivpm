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
import org.jdesktop.swingx.JXLabel;

import javax.swing.*;

import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.stats.Stats;

//import org.jdesktop.swingx.JXDatePicker;



import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;

public class Home extends JFrame
{	

	Vector<String> states = new Vector<String>();
	Vector<String> cities = new Vector<String>();
	String segment = "";
	Vector<String> genres = new Vector<String>();
	


	JComboBox<String> statesBox = new JComboBox<String>();
	JComboBox<String> citiesBox = new JComboBox<String>();
	JComboBox<String> segmentsBox = new JComboBox<String>();
	JComboBox<String> genresBox = new JComboBox<String>();
	
		
	JButton searchButton = new JButton("Search");
	JButton resetButton = new JButton("Reset");
	JButton exitButton = new JButton("Exit");
	
	
	JXLabel statesFilterLabel = new JXLabel();
	JXLabel citiesFilterLabel = new JXLabel();
	JXLabel segmentFilterLabel = new JXLabel();
	JXLabel genresFilterLabel = new JXLabel();
	
	JXLabel statesBoxLabel = new JXLabel("STATES");
	JXLabel citiesBoxLabel = new JXLabel("CITIES");
	JXLabel segmentBoxLabel = new JXLabel("SEGMENT");
	JXLabel genresBoxLabel = new JXLabel("GENRES");
	
	JXLabel statesFilterTitleLabel = new JXLabel("STATES");
	JXLabel citiesFilterTitleLabel = new JXLabel("CITIES");
	JXLabel segmentFilterTitleLabel = new JXLabel("SEGMENT");
	JXLabel genresFilterTitleLabel = new JXLabel("GENRES");
	JXLabel periodTitleLabel = new JXLabel("PERIOD");
	
	JXLabel fromDateLabel = new JXLabel("From:");
	JXLabel toDateLabel = new JXLabel("To:");
	
	
	
	JXDatePicker fromDatePicker = new JXDatePicker();
	JXDatePicker toDatePicker = new JXDatePicker();


	
	
	
	public Home()
	{
		this.setTitle("Filtraggio Eventi");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200, 780);
		this.setResizable(false);
		//this.setLayout(new FlowLayout());//poi mettere null
		this.setLayout(null);
		
		
		this.getContentPane().setBackground(new Color(120, 120, 120));
		
		
		//this.pack();
		
		//filtraggio.setSize(1280, 720);
		//filtraggio.setLayout(null);
		this.setLocationRelativeTo(null);
		//filtraggio.getContentPane().setBackground(new Color(30, 30, 30));
		//filtraggio.setVisible(true);
		
		

		
		
		/*
		 * 	LABELS
		 */		
		
		loadLabels();

		statesFilterLabel.setBounds(20, 300, 900, 83);
		citiesFilterLabel.setBounds(20, 410, 900, 83);
		segmentFilterLabel.setBounds(20, 520, 900, 40);
		genresFilterLabel.setBounds(20, 610, 900, 83);
		
		statesFilterTitleLabel.setBounds(20, 278, 90, 21);
		citiesFilterTitleLabel.setBounds(20, 388, 90, 21);
		segmentFilterTitleLabel.setBounds(20, 498, 90, 21);
		genresFilterTitleLabel.setBounds(20, 588, 90, 21);
		
		statesBoxLabel.setBounds(22, 20, 165, 22);
		citiesBoxLabel.setBounds(212, 20, 165, 22);
		segmentBoxLabel.setBounds(407, 20, 165, 22);
		genresBoxLabel.setBounds(607, 20, 165, 22);
		periodTitleLabel.setBounds(890, 20, 165, 22);
		
		fromDateLabel.setBounds(840, 55, 40, 30);
		toDateLabel.setBounds(840, 135, 40, 30);

		
		setFiltersLabelFont(new Font("Calibri", Font.PLAIN, 17));
		setTitlesLabelsFont(new Font("Calibri", Font.BOLD, 19));		
		

		this.statesFilterLabel.setLineWrap(true);
		this.citiesFilterLabel.setLineWrap(true);
		this.segmentFilterLabel.setLineWrap(true);
		this.genresFilterLabel.setLineWrap(true);
		
		setAlignmentFilterLabels(JXLabel.LEFT, JXLabel.TOP);
		setAlignmentFilterTitleLabels(JXLabel.LEFT, JXLabel.TOP);
		
		
		this.add(statesFilterLabel);
		this.add(citiesFilterLabel);
		this.add(segmentFilterLabel);
		this.add(genresFilterLabel);
		
		this.add(statesFilterTitleLabel);
		this.add(citiesFilterTitleLabel);
		this.add(segmentFilterTitleLabel);
		this.add(genresFilterTitleLabel);

		this.add(statesBoxLabel);
		this.add(citiesBoxLabel);
		this.add(segmentBoxLabel);
		this.add(genresBoxLabel);
		this.add(periodTitleLabel);

		this.add(fromDateLabel);
		this.add(toDateLabel);
		
		
		
		
		
		
		
		
		
		/* 
		 *	COMBOBOXES
		 */
				
		statesBox.setBounds(20, 45, 165, 40);
		citiesBox.setBounds(210, 45, 165, 40);
		segmentsBox.setBounds(405, 45, 165, 40);
		genresBox.setBounds(600, 45, 165, 40);
		
		setComboBoxesFont(new Font("Calibri", Font.PLAIN, 16));		
		
		loadBox();
		
		//aggiunge ActionListener delle comboBox per filtrare
		addComboBoxesActionListener();
		
		this.add(statesBox);
		this.add(citiesBox);
		this.add(segmentsBox);
		this.add(genresBox);


		
		
		
		
		

		/*
		 * BUTTONS
		 */
		
		
		searchButton.setBounds(950,500,150,65);
		resetButton.setBounds(950,580,150,65);
		exitButton.setBounds(950,660,150,65);
		
		setButtonsFont(new Font("Calibri", Font.BOLD, 18));
		
		this.add(searchButton);
		this.add(resetButton);
		this.add(exitButton);
		
		
		//aggiunge ActionListener dei bottoni
		addButtonsMouseListener();
		
		
		
		/*
		 * DATEPICKERS
		 */
		fromDatePicker.setDate(Calendar.getInstance().getTime());
		fromDatePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		fromDatePicker.setBounds(880, 55, 200, 30);
		
		//FontDate
		
		toDatePicker.setDate(Calendar.getInstance().getTime());
		toDatePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		toDatePicker.setBounds(880, 135, 200, 30);

		resetPeriod();
				
		this.add(fromDatePicker);
		this.add(toDatePicker);
		
		

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
		
		pan1.add(statesFilterLabel);
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
	
	public void addCityFilter(String city)
	{
		if(!this.cities.contains(city))
			this.cities.add(city);
		else
			this.cities.remove(city);	
	}
	
	
	public void setSegmentFilter(String segment)
	{
		if(!this.segment.equals(segment))
			this.segment = segment;
		else
			this.segment = "";
	}
	
	public void addGenreFilter(String genre)
	{
		if(!this.genres.contains(genre))
			this.genres.add(genre);
		else
			this.genres.remove(genre);
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
		this.statesFilterLabel.setText("All States");
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
	
	
	
	
	

	public void resetPeriod()
	{
		fromDatePicker.setDate(convertToDate(Stats.getFirstDate()));
		toDatePicker.setDate(convertToDate(Stats.getLastDate()));
	}
	
	
	
	
	public void loadstatesFilterLabel()
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
		
		this.statesFilterLabel.setText(text);
	}
	
	
	
	
	public void loadcitiesFilterLabel()
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
		
		this.citiesFilterLabel.setText(text);
	}
	
	
	
	public void loadsegmentFilterLabel()
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
		
		this.segmentFilterLabel.setText(text);
	}
	
	

	public void loadgenresFilterLabel()
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
		
		this.genresFilterLabel.setText(text);
	}
	
	
	
	
	public void loadLabels()
	{
		loadstatesFilterLabel();
		loadcitiesFilterLabel();
		loadsegmentFilterLabel();
		loadgenresFilterLabel();
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
		    	
		    	loadstatesFilterLabel();
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
		    	
		    	loadcitiesFilterLabel();
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
		    	
		    	loadsegmentFilterLabel();
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
		    	
		    	loadgenresFilterLabel();
		    }
		});  
		
	}
	
	
	
	
	
	public void addButtonsMouseListener()
	{
		addExitButtonMouseListener();
		addResetButtonMouseListener();
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
				
				Vector<Event> events = new Vector<Event>();
				events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
				
				
				if(events.size() == 0)
				{
					JOptionPane.showMessageDialog(null, "There aren't events with your filters", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					visible(false);
					new Result(getThis(), events);
				}
				
				
				
			}
		});
	}
	
	
	
	
	public void addResetButtonMouseListener()
	{
		resetButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				resetFilters();
				loadLabels();
			}
		});
	}
	
	
	
	
	
	public void resetFilters()
	{
		addAllStatesFilter();
		addAllCitiesFilter();
		addAllSegmentsFilter();
		addAllGenresFilter();
		resetPeriod();		
	}
	
	
	
	
	
	/*
	 * Metodo che permette di modificare la visibilità della finestra, fatto
	 * perchè all'interno dell'actionListener, l'oggetto this non è accessibile
	 */
	public void visible(boolean visible)
	{
		this.setVisible(visible);
	}
	
	
	/*
	 * Metodo che ritorna l'oggetto stesso, fatto perchè all'interno
	 * dell'actionListener, l'oggetto this non è accessibile
	 */
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
		filter.setStartDate(convertToLocalDate(this.fromDatePicker.getDate()));
		filter.setEndDate(convertToLocalDate(this.toDatePicker.getDate()));
		
		return filter;
	}
	
	
	public LocalDate convertToLocalDate(Date date)
	{
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public Date convertToDate(LocalDate date)
	{
	    return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
	
	
	
	
	public void setFiltersLabelFont(Font font)
	{
		statesFilterLabel.setFont(font);
		citiesFilterLabel.setFont(font);
		segmentFilterLabel.setFont(font);
		genresFilterLabel.setFont(font);
	}
	
	
	public void setTitlesLabelsFont(Font font)
	{		
		statesFilterTitleLabel.setFont(font);
		citiesFilterTitleLabel.setFont(font);
		segmentFilterTitleLabel.setFont(font);
		genresFilterTitleLabel.setFont(font);
		
		statesBoxLabel.setFont(font);
		citiesBoxLabel.setFont(font);
		segmentBoxLabel.setFont(font);
		genresBoxLabel.setFont(font);;
		periodTitleLabel.setFont(font);
	}
	
	
	public void setComboBoxesFont(Font font)
	{
		statesBox.setFont(font);
		citiesBox.setFont(font);
		segmentsBox.setFont(font);
		genresBox.setFont(font);
	}
	
	
	public void setButtonsFont(Font font)
	{
		searchButton.setFont(font);
		resetButton.setFont(font);
		exitButton.setFont(font);
	}
	
	
	
	
	
	
	public void setAlignmentFilterLabels(int horizontal, int vertical)
	{
		this.statesFilterLabel.setHorizontalAlignment(horizontal);
		this.statesFilterLabel.setVerticalAlignment(vertical);		
		
		this.citiesFilterLabel.setHorizontalAlignment(horizontal);
		this.citiesFilterLabel.setVerticalAlignment(vertical);		
		
		this.segmentFilterLabel.setHorizontalAlignment(horizontal);
		this.segmentFilterLabel.setVerticalAlignment(vertical);		
		
		this.genresFilterLabel.setHorizontalAlignment(horizontal);
		this.genresFilterLabel.setVerticalAlignment(vertical);				
	}
	
	
	public void setAlignmentFilterTitleLabels(int horizontal, int vertical)
	{
		this.statesFilterTitleLabel.setHorizontalAlignment(horizontal);
		this.statesFilterLabel.setVerticalAlignment(vertical);		
		
		this.citiesFilterTitleLabel.setHorizontalAlignment(horizontal);
		this.citiesFilterTitleLabel.setVerticalAlignment(vertical);		
		
		this.segmentFilterTitleLabel.setHorizontalAlignment(horizontal);
		this.segmentFilterTitleLabel.setVerticalAlignment(vertical);		
		
		this.genresFilterTitleLabel.setHorizontalAlignment(horizontal);
		this.genresFilterTitleLabel.setVerticalAlignment(vertical);				
	}
	
	
}

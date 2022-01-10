package it.univpm.app.ticketmaster.view;

import java.util.Vector;
import java.util.Collections;

import java.awt.BorderLayout;
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
import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.NoEventsException;
import it.univpm.app.ticketmaster.exception.NullDateException;

//import org.jdesktop.swingx.JXDatePicker;



import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;

@SuppressWarnings("serial")
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
	
		
	JButton searchButton = new JButton("SEARCH");
	JButton resetButton = new JButton("RESET");
	JButton exitButton = new JButton("EXIT");
	
	
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
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setLayout(null);		
		this.getContentPane().setBackground(new Color(133,173,225));
		this.setLocationRelativeTo(null);
		
		

		
		
		/*
		 * 	LABELS
		 */		
		
		loadLabels();

		statesFilterLabel.setBounds(35, 310, 920, 83);
		citiesFilterLabel.setBounds(35, 430, 920, 83);
		segmentFilterLabel.setBounds(35, 550, 920, 40);
		genresFilterLabel.setBounds(35, 650, 920, 83);
		
		statesFilterTitleLabel.setBounds(35, 288, 90, 21);
		citiesFilterTitleLabel.setBounds(35, 408, 90, 21);
		segmentFilterTitleLabel.setBounds(35, 523, 90, 21);
		genresFilterTitleLabel.setBounds(35, 628, 90, 21);
		
		statesBoxLabel.setBounds(45, 25, 165, 22);
		citiesBoxLabel.setBounds(246, 25, 165, 22);
		segmentBoxLabel.setBounds(447, 25, 165, 22);
		genresBoxLabel.setBounds(651, 25, 165, 22);
		periodTitleLabel.setBounds(960, 25, 165, 22);
		
		fromDateLabel.setBounds(881, 54, 55, 40);
		toDateLabel.setBounds(902, 135, 30, 30);
		
		setFiltersLabelFont(new Font("Calibri", Font.PLAIN, 17));
		setTitlesLabelsFont(new Font("Calibri", Font.BOLD, 20));
		setDatesLabelFont(new Font("Calibri", Font.PLAIN, 19));

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
				
		statesBox.setBounds(40, 50, 165, 40);
		citiesBox.setBounds(240, 50, 165, 40);
		segmentsBox.setBounds(445, 50, 165, 40);
		genresBox.setBounds(650, 50, 165, 40);
		
		setComboBoxesFont(new Font("Calibri", Font.PLAIN, 17));		
		
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
		
		searchButton.setBounds(983,455,150,65);
		resetButton.setBounds(983,545,150,65);
		exitButton.setBounds(983,635,150,65);
		
		setButtonsFont(new Font("Calibri", Font.BOLD, 25));
		
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
		fromDatePicker.setBounds(935, 57, 170, 30);
				
		toDatePicker.setDate(Calendar.getInstance().getTime());
		toDatePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		toDatePicker.setBounds(935, 135, 170, 30);
		
		setDatePickersFont(new Font("Calibri", Font.PLAIN, 17));

		resetPeriod();
				
		this.add(fromDatePicker);
		this.add(toDatePicker);
		
	
		
		
		
		this.setVisible(true);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void loadBox(JComboBox<String> box, Vector<String> list)
	{
		Collections.sort(list);
		
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
		{			
			int i=0;
			while(i<states.size() && state.compareTo(states.get(i))>0)
				i++;
			
			this.states.add(i ,state);
			//this.states.add(state);
		}
		//rimuovi
		else
			this.states.remove(state);			
	}
	
	public void addCityFilter(String city)
	{
		if(!this.cities.contains(city))
		{
			int i=0;
			while(i<cities.size() && city.compareTo(cities.get(i))>0)
				i++;
			
			this.cities.add(i ,city);
			//this.cities.add(city);
		}
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
		{
			int i=0;
			while(i<genres.size() && genre.compareTo(genres.get(i))>0)
				i++;
			
			this.genres.add(i ,genre);
			//this.genres.add(genre);
		}
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
		fromDatePicker.setDate(convertToDate(EventsFilter.getFirstDate()));
		toDatePicker.setDate(convertToDate(EventsFilter.getLastDate()));
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
				Filter filter;
				Vector<Event> events;
				Result result = null;
				
				try
				{				
					filter = readFilter();
	
					events = EventsFilter.getFilteredEvents(filter);
					
					if(events.size() == 0)
						throw new NoEventsException();
					
					visible(false);
					result = new Result(getThis(), filter, events);
				}
				catch(IncorrectOrderOfDatesException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
				}
				catch(NoEventsException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception e)
				{
					if(result != null)
						result.close();					
					visible(true);
					
					JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);					
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
	
	
	
	
	public Filter readFilter() throws IncorrectOrderOfDatesException
	{
		Filter filter = new Filter();

		filter.setStates(this.states);
		filter.setCities(this.cities);
		filter.setSegment(this.segment);
		filter.setGenres(this.genres);
		
		filter.setStartDate(convertToLocalDate(this.fromDatePicker.getDate()));
		filter.setEndDate(convertToLocalDate(this.toDatePicker.getDate()));
		
		if(filter.getStartDate().isAfter(filter.getEndDate()))
			throw new IncorrectOrderOfDatesException();
		
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
	
	public void setDatePickersFont(Font font)
	{
		fromDatePicker.setFont(font);
		toDatePicker.setFont(font);
	}
	
	
	public void setDatesLabelFont(Font font)
	{
		fromDateLabel.setFont(font);
		toDateLabel.setFont(font);
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

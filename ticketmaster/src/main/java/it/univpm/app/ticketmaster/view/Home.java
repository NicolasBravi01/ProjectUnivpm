package it.univpm.app.ticketmaster.view;

import java.util.Vector;
import java.util.Collections;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXLabel;


import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.NoEventsException;



import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;

@SuppressWarnings("serial")
public class Home extends JFrame
{	
	/**
	 * Oggetto Filter nel quale vengono memorizzati i filtri inseriti dall'utente
	 * tramite l'interfaccia grafica 
	 */
	Filter filter = new Filter();
	
	
	/*
	 * ComboBoxes per stati, città, segmento e generi 
	 */
	JComboBox<String> statesBox = new JComboBox<String>();
	JComboBox<String> citiesBox = new JComboBox<String>();
	JComboBox<String> segmentsBox = new JComboBox<String>();
	JComboBox<String> genresBox = new JComboBox<String>();
		
	
	/*
	 * Bottoni Search, reset, exit. Guardare i rispettivi eventi MouseListener per capire cosa fanno
	 */
	JButton searchButton = new JButton("SEARCH");
	JButton resetButton = new JButton("RESET");
	JButton exitButton = new JButton("EXIT");
	
	
	/*
	 * Label di tutta la finestra
	 */
	JXLabel statesFilterLabel = new JXLabel();
	JXLabel citiesFilterLabel = new JXLabel();
	JXLabel segmentFilterLabel = new JXLabel();
	JXLabel genresFilterLabel = new JXLabel();
	
	JXLabel statesBoxLabel = new JXLabel("STATES");
	JXLabel citiesBoxLabel = new JXLabel("CITIES");
	JXLabel segmentBoxLabel = new JXLabel("SEGMENT");
	JXLabel genresBoxLabel = new JXLabel("GENRES");	
	JXLabel periodTitleLabel = new JXLabel("PERIOD");
	
	JXLabel statesFilterTitleLabel = new JXLabel("STATES");
	JXLabel citiesFilterTitleLabel = new JXLabel("CITIES");
	JXLabel segmentFilterTitleLabel = new JXLabel("SEGMENT");
	JXLabel genresFilterTitleLabel = new JXLabel("GENRES");
	
	JXLabel fromDateLabel = new JXLabel("From:");
	JXLabel toDateLabel = new JXLabel("To:");
	
	/*
	 * Calendari
	 */
	JXDatePicker fromDatePicker = new JXDatePicker();
	JXDatePicker toDatePicker = new JXDatePicker();


	
	
	/**
	 *  Costruttore della finestra principale con cui è possibile settare i filtri per la visualizzazione degli eventi
	 */
	public Home()
	{
		/*
		 * Impostazioni di settaggio della finestra
		 */
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
		
		loadBoxes();
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 *  Metodo che inserisce gli elementi di una lista di stringhe all'interno di una data comboBox
	 *  
	 *  @param box ComboBox di stringhe
	 *  @param list Lista di stringhe
	 */
	public void loadBox(JComboBox<String> box, Vector<String> list)
	{
		Collections.sort(list);
		
		box.addItem("-ALL-");
		
		for(int i = 0; i<list.size(); i++)
		{
			box.addItem(list.get(i));
		}
	}
	
	
	/**
	 * Metodo che inserisce stati, città, segmenti e generi disponibili, attraverso il quale l'utente
	 * può decidere di filtrare gli eventi, all'interno delle rispettive comboBoxes
	 * 
	 * @see loadBox
	 */
	public void loadBoxes()
	{
		loadBox(statesBox, EventsFilter.getStates());
		loadBox(citiesBox, EventsFilter.getCities());
		loadBox(segmentsBox, EventsFilter.getSegments());
		loadBox(genresBox, EventsFilter.getGenres());
	}

	
	
	/**
	 * Metodo che aggiunge alla lista di stati, presente all'interno dell'oggetto filter, un certo stato
	 * passato come parametro. Se esso è già presente nella lista allora viene rimosso
	 * 
	 * @param state Stringa contenente uno stato
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void addStateFilter(String state)
	{
		if(!this.filter.getStates().contains(state))
		{			
			int i=0;
			while(i < this.filter.getStates().size() && state.compareTo(this.filter.getStates().get(i)) > 0)
				i++;
			
			this.filter.getStates().add(i ,state);
		}
		else
			this.filter.getStates().remove(state);		
	}
	
	
	/**
	 * Metodo che aggiunge alla lista di città, presente all'interno dell'oggetto filter, una certa città
	 * passata come parametro. Se essa è già presente nella lista allora viene rimossa
	 * 
	 * @param city Stringa contenente una città
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void addCityFilter(String city)
	{
		if(!this.filter.getCities().contains(city))
		{
			int i=0;
			while(i < this.filter.getCities().size() && city.compareTo(this.filter.getCities().get(i))>0)
				i++;
			
			this.filter.getCities().add(i ,city);
		}
		else
			this.filter.getCities().remove(city);	
	}
	
	
	/**
	 * Metodo setta la variabile rappresentante un segmento, presente all'interno dell'oggetto filter, un certo
	 * segmento passato come parametro. Se esso è lo stesso del precedente, allora il filtro per segmento viene rimosso
	 * 
	 * @param segment Stringa contenente un segmento
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void setSegmentFilter(String segment)
	{
		if(!this.filter.getSegment().equals(segment))
			this.filter.setSegment(segment);
		else
			this.filter.setSegment("");
	}
	
	
	/**
	 * Metodo che aggiunge alla lista di generi, presente all'interno dell'oggetto filter, un certo genere
	 * passato come parametro. Se esso è già presente nella lista allora viene rimosso
	 * 
	 * @param genre Stringa contenente un genere
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void addGenreFilter(String genre)
	{
		if(!this.filter.getGenres().contains(genre))
		{
			int i=0;
			while(i < this.filter.getGenres().size() && genre.compareTo(this.filter.getGenres().get(i))>0)
				i++;
			
			this.filter.getGenres().add(i ,genre);
		}
		else
			this.filter.getGenres().remove(genre);
	}
	
	
	
	
	
	/**
	 * Metodo che resetta il filtro per stati
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter.isIncludedState(String) 
	 */
	public void removeAllStatesFilter()
	{
		this.filter.getStates().removeAllElements();
	}
	
	
	
	/**
	 * Metodo che resetta il filtro per città
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter.isIncludedCity(String) 
	 */
	public void removeAllCitiesFilter()
	{
		this.filter.getCities().removeAllElements();
	}
	
	
	
	
	/**
	 * Metodo che resetta il filtro per segmento
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter.isIncludedSegment(String) 
	 */
	public void removeAllSegmentsFilter()
	{
		this.filter.setSegment("");
	}
	
	
	
	
	/**
	 * Metodo che resetta il filtro per generi
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter.isIncludedGenres(String) 
	 */
	public void removeAllGenresFilter()
	{
		this.filter.getGenres().removeAllElements();
	}
	
	
	
	
	
	/**
	 * Metodo che resetta il filtro per periodo, facendo visualizzare nel primo calendario la data del
	 * primo evento e nel secondo calendario la data dell'ultimo evento in ordine cronologico
	 */
	public void resetPeriod()
	{
		this.fromDatePicker.setDate(convertToDate(EventsFilter.getFirstDate()));
		this.toDatePicker.setDate(convertToDate(EventsFilter.getLastDate()));
	}
	
	
	
	/**
	 * Metodo che fa visualizzare a schermo la lista degli stati attraverso i quali filtrare gli eventi
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void loadStatesFilterLabel()
	{		
		String text;
				
		if(this.filter.getStates().isEmpty())
		{
			text = "All states";
		}
		else
		{
			text = this.filter.getStates().toString();
			text = text.substring(1, text.length() - 1);
		}
		
		this.statesFilterLabel.setText(text);
	}
	
	
	
	/**
	 * Metodo che fa visualizzare a schermo la lista delle città attraverso le quali filtrare gli eventi
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void loadCitiesFilterLabel()
	{
		String text;
		
		if(this.filter.getCities().isEmpty())
		{
			text = "All cities";
		}
		else
		{
			text = this.filter.getCities().toString();
			text = text.substring(1, text.length() - 1);
		}		
		
		this.citiesFilterLabel.setText(text);
	}
	
	
	/**
	 * Metodo che fa visualizzare a schermo il segmento attraverso il quale filtrare gli eventi
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void loadSegmentFilterLabel()
	{
		String text;
		
		if(this.filter.getSegment().isEmpty())
		{
			text = "All segments";
		}
		else
		{
			text = this.filter.getSegment();
		}		
		
		this.segmentFilterLabel.setText(text);
	}
	
	
	/**
	 * Metodo che fa visualizzare a schermo la lista dei generi attraverso i quali filtrare gli eventi
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void loadGenresFilterLabel()
	{
		String text;
		
		if(this.filter.getGenres().isEmpty())
		{
			text = "All genres";
		}
		else
		{
			text = this.filter.getGenres().toString();
			text = text.substring(1, text.length() - 1);
		}		
		
		this.genresFilterLabel.setText(text);
	}
	
	
	
	/**
	 * Metodo che fa visualizzare a schermo tutti i filtri con cui filtrare gli eventi
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void loadLabels()
	{
		loadStatesFilterLabel();
		loadCitiesFilterLabel();
		loadSegmentFilterLabel();
		loadGenresFilterLabel();
	}
	
	
	
	/**
	 * Metodo che aggiunge le ActionListener per le comboBoxes di stati, città, generi e segmenti
	 */
	public void addComboBoxesActionListener()
	{
		addStatesBoxActionListener();
		addCitiesBoxActionListener();
		addSegmentsBoxActionListener();
		addGenresBoxActionListener();
	}
	
	
	/**
	 * Metodo che aggiunge l'ActionListener per la comboBox relativa agli stati
	 */
	public void addStatesBoxActionListener()
	{
		this.statesBox.addActionListener (new ActionListener () 
		{
			@Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(statesBox.getSelectedIndex() == 0)
		    		removeAllStatesFilter();
		    	else
		    		addStateFilter(statesBox.getSelectedItem().toString());
		    	
		    	loadStatesFilterLabel();
		    }
		});
	}
	
	

	/**
	 * Metodo che aggiunge l'ActionListener per la comboBox relativa alle città
	 */
	public void addCitiesBoxActionListener()
	{
		this.citiesBox.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(citiesBox.getSelectedIndex() == 0)
		    		removeAllCitiesFilter();
		    	else
		    		addCityFilter(citiesBox.getSelectedItem().toString());
		    	
		    	loadCitiesFilterLabel();
		    }
		});
	}
	
	

	/**
	 * Metodo che aggiunge l'ActionListener per la comboBox relativa ai segmenti
	 */
	public void addSegmentsBoxActionListener()
	{		
		this.segmentsBox.addActionListener(new ActionListener() 
		{
			@Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(segmentsBox.getSelectedIndex() == 0)
		    		removeAllSegmentsFilter();
		    	else
		    		setSegmentFilter(segmentsBox.getSelectedItem().toString());
		    	
		    	loadSegmentFilterLabel();
		    }
		});
	}

	
	

	/**
	 * Metodo che aggiunge l'ActionListener per la comboBox relativa ai generi
	 */
	public void addGenresBoxActionListener()
	{
		this.genresBox.addActionListener(new ActionListener() 
		{
			@Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	if(genresBox.getSelectedIndex() == 0)
		    		removeAllGenresFilter();
		    	else
		    		addGenreFilter(genresBox.getSelectedItem().toString());
		    	
		    	loadGenresFilterLabel();
		    }
		});  
		
	}
	
	
	
	

	/**
	 * Metodo che aggiunge i MouseListener dei bottoni Search, Reset e Exit
	 */
	public void addButtonsMouseListener()
	{
		addExitButtonMouseListener();
		addResetButtonMouseListener();
		addSearchButtonMouseListener();
	}
	
	
	
	/**
	 * Metodo che aggiunge il MouseListener del bottone Exit.
	 * Se il bottone viene premuto, termina il debug
	 */
	public void addExitButtonMouseListener()
	{
		this.exitButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				System.exit(NORMAL);
			}
		});
	}
	
	
	

	/**
	 * Metodo che aggiunge il MouseListener del bottone Search.
	 * Se il bottone viene premuto, viene mostrata la finestra contenente gli eventi filtrare
	 * mentre questa finestra viene nascosta.
	 * In caso di errori la finestra non si apre
	 * 
	 * @see it.univpm.app.ticketmaster.view.Result
	 * 
	 */
	public void addSearchButtonMouseListener()
	{
		this.searchButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				Vector<Event> events;
				Result result = null;
				
				try
				{				
					readPeriod();
	
					events = EventsFilter.getFilteredEvents(filter);
					
					if(events.isEmpty())
						throw new NoEventsException("There are not events with your filters");
					
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
	
	
	

	/**
	 * Metodo che aggiunge il MouseListener del bottone Reset.
	 * Se il bottone viene premuto, Tutti i filtri vengono resettati
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 */
	public void addResetButtonMouseListener()
	{
		this.resetButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				filter.reset();
				loadLabels();
			}
		});
	}
	
	
	
	
	
	/**
	 * Metodo che permette di modificare la visibilità della finestra.
	 * Utilizzato per la programmazione ad eventi poichè, nei Listener, l'oggetto this non è accessibile
	 */
	public void visible(boolean visible)
	{
		this.setVisible(visible);
	}
	
	
	/**
	 * Metodo che ritorna l'oggetto stesso.
	 * Utilizzato per la programmazione ad eventi poichè, nei Listener, l'oggetto this non è accessibile
	 */
	public Home getThis()
	{
		return this;
	}
	
	
	
	/**
	 * Metodo che legge dai JXDatePicker le date e le inserisce negli appositi spazi all'interno
	 * dell'oggetto Filter
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * 
	 * @throws IncorrectOrderOfDatesException
	 */
	public void readPeriod() throws IncorrectOrderOfDatesException
	{
		this.filter.setStartDate(convertToLocalDate(this.fromDatePicker.getDate()));
		this.filter.setEndDate(convertToLocalDate(this.toDatePicker.getDate()));
		
		this.filter.checkPeriod();
	}
	
	
	/**
	 * Metodo che converte una data di tipo Date in LocalDate
	 * 
	 * @param date Date
	 * 
	 * @return date LocalDate
	 */
	public LocalDate convertToLocalDate(Date date)
	{
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	
	/**
	 * Metodo che converte una data di tipo LocalDate in Date
	 * 
	 * @param date Date
	 * 
	 * @return date LocalDate
	 */
	public Date convertToDate(LocalDate date)
	{
	    return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
	
	/**
	 * Metodo che setta il font passato come parametro per le Label in cui vengono scritti i filtri
	 * per stati, città, segmento e generi
	 * 
	 * @param font Font
	 */
	public void setFiltersLabelFont(Font font)
	{
		this.statesFilterLabel.setFont(font);
		this.citiesFilterLabel.setFont(font);
		this.segmentFilterLabel.setFont(font);
		this.genresFilterLabel.setFont(font);
	}
	
	
	
	/**
	 * Metodo che setta il font passato come parametro per le Label in cui vengono scritti i titoli
	 * dei filtri stati, città, segmento e generi
	 * 
	 * @param font Font
	 */
	public void setTitlesLabelsFont(Font font)
	{		
		this.statesFilterTitleLabel.setFont(font);
		this.citiesFilterTitleLabel.setFont(font);
		this.segmentFilterTitleLabel.setFont(font);
		this.genresFilterTitleLabel.setFont(font);
		
		this.statesBoxLabel.setFont(font);
		this.citiesBoxLabel.setFont(font);
		this.segmentBoxLabel.setFont(font);
		this.genresBoxLabel.setFont(font);;
		this.periodTitleLabel.setFont(font);
	}
	
	
	
	/**
	 * Metodo che setta il font passato come parametro per le comboBoxes utilizzate per la selezione
	 * dei filtri stati, città, segmento e generi
	 * 
	 * @param font Font
	 */
	public void setComboBoxesFont(Font font)
	{
		this.statesBox.setFont(font);
		this.citiesBox.setFont(font);
		this.segmentsBox.setFont(font);
		this.genresBox.setFont(font);
	}
	
	
	/**
	 * Metodo che setta il font passato come parametro per i bottoni Search, Reset e Exit
	 * 
	 * @param font Font
	 */
	public void setButtonsFont(Font font)
	{
		this.searchButton.setFont(font);
		this.resetButton.setFont(font);
		this.exitButton.setFont(font);
	}
	
	
	/**
	 * Metodo che setta il font passato come parametro per i calendari in cui vengono inserite
	 * la data iniziale e finale di un periodo
	 * 
	 * @param font Font
	 */
	public void setDatePickersFont(Font font)
	{
		this.fromDatePicker.setFont(font);
		this.toDatePicker.setFont(font);
	}
	
	
	/**
	 * Metodo che setta il font passato come parametro per le Label vicine ai calendari
	 * 
	 * @param font Font
	 */
	public void setDatesLabelFont(Font font)
	{
		this.fromDateLabel.setFont(font);
		this.toDateLabel.setFont(font);
	}
	
	
	/**
	 * Metodo che setta l'allineamento orizzontale e verticale delle label in cui vengono visualizzati i filtri
	 * 
	 * @param horizontal
	 * @param vertical
	 */
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
	
	
	
	/**
	 * Metodo che setta l'allineamento orizzontale e verticale delle label con i titoli dei filtri
	 * 
	 * @param horizontal
	 * @param vertical
	 */
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

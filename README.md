
# Progetto Oriented Object Programming su Ticketmaster

![Ticketmaster](https://ichef.bbci.co.uk/news/976/cpsprodpb/136AB/production/_116313597_ticketmaster.jpg "Ticketmaster")

## Indice

* [Introduzione](#id-section1)
* [Installazione ed utilizzo](#id-section2)
* [Rotte](#id-section3)
* [Autori](#id-section4)

<div id='id-section1'/>

## Introduzione

L'applicazione SpringBoot utilizza il sito di Ticketmaster, un software che si occupa della gestione e della prenotazione di eventi sportivi, musicali, teatrali, cinematografici ed artistici, reperibile presso l’indirizzo [Ticketmaster](https://ticketmaster.com). In particolare, attraverso le Application Programming Interface, comunemente note come `api`, derivate dalla pagina TM Developer e accessibili mediante l’indirizzo [Ticketmaster Api V2](https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/), il programma valuterà gli eventi che si verificheranno negli Stati Uniti d'America. L'utente può, in base alle sue preferenze, imporre dei filtri per poter visualizzare gli eventi di interesse o le statistiche relative agli eventi.

<div id='id-section2'/>

## Installazione ed utilizzo

Per poter accedere al programma è necessario clonare la repository in locale utilizzando Github Desktop oppure da terminale con il comando
`git clone https://github.com/NicolasBravi01/ProjectUnivpm.git`.
Successivamente sarà possibile mandare in esecuzione il programma con un IDE (ad esempio Eclipse) o direttamente da terminale.

In seguito l'utente deve accedere alla cartella resources, reperibile attraverso il percorso `ProjectUnivpm\ticketmaster\src\main\resources`, e creare il file **apiKey.txt**,  dentro il quale è necessario inserire la propria `apiKey`, ottenuta in seguito all'autenticazione sul portale TM Developer ([Ticketmaster Api V2](https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/)).

Dopodiché l'utente può scegliere se utilizzare il programma con le rotte (che verranno elencate e spiegate nel paragrafo successivo) sul client [Postman](https://www.postman.com/), che permettono di visualizzare anche le statistiche ed altre informazioni relative agli eventi, oppure tramite la Graphic User Interface implementata all'interno del progetto (che verrà spiegata nel paragrafo successivo alle rotte), che consente unicamente di ottenere la lista deglie eventi filtrata dall'utente.

<div id='id-section3'/>

## Rotte

Le rotte si dividono in 3 categorie in base a ciò che restituiscono, ma prima di analizzarle è importante comprendere il significato dei parametri:
- `states` è una stringa contenente i nomi di uno o più stati
- `cities` è una stringa contenente i nomi di uno o più città
- `period` è una stringa contenente il periodo (data iniziale e data finale scritte in formato LocalDate e separate da una virgola)
- `segment` è una stringa contente il nome del segmento
- `states` è una stringa contenente i nomi di uno o più generi

### Rotte /events

Le rotte **/events** permettono di ottenere la lista di tutti gli eventi, che possono essere raggruppati per stati, città, segmenti o generi, in base alla scelta della rotta da parte dell'utente. 

I parametri inseriti dall'utente servono per filtrare la lista di tutti gli eventi e visualizzare solo quelli di interesse (ad esempio solo quelli che hanno luogo in alcuni stati o che fanno parte di un certo segmento; inoltre si può selezionare anche il periodo di interesse per filtrare gli eventi e visualizzare solo quelli che ricadono in questo arco temporale. Se i parametri di filtraggio non vengono inseriti non viene effettuato alcun filtro, e dunque verranno restituiti tutti gli eventi, raggruppati o meno a seconda della rotta.

| Tipo    | Rotta /events                            | Descrizione                                                         | Parametri                                   |
|---------|------------------------------------------|---------------------------------------------------------------------|---------------------------------------------|
| ` get ` | [`/events`](#/events)                    | Restituisce l'elenco di tutti gli eventi                            |`states`,`cities`,`period`,`segment`,`genres`|
| ` get ` | [`/events/states`](#/events/states)      | Restituisce l'elenco di tutti gli eventi raggruppati per stati      | `period`                                    |
| ` get ` | [`/events/cities`](#/events/cities)      | Restituisce l'elenco di tutti gli eventi raggruppati per città      | `states`,`period`                           |  
| ` get ` | [`/events/segments`](#/events/segments)  | Restituisce l'elenco di tutti gli eventi raggruppati per segmenti   | `period`                                    |
| ` get ` | [`/events/genres`](#/events/genres)      | Restituisce l'elenco di tutti gli eventi raggruppati per generi     | `period`,`segment`                          |              

#### /events

La rotta `/events` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "list events": [
    {
        "name":"Phoenix Suns vs. Miami Heat",
        "url":"https://www.ticketmaster.com/phoenix-suns-vs-miami-heat-phoenix-arizona-01-08-2022/event/19005B134A043F86",
        "localTime":"19:00:00",
        "localDate":"2022-01-08",
        "venue":"Footprint Center",
        "city":"Phoenix",
        "state":"Arizona",
        "segment":"Sports",
        "genre":"Basketball"
     },
     {
        "name":"Indiana Pacers vs. Phoenix Suns",
        "url":"https://www.ticketmaster.com/indiana-pacers-vs-phoenix-suns-indianapolis-indiana-01-14-2022/event/05005B10EE583544",
        "localTime":"19:00:00",
        "localDate":"2022-01-14",
        "venue":"Gainbridge Fieldhouse",
        "city":"Indianapolis",
        "state":"Indiana"
        ,"segment":"Sports",
        "genre":"Basketball"
      },
      {
         "name":"Golden State Warriors vs. Phoenix Suns",
         "url":"https://www.ticketmaster.com/golden-state-warriors-vs-phoenix-suns-san-francisco-california-03-30-2022/event/1C005B12A59E3CBB",
         "localTime":"19:00:00",
         "localDate":"2022-03-30",
         "venue":"Chase Center",
         "city":"San Francisco",
         "state":"California",
         "segment":"Sports",
         "genre":"Basketball"
       },
       {
       },
   ],
   "number events": 200
}
```

#### /events/states

La rotta `/events/states` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "North Carolina": {
        "list events": [
            {
                "name": "Charlotte Hornets vs. Los Angeles Lakers",
                "url": "https://www.ticketmaster.com/charlotte-hornets-vs-los-angeles-lakers-charlotte-north-carolina-01-28-2022/event/2D005B0297F52EB4",
                "localTime": "19:00:00",
                "localDate": "2022-01-28",
                "venue": "Spectrum Center ",
                "city": "Charlotte",
                "state": "North Carolina",
                "segment": "Sports",
                "genre": "Basketball"
            },
            {
                "name": "Eagles",
                "url": "https://www.ticketmaster.com/eagles-charlotte-north-carolina-02-21-2022/event/2D005B768B2A1822",
                "localTime": "20:00:00",
                "localDate": "2022-02-21",
                "venue": "Spectrum Center ",
                "city": "Charlotte",
                "state": "North Carolina",
                "segment": "Music",
                "genre": "Rock"
            },
            {
                "name": "Imagine Dragons: Mercury World Tour",
                "url": "https://www.ticketmaster.com/imagine-dragons-mercury-world-tour-raleigh-north-carolina-02-10-2022/event/2D005B1FFA456D3F",
                "localTime": "19:00:00",
                "localDate": "2022-02-10",
                "venue": "PNC Arena",
                "city": "Raleigh",
                "state": "North Carolina",
                "segment": "Music",
                "genre": "Rock"
            },
            {
            }
        ],
        "number events": 4
    },
    "Oregon": {
        "list events": [
            {
                "name": "Imagine Dragons: Mercury World Tour",
                "url": "https://www.ticketmaster.com/imagine-dragons-mercury-world-tour-portland-oregon-03-09-2022/event/0F005B21CC2C3FC5",
                "localTime": "19:00:00",
                "localDate": "2022-03-09",
                "venue": "Moda Center",
                "city": "Portland",
                "state": "Oregon",
                "segment": "Music",
                "genre": "Rock"
            },
            {
                "name": "Portland Trail Blazers vs. Los Angeles Lakers",
                "url": "https://www.ticketmaster.com/portland-trail-blazers-vs-los-angeles-portland-oregon-02-09-2022/event/0F005AFA84301C48",
                "localTime": "19:00:00",
                "localDate": "2022-02-09",
                "venue": "Moda Center",
                "city": "Portland",
                "state": "Oregon",
                "segment": "Sports",
                "genre": "Basketball"
            }
        ],
        "number events": 2
    },
    {
    },
 }    
```

#### /events/cities

La rotta `/events/cities` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "Milwaukee": {
        "list events": [
            {
                "name": "Milwaukee Bucks vs. Phoenix Suns",
                "url": "https://www.ticketmaster.com/milwaukee-bucks-vs-phoenix-suns-milwaukee-wisconsin-03-06-2022/event/07005B1A9CEA148A",
                "localTime": "14:30:00",
                "localDate": "2022-03-06",
                "venue": "Fiserv Forum",
                "city": "Milwaukee",
                "state": "Wisconsin",
                "segment": "Sports",
                "genre": "Basketball"
            },
            {
                "name": "Imagine Dragons: Mercury World Tour",
                "url": "https://www.ticketmaster.com/imagine-dragons-mercury-world-tour-milwaukee-wisconsin-02-25-2022/event/07005B229B442C9C",
                "localTime": "19:00:00",
                "localDate": "2022-02-25",
                "venue": "Fiserv Forum",
                "city": "Milwaukee",
                "state": "Wisconsin",
                "segment": "Music",
                "genre": "Rock"
            },
            {
                "name": "Milwaukee Bucks vs. Toronto Raptors",
                "url": "https://www.ticketmaster.com/milwaukee-bucks-vs-toronto-raptors-milwaukee-wisconsin-01-15-2022/event/07005B1A9CCB143A",
                "localTime": "17:30:00",
                "localDate": "2022-01-15",
                "venue": "Fiserv Forum",
                "city": "Milwaukee",
                "state": "Wisconsin",
                "segment": "Sports",
                "genre": "Basketball"
            },
            {
            }
        ],
        "number events": 5
    },
    "Oklahoma City": {
        "list events": [
            {
                "name": "Oklahoma City Thunder vs. Phoenix Suns",
                "url": "https://www.ticketmaster.com/oklahoma-city-thunder-vs-phoenix-suns-oklahoma-city-oklahoma-02-24-2022/event/0C005B1343215F1F",
                "localTime": "19:00:00",
                "localDate": "2022-02-24",
                "venue": "Paycom Center",
                "city": "Oklahoma City",
                "state": "Oklahoma",
                "segment": "Sports",
                "genre": "Basketball"
            },
            {
                "name": "Oklahoma City Thunder vs. Phoenix Suns",
                "url": "https://www.ticketmaster.com/oklahoma-city-thunder-vs-phoenix-suns-oklahoma-city-oklahoma-04-03-2022/event/0C005B1343425F3C",
                "localTime": "18:00:00",
                "localDate": "2022-04-03",
                "venue": "Paycom Center",
                "city": "Oklahoma City",
                "state": "Oklahoma",
                "segment": "Sports",
                "genre": "Basketball"
            }
        ],
        "number events": 2
    },
    {
    },
 }    
```

#### /events/segments

La rotta `/events/segments` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "Miscellaneous": {
        "list events": [
            {
                "name": "Disney On Ice presents Dream Big",
                "url": "https://www.ticketmaster.com/event/Z7r9jZ1AdG7fd",
                "localTime": "11:00:00",
                "localDate": "2022-01-22",
                "venue": "Pechanga Arena San Diego",
                "city": "San Diego",
                "state": "California",
                "segment": "Miscellaneous",
                "genre": "Ice Shows"
            },
            {
                "name": "Disney On Ice presents Dream Big",
                "url": "https://www.ticketmaster.com/disney-on-ice-presents-dream-big-phoenix-arizona-01-13-2022/event/19005B34DC9E3BCE",
                "localTime": "19:00:00",
                "localDate": "2022-01-13",
                "venue": "Footprint Center",
                "city": "Phoenix",
                "state": "Arizona",
                "segment": "Miscellaneous",
                "genre": "Ice Shows"
            },
            {
                "name": "Disney On Ice presents Dream Big",
                "url": "https://www.ticketmaster.com/disney-on-ice-presents-dream-big-jacksonville-florida-04-02-2022/event/22005B829ACDD0B8",
                "localTime": "11:00:00",
                "localDate": "2022-04-02",
                "venue": "VyStar Veterans Arena",
                "city": "Jacksonville",
                "state": "Florida",
                "segment": "Miscellaneous",
                "genre": "Ice Shows"
            },
            {
            }
        ],
        "number events": 26
    },
    "Music": {
        "list events": [
            {
                "name": "iHeartRadio ALTer EGO Presented by Capital One",
                "url": "https://www.ticketmaster.com/iheartradio-alter-ego-presented-by-capital-inglewood-california-01-15-2022/event/09005B4712E4601E",
                "localTime": "19:00:00",
                "localDate": "2022-01-15",
                "venue": "The Forum",
                "city": "Inglewood",
                "state": "California",
                "segment": "Music",
                "genre": "Rock"
            },
            {
                "name": "Imagine Dragons: Mercury World Tour",
                "url": "https://www.ticketmaster.com/imagine-dragons-mercury-world-tour-los-angeles-california-03-12-2022/event/2C005B1FEC0B0D99",
                "localTime": "19:00:00",
                "localDate": "2022-03-12",
                "venue": "Crypto.com Arena",
                "city": "Los Angeles",
                "state": "California",
                "segment": "Music",
                "genre": "Rock"
            },
            {
                "name": "Eagles",
                "url": "https://www.ticketmaster.com/eagles-charlotte-north-carolina-02-21-2022/event/2D005B768B2A1822",
                "localTime": "20:00:00",
                "localDate": "2022-02-21",
                "venue": "Spectrum Center ",
                "city": "Charlotte",
                "state": "North Carolina",
                "segment": "Music",
                "genre": "Rock"
            },
            {
            }
        ],
        "number events": 32
    },
    {
    },
 }    
```

#### /events/genres

La rotta `/events/genres` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "Motorsports/Racing": {
        "list events": [
            {
                "name": "Monster Jam",
                "url": "https://www.ticketmaster.com/monster-jam-anaheim-california-01-22-2022/event/09005B211AFA6C33",
                "localTime": "18:30:00",
                "localDate": "2022-01-22",
                "venue": "Angel Stadium of Anaheim",
                "city": "Anaheim",
                "state": "California",
                "segment": "Sports",
                "genre": "Motorsports/Racing"
            },
            {
                "name": "Monster Jam",
                "url": "https://www.ticketmaster.com/monster-jam-san-diego-california-01-15-2022/event/0A005B15F0633B3E",
                "localTime": "19:00:00",
                "localDate": "2022-01-15",
                "venue": "Petco Park",
                "city": "San Diego",
                "state": "California",
                "segment": "Sports",
                "genre": "Motorsports/Racing"
            },
            {
                "name": "Monster Jam",
                "url": "https://www.ticketmaster.com/monster-jam-oakland-california-01-08-2022/event/1C005B11CF3D4422",
                "localTime": "19:00:00",
                "localDate": "2022-01-08",
                "venue": "RingCentral Coliseum",
                "city": "Oakland",
                "state": "California",
                "segment": "Sports",
                "genre": "Motorsports/Racing"
            }
        ],
        "number events": 3
    },
    "Rock": {
        "list events": [
            {
                "name": "iHeartRadio ALTer EGO Presented by Capital One",
                "url": "https://www.ticketmaster.com/iheartradio-alter-ego-presented-by-capital-inglewood-california-01-15-2022/event/09005B4712E4601E",
                "localTime": "19:00:00",
                "localDate": "2022-01-15",
                "venue": "The Forum",
                "city": "Inglewood",
                "state": "California",
                "segment": "Music",
                "genre": "Rock"
            },
            {
                "name": "Imagine Dragons: Mercury World Tour",
                "url": "https://www.ticketmaster.com/imagine-dragons-mercury-world-tour-los-angeles-california-03-12-2022/event/2C005B1FEC0B0D99",
                "localTime": "19:00:00",
                "localDate": "2022-03-12",
                "venue": "Crypto.com Arena",
                "city": "Los Angeles",
                "state": "California",
                "segment": "Music",
                "genre": "Rock"
            },
            {
                "name": "Eagles",
                "url": "https://www.ticketmaster.com/eagles-charlotte-north-carolina-02-21-2022/event/2D005B768B2A1822",
                "localTime": "20:00:00",
                "localDate": "2022-02-21",
                "venue": "Spectrum Center ",
                "city": "Charlotte",
                "state": "North Carolina",
                "segment": "Music",
                "genre": "Rock"
            },
            {
            }
        ],
        "number events": 26
    },
    {
    },
 }    
```

### Rotte /stats

Le rotte **/stats** permettono di visualizzare le statistiche relative agli eventi, ad esempio il numero massimo, il numero minimo e la media di eventi di determinati stati, città, segmenti o generi, in base alla rotta scelta dall'utente; in alternativa, se si vuole avere una visione ancora più ampia, si potranno anche visionare le statistiche ancora più generali, che indicano gli stati, le città, i segmenti e i generi con il maggiore e minore numero di eventi.

L'unico parametro che l'utente può immettere è il periodo di interesse, che in questo caso rappresenta il periodo sul quale verranno calcolate le statistiche.

| Tipo    | Rotta /stats                              | Descrizione                                                                  | Parametri       |
|---------|-------------------------------------------|------------------------------------------------------------------------------|-----------------|
| ` get ` | [`/stats`](#/stats)                       | Restituisce il quadro generale delle statistiche                             | `period`        |
| ` get ` | [`/stats/states`](#/stats/states)         | Restituisce le statistiche per ogni stato calcolate su tutti gli eventi      | `period`        |
| ` get ` | [`/stats/cities`](#/stats/cities)         | Restituisce le statistiche per ogni città calcolate su tutti gli eventi      | `period`        |
| ` get ` | [`/stats/segments`](#/stats/segments)     | Restituisce le statistiche per ogni segmento calcolate su tutti gli eventi   | `period`        |
| ` get ` | [`/stats/genres`](#/stats/genres)         | Restituisce le statistiche per ogni stato calcolate su tutti gli eventi      | `period`        |

#### /stats

La rotta `/stats` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "min events in a month": "0, in July",
    "max events in a month": "123, in January",
    "Respect": {
        "cities": {
            "min": {
                "average": 0.12,
                "name": "Memphis",
                "n events": 1
            },
            "max": {
                "average": 2.78,
                "name": "Phoenix",
                "n events": 22
            }
        },
        "genres": {
            "min": {
                "average": 0.12,
                "name": "Fairs & Festivals",
                "n events": 1
            },
            "max": {
                "average": 8.86,
                "name": "Basketball",
                "n events": 70
            }
        },
        "states": {
            "min": {
                "average": 0.12,
                "name": "Maryland",
                "n events": 1
            },
            "max": {
                "average": 4.43,
                "name": "California",
                "n events": 35
            }
        },
        "segments": {
            "min": {
                "average": 0.88,
                "name": "Arts & Theatre",
                "n events": 7
            },
            "max": {
                "average": 17.08,
                "name": "Sports",
                "n events": 135
            }
        }
    },
    "monthly average": 25.31,
    "number events": 200
}  
```

#### /stats/events

La rotta `/stats/events` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "North Carolina": {
        "min events in a month": "0, in April",
        "max events in a month": "2, in February",
        "monthly average": 0.5,
        "number events": 4
    },
    "Oregon": {
        "min events in a month": "0, in January",
        "max events in a month": "1, in February",
        "monthly average": 0.25,
        "number events": 2
    },
    "Indiana": {
        "min events in a month": "0, in March",
        "max events in a month": "1, in January",
        "monthly average": 0.25,
        "number events": 2
    },
    {
    },
}
```

#### /stats/cities

La rotta `/stats/cities` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "Milwaukee": {
        "min events in a month": "0, in April",
        "max events in a month": "3, in February",
        "monthly average": 0.63,
        "number events": 5
    },
    "Oklahoma City": {
        "min events in a month": "0, in January",
        "max events in a month": "1, in February",
        "monthly average": 0.25,
        "number events": 2
    },
    "Detroit": {
        "min events in a month": "0, in February",
        "max events in a month": "4, in January",
        "monthly average": 0.63,
        "number events": 5
    },
    {   
    }
}
```

#### /stats/segments

La rotta `/stats/segments` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "Miscellaneous": {
        "min events in a month": "0, in May",
        "max events in a month": "20, in January",
        "monthly average": 3.29,
        "number events": 26
    },
    "Music": {
        "min events in a month": "0, in April",
        "max events in a month": "21, in February",
        "monthly average": 4.05,
        "number events": 32
    },
    "Arts & Theatre": {
        "min events in a month": "0, in February",
        "max events in a month": "4, in January",
        "monthly average": 0.88,
        "number events": 7
    },
    "Sports": {
        "min events in a month": "0, in May",
        "max events in a month": "98, in January",
        "monthly average": 17.08,
        "number events": 135
    }
}
```

#### /stats/genres

La rotta `/stats/genres` restituisce un JSONObject strutturato nel seguente modo:

```json
{
    "Motorsports/Racing": {
        "min events in a month": "0, in February",
        "max events in a month": "3, in January",
        "monthly average": 0.37,
        "number events": 3
    },
    "Rock": {
        "min events in a month": "0, in April",
        "max events in a month": "15, in February",
        "monthly average": 3.29,
        "number events": 26
    },
    "Hockey": {
        "min events in a month": "0, in May",
        "max events in a month": "36, in January",
        "monthly average": 5.18,
        "number events": 41
    },
    {
    }
}
```

### Rotte /list

Le rotte **/list** permettono semplicemente di visualizzare la lista di tutti gli stati, di tutte le città, di tutti i segmenti o di tutti i generi, in base alla scelta della rotta. 

| Tipo    | Rotta /list                             | Descrizione                              |
|---------|-----------------------------------------|------------------------------------------|
| ` get ` | [`/list/states`](#/list/states)         | Restituisce la lista di tutti gli stati  |               
| ` get ` | [`/list/cities`](#/list/cities)         | Restituisce la lista di tutte le città   |                
| ` get ` | [`/list/ssegments`](#/list/segments)    | Restituisce la lista di tutti i segmenti |                 
| ` get ` | [`/list/genres`](#/list/genres)         | Restituisce la lista di tutti i generi   |                

#### /list/states

La rotta `/list/states` restituisce:

```json
[
    "Arizona",
    "Indiana",
    "California",
    "Louisiana",
    "Utah",
    "",
]
```
#### /list/cities

La rotta `/list/cities` restituisce:

```json
[
    "Phoenix",
    "Indianapolis",
    "San Francisco",
    "New Orleans",
    "Salt Lake City",
    "",
]
```
#### /list/segments

La rotta `/list/segments` restituisce:

```json
[
    "Sports",
    "Music",
    "Miscellaneous",
    "Arts & Theatre"
]
```
#### /list/genres

La rotta `/list/genres` restituisce:

```json
[
    "Basketball",
    "Football",
    "Rock",
    "Hockey",
    "Motorsports/Racing",
    "",
]
```











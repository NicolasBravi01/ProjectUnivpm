
# Progetto Oriented Object Programming su Ticketmaster

![Ticketmaster](https://ichef.bbci.co.uk/news/976/cpsprodpb/136AB/production/_116313597_ticketmaster.jpg "Ticketmaster")

## Indice

* [Introduzione](#id-section1)
* [Installazione ed utilizzo](#id-section2)
* [Rotte](#id-section3)
* [Autori](#id-section4)

<div id='id-section1'/>

## Introduzione 

L'applicazione SpringBoot utilizza il sito di Ticketmaster, un software che si occupa della gestione e della prenotazione di eventi sportivi, musicali, teatrali, cinematografici ed artistici, reperibile presso l’indirizzo [Ticketmaster](https://ticketmaster.com). In particolare, attraverso le Application Programming Interface, comunemente note come `api`, derivate dalla pagina TM Developer e accessibili mediante l’indirizzo [Ticketmaster Api V2](https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/), il programma valuterà gli eventi che si verificheranno negli Stati Uniti d'America. L'utente potrà, in base alle sue preferenze, imporre dei filtri per poter visualizzare gli eventi di interesse o le statistiche relative agli eventi.

<div id='id-section2'/>

## Installazione ed utilizzo

Per poter accedere al programma è necessario clonare la repository in locale utilizzando Github Desktop oppure da terminale con il comando
`git clone https://github.com/NicolasBravi01/ProjectUnivpm.`
Successivamente sarà possibile mandare in esecuzione il programma con un IDE (ad esempio Eclipse) o direttamente da terminale.

In seguito l'utente deve accedere alla cartella resources, reperibile attraverso il percorso `ProjectUnivpm\ticketmaster\src\main\resources`, e creare il file **apiKey.txt**,  dentro il quale è necessario inserire la propria `apiKey`, ottenuta in seguito all'autenticazione sul portale TM Developer ([Ticketmaster Api V2](https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/)).

Dopodiché l'utente potrà scegliere se utilizzare il programma con le rotte (che verranno elencate e spiegate nel paragrafo successivo) sul client [Postman](https://www.postman.com/), che permettono di visualizzare anche le statistiche ed altre informazioni relative agli eventi, oppure tramite la Graphic User Interface implementata all'interno del progetto (che verrà spiegata nel paragrafo successivo alle rotte), che consente unicamente di ottenere la lista deglie eventi filtrata dall'utente.




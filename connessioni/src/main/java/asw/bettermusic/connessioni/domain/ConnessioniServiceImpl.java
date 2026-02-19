package asw.bettermusic.connessioni.domain;

import asw.bettermusic.connessioni.api.event.ConnessioneCreatedEvent;
import asw.bettermusic.connessioni.api.event.ConnessioneDeletedEvent;
import asw.bettermusic.common.api.event.DomainEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import java.util.logging.Logger;


@Service
public class ConnessioniServiceImpl implements ConnessioniService {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private ConnessioniRepository connessioniRepository;

    @Autowired
    private ConnessioniEventPublisher connessioniEventPublisher;

    /* Crea una nuova connessione, dati utente, seguito e ruolo. */
    public Connessione createConnessione(String utente, String seguito, String ruolo) {
        // verifica unicità-> DA VEDERE
        if (connessioniRepository.findByUtenteAndSeguitoAndRuolo(utente, seguito, ruolo) != null) {
            return null; // TODO: oppure lancia eccezione personalizzata 
        }

        Connessione connessione = new Connessione(utente, seguito, ruolo);

        try{
            connessione = connessioniRepository.save(connessione);
            DomainEvent event = new ConnessioneCreatedEvent(connessione.getId(), connessione.getUtente(), connessione.getSeguito(), connessione.getRuolo());
            connessioniEventPublisher.publish(event);

            return connessione;
        }
        catch(Exception e){ // TODO: metti un eccezione più specifica
            logger.info("DataAccessException:" + e.toString());
			return null; 
        }
        
    }

    /* Trova una connessione, dato l'id. */
    public Connessione getConnessione(Long id) {
        return connessioniRepository.findById(id).orElse(null);
    }

    /* Trova una connessione, dati utente, seguito e ruolo. */
    public Connessione getConnessione(String utente, String seguito, String ruolo) {
        return connessioniRepository.findByUtenteAndSeguitoAndRuolo(utente, seguito, ruolo);
    }

    /* Trova tutte le connessioni. */
    public Collection<Connessione> getConnessioni() {
        return connessioniRepository.findAll();
    }

    /* Trova tutte le connessioni di un utente. */
    public Collection<Connessione> getConnessioniByUtente(String utente) {
        return connessioniRepository.findByUtente(utente);
    }

    /* Trova tutte le connessioni con un certo ruolo. */
    public Collection<Connessione> getConnessioniByRuolo(String ruolo) {
        return connessioniRepository.findByRuolo(ruolo);
    }

    /* Trova tutte le connessioni di un utente con un certo ruolo. */
    public Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo) {
        return connessioniRepository.findByUtenteAndRuolo(utente, ruolo);
    }

    /* Cancella una connessione, dati utente, seguito e ruolo. */
    public Connessione deleteConnessione(String utente, String seguito, String ruolo) {
        Connessione connessione = getConnessione(utente, seguito, ruolo);
        if (connessione != null) {
            try{
                connessioniRepository.delete(connessione);
                DomainEvent event = new ConnessioneDeletedEvent(connessione.getId(), connessione.getUtente(), connessione.getSeguito(), connessione.getRuolo());
                connessioniEventPublisher.publish(event);

                return connessione;
            }
            catch(Exception e){ // TODO: metti un eccezione più specifica
                logger.info("DataAccessException:" + e.toString());
                return null; 
            }
        }

        return connessione;
    }
}

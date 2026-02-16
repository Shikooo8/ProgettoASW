package asw.bettermusic.connessioni.domain;

import com.bettermusic.connessioni.api.event.ConnessionCreatedEvent;
import com.bettermusic.connessioni.api.event.ConnessionDeletedEvent;
import com.bettermusic.connessioni.api.event.ConnessionEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ConnessioniServiceImpl implements ConnessioniService {

    @Autowired
    private ConnessioniRepository connessioniRepository;

    @Autowired
    private ConnessionEventProducer eventProducer;

    /* Crea una nuova connessione, dati utente, seguito e ruolo. */
    @Override
    @Transactional
    public Connessione createConnessione(String utente, String seguito, String ruolo) {
        // verifica unicità
        if (connessioniRepository.findByUtenteAndSeguitoAndRuolo(utente, seguito, ruolo) != null) {
            return null; // oppure lancia eccezione personalizzata
        }

        Connessione connessione = new Connessione(utente, seguito, ruolo);
        connessione = connessioniRepository.save(connessione);

        // invio evento
        eventProducer.sendCreatedEvent(new ConnessionCreatedEvent(connessione.getId(), utente, seguito));

        return connessione;
    }

    /* Trova una connessione, dato l'id. */
    @Override
    public Connessione getConnessione(Long id) {
        return connessioniRepository.findById(id).orElse(null);
    }

    /* Trova una connessione, dati utente, seguito e ruolo. */
    @Override
    public Connessione getConnessione(String utente, String seguito, String ruolo) {
        return connessioniRepository.findByUtenteAndSeguitoAndRuolo(utente, seguito, ruolo);
    }

    /* Trova tutte le connessioni. */
    @Override
    public Collection<Connessione> getConnessioni() {
        return connessioniRepository.findAll();
    }

    /* Trova tutte le connessioni di un utente. */
    @Override
    public Collection<Connessione> getConnessioniByUtente(String utente) {
        return connessioniRepository.findByUtente(utente);
    }

    /* Trova tutte le connessioni con un certo ruolo. */
    @Override
    public Collection<Connessione> getConnessioniByRuolo(String ruolo) {
        return connessioniRepository.findByRuolo(ruolo);
    }

    /* Trova tutte le connessioni di un utente con un certo ruolo. */
    @Override
    public Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo) {
        return connessioniRepository.findByUtenteAndRuolo(utente, ruolo);
    }

    /* Cancella una connessione, dati utente, seguito e ruolo. */
    @Override
    @Transactional
    public Connessione deleteConnessione(String utente, String seguito, String ruolo) {
        Connessione connessione = getConnessione(utente, seguito, ruolo);
        if (connessione != null) {
            connessioniRepository.delete(connessione);
            eventProducer.sendDeletedEvent(new ConnessionDeletedEvent(connessione.getId()));
        }
        return connessione;
    }
}

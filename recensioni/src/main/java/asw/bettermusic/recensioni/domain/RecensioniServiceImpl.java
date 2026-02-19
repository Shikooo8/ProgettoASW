package asw.bettermusic.recensioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import asw.bettermusic.recensioni.api.event.RecensioneCreatedEvent;
import asw.bettermusic.common.api.event.DomainEvent;

import java.util.*; 

import java.util.logging.Logger; 


@Service
public class RecensioniServiceImpl implements RecensioniService {
	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RecensioniRepository recensioniRepository;

//	@Autowired
//	private AlbumClientPort albumClient;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private RecensioniEventPublisher recensioniEventPublisher;

	/* Crea una nuova recensione, a partire dai suoi dati. */ 
 	public Recensione createRecensione(String recensore, String titoloAlbum, String artistaAlbum, String testo, String sunto) {
	//	Album album = albumClient.getAlbum(titoloAlbum, artistaAlbum);
		//Album album= albumRepository.findByTitoloAndArtista(titoloAlbum, artistaAlbum).orElseThrow(()-> new RuntimeException("Album not found"));
		Optional<Album> optAlbum = albumRepository.findByTitoloAndArtista(titoloAlbum, artistaAlbum);
		if (optAlbum.isEmpty()) {		// TODO: sistema e metti un eccezione più specifica
			throw new RuntimeException("Album not found in Recensioni: " + titoloAlbum + " / " + artistaAlbum);
		}
		Album album = optAlbum.get();
		Recensione recensione = new Recensione(recensore, album.getId(),  testo, sunto); 
		
		try{
			recensione = recensioniRepository.save(recensione);
			DomainEvent event= new RecensioneCreatedEvent(recensione.getId(), recensione.getRecensore(), recensione.getIdAlbum(), recensione.getTesto(), recensione.getSunto());
			recensioniEventPublisher.publish(event);

			return recensione;
		}catch (Exception e){		// TODO: metti un eccezione più specifica
			logger.info("DataAccessException:" + e.toString());
			return null; 
		}
	}

	/* Trova una recensione, dato l'id. */ 
 	public Recensione getRecensione(Long id) {
		Recensione recensione = recensioniRepository.findById(id).orElse(null);
		return recensione;
	}

	/* Trova tutte le recensioni. */ 
	public Collection<Recensione> getRecensioni() {
		Collection<Recensione> recensioni = recensioniRepository.findAll();
		return recensioni;
	}

	/* Trova tutte le recensioni di un album, dato l'id. */ 
	public Collection<Recensione> getRecensioniByIdAlbum(Long idAlbum) { 
		Collection<Recensione> recensioni = recensioniRepository.findByIdAlbum(idAlbum);
		return recensioni;
	}

	/* Trova tutte le recensioni scritte da un recensore. */ 
	public Collection<Recensione> getRecensioniByRecensore(String recensore) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensore(recensore);
		return recensioni;
	}

}

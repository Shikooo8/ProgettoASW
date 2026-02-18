package asw.bettermusic.recensioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class RecensioniServiceImpl implements RecensioniService {

	@Autowired
	private RecensioniRepository recensioniRepository;

//	@Autowired
//	private AlbumClientPort albumClient;

	@Autowired
	private AlbumRepository albumRepository;

	/* Crea una nuova recensione, a partire dai suoi dati. */ 
 	public Recensione createRecensione(String recensore, String titoloAlbum, String artistaAlbum, String testo, String sunto) {
	//	Album album = albumClient.getAlbum(titoloAlbum, artistaAlbum);
		//Album album= albumRepository.findByTitoloAndArtista(titoloAlbum, artistaAlbum).orElseThrow(()-> new RuntimeException("Album not found"));
		Optional<Album> optAlbum = albumRepository.findByTitoloAndArtista(titoloAlbum, artistaAlbum);
		if (optAlbum.isEmpty()) {
			throw new RuntimeException("Album not found in Recensioni: " + titoloAlbum + " / " + artistaAlbum);
		}
		Album album = optAlbum.get();
		Recensione recensione = new Recensione(recensore, album.getId(),  testo, sunto); 
		recensione = recensioniRepository.save(recensione);
		return recensione;

		//qui andrà la parte di EventPublish di Recensione per il servizio Recensioni-Seguite
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

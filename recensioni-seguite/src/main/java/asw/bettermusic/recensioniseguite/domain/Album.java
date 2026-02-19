package asw.bettermusic.recensioniseguite.domain;

import jakarta.persistence.*; 

import lombok.*; 

import java.util.*; 

/* Un album. */  
@Entity
@Table(name="albums", uniqueConstraints = { @UniqueConstraint(name = "UniqueTitoloArtista", columnNames = { "titolo", "artista" }) })
@Data @NoArgsConstructor @AllArgsConstructor
public class Album {

	/* id dell'album */ 
	@Id
	private Long id; 
	/* titolo dell'album */ 
	private String titolo; 
	/* artista dell'album */ 
	private String artista; 
	/* generi dell'album */ 
	@ElementCollection( fetch = FetchType.LAZY)		//DA VEDERE (lazy o eager?-> specifiche prof)
	private Set<String> generi; 
	
}

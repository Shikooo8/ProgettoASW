package asw.bettermusic.recensioni.domain;

import jakarta.persistence.*; 

import lombok.*; 

import java.util.*; 

/* Un album. */  
@Entity 		//Recensioni memorizzerà una tabella contenente gli album
@Table(name="albums", uniqueConstraints = { @UniqueConstraint(name = "UniqueTitoloArtista", columnNames = { "titolo", "artista" }) })
@Data @NoArgsConstructor @AllArgsConstructor
public class Album {

	/* id dell'album */ 
	//	@EqualsAndHashCode.Include
	@Id
	private Long id; 
	/* titolo dell'album */ 
	private String titolo; 
	/* artista dell'album */ 
	private String artista; 
	/* generi dell'album */ 
	@ElementCollection( fetch = FetchType.LAZY)	//fa il fetch solo quando viene fatto getGeneri()
	private Set<String> generi; 

	/*@Override
	public int compareTo(Album other) {
		return this.id.compareTo(other.id); 
	}*/
	
}

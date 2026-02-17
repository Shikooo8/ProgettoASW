package asw.bettermusic.recensioni.domain;

import lombok.*; 

import java.util.*; 

/* Un album. */  
@Entity 		//Recenzioni memorizzerà una tabella contenente gli album
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueTitoloArtista", columnNames = { "titolo", "artista" }) })
@Data @NoArgsConstructor @AllArgsConstructor
public class Album implements Comparable<Album> {

	/* id dell'album */ 
	@Id @GeneratedValue
	@EqualsAndHashCode.Include
	private Long id; 
	/* titolo dell'album */ 
	private String titolo; 
	/* artista dell'album */ 
	private String artista; 
	/* generi dell'album */ 
	@ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)	//fa il fetch solo quando viene fatto getGeneri()
	private Set<String> generi; 

	@Override
	public int compareTo(Album other) {
		return this.id.compareTo(other.id); 
	}
	
}

package utilities.peronnel;

import java.util.ArrayList;
import java.util.List;

public class Personnels {

	private Mpiasa personne;
	private List<Mpiasa> liste = new ArrayList<>();

	public Personnels(Mpiasa personne) {
		super();
		this.personne = personne;
	}

	public Mpiasa getPersonne() {
		return personne;
	}

	public void setPersonne(Mpiasa personne) {
		this.personne = personne;
	}
	
	
}

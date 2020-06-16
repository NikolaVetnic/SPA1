/**
 * Klasa Savez predstavlja košarkaške klubove i njihove igrače. Klubovi
 * su predstavljeni imenom i čuvaju se u listi. Svaki klub ima listu i-
 * grača predstavljenih jedinstvenim brojem dresa, imenom i brojem uta-
 * kmica.
 * Glavna klasa treba da ima i jasan toString() metod (ali i ostale kl-
 * ase mogu imati). Napisati program koji demonstrira napravljene meto-
 * de.
 * Napraviti dodavanje kluba u listu. Svi klubovi treba da su jedinstv-
 * eni po imenu. Napraviti dodavanje igrača u klub po imenu, tako da su
 * sortirani po broju dresa. Ako klub ne postoji napraviti ga.
 * Dodati metod koji izbacuje igrača sa najviše utakmica iz zadatog kl-
 * uba.
 */
 
class Z04P01 {
	
	public static void main(String[] args) {
		
		Savez savez = new Savez();
		
		savez.dodajKlub("Zvezda");
		savez.dodajIgraca("Petrovic", 3, 10, "Zvezda");
		savez.dodajIgraca("Djordjevic", 4, 20, "Zvezda");
		savez.dodajIgraca("Veselji", 5, 5, "Zvezda");
		savez.dodajIgraca("Idrizi", 3, 5, "Zvezda");
		
		savez.dodajKlub("Partizan");
		savez.dodajIgraca("Tudjman", 13, 35, "Partizan");
		
		savez.dodajKlub("Vojvodina");
		savez.dodajIgraca("Sabados", 23, 15, "Vojvodina");
		savez.dodajIgraca("Nadj", 21, 5, "Vojvodina");
		
		System.out.println(savez);
		
		savez.izbaciSaNajviseUtakmica("Zvezda");
		savez.izbaciSaNajviseUtakmica("Partizan");
		savez.izbaciSaNajviseUtakmica("Vojvodina");
		System.out.println(savez);
	}
}

class Savez {
	
	class Igrac {
		
		String ime;
		int dres, utak;
		Igrac veza;
		
		
		public Igrac(String ime, int dres, int utak) {
			this.ime = ime;
			this.dres = dres;
			this.utak = utak;
			this.veza = null;
		}
		
		
		public String toString() {
			return "(" + ime + ", dres " + dres + ", utakmica: " + utak + ")";
		}
	}
	
	
	public boolean dodajIgraca(String ime, int dres, int utak, String naziv) {
		
		Klub cilj = nadjiKlub(naziv);
		
		if (cilj == null || postojiDres(dres, naziv))
			return false;
			
		Igrac novi = new Igrac(ime, dres, utak);
		
		if (cilj.prviIgrac == null || dres < cilj.prviIgrac.dres) {
			
			novi.veza = cilj.prviIgrac;
			cilj.prviIgrac = novi;
		} else {
			
			Igrac pret = cilj.prviIgrac;
			
			while (pret.veza != null && dres > pret.veza.dres)
				pret = pret.veza;
				
			novi.veza = pret.veza;
			pret.veza = novi;
		}
			
		return true;
	}
	
	
	private boolean postojiDres(int dres, String naziv) {
		
		Klub cilj = nadjiKlub(naziv);
		
		if (cilj == null || cilj.prviIgrac == null)
			return false;
		
		Igrac tek = cilj.prviIgrac;
		
		while (tek != null) {
			
			if (tek.dres == dres)
				return true;
				
			tek = tek.veza;
		}
		
		return false;
	}
	
	
	class Klub {
		
		String naziv;
		Igrac prviIgrac;
		Klub veza;
		
		
		public Klub(String naziv) {
			this.naziv = naziv;
			this.prviIgrac = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ '" + naziv + "' : ";
			
			Igrac tek = prviIgrac;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Klub prviKlub;
	
	
	public boolean dodajKlub(String naziv) {
		
		if (nadjiKlub(naziv) != null)
			return false;
			
		Klub novi = new Klub(naziv);
		
		novi.veza = prviKlub;
		prviKlub = novi;
		
		return true;
	}
	
	
	private Klub nadjiKlub(String naziv) {
		
		Klub tek = prviKlub;
		
		while (tek != null) {
			
			if (tek.naziv.equals(naziv))
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public void izbaciSaNajviseUtakmica(String naziv) {
		
		Klub cilj = nadjiKlub(naziv);
		
		if (cilj == null || cilj.prviIgrac == null)
			return;
			
		Igrac saNajvise = null;
		Igrac pret = cilj.prviIgrac;
		
		int max = cilj.prviIgrac.utak;
		
		while (pret.veza != null) {
			
			if (pret.veza.utak > max) {
				
				saNajvise = pret;
				max = pret.veza.utak;
			}
			
			pret = pret.veza;
		}
		
		pret = saNajvise;
		
		if (pret != null) 
			pret.veza = pret.veza.veza;
		else
			cilj.prviIgrac = cilj.prviIgrac.veza;
	}
	
	
	public String toString() {
		
		String output = "SAVEZ : ";
		
		Klub tek = prviKlub;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output;
	}
}

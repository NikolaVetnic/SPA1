/**
 * Napraviti klasu SpisakFilmova. Pojedinačne filmove pamtiti u jednos-
 * truko povezanoj listi. Za svaki film zapamtiti naslov (string). Dod-
 * avati tako da su filmovi u listi jedinstveni po imenu.
 * 
 * Proširiti klasu koja predstavlja film tako da ima listu glumaca vez-
 * anih za taj film. Svaki glumac je predstavljen u filmu jednim strin-
 * gom tipa "Ime Prezime". Napraviti metode koji dodaju glumce u filmo-
 * ve po nazivu. Glumac se može samo jednom pojaviti u jednom filmu. A-
 * ko film ne postoji, odustati od dodavanja. Odštampati sve filmove, i
 * glumce u njima uredno na ekran (za ovo se može koristiti toString m-
 * etod ako je adekvatan, ali i odvojeni metod koji će nekako jasno oz-
 * načiti koji glumac je u kom filmu).
 * 
 * Dodati metod u klasu SpisakFilmova takav da briše sve filmove u koj-
 * ima je glumio zadati glumac.
 */

class N04Z01P13 {
	
	public static void main(String[] args) {
		
		// kreiranje spiska filmova
		SadrzajSpiskaFilmova mojSpisak = new SadrzajSpiskaFilmova();
		mojSpisak.stampajSpisak();
		System.out.println();
		
		// film #1
		mojSpisak.dodajFilm("Dune");
		mojSpisak.dodajGlumca("Dune", "Kyle McLachlan");
		mojSpisak.dodajGlumca("Dune", "Kyle McLachlan");
		mojSpisak.dodajGlumca("Dune", "Kyle McLachlan");
		mojSpisak.dodajGlumca("Dune", "Kyle McLachlan");
		mojSpisak.dodajGlumca("Dune", "Kyle McLachlan");
		mojSpisak.dodajGlumca("Dune", "Kyle McLachlan");
		mojSpisak.dodajGlumca("Dune", "Max von Sydow");
		
		// film #2
		mojSpisak.dodajFilm("Blade Runner");
		mojSpisak.dodajGlumca("Blade Runner", "Harrison Ford");
		
		// film #3 (serija)
		mojSpisak.dodajFilm("Twin Peaks");
		mojSpisak.dodajGlumca("Twin Peaks", "Kyle McLachlan");
		
		// film #4
		mojSpisak.dodajFilm("Brasil");
		
		// pokusaj dodavanja u nepostojeci film
		mojSpisak.dodajGlumca("Lord of the Rings 4", "Mark Hamilton");
		
		// stampanje spiska
		mojSpisak.stampajSpisak();
		System.out.println();
		
		// izbacivanje svih filmova sa zadatim glumcem i stampanje
		String glumac1 = "Kyle McLachlan";
		String glumac2 = "Harrison Ford";
		mojSpisak.ukloniFilmoveSaGlumcem(glumac1);
		mojSpisak.stampajSpisak();
		System.out.println();
	}
}
	
class SadrzajSpiskaFilmova {
	
	private Film prviFilm;

	
	class Glumac {
		
		String info;
		Glumac veza;
		
		
		public Glumac(String info) {
			this.info = info;
			this.veza = null;
		}
		
		
		public String toString() {
			return info;
		}
	}	// unutrasnja klasa


	class Film {
		
		String naziv;
		Film veza;
		Glumac sadrzaj;
		
		
		public Film(String naziv) {
			this.naziv = naziv;
			this.veza = null;
		}	// konstruktor
		
		
		public String toString() {
			
			String output = "[" + naziv + ":";
			Glumac tekuci = sadrzaj;
			
			if (sadrzaj != null) {
				
				output += " " + sadrzaj;
				tekuci = tekuci.veza;
				
				while (tekuci != null) {
					
					output += ", " + tekuci;
					tekuci = tekuci.veza;
				}
			}
			
			return output + " ]";
		}	// toString
	}	// unutrasnja lista

	
	public String toString() {
		
		String output = "Filmovi:";
		Film tekuci = prviFilm;
		
		while (tekuci != null) {
			
			output += " " + tekuci;
			tekuci = tekuci.veza;
		}
		
		return output;
	}	// toString
	
	
	public void dodajFilm(String noviNaziv) {
		
		if (!postojiFilm(noviNaziv)) {
			
			Film novi = new Film(noviNaziv);
			novi.veza = prviFilm;
			prviFilm = novi;
		}
	}	// dodaje novi film ako vec ne postoji u spisku
	
	
	public Film nadjiFilm(String film) {
		
		Film tekuci = prviFilm;
		
		while (tekuci != null) {
			
			if (tekuci.naziv.equals(film))
				return tekuci;
				
			tekuci = tekuci.veza;
		}
		
		return null;
	}	// pronalazi film u spisku filmova
	
	
	public boolean postojiFilm(String trazeni) {
		return nadjiFilm(trazeni) != null;
	}	// vraca true ako film postoji u spisku filmova
	

	public boolean ukloniFilm(String naziv) {
		
		if (prviFilm == null) {
			return false;
		}
		
		if (prviFilm.naziv.equals(naziv)) {
			prviFilm = prviFilm.veza;
			return true;
		}
		
		Film prethodni = prviFilm;
		
		while (prethodni.veza != null) {
			
			if (prethodni.veza.naziv.equals(naziv)) {
				
				prethodni.veza = prethodni.veza.veza;
				return true;
			}
			
			prethodni = prethodni.veza;
		}
		
		return false;
	}	// uklanja film zadat kao string
	
	
	public void ukloniFilmoveSaGlumcem(String glumac) {
		
		Film tekuciFilm = prviFilm;
		
		while (tekuciFilm != null) {
			
			if (postojiGlumacUFilmu(tekuciFilm.naziv, glumac)) 
				ukloniFilm(tekuciFilm.naziv);
			
			tekuciFilm = tekuciFilm.veza;
		}
	}	// uklanja sve filmove sa zadatim glumcem
	
	
	public void dodajGlumca(String film, String glumac) {
		
		Film cilj = nadjiFilm(film);
		
		if (cilj != null) {
			
			Glumac tekuci = cilj.sadrzaj;
			boolean nadjen = false;
			
			while (tekuci != null && !nadjen) {
				
				if (tekuci.info.equals(glumac)) nadjen = true;
				tekuci = tekuci.veza;
			}
			
			if (!nadjen) {
				
				Glumac noviGlumac = new Glumac(glumac);
				noviGlumac.veza = cilj.sadrzaj;
				cilj.sadrzaj = noviGlumac;
			} else {
				
				System.out.println("Glumac se vec nalazi u zadatom filmu.");
			}
		} else {
			
			System.out.println("Film u koji pokusavate da dodate glumca ne postoji.");
		}
	}	// proverava da li se lgumac vec nalazi u filmu i dodaje ako ne
	

	public boolean postojiGlumacUFilmu(String film, String glumac) {
		
		Film cilj = nadjiFilm(film);
		boolean nadjen = false;
		
		if (cilj != null) {
			
			Glumac tekuci = cilj.sadrzaj;
			
			while (tekuci != null && !nadjen) {
				
				if (tekuci.info.equals(glumac)) nadjen = true;
				tekuci = tekuci.veza;
			}
		}
		
		return nadjen;
	}
	
	
	public void stampajSpisak() {
		if (prviFilm == null) {
			
			System.out.println("Nema filmova na spisku.");
		} else {
			
			System.out.println("Filmovi: ");
			
			Film tekuci = prviFilm;
			int count = 0;
			
			while (tekuci != null) {
				
				count++;
				System.out.println(
					"Film #" + count + ": '" + tekuci.naziv + "'");
				
				if (tekuci.sadrzaj == null) {
					
					System.out.println("\tNema unetih glumaca.");
				} else {
					
					Glumac tekuciGlumac = tekuci.sadrzaj;
					
					while (tekuciGlumac != null) {
						
						System.out.println("\t" + tekuciGlumac);
						tekuciGlumac = tekuciGlumac.veza;
					}
				}
				
				tekuci = tekuci.veza;
			}
		}
	}	// (uredno) stampanje spiska na ekran
}

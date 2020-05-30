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

class N04Z01P01 {
	
	public static void main(String[] args) {
		
		// kreiranje spiska filmova
		SpisakFilmova spisakFilmova = new SpisakFilmova();
		spisakFilmova.stampajSpisak();
		System.out.println();
		
		// film #1
		spisakFilmova.dodajFilm("Dune");
		spisakFilmova.dodajGlumca("Dune", "Kyle McLachlan");
		spisakFilmova.dodajGlumca("Dune", "Kyle McLachlan");
		spisakFilmova.dodajGlumca("Dune", "Francesca Annis");
		spisakFilmova.dodajGlumca("Dune", "Jurgen Prochnow");
		spisakFilmova.dodajGlumca("Dune", "Brad Dourif");
		spisakFilmova.dodajGlumca("Dune", "Max von Sydow");
		
		// film #2
		spisakFilmova.dodajFilm("Blade Runner");
		spisakFilmova.dodajGlumca("Blade Runner", "Harrison Ford");
		
		// film #3 (serija)
		spisakFilmova.dodajFilm("Twin Peaks");
		spisakFilmova.dodajGlumca("Twin Peaks", "Kyle McLachlan");
		
		// film #4
		spisakFilmova.dodajFilm("Brasil");
		
		// pokusaj dodavanja u nepostojeci film
		spisakFilmova.dodajGlumca("Lord of the Rings 4", "Mark Hamilton");
		
		// stampanje spiska
		spisakFilmova.stampajSpisak();
		System.out.println();
		
		// izbacivanje svih filmova sa zadatim glumcem i stampanje
		String glumac1 = "Kyle McLachlan";
		String glumac2 = "Harrison Ford";
		spisakFilmova.brisiFilmoveSaGlumcem(glumac1);
		spisakFilmova.stampajSpisak();
		System.out.println();
		
	}
}

class SpisakFilmova {
		
	class Glumac {
		
		String ime;
		Glumac veza;
		
		
		public Glumac(String ime) {
			this.ime = ime;
			this.veza = null;
		}
		
		
		public String toString() {
			return ime;
		}
	}	// unutrasnja klasa
	
	
	class Film {
		
		String naziv;
		Film veza;
		Glumac sadrzaj;
		
		
		public Film(String naziv) {
			this.naziv = naziv;
			this.sadrzaj = null;
		}
		
		
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
		}
	}	// unutrasnja lista

	
	Film prviFilm;
	
	
	public SpisakFilmova() {
		this.prviFilm = null;
	}
	
	
	public void dodajFilm(String naziv) {
		
		/**
		 * film dodajemo samo ako vec ne postoji u listi
		 */
		
		if (!postojiFilm(naziv)) {
			
			Film noviFilm = new Film(naziv);
			
			noviFilm.veza = prviFilm;
			prviFilm = noviFilm;
		}
	}
	
	
	public Film nadjiFilm(String naziv) {
		
		/**
		 * ideja je da metod prolazi kroz listu sve dok ne dodje do kr-
		 * aja ili dok ne nadje film sa trazenim nazivom, nakon cega v-
		 * raca tekuciFilm koji, ako je dosao do kraja, ima vrednost n-
		 * ull, u suprotnom vrednost bas trazenog filma
		 */
		
		Film tekuciFilm = prviFilm;
		
		while (tekuciFilm != null && !tekuciFilm.naziv.equals(naziv))
			tekuciFilm = tekuciFilm.veza;
		
		return tekuciFilm;
	}
	
	
	public boolean postojiFilm(String naziv) {
		
		/**
		 * ako film postoji, bice pronadjen i rezultat pretrage nece b-
		 * iti null
		 */
		
		return nadjiFilm(naziv) != null;
	}
	
	
	public boolean obrisiFilm(String naziv) {
		
		/**
		 * prvo se proverava da li uopste postoje filmovi u spisku, ako
		 * postoje proverava se da li je prvi film u spisku bas taj ko-
		 * ji zelimo da obrisemo, a ako nije krecemo se kroz spisak tr-
		 * azeci film koji odgovara koristeci prethodni i tekuci da bi-
		 * smo mogli da ih prevezemo ukoliko ga pronadjemo
		 */
		
		if (prviFilm == null)
			return false;
		
		if (prviFilm.naziv.equals(naziv)) {
			
			prviFilm = prviFilm.veza;
			return true;
		}
			
		Film prethodniFilm = prviFilm;
		boolean nadjen = false;
		
		while (prethodniFilm.veza != null && !nadjen) {
			
			if (prethodniFilm.veza.naziv.equals(naziv)) {
				
				prethodniFilm.veza = prethodniFilm.veza.veza;
				return true;
			}
			prethodniFilm = prethodniFilm.veza;
		}
		
		return false;
	}
	
	
	public boolean dodajGlumca(String naziv, String ime) {
		
		/**
		 * prvo trazimo film u koji ubacujemo glumca, zatim se provera-
		 * va da li takav glumac vec postoji u navedenom filmu i tek a-
		 * ko nije pronadjen se glumac dodaje u film
		 */
		
		Film ciljaniFilm = nadjiFilm(naziv);
		
		if (ciljaniFilm != null) {
			
			Glumac tekuciGlumac = ciljaniFilm.sadrzaj;
			boolean nadjen = false;
			
			while (tekuciGlumac != null && !nadjen) {
				
				if (tekuciGlumac.ime.equals(ime)) nadjen = true;
				tekuciGlumac = tekuciGlumac.veza;
			}
			
			if (!nadjen) {
				
				Glumac noviGlumac = new Glumac(ime);
				noviGlumac.veza = ciljaniFilm.sadrzaj;
				ciljaniFilm.sadrzaj = noviGlumac;
				return true;
				
			} else {
				
				System.out.println("Glumac se vec nalazi u zadatom filmu.");
				return false;
			}
			
		} else {
			
			System.out.println("Uneti film ne postoji.");
			return false;
		}
	}
	
	
	public boolean uFilmuGlumi(String naziv, String ime) {
		
		/**
		 * proveravamo pripadanje odredjenog glumca zadatom filmu i ta-
		 * ko sto trazimo zadati film, zatim proveravamo sve njegove g-
		 * lumce trazeci zadatog
		 */
		
		Film ciljaniFilm = nadjiFilm(naziv);
		
		if (ciljaniFilm != null) {
			
			Glumac tekuciGlumac = ciljaniFilm.sadrzaj;
			boolean nadjen = false;
			
			while (tekuciGlumac != null && !nadjen) {
				
				if (tekuciGlumac.ime.equals(ime)) nadjen = true;
				
				tekuciGlumac = tekuciGlumac.veza;
			}
			
			return nadjen;
			
		} else {
			System.out.println("Trazeni film ne postoji.");
			return false;
		}
	}
	
	
	public void brisiFilmoveSaGlumcem(String ime) {
		
		/**
		 * nakon provere da li je spisak prazan krecemo se po filmovima
		 * u spisku i ukoliko se u filmu nalazi zadati glumac uklanjamo
		 * taj film
		 */
		
		if (prviFilm != null) {
			
			Film tekuciFilm = prviFilm;
			
			while (tekuciFilm != null) {
				
				if (uFilmuGlumi(tekuciFilm.naziv, ime))
					obrisiFilm(tekuciFilm.naziv);
				
				tekuciFilm = tekuciFilm.veza;
			}
			
		} else {
			System.out.println("Spisak filmova je prazan.");
		}
		
	}
	
	
	public void stampajSpisak() {
		
		/**
		 * uredno stampanje spiska uz navodjenje svih glumaca
		 */
		
		if (prviFilm != null) {
			
			Film tekuciFilm = prviFilm;
			int count = 0;
			
			System.out.println("Filmovi: ");
			
			while (tekuciFilm != null) {
				
				count++;
				
				System.out.println(
					"Film #" + count + ": '" + tekuciFilm.naziv + "'");
				
				if (tekuciFilm.sadrzaj == null) {
					System.out.println("\tNema unetih glumaca.");
				} else {
					Glumac tekuciGlumac = tekuciFilm.sadrzaj;
					
					while (tekuciGlumac != null) {
						System.out.println("\t" + tekuciGlumac);
						tekuciGlumac = tekuciGlumac.veza;
					}
				}
				tekuciFilm = tekuciFilm.veza;
			}
			
		} else {
			System.out.println("Spisak je prazan.");
		}
	}
}

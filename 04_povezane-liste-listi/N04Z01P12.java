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

class N04Z01P12 {
	
	public static void main(String[] args) {
		
		SpisakFilmova spisak = new SpisakFilmova();
		
		spisak.dodajFilm("Dune");
			spisak.dodajGlumca("Kyle McLachlan", "Dune");
			spisak.dodajGlumca("Francesca Annis", "Dune");
			spisak.dodajGlumca("Max von Sydow", "Dune");
		spisak.dodajFilm("Blade Runner");
			spisak.dodajGlumca("Harrison Ford", "Blade Runner");
		spisak.dodajFilm("Dune");
		spisak.dodajFilm("Twin Peaks");
			spisak.dodajGlumca("Kyle McLachlan", "Twin Peaks");
			
		System.out.println(spisak);
		
		spisak.obrisiSveSaGlumcem("Kyle McLachlan");
		
		System.out.println(spisak);
	}
}

class SpisakFilmova {
	
	class Glumac {
		
		String m;
		Glumac veza;
		
		
		public Glumac(String m) {
			this.m = m;
			this.veza = null;
		}
		
		
		public String toString() {
			return m;
		}
	}
	
	
	public boolean dodajGlumca(String m, String n) {
		
		Film cilj = nadjiFilm(n);
		
		if (cilj == null)
			return false;
			
		Glumac novi = new Glumac(m);
		
		novi.veza = cilj.prviGlumac;
		cilj.prviGlumac = novi;
		
		return true;
	}
	
	
	private Glumac nadjiGlumca(String m, String n) {
		
		Film cilj = nadjiFilm(n);
		
		if (cilj == null)
			return null;
			
		Glumac tek = cilj.prviGlumac;
		
		while (tek != null) {
			
			if (tek.m.equals(m))
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	class Film {
		
		String n;
		Glumac prviGlumac;
		Film veza;
		
		
		public Film(String n) {
			this.n = n;
			this.prviGlumac = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ '" + n + "' : ";
			
			Glumac tek = prviGlumac;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Film prviFilm;
	
	
	public boolean dodajFilm(String n) {
		
		if (nadjiFilm(n) != null)
			return false;
			
		Film novi = new Film(n);
		
		novi.veza = prviFilm;
		prviFilm = novi;
		
		return true;
	}
	
	
	private Film nadjiFilm(String n) {
		
		if (prviFilm == null)
			return null;
			
		Film tek = prviFilm;
		
		while (tek != null) {
			
			if (tek.n.equals(n))
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public void obrisiSveSaGlumcem(String m) {
		
		if (prviFilm == null)
			return;
			
		while (prviFilm != null && nadjiGlumca(m, prviFilm.n) != null)
			prviFilm = prviFilm.veza;
			
		if (prviFilm == null)
			return;
			
		Film pret = prviFilm;
		
		while (pret.veza != null) {
			
			if (nadjiGlumca(m, pret.veza.n) != null)
				pret.veza = pret.veza.veza;
			else
				pret = pret.veza;
		}
	}
	
	
	public String toString() {
		
		String output = "SPISAK FILMOVA ";
		
		Film tek = prviFilm;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output;
	}
}

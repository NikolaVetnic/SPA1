class Z02P01 {
	
	public static void main(String[] args) {
		
		KnjigaRecepata knjiga = new KnjigaRecepata();
		
		knjiga.dodajRecept("Banana", true);
		
		knjiga.dodajRecept("Kajgana", false);
			knjiga.dodajSastojak("Jaje", 5, "Kajgana");
			knjiga.dodajSastojak("Slanina", 1, "Kajgana");
			
		knjiga.dodajRecept("Pasulj", false);
		
		knjiga.dodajRecept("Kupus salata", true);
			knjiga.dodajSastojak("Kupus", 1, "Kupus salata");
			knjiga.dodajSastojak("Sirce", 1, "Kupus salata");
		
		knjiga.dodajRecept("Ananas", true);
		
		System.out.println(knjiga);
		
		KnjigaRecepata pKnjiga = knjiga.izdvojPosne();
		
		System.out.println(knjiga);
		System.out.println(pKnjiga);
	}
}

class KnjigaRecepata {
	
	class Sastojak {
		
		String ime;
		int kol;
		Sastojak veza;
		
		
		public Sastojak(String ime, int kol) {
			this.ime = ime;
			this.kol = kol;
			this.veza = null;
		}
		
		
		public String toString() {
			return "( " + ime + ", kol.: " + kol + " )";
		}
	}
		
	
	class Recept {
		
		String naziv;
		boolean postan;
		Sastojak prviSastojak;
		Recept veza;
		
		
		public Recept(String naziv, boolean postan) {
			this.naziv = naziv;
			this.postan = postan;
			this.prviSastojak = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ '" + naziv + "' <";
			
			if (postan)
				output += "postan> : ";
			else
				output += "mrsan> : ";
				
			Sastojak tek = prviSastojak;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Recept prviRecept;
	
	
	public boolean dodajRecept(String naziv, boolean postan) {
		
		if (nadjiRecept(naziv) != null)
			return false;
			
		Recept novi = new Recept(naziv, postan);
		
		if (prviRecept == null || naziv.compareTo(prviRecept.naziv) < 0) {
			
			novi.veza = prviRecept;
			prviRecept = novi;
			
			return true;
		}
		
		Recept pret = prviRecept;
		
		while (pret.veza != null && naziv.compareTo(pret.veza.naziv) >= 0)
			pret = pret.veza;
			
		novi.veza = pret.veza;
		pret.veza = novi;
		
		return true;
	}
	
	
	public boolean dodajSastojak(String ime, int kol, String naziv) {
		
		Recept cilj = nadjiRecept(naziv);
		
		if (cilj == null)
			return false;
			
		Sastojak novi = new Sastojak(ime, kol);
		
		novi.veza = cilj.prviSastojak;
		cilj.prviSastojak = novi;
		
		return true;
	}
	
	
	public KnjigaRecepata izdvojPosne() {
		
		KnjigaRecepata pKnjiga = new KnjigaRecepata();
		
		Recept pom = null;
		
		while (prviRecept != null && prviRecept.postan) {
			
			pom = prviRecept.veza;
			
			prviRecept.veza = pKnjiga.prviRecept;
			pKnjiga.prviRecept = prviRecept;
			
			prviRecept = pom;
		}
		
		if (prviRecept == null) {
			
			pKnjiga.obrniListu();
			return pKnjiga;
		}
			
		Recept pret = prviRecept;
		
		while (pret.veza != null) {
			
			if (pret.veza.postan) {
				
				pom = pret.veza.veza;
				pret.veza.veza = pKnjiga.prviRecept;
				pKnjiga.prviRecept = pret.veza;
				
				pret.veza = pom;
			} else {
				
				pret = pret.veza;
			}
		}
		
		pKnjiga.obrniListu();
		
		return pKnjiga;
	}
	
	
	public void obrniListu() {
		
		if (prviRecept == null || prviRecept.veza == null)
			return;
		   
		Recept pret = null; 
		Recept tek = prviRecept;
		
		while (tek != null) {
			
			Recept sled = tek.veza;
			
			tek.veza = pret;
			pret = tek;
		
			tek = sled; 
		}
		
		prviRecept = pret;
	}
	
	
	private Recept nadjiRecept(String naziv) {
		
		Recept tek = prviRecept;
		
		while (tek != null) {
			
			if (tek.naziv.equals(naziv))
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public String toString() {
		
		String output = "KNJIGA RECEPATA : ";
		
		Recept tek = prviRecept;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output;
	}
}

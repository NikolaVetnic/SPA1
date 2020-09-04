/**
 * U klasi Automobil se u listi pamte automobili. Za svaki automobil se
 * pamti ime proizvođača, model i listu menjanih delova. Za svaki se d-
 * eo pamti kratak opis.
 * 
 * Glavna klasa treba da ima i jasan toString() metod (i ostale ga kla-
 * se mogu imati). Napisati program koji demonstrira napravaljene meto-
 * de.
 * 
 * Napraviti metod za dodavanje automobila u listu. Automobili se u li-
 * sti mogu ponavljati i sortirani su po imenu modela. Potrebno je nap-
 * raviti metod koji dodaje deo zadat rednim brojem u automobil. Ako a-
 * utomobil ne postoji odustati od dodavanja. Delovi ne treba da se po-
 * navljaju u listi.
 * 
 * Dodati metod koji izdvaja u novu listu sve automobile kojima je zad-
 * ati deo menjan. Ostali ostaju u ovoj listi. Ne zauzimati novu memor-
 * iju za automobile.
 */
 
class Z02P01 {
	
	public static void main(String[] args) {
		
		Automobili lista = new Automobili();
		
		lista.dodajAutomobil("Ford", "Escort");
		lista.dodajAutomobil("Mercedes", "Benz");
		lista.dodajAutomobil("Alfa", "Romeo");
		lista.dodajAutomobil("Fiat", "Punto");
		
		System.out.println(lista + "\n");
		
		lista.dodajDeo("Alfa", "Romeo", 0, "menjac");
		
		lista.dodajDeo("Mercedes", "Benz", 0, "volan");
		lista.dodajDeo("Mercedes", "Benz", 3, "sediste");
		lista.dodajDeo("Mercedes", "Benz", 0, "menjac");
		lista.dodajDeo("Mercedes", "Benz", 1, "jelkica");
		lista.dodajDeo("Mercedes", "Benz", 5, "brisac");
		System.out.println(lista + "\n");
		
		Automobili nova = lista.metodZadatka("menjac");
		
		System.out.println(lista + "\n");
		System.out.println(nova + "\n");
	}
}

class Automobili {
	 
	class Deo {
		 
		String opis;
		Deo veza;
		 
		 
		public Deo(String opis) {
			this.opis = opis;
			this.veza = null;
		}
		 
		 
		public String toString() {
			return opis;
		}
	}
	
	
	public boolean dodajDeo(String pr, String mod, int br, String opis) {
		
		Automobil cilj = nadjiAutomobil(pr, mod);
		
		if (cilj == null)
			return false;
			
		if (deoPostoji(cilj, opis))
			return false;
			
		Deo novi = new Deo(opis);
		
		if (br == 0) {
			
			novi.veza = cilj.prviDeo;
			cilj.prviDeo = novi;
			
			return true;
		}
		
		Deo pret = cilj.prviDeo;
		int count = 0;
		
		while (pret.veza != null && br > count) {
			
			pret = pret.veza;
			count++;
		}
		
		novi.veza = pret.veza;
		pret.veza = novi;
		
		return true;
	}
	
	
	private boolean deoPostoji(Automobil cilj, String opis) {
		
		if (cilj.prviDeo == null)
			return false;
			
		Deo tek = cilj.prviDeo;
		
		while (tek != null) {
			
			if (opis.equals(tek.opis))
				return true;
				
			tek = tek.veza;
		}
		
		return false;
	}
	 
	 
	class Automobil {
		 
		String pr, mod;
		Deo prviDeo;
		Automobil veza;
		 
		 
		public Automobil(String pr, String mod) {
			 
			this.pr = pr;
			this.mod = mod;
			this.prviDeo = null;
			this.veza = null;
		}
		 
		 
		public String toString() {
			 
			String output = "[ " + mod + " " + pr;
			 
			if (prviDeo == null)
				return output + " ]";
			else
				output += ", menjani delovi: ";
				
			Deo tek = prviDeo;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Automobil prvi;
	
	
	public void dodajAutomobil(String pr, String mod) {
		
		Automobil novi = new Automobil(pr, mod);
		
		if (prvi == null || novi.mod.compareTo(prvi.mod) < 0) {
			
			novi.veza = prvi;
			prvi = novi;
			
			return;
		}
		
		Automobil pret = prvi;
		
		while (pret.veza != null && novi.mod.compareTo(pret.veza.mod) >= 0)
			pret = pret.veza;
			
		novi.veza = pret.veza;
		pret.veza = novi;
		
		return;
	}
	
	
	public Automobili metodZadatka(String opis) {
		
		Automobili nova = new Automobili();
			
		while (prvi != null && deoPostoji(prvi, opis)) {
			
			Automobil tmp = prvi.veza;
			
			prvi.veza = nova.prvi;
			nova.prvi = prvi;
			
			prvi = tmp;
		}
		
		if (prvi == null) {
			
			return nova;
		} else {
			
			Automobil pret = prvi;
			
			while (pret.veza != null) {
				
				if (deoPostoji(pret.veza, opis)) {
					
					Automobil tmp = pret.veza.veza;
					
					pret.veza.veza = nova.prvi;
					nova.prvi = pret.veza;
					
					pret.veza = tmp;
				} else {
					
					pret = pret.veza;
				}
			}
		}
			
		nova.obrniListu();
		return nova;
	}
	
	
	private Automobil nadjiAutomobil(String pr, String mod) {
		
		if (prvi == null)
			return null;
			
		Automobil tek = prvi;
		
		while (tek != null) {
			
			if (pr.equals(tek.pr) && mod.equals(tek.mod))
				return tek;
			
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	private void obrniListu() {
		
		if (prvi == null || prvi.veza == null)
			return;
			
		Automobil pret = null;
		Automobil tek = prvi;
		
		while (tek != null) {
			
			Automobil sled = tek.veza;
			
			tek.veza = pret;
			pret = tek;
			
			tek = sled;
		}
		
		prvi = pret;
	}
	
	
	public String toString() {
		
		String output = "SPISAK : ";
		
		Automobil tek = prvi;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output;
	}
}

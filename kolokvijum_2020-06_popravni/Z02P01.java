/**
 * Napisati klasu Aerodrom koja predstavlja listu letova. Svaki let ima
 * šifru leta koja se sastoji od slova i cifara, broj sedišta, listu p-
 * utnika. Svaki putnik ima ime, masu prtljaga.
 * 
 * Glavna klasa treba da ima i jasan toString() metod (ostale klase ta-
 * kođe mogu imati). Napisati program koji demonstrira napravljene met-
 * ode.
 * 
 * Napraviti dodavanje leta u listu. Ne smeju postojati letovi sa istom
 * šifrom leta i lista uvek treba biti sortirana po šiframa leta. Napr-
 * aviti dodavanje putnika na let sa datom šifrom.
 * 
 * Dodati metod koji otkazuje (izbacuje) let A iz liste ako je to mogu-
 * će. Putnike prebaciti u avion u kome ima mesta za sve sa leta A, ali
 * poslednji takav u listi aviona. Ako nema takvog ne brisati let A. Ne
 * zauzimati novu memoriju za putnike.
 */ 

class Z02P01 {
	
	public static void main(String[] args) {
		
		Aerodrom a = new Aerodrom();
		
		a.dodajLet("AB033", 5);
		a.dodajPutnika("A", 20, "AB033");
		a.dodajPutnika("B", 52, "AB033");
		a.dodajPutnika("C", 8, "AB033");
		a.dodajPutnika("D", 8, "AB033");
		
		a.dodajLet("RU021", 12);
		a.dodajPutnika("Branislav Nestorovic", 18, "RU021");
		
		a.dodajLet("YU231", 6);
		
		System.out.println(a);
		System.out.println();
		
		a.izbaciLet("AB033");
		
		System.out.println(a);
		System.out.println();
	}
}

class Aerodrom {
	
	class Putnik {
		
		String ime;
		int m;
		Putnik veza;
		
		
		public Putnik(String ime, int m) {
			
			this.ime = ime;
			this.m = m;
			this.veza = null;
		}
		
		
		public String toString() {
			return "( " + ime + ", prtljag: " + m + " )";
		}
	}
	
	
	public boolean dodajPutnika(String ime, int m, String sifra) {
		
		Let cilj = nadjiLet(sifra);
		
		if (cilj == null)
			return false;
			
		Putnik novi = new Putnik(ime, m);
		
		novi.veza = cilj.prviPutnik;
		cilj.prviPutnik = novi;
		cilj.putnici++;
		
		return true;
	}
	
	
	class Let {
		
		String sifra;
		int broj;
		int putnici;
		Putnik prviPutnik;
		Let veza;
		
		
		public Let(String sifra, int broj) {
			
			this.sifra = sifra;
			this.broj = broj;
			this.putnici = 0;
			this.prviPutnik = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ Let '" + sifra + "', " + broj + " sedista : ";
			
			Putnik tek = prviPutnik;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			} 
			
			return output + "]";
		}
	}
	
	
	Let prviLet;
	
	
	public boolean dodajLet(String sifra, int broj) {
		
		if (nadjiLet(sifra) != null)
			return false;
			
		Let novi = new Let(sifra, broj);
		
		if (prviLet == null || sifra.compareTo(prviLet.sifra) < 0) {
			
			novi.veza = prviLet;
			prviLet = novi;
			
			return true;
		}
		
		Let pret = prviLet;
		
		while (pret.veza != null && sifra.compareTo(prviLet.sifra) > 0)
			pret = pret.veza;
		
		novi.veza = pret.veza;
		pret.veza = novi;
		
		return true;
	}
	
	
	public boolean izbaciLet(String sifra) {
		
		if (prviLet == null)
			return false;
		
		/**
		 * U jednom prolasku kroz listu tražimo let PRE onog koji izba-
		 * cujemo i ujedno broj putnika na letu. Posebno proveravamo p-
		 * rvi let, posebno sve ostale.
		 */
		 
		Let kaCilju = null;
		int putnici = 0;
		
		if (prviLet.sifra.equals(sifra)) {
			
			kaCilju = null;
			putnici = prviLet.putnici;
		} else {
			
			Let pret = prviLet;
			
			while (pret.veza != null && !pret.veza.sifra.equals(sifra))
				pret = pret.veza;
			
			kaCilju = pret;
			putnici = pret.veza.putnici;
		}
		
		/**
		 * U drugom prolasku tražimo poslednji let u listi koji može da
		 * preuzme sve putnike sa onog koji izbacujemo.
		 */
		
		Let tek = prviLet;
		Let slb = null;
		
		while (tek != null) {
			
			if (tek.broj - tek.putnici >= putnici && !tek.sifra.equals(sifra))
				slb = tek;
			
			tek = tek.veza;
		}
		
		/**
		 * Ako pogodan let ne postoji u listi, nećemo izbacivati let.
		 */
		
		if (slb == null)
			return false;
		
		/**
		 * Promenljiva kaCilju sada može ili biti null (što se dešava u
		 * slučaju da je traženi let prvi u listi) ili ne (što se deša-
		 * va kada je let na ne-prvom mestu u listu). Zavisno od situa-
		 * cije pokreće se jedna od dve grane za prebacivanje putnika i
		 * brisanje leta.
		 */
		
		if (kaCilju != null) {
			
			while (kaCilju.veza.prviPutnik != null) {
				
				Putnik pom = kaCilju.veza.prviPutnik.veza;
				
				kaCilju.veza.prviPutnik.veza = slb.prviPutnik;
				slb.prviPutnik = kaCilju.veza.prviPutnik;
				
				kaCilju.veza.prviPutnik = pom;
			}
			
			kaCilju.veza = kaCilju.veza.veza;
		} else {
			
			while (prviLet.prviPutnik != null) {
				
				Putnik pom = prviLet.prviPutnik.veza;
				
				prviLet.prviPutnik.veza = slb.prviPutnik;
				slb.prviPutnik = prviLet.prviPutnik;
				
				prviLet.prviPutnik = pom;
			}
			
			prviLet = prviLet.veza;
		}
		
		return true;
	}
	
	
	private Let nadjiLet(String sifra) {
		
		if (prviLet == null)
			return null;
			
		Let tek = prviLet;
		
		while (tek != null) {
			
			if (tek.sifra.equals(sifra))
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public String toString() {
		
		String output = "AERODROM : ";
		
		Let tek = prviLet;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output;
	}
}

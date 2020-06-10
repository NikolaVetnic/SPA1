/**
 * Klasa Ulica pamti listu kuća koje se nalaze u ulici. Za kuću se pam-
 * ti redni broj i vezuje se lista osoba koje žive u njoj, a lista kuća
 * je sortirana po rednim brojevima i redni brojevi su naravno jedinst-
 * veni. Za osobu se pamti lično ime kao jedan String, a osobe sa istim
 * imenom mogu živeti u istoj kući. Napraviti i testirati metode koji:
 * 1) Dodaje novu kuću sa datim brojem u listu.
 * 2) Dodaje osobu u kuću sa datim brojem. Osoba se ne može dodati u k-
 *    uću koja ne postoji, tj treba odustati od dodavanja osobe ako ku-
 *    ća ne postoji.
 * 3) Vraća broj kuće u kojoj ima najviše stanovnika sa imenom dužim od
 *    zadatog N.
 * 4) Prebacuje poslednju osobu iz date kuće A u kuću sa brojem B. Kuća
 *    može biti prazna, to jest ne treba je ukloniti ako nema više oso-
 *    ba u njoj. Ako kuća sa brojem B ne postoji izbaciti osobu iz lis-
 *    ti načisto.
 */
 
class N04Z02P13 {
	
	public static void main(String[] args) {
		
		Ulica ulica = new Ulica();
		
		ulica.dodajKucu(5);
			ulica.dodajOsobu("Svetozar", 5);
			ulica.dodajOsobu("Ira", 5);
		ulica.dodajKucu(6);
			ulica.dodajOsobu("Bojan", 6);
			ulica.dodajOsobu("Ana", 6);
		ulica.dodajKucu(5);
		ulica.dodajKucu(9);
			ulica.dodajOsobu("Vladimir", 9);
			ulica.dodajOsobu("Slavica", 9);
			
		System.out.println(ulica);
		
		ulica.prebrojOsobe(5);
		
		ulica.preseliPoslednjuOsobu(5, 9);
		System.out.println(ulica);
		
		ulica.preseliPoslednjuOsobu(5, 9);
		System.out.println(ulica);
	}
}

class Ulica {
	
	class Osoba {
		
		String m;
		Osoba veza;
		
		
		public Osoba(String m) {
			this.m = m;
			this.veza = null;
		}
		
		
		public String toString() {
			return m;
		}
	}
	
	
	public boolean dodajOsobu(String m, int b) {
		
		Kuca cilj = nadjiKucu(b);
		
		if (cilj == null)
			return false;
			
		Osoba nova = new Osoba(m);
		
		nova.veza = cilj.prvaOsoba;
		cilj.prvaOsoba = nova;
		
		return true;
	}
	
	
	class Kuca {
		
		int b;
		Osoba prvaOsoba;
		Kuca veza;
		
		
		public Kuca(int b) {
			this.b = b;
			this.prvaOsoba = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ Kuca br. " + b + ": ";
			
			Osoba tek = prvaOsoba;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Kuca prvaKuca;
	
	
	public boolean dodajKucu(int b) {
		
		if (nadjiKucu(b) != null)
			return false;
			
		Kuca nova = new Kuca(b);
		
		if (prvaKuca == null || b < prvaKuca.b) {
			
			nova.veza = prvaKuca;
			prvaKuca = nova;
			
			return true;
		}
		
		Kuca pret = prvaKuca;
		
		while (pret.veza != null && b > pret.veza.b)
			pret = pret.veza;
			
		nova.veza = pret.veza;
		pret.veza = nova;
		
		return true;
	}
	
	
	private Kuca nadjiKucu(int b) {
		
		if (prvaKuca == null)
			return null;
			
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			if (tek.b == b)
				return tek;
			
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public int prebrojOsobe(int duz) {
		
		if (prvaKuca == null)
			return -1;
			
		int count;
		int max = 0;
		int b = 0;
		
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			count = 0;
			
			Osoba osb = tek.prvaOsoba;
			
			while (osb != null) {
				
				if (osb.m.length() > duz)
					count++;
				
				osb = osb.veza;
			}
			
			if (count > max) {
				
				max = count;
				b = tek.b;
			}
			
			tek = tek.veza;
		}
		
		System.out.println("Najvise imena duzih od " + duz + " je u broju: " + b);
		return b;
	}
	
	
	public void preseliPoslednjuOsobu(int b1, int b2) {
		
		if (prvaKuca == null)
			return;
		
		Kuca k1 = null;
		Kuca k2 = null;
		
		Kuca tek = prvaKuca;
		
		while (tek != null && (k1 == null || k2 == null)) {
			
			if (tek.b == b1)
				k1 = tek;
			
			if (tek.b == b2)
				k2 = tek;
				
			tek = tek.veza;
		}
		
		if (k1.prvaOsoba == null)
			return;
			
		Osoba pom;
		
		if (k1.prvaOsoba.veza == null) {
			
			pom = k1.prvaOsoba.veza;
			
			if (k2 != null) {
				
				k1.prvaOsoba.veza = k2.prvaOsoba;
				k2.prvaOsoba = k1.prvaOsoba;
			}
			
			k1.prvaOsoba = pom;
			
			return;
		} else {
			
			Osoba pret = k1.prvaOsoba;
			
			while (pret.veza.veza != null)
				pret = pret.veza;
				
			if (k2 != null) {
				
				pom = pret.veza.veza;
				
				pret.veza.veza = k2.prvaOsoba;
				k2.prvaOsoba = pret.veza;
				
				pret.veza = pom;
			}
			
			return;
		}
	}
	
	
	public String toString() {
		
		String output = "ULICA ";
		
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output;
	}
}

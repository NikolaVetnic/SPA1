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
 
class N04Z02P11 {
	
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
	
	
	private Osoba izbaciPoslednju(int b) {
		
		Kuca cilj = nadjiKucu(b);
		
		if (cilj == null)
			return null;
		
		Osoba pom;
		
		if (cilj.prvaOsoba.veza == null) {
			
			pom = cilj.prvaOsoba;
			pom.veza = null;
			
			cilj.prvaOsoba = null;
			
			return pom;
		}
			
			
		Osoba pret = cilj.prvaOsoba;
		
		while (pret.veza.veza != null)
			pret = pret.veza;
			
		pom = pret.veza;
		pom.veza = null;
		
		pret.veza = pret.veza.veza;
		
		return pom;
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
			
			String output = "[ Kuca br. " + b + " : ";
			
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
	
	
	public int prebrojOsobe(int n) {
		
		if (prvaKuca == null)
			return -1;
			
		int count;
		int max = 0;
		int broj = 0;
		
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			count = 0;
			
			Osoba osb = tek.prvaOsoba;
			
			while (osb != null) {
				
				if (osb.m.length() > n)
					count++;
					
				osb = osb.veza;
			}
			
			if (count > max) {
				
				max = count;
				broj = tek.b;
			}
			
			tek = tek.veza;
		}
		
		System.out.println("Najveci broj imena duzih od " + n + ": " + broj);
		return broj;
	}
	
	
	public void preseliPoslednjuOsobu(int b1, int b2) {
		
		if (nadjiKucu(b1) == null)
			return;
		
		Osoba osb = izbaciPoslednju(b1);
		Kuca cilj = nadjiKucu(b2);
		
		if (osb == null || cilj == null)
			return;
		
		osb.veza = cilj.prvaOsoba;
		cilj.prvaOsoba = osb;
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

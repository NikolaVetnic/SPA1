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
 
class N04Z02P08 {
	
	public static void main(String[] args) {
		
		Ulica ulica = new Ulica();
		
		ulica.dodajKucu(5);
			ulica.dodajOsobu("Dragutin", 5);
			ulica.dodajOsobu("Jasminka", 5);
		ulica.dodajKucu(9);
			ulica.dodajOsobu("Fric", 9);
			ulica.dodajOsobu("Greta", 9);
			ulica.dodajOsobu("Dado", 9);
		ulica.dodajKucu(5);
		ulica.dodajKucu(6);
			ulica.dodajOsobu("Redzepa", 6);
			
			ulica.dodajOsobu("Ismet", 7);
		
		System.out.println(ulica);
		
		ulica.najviseOsoba(5);
		
		ulica.preseliPoslednjuOsobu(5, 9);
		System.out.println(ulica);
	}
}

class Ulica {
	
	class Osoba {
		
		String ime;
		Osoba veza;
		
		
		public Osoba(String ime) {
			this.ime = ime;
			this.veza = null;
		}
		
		
		public String toString() {
			return ime;
		}
	}
	
	
	public boolean dodajOsobu(String ime, int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null)
			return false;
			
		Osoba nova = new Osoba(ime);
		
		nova.veza = cilj.prvaOsoba;
		cilj.prvaOsoba = nova;
		
		return true;
	}
	
	
	public Osoba izbaciPoslednjuOsobu(int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null || cilj.prvaOsoba == null)
			return null;
			
		Osoba pom;
			
		if (cilj.prvaOsoba.veza == null) {
			
			pom = cilj.prvaOsoba;
			cilj.prvaOsoba = null;
			
			return pom;
		}
			
		Osoba pret = cilj.prvaOsoba;
		
		while (pret.veza.veza != null)
			pret = pret.veza;
			
		pom = pret.veza;
		pret.veza = null;
		
		return pom;
	}
	
	
	public boolean preseliPoslednjuOsobu(int broj1, int broj2) {
		
		Kuca cilj = nadjiKucu(broj2);
		
		if (nadjiKucu(broj1) == null || cilj == null)
			return false;
			
		Osoba tek = izbaciPoslednjuOsobu(broj1);
		
		tek.veza = cilj.prvaOsoba;
		cilj.prvaOsoba = tek;
		
		return true;
	}
	
	
	class Kuca {
		
		int broj;
		Osoba prvaOsoba;
		Kuca veza;
		
		
		public Kuca(int broj) {
			this.broj = broj;
			this.prvaOsoba = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ Kuca br. " + broj + " : ";
			
			Osoba tek = prvaOsoba;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Kuca prvaKuca;
	
	
	public boolean dodajKucu(int broj) {
		
		if (nadjiKucu(broj) != null)
			return false;
			
		Kuca nova = new Kuca(broj);
		
		if (prvaKuca == null || broj < prvaKuca.broj) {
			
			nova.veza = prvaKuca;
			prvaKuca = nova;
			
			return true;
		}
		
		Kuca pret = prvaKuca;
		
		while (pret.veza != null && broj >= pret.veza.broj)
			pret = pret.veza;
			
		nova.veza = pret.veza;
		pret.veza = nova;
		
		return true;
	}
	
	
	public Kuca nadjiKucu(int broj) {
		
		if (prvaKuca == null)
			return null;
			
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			if (tek.broj == broj)
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public Kuca nadjiPoslednjuKucu() {
		
		if (prvaKuca == null)
			return null;
			
		Kuca pret = prvaKuca;
		
		if (pret.veza != null)
			pret = pret.veza;
			
		return pret;
	}
	
	
	public int najviseOsoba(int duz) {
		
		if (prvaKuca == null)
			return -1;
			
		Kuca tek = prvaKuca;
		
		int count;
		int max = 0;
		int broj = 0;
		
		while (tek != null) {
			
			count = 0;
			
			Osoba osb = tek.prvaOsoba;
			
			while (osb != null) {
				
				if (osb.ime.length() > duz)
					count++;
				
				osb = osb.veza;
			}
			
			if (count > max) {
				max = count;
				broj = tek.broj;
			}
			
			tek = tek.veza;
		}
		
		System.out.println("Najveci broj ljudi imena duzeg od " + duz + " je u kuci br. " + broj + ".");
		return broj;
	}
	
	
	public String toString() {
		
		String output = "ULICA ";
		
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output + "\n";
	}
}

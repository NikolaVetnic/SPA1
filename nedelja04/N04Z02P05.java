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
 
class N04Z02P05 {
	
	public static void main(String[] args) {
		
		Ulica ulica = new Ulica();
		
		ulica.dodajKucu(9);
			ulica.dodajOsobu("Dragan", 9);
			ulica.dodajOsobu("Ana", 9);
		ulica.dodajKucu(9);
		ulica.dodajKucu(5);
			ulica.dodajOsobu("Dragutin", 5);
			ulica.dodajOsobu("Bosiljka", 5);
		ulica.dodajKucu(6);
			ulica.dodajOsobu("Rastko", 6);
			ulica.dodajOsobu("Prvoslav", 6);
		
		System.out.println(ulica);
		
		ulica.prebrojOsobe(6);
		
		ulica.preseliPoslednjuOsobu(9, 5);
		System.out.println(ulica);
		
		ulica.preseliPoslednjuOsobu(9, 5);
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
	
	
	public int prebrojOsobe(int duz) {
		
		if (prvaKuca == null)
			return -1;
			
		int count;
		int max = 0;
		int ind = 0;
		
		Kuca tek = prvaKuca;
		
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
				ind = tek.broj;
			}
			
			tek = tek.veza;
		}
		
		System.out.println("Najvise osoba imena duzih od " + duz + " je u kuci br. " + ind + ".");
		return ind;
	}
	
	
	public Osoba nadjiPoslednjuOsobu(int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null || cilj.prvaOsoba == null)
			return null;
		
		Osoba pret = cilj.prvaOsoba;
		
		while (pret.veza != null)
			pret = pret.veza;
			
		return pret;
	}
	
	
	public void preseliPoslednjuOsobu(int broj1, int broj2) {
		
		Kuca start = nadjiKucu(broj1);
		Kuca cilj = nadjiKucu(broj2);
		
		if (start == null)
			return;
			
		Osoba osb = nadjiPoslednjuOsobu(start.broj);
		
		if (osb == null)
			return;
			
		izbaciOsobu(osb.ime, start.broj);
		
		if (cilj != null) {
			osb.veza = cilj.prvaOsoba;
			cilj.prvaOsoba = osb;
		}
	}
	
	
	public boolean izbaciOsobu(String ime, int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null || cilj.prvaOsoba == null)
			return false;
			
		if (cilj.prvaOsoba.ime.equals(ime)) {
			
			cilj.prvaOsoba = cilj.prvaOsoba.veza;
			return true;
		}
		
		Osoba pret = cilj.prvaOsoba;
		
		while (pret.veza != null) {
			
			if (pret.veza.ime.equals(ime)) {
				
				pret.veza = pret.veza.veza;
				return true;
			}
			
			pret = pret.veza;
		}
		
		return false;
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
		
		while (pret.veza != null && broj > pret.veza.broj)
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
	
	
	public Kuca poRedu(int ind) {
		
		if (prvaKuca == null)
			return null;
			
		Kuca tek = prvaKuca;
		int count = 0;
		
		while (tek != null) {
			
			if (count == ind)
				return tek;
				
			tek = tek.veza;
			count++;
		}
		
		return null;
	}
	
	
	public int prebrojKuce() {
		
		if (prvaKuca == null)
			return 0;
			
		Kuca tek = prvaKuca;
		int count = 0;
		
		while (tek != null) {
			
			count++;
			tek = tek.veza;
		}
		
		return count;
	}
	
	
	public boolean izbaciKucu(int broj) {
		
		if (prvaKuca == null)
			return false;
		
		if (prvaKuca.broj == broj) {
			
			prvaKuca = prvaKuca.veza;
			return true;
		}
		
		Kuca pret = prvaKuca;
		
		while (pret.veza != null) {
			
			if (pret.veza.broj == broj) {
				
				pret.veza = pret.veza.veza;
				return true;
			}
			
			pret = pret.veza;
		}
		
		return false;
	}
	
	
	public String toString() {
		
		String output = "ULICA ";
		
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			output += tek;
			tek = tek.veza;
		}
		
		return output + "\n";
	}
}

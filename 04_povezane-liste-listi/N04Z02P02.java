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

class N04Z02P01 {
	
	public static void main(String[] args) {
		
		Ulica ulica = new Ulica();
		
		ulica.dodajKucu(5);
		ulica.dodajKucu(6);
		ulica.dodajKucu(5);
		ulica.dodajKucu(9);
		
		ulica.dodajOsobu("Dragan", 5);
		ulica.dodajOsobu("Sab", 5);
		ulica.dodajOsobu("Sara", 5);
		
		ulica.dodajOsobu("Milanka", 6);
		ulica.dodajOsobu("Lazar", 6);
		
		System.out.println(ulica);
		
		ulica.prebrojOsobe(4);
		
		ulica.preseliPoslednjuOsobu(5, 6);
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
		
		if (prvaKuca != null) {
			
			Kuca tek = prvaKuca;
			int[] rez = new int[prebrojKuce()];
			int count = 0;
			
			while (tek != null) {
				
				Osoba tekO = tek.prvaOsoba;
				
				while (tekO != null) {
					if (tekO.ime.length() > duz)
						rez[count]++;
					
					tekO = tekO.veza;
				}
				
				tek = tek.veza;
				count++;
			}
			
			int max = rez[0];
			int ind = 0;
			
			for (int i = 1; i < rez.length; i++)
				if (rez[i] > max) {
					max = rez[i];
					ind = i;
				}
			
			System.out.println(
				"Kuca broj " + nadjiKucuPoRedu(ind).broj + " ima najvise osoba sa imenom duzim od " + duz + ".");
			
			return nadjiKucuPoRedu(ind).broj;
			
		} else {
			
			return -1;
		}
	}
	
	
	public Osoba nadjiOsobu(String ime, int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null)
			return null;
			
		Osoba tek = cilj.prvaOsoba;
		
		if (tek == null)
			return null;
		
		while (tek != null) {
			if (tek.ime.equals(ime))
				return tek;
			
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public Osoba nadjiPoslednjuOsobu(int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null)
			return null;
			
		Osoba pret = cilj.prvaOsoba;
		
		if (pret == null)
			return null;
		
		while (pret.veza != null)
			pret = pret.veza;
		
		return pret;
	}
	
	
	public boolean izbaciOsobu(String ime, int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null)
			return false;
			
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
		
	
	
	public void preseliPoslednjuOsobu(int broj1, int broj2) {
		
		Kuca polaz = nadjiKucu(broj1);
		Kuca cilj = nadjiKucu(broj2);
		
		if (polaz == null || cilj == null)
			return;
			
		Osoba tek = nadjiPoslednjuOsobu(polaz.broj);
		
		if (tek == null)
			return;
		
		izbaciOsobu(tek.ime, polaz.broj);
			
		tek.veza = cilj.prvaOsoba;
		cilj.prvaOsoba = tek;
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
			String output = "[ Kuca br. " + broj + ": ";
			
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
		
		while (pret.veza != null && broj >pret.veza.broj)
			pret = pret.veza;
		
		nova.veza = pret.veza;
		pret.veza = nova;
		return true;
	}
	
	
	public Kuca nadjiKucu(int broj) {
		
		if (prvaKuca != null) {
			
			if (broj < prvaKuca.broj)
				return null;
				
			Kuca tek = prvaKuca;
			
			while (tek != null) {
				if (tek.broj == broj)
					return tek;
				
				tek = tek.veza;
			}
			
			return tek;
			
		} else {
			
			return null;
		}
	}
	
	
	public Kuca nadjiKucuPoRedu(int broj) {
		
		if (prvaKuca != null) {
			
			Kuca tek = prvaKuca;
			int count = 0;
			
			while (tek != null) {
				if (count == broj)
					return tek;
				
				tek = tek.veza;
				count++;
			}
			
			return null;
			
		} else {
			
			return null;
		}
	}
	
	
	public int prebrojKuce() {
		
		if (prvaKuca != null) {
			
			int count = 0;
			Kuca tek = prvaKuca;
			
			while (tek != null) {
				count++;
				tek = tek.veza;
			}
			
			return count;
		} else {
			
			return -1;
		}
	}
	
	
	public String toString() {
		
		String output = "ULICA ";
		
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			output += tek;
			tek = tek.veza;
		}
		
		return output;
	}
}

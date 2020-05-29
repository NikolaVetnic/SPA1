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

class N04Z02 {
	
	public static void main(String[] args) {
		
		System.out.println();
		
		Ulica ulica = new Ulica();
		
		ulica.dodajKucu(6);
		ulica.dodajKucu(5);
		ulica.dodajKucu(9);
		ulica.dodajKucu(5);
		System.out.println();
		
		ulica.stampajUlicu();
		
		ulica.useliOsobu("Milan", 5);
		ulica.useliOsobu("Franjo", 5);
		ulica.useliOsobu("Velimir", 5);
		ulica.useliOsobu("Pribislav", 5);
		ulica.useliOsobu("Stjepan", 5);
		
		ulica.useliOsobu("Marko", 6);
		ulica.useliOsobu("Ivan", 6);
		ulica.useliOsobu("Ivo", 6);
		
		ulica.useliOsobu("Domagoj", 9);
		ulica.useliOsobu("Prvoslav", 9);
		ulica.useliOsobu("Vatroslav", 9);
		
		ulica.useliOsobu("Aleksandar", 10);
		
		System.out.println();
		
		ulica.stampajUlicu();
		
		int n = 5;
		System.out.println("Najvise ukucana sa imenom duzim od " + n + " je u kuci: " + ulica.najviseUkucana(n));
		System.out.println();
		
		ulica.preseliPoslednjuOsobu(9, 5);
		ulica.preseliPoslednjuOsobu(6, 5);
		ulica.preseliPoslednjuOsobu(9, 5);
		ulica.stampajUlicu();
		
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
	
	
	class Kuca {
		
		int broj;
		Osoba ukucani;
		Kuca veza;
		
		
		public Kuca(int broj) {
			this.broj = broj;
			this.ukucani = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ Kuca br. " + broj + ": ";
			
			Osoba tekucaOsoba = ukucani;
			
			while (tekucaOsoba != null) {
				
				output += tekucaOsoba.ime + " ";
				tekucaOsoba = tekucaOsoba.veza;
			}
			return output + "]";
		}
	}
	
	
	Kuca prvaKuca;
	int brojKuca;
	
	
	public Ulica() {
		this.prvaKuca = null;
		this.brojKuca = 0;
	}
	
	
	public boolean useliOsobu(String ime, int broj) {
		
		Kuca ciljnaKuca = nadjiKucu(broj);
		
		if (ciljnaKuca != null) {
			
			Osoba novaOsoba = new Osoba(ime);
			novaOsoba.veza = ciljnaKuca.ukucani;
			ciljnaKuca.ukucani = novaOsoba;
			
			return true;
			
		} else {
			
			System.out.println("Kuca br. " + broj + " ne postoji.");
			return false;
		}
	}
	
	
	public Osoba iseliPoslednjuOsobuIzKuce(int broj) {
		
		/**
		 * prvo se proverava da li trazena kuca uopste postoji i ako p-
		 * ostoji, proverava se da li neko u njoj zivi i ako zivi poci-
		 * nje kretanje kroz listu tako da se zaustavimo na drugoj oso-
		 * bi od kraja; kada se to desi, trazena osoba nam je poslednja
		 * u listi, pa je prebacujemo u ciljanu osobu a istovremeno sp-
		 * ajamo vezu druge od kraja na null buduci da je ona novi kraj
		 * liste
		 */
		
		Kuca ciljnaKuca = nadjiKucu(broj);
		
		if (ciljnaKuca != null) {
			
			if (ciljnaKuca.ukucani != null) {
				
				Osoba prethodnaOsoba = ciljnaKuca.ukucani;
				
				while (prethodnaOsoba.veza.veza != null)
					prethodnaOsoba = prethodnaOsoba.veza;
				
				Osoba ciljnaOsoba = prethodnaOsoba.veza;
				prethodnaOsoba.veza = null;
				
				return ciljnaOsoba;
				
			} else {
				
				System.out.println("U trazenoj kuci niko ne zivi.");
				return null;
			}
			
		} else {
			
			System.out.println("Trazena kuca ne postoji.");
			return null;
		}
	}
	
	
	public boolean preseliPoslednjuOsobu(int broj1, int broj2) {
		
		/**
		 * iseljavamo osobu iz trazene kuce (taj metod ce nam vec izvr-
		 * siti sve neophodne provere) i ako smo u tome uspeli krece p-
		 * otraga ciljane kuce, ako ona uspe useljavamo osobu u ciljanu
		 * kucu
		 */
		
		Osoba izbeglica = iseliPoslednjuOsobuIzKuce(broj1);
		
		if (izbeglica == null)
			return false;
		
		Kuca ciljnaKuca = nadjiKucu(broj2);
		
		if (ciljnaKuca != null) {
			
			useliOsobu(izbeglica.ime, ciljnaKuca.broj);
			return true;
			
		} else {
			System.out.println("Ciljna kuca ne postoji.");
			return false;
		}
	}
	
	
	public boolean dodajKucu(int broj) {
		
		/**
		 * prvo kreiramo objekat nove kuce - lakse je kreirati ga na p-
		 * ocetku s obzirom koliko scenarija ima gde se kuca uspesno d-
		 * odaje; nakon toga proveravamo da li je ulica prazna i ako j-
		 * este onda je nova kuca dodata na pocetak; ukoliko nije prov-
		 * eravamo da li je uneti broj manji od prve kuce i ako je tako
		 * dodaje se ispred svih, odnosno opet na pocetak; ukoliko nije
		 * tako krecemo kroz listu kuca i idemo sve dok je uneseni broj
		 * manji od broja kuce koja se gleda - kada to vise nije slucaj
		 * proveravamo da li je broj trenutne kuce bas jednak broju un-
		 * ete kuce i ako jeste kucu ne unosimo, a ako nije unosimo ku-
		 * cu tako sto je ubacujemo izedju prethodne i tekuce
		 */
		
		Kuca novaKuca = new Kuca(broj);
		
		if (prvaKuca == null) {
			
			novaKuca.veza = prvaKuca;
			prvaKuca = novaKuca;
			brojKuca++;
			
			return true;
			
		} else {
			
			if (broj < prvaKuca.broj) {
				
				novaKuca.veza = prvaKuca;
				prvaKuca = novaKuca;
				brojKuca++;
				
				return true;
				
			} else {
				
				Kuca prethodnaKuca = prvaKuca;
				
				while (prethodnaKuca.veza != null && broj > prethodnaKuca.veza.broj)
					prethodnaKuca = prethodnaKuca.veza;
					
				if (prethodnaKuca.veza != null) {
					
					if (prethodnaKuca.broj != broj) {
						
						novaKuca.veza = prethodnaKuca.veza.veza;
						prethodnaKuca.veza = novaKuca;
						brojKuca++;
						
						return true;
						
					} else {
						
						System.out.println("Kuca sa unetim brojem vec postoji.");
						return false;
						
					}
					
				} else {
					
					prethodnaKuca.veza = novaKuca;
					novaKuca.veza = null;
					brojKuca++;
					
					return true;
					
				}
				
			}
			
		}
		
	}
	
	
	public Kuca nadjiKucu(int broj) {
		
		/**
		 * krecemo od prve kuce i idemo redom sve dok ili ne dodjemo do
		 * kraja liste ili uneti broj ne postane manji od tekuce kuce i
		 * tada se proverava da li nismo na kraju i da li je broj teku-
		 * ce kuce jednak broju unete (sto vraca tekucu) ili se kao re-
		 * zultat vraca null jer kuca unetog broja nije pronadjen
		 */
		
		Kuca tekucaKuca = prvaKuca;
		
		while (tekucaKuca != null && broj > tekucaKuca.broj)
			tekucaKuca = tekucaKuca.veza;
		
		if (tekucaKuca != null && tekucaKuca.broj == broj)
			return tekucaKuca;
		else
			return null;
	}
	
	
	public int najviseUkucana(int duzina) {
		
		/**
		 * prvo se proverava kao i uvek da li je prva kuca uopste u sp-
		 * isku, ako jeste krecemo kroz listu kuca i deklarisemo celob-
		 * rojni niz koji ce nam cuvati podatke po kucama o broju osoba
		 * cija su imena duza od unete vrednosti; tada krecemo po spis-
		 * ku osoba svake kuce i povecavamo brojac ukoliko je ime osobe
		 * duze od zadate vrednosti; na kraju for petljom trazimo maks-
		 * imalnu vrednost tog niza i to vracamo kao rezultat; u sluca-
		 * ju da je lista prazna vracamo -1 po dogovoru
		 */
		
		if (prvaKuca != null) {
			
			Kuca tekucaKuca = prvaKuca;
			
			int[] brojevi = new int[brojKuca];
			int count = 0;
			
			while (tekucaKuca != null) {
				
				if (tekucaKuca.ukucani != null) {
					
					Osoba tekucaOsoba = tekucaKuca.ukucani;
					
					while (tekucaOsoba != null) {
						
						if (tekucaOsoba.ime.length() > duzina)
							brojevi[count]++;
						
						tekucaOsoba = tekucaOsoba.veza;
					}
				}
				
				count++;
				tekucaKuca = tekucaKuca.veza;
			}
			
			int max = brojevi[0];
			
			for (int i = 1; i < brojevi.length; i++)
				if (brojevi[i] > max)
					max = brojevi[i];
			
			return max;
			
		} else {
			
			System.out.println("Lista je prazna.");
			return -1;
		}
			
		
	}
	
	
	public void stampajUlicu() {
		
		if (prvaKuca != null) {
			
			Kuca tekucaKuca = prvaKuca;
			
			System.out.println("Ulica: ");
			
			while (tekucaKuca != null) {
				System.out.println(tekucaKuca);
				tekucaKuca = tekucaKuca.veza;
			}
			
		} else {
			System.out.println("Ulica je prazna.");
		}
	}
}



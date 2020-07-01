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

class N04Z02P14 {
	
	public static void main(String[] args) {
		
		System.out.println();
		
		Ulica mojaUlica = new Ulica();
		
		mojaUlica.dodajKucu(6);
		mojaUlica.dodajKucu(5);
		mojaUlica.dodajKucu(9);
		mojaUlica.dodajKucu(5);
		System.out.println();
		
		mojaUlica.stampajUlicu();
			
		mojaUlica.useliOsobu("Milan", 5);
		mojaUlica.useliOsobu("Franjo", 5);
		mojaUlica.useliOsobu("Velimir", 5);
		mojaUlica.useliOsobu("Pribislav", 5);
		mojaUlica.useliOsobu("Stjepan", 5);
		
		mojaUlica.useliOsobu("Marko", 6);
		mojaUlica.useliOsobu("Ivan", 6);
		mojaUlica.useliOsobu("Ivo", 6);
		
		mojaUlica.useliOsobu("Domagoj", 9);
		mojaUlica.useliOsobu("Prvoslav", 9);
		mojaUlica.useliOsobu("Vatroslav", 9);
		
		mojaUlica.useliOsobu("Aleksandar", 10);
		
		System.out.println();
		
		mojaUlica.stampajUlicu();
		
		int n = 5;
		System.out.println("Najvise ukucana sa imenom duzim od " + n + " je u kuci: " + mojaUlica.najviseUkucana(n));
		System.out.println();
		
		mojaUlica.preseliPoslednjuOsobu(9, 5);
		mojaUlica.preseliPoslednjuOsobu(6, 5);
		mojaUlica.preseliPoslednjuOsobu(9, 5);
		mojaUlica.stampajUlicu();
		
	}
	
} 

class Ulica {
	
	private Kuca prvaKuca;
	public int brojKuca = 0;
	
	
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
	}	// unutrasnja klasa
	
	
	class Kuca {
		
		int broj;
		Kuca veza;
		Osoba prvaOsoba;
		
		
		public Kuca(int broj) {
			this.broj = broj;
			this.veza = null;
		}	// konstruktor
		
		
		public boolean jePrazna() {
			
			return prvaOsoba == null;
		}
		
		
		public String toString() {
			
			String output = "[ Kuca br. " + broj + ":";
			Osoba tekuci = prvaOsoba;
			
			if (prvaOsoba != null) {
				
				output += " " + prvaOsoba;
				tekuci = tekuci.veza;
				
				while (tekuci != null) {
					
					output += ", " + tekuci;
					tekuci = tekuci.veza;
				}
			}
			
			return output + " ]";
		}	// toString
	}	// unutrasnja lista
	
	
	public void dodajKucu(int broj) {
		
		Kuca prethodnaKuca = prvaKuca;
		Kuca novaKuca = new Kuca(broj);
		
		if (prvaKuca == null) {
			
			prvaKuca = novaKuca;
			brojKuca++;
		} else {	
				
			if (broj < prvaKuca.broj) {
				
				novaKuca.veza = prvaKuca;
				prvaKuca = novaKuca;
				brojKuca++;
			} else {
				
				while(prethodnaKuca.veza != null && broj > prethodnaKuca.broj)
					prethodnaKuca = prethodnaKuca.veza;
				
				if (prethodnaKuca.veza != null) {
					
					if (prethodnaKuca.broj != broj) {
						
						novaKuca = prethodnaKuca.veza.veza;
						prethodnaKuca.veza = novaKuca;
						brojKuca++;
					} else {
						
						System.out.println("Kuca sa brojem " + broj + " vec postoji.");
					}
				} else {
					
					prethodnaKuca.veza = novaKuca;
					brojKuca++;
				}
			}
		}
	}	// dodavanje novih kuca uz automatsko sortiranje
	
	
	public boolean postojiKuca(int broj) {
		
		if (prvaKuca != null) {
			
			Kuca tekucaKuca = prvaKuca;
			
			while (tekucaKuca != null && tekucaKuca.broj != broj)
				tekucaKuca = tekucaKuca.veza;
			
			return tekucaKuca != null;
		} else {
			
			return false;
		}
	}	// proverava da li postoji kuca sa zadatim brojem
	
	
	public Kuca nadjiKucu(int broj) {
		
		if (prvaKuca != null) {
			
			Kuca tekucaKuca = prvaKuca;
			
			while (tekucaKuca != null && tekucaKuca.broj != broj)
				tekucaKuca = tekucaKuca.veza;
			
			if (tekucaKuca != null)
				return tekucaKuca;
			else
				return null;
		} else {
			
			return null;
		}
	}	// pronalazi kucu sa datim brojem
	
	
	public boolean useliOsobu(String osoba, int broj) {
		
		Kuca ciljanaKuca = nadjiKucu(broj);
		
		if (ciljanaKuca != null) {
			
			Osoba novaOsoba = new Osoba(osoba);
			
			novaOsoba.veza = ciljanaKuca.prvaOsoba;
			ciljanaKuca.prvaOsoba = novaOsoba;
			
			return true;
		} else {
			
			System.out.println("Osoba " + osoba + " se ne moze useliti u nepostojecu kucu.");
			return false;
		}
	}	// useljava osobu u kucu
	
	
	public Osoba iseliOsobuIzKuce(String osoba, int broj) {
		
		Kuca ciljanaKuca = nadjiKucu(broj);
		
		if (ciljanaKuca != null) {
			
			if (ciljanaKuca.prvaOsoba != null) {
				
				Osoba prethodnaOsoba = ciljanaKuca.prvaOsoba;
				
				while (prethodnaOsoba.veza != null && prethodnaOsoba.veza.equals(osoba))
					prethodnaOsoba = prethodnaOsoba.veza;
				
				if (prethodnaOsoba.veza != null) {
					
					Osoba trazenaOsoba = prethodnaOsoba.veza;
					prethodnaOsoba.veza = prethodnaOsoba.veza.veza;
					return trazenaOsoba;
				}
			}
		}
		
		return null;
	}
	
	
	public Osoba iseliPoslednjuOsobuIzKuce(int broj) {
		
		Kuca ciljanaKuca = nadjiKucu(broj);
		
		if (ciljanaKuca != null) {
			
			if (ciljanaKuca.prvaOsoba != null) {
				
				Osoba tekucaOsoba = ciljanaKuca.prvaOsoba;
				Osoba prethodnaOsoba = null;
				
				while (tekucaOsoba.veza != null) {
					
					prethodnaOsoba = tekucaOsoba;
					tekucaOsoba = tekucaOsoba.veza;
				}
				
				Osoba trazenaOsoba = tekucaOsoba;
				prethodnaOsoba.veza = null;
				return trazenaOsoba;
			}
		}
		
		return null;
	}
	
	
	public void preseliPoslednjuOsobu(int broj1, int broj2) {
		
		Osoba izbeglica = iseliPoslednjuOsobuIzKuce(broj1);
		
		if (izbeglica != null) {
			
			Kuca ciljanaKuca = nadjiKucu(broj2);
			
			if (ciljanaKuca != null)
				useliOsobu(izbeglica.ime, broj2);
		}
		
		return;
	}
	
	
	public int najviseUkucana(int n) {
		
		int maksOsoba = 0;
		int brojKuce = -1;
		
		if (prvaKuca != null) {
			
			Kuca tekucaKuca = prvaKuca;
			
			while (tekucaKuca != null) {
				
				int brojOsoba = 0;
				
				if (!tekucaKuca.jePrazna()) {
					
					Osoba tekucaOsoba = tekucaKuca.prvaOsoba;
					
					while (tekucaOsoba != null) {
						
						int duz = tekucaOsoba.ime.length();
						
						if (duz > n)
							brojOsoba++;
						
						if (brojOsoba > maksOsoba) {
							
							maksOsoba = brojOsoba;
							brojKuce = tekucaKuca.broj;
						}
							
						tekucaOsoba = tekucaOsoba.veza;
					}
				}
				
				tekucaKuca = tekucaKuca.veza;
			}
		}
		
		return brojKuce;
	}	// trazi kucu sa najvise ukucana sa imenima duzim od zadate duzine
	
	
	public String toString() {
		
		String output = "Ulica:";
		Kuca tekuci = prvaKuca;
		
		while (tekuci != null) {
			
			output += " " + tekuci;
			tekuci = tekuci.veza;
		}
		
		return output;
	}	// toString
	
	
	public void stampajUlicu() {
		
		if (prvaKuca == null) {
			
			System.out.println("Ulica nema kuca.");
		} else {
			
			System.out.println("Kuce: ");
			Kuca tekucaKuca = prvaKuca;
			
			while (tekucaKuca != null) {
				
				if (tekucaKuca.prvaOsoba == null) {
					
					System.out.println("U kuci br. " + tekucaKuca.broj + " niko ne zivi.");
				} else {
					
					System.out.println("U kuci br. " + tekucaKuca.broj + " zive: ");
					
					Osoba tekucaOsoba = tekucaKuca.prvaOsoba;
					
					while (tekucaOsoba != null) {
						
						System.out.println("\t" + tekucaOsoba);
						tekucaOsoba = tekucaOsoba.veza;
					}
				}
				
				tekucaKuca = tekucaKuca.veza;
			}
		}
		
		System.out.println();
	}	// (uredno) stampanje ulice na ekran
}

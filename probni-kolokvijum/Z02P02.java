/**
 * Napisati klasu LanacHotela koja predstavlja jednostruko-povezanu li-
 * stu hotela u nekom lancu hotela. Svaki hotel ima ime, te grad u koj-
 * em je lociran i listu zaposlenih radnika. Svaki radnik ima ime, pla-
 * tu i godine radnog staža.
 * 
 * U listi ne smeju postojati hoteli sa istim imenom i lista mora u sv-
 * akom trenutku biti sortirana po imenu grada. Napraviti dodavanje ho-
 * tela. Napraviti dodavanje radnika u hotel. Dodati metode koji:
 * a) za hotele iz datog grada računa prosek plata svih zaposlenih rad-
 *    nika.
 * b) zatvara hotel (briše ga iz liste hotela). Radnici ovog hotela ko-
 *    ji imaju jednocifrene godine radnog staža se otpuštaju, a preost-
 *    ali se prebacuju u poslednji hotel u listi (ako je moguće). Real-
 *    izovati bez ponovnog zauzimanja memorije. 
 * 
 * Klase treba da imaju odgovarajuće toString() metode. Napisati progr-
 * am koji demonstrira napravljene metode.
 */
 
class Z02P02 {
	
	public static void main(String[] args) {
		
		LanacHotela lanac = new LanacHotela();
		
		lanac.dodajHotel("Sloboda", "Xabac");
			lanac.dodajRadnika("Miljan", 4.50, 5, "Sloboda");
			lanac.dodajRadnika("Dragan", 4.50, 25, "Sloboda");
		lanac.dodajHotel("Centar", "Novi Sad");
			lanac.dodajRadnika("Zoran", 2.50, 3, "Centar");
			lanac.dodajRadnika("Saban", 0.50, 43, "Centar");
		lanac.dodajHotel("Hilton", "Beograd");
			lanac.dodajRadnika("Redzep", 2.50, 13, "Hilton");
			lanac.dodajRadnika("Sabo", 0.50, 2, "Hilton");
		
			lanac.dodajRadnika("Dusko", 0.50, 2, "Kopaonik");
			lanac.dodajRadnika("Gegan", 0.50, 2, "Slavija");
		System.out.println(lanac);
		System.out.println();
		
		System.out.println("Prosek plata u Centru: " + lanac.prosekPlata("Centar"));
		System.out.println();
		
		lanac.zatvoriHotel("Sloboda");
		System.out.println(lanac);
		System.out.println();
		
		lanac.zatvoriHotel("Hilton");
		System.out.println(lanac);
		System.out.println();
		
		lanac.zatvoriHotel("Centar");
		System.out.println(lanac);
		System.out.println();
	}
}

class LanacHotela {
	
	class Radnik {
		
		String ime;
		double plata;
		int god;
		Radnik veza;
		
		
		public Radnik(String ime, double plata, int god) {
			this.ime = ime;
			this.plata = plata;
			this.god = god;
			this.veza = null;
		}
		
		
		public String toString() {
			return ime + " " + plata + " " + god;
		}
	}
	
	
	public boolean dodajRadnika(String ime, double plata, int god, String naziv) {
		
		Hotel cilj = nadjiHotel(naziv);
		
		if (cilj == null)
			return false;
			
		Radnik novi = new Radnik(ime, plata, god);
		
		novi.veza = cilj.prviRadnik;
		cilj.prviRadnik = novi;
			
		return true;
	}
	
	
	class Hotel {
		
		String naziv, grad;
		Radnik prviRadnik;
		Hotel veza;
		
		
		public Hotel(String naziv, String grad) {
			this.naziv = naziv;
			this.grad = grad;
			this.prviRadnik = null;
			this.veza = null;
		}
		
		
		public String toString() {
			String output = "[ " + naziv + ", " + grad + " : ";
			
			Radnik tek = prviRadnik;
			
			while (tek != null) {
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Hotel prviHotel;
	
	
	public boolean dodajHotel(String naziv, String grad) {
		
		if (postojiHotel(naziv))
			return false;
		
		Hotel novi = new Hotel(naziv, grad);
		
		if (prviHotel == null) {
			
			novi.veza = prviHotel;
			prviHotel = novi;
			return true;
		} else {
			
			if (grad.compareTo(prviHotel.grad) <= 0) {
				
				novi.veza = prviHotel;
				prviHotel = novi;
				return true;
			}
			
			Hotel pret = prviHotel;
			
			while (pret.veza != null && grad.compareTo(pret.veza.grad) > 0)
				pret = pret.veza;
				
			novi.veza = pret.veza;
			pret.veza = novi;
			return true;
		}
	}
	
	
	public Hotel nadjiHotel(String naziv) {
		
		if (prviHotel != null) {
			
			Hotel tek = prviHotel;
			
			while (tek != null) {
				if (tek.naziv.equals(naziv))
					return tek;
				else
					tek = tek.veza;
			}
			
			return null;
		} else {
			
			return null;
		}
	}
	
	
	public Hotel nadjiPoslednjiHotel() {
		
		Hotel tek = prviHotel;
		
		if (tek != null) {
			
			while (tek.veza != null)
				tek = tek.veza;
			
			return tek;
		} else {
			
			return null;
		}
	}
	
	
	public boolean postojiHotel(String naziv) {
		
		return nadjiHotel(naziv) != null;
	}
	
	
	public void izbaciHotel(String naziv) {
		
		if (prviHotel != null) {
		
			if (prviHotel.naziv.equals(naziv)) {
				
				prviHotel = prviHotel.veza;
			}
			
			Hotel pret = prviHotel;
			
			while (pret.veza != null && !pret.veza.naziv.equals(naziv))
				pret = pret.veza;
				
			if (pret.veza != null) {
				
				pret.veza = pret.veza.veza;
			}
		}
	}
	
	
	public boolean zatvoriHotel(String naziv) {
		
		Hotel cilj = nadjiHotel(naziv);
		Hotel posl = nadjiPoslednjiHotel();
		
		if (cilj != null && cilj != posl) {
			
			Radnik pom;
			Radnik tek = cilj.prviRadnik;
			
			while (tek != null) {
				
				if (tek != null && tek.god / 10 != 0) {
					
					pom = tek.veza;
					
					tek.veza = posl.prviRadnik;
					posl.prviRadnik = tek;
					
					tek = pom;
				} else {
					tek = tek.veza;
				}
			}
			
			izbaciHotel(cilj.naziv);
			return true;
		} else if (cilj == posl) {
			
			if (prviHotel != posl)
				izbaciHotel(cilj.naziv);
			else
				prviHotel = null;
				
			return true;
		} else {
			
			return false;
		}
	}
	
	
	public double prosekPlata(String naziv) {
		
		Hotel cilj = nadjiHotel(naziv);
		
		if (cilj.prviRadnik != null) {
			
			Radnik tek = cilj.prviRadnik;
			double sum = 0.0;
			int count = 0;
			
			while (tek != null) {
				
				sum += tek.plata;
				count++;
				
				tek = tek.veza;
			}
			
			return (sum / count);
		} else {
			
			return -1.0;
		}
	}
	
	
	public String toString() {
		
		if (prviHotel != null) {
			
			String output = "LANAC HOTELA ";
			
			Hotel tek = prviHotel;
			
			while (tek != null) {
				output += tek + " ";
				
				tek = tek.veza;
			}
			
			return output;
			
		} else {
			
			return "Lista je prazna.";
		}
	}
}

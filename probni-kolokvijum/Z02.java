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
 
class Z02 {
	
	public static void main(String[] args) {
		
		LanacHotela lanacHotela = new LanacHotela();
		
		// dodavanje hotela i zaposlenih u neke od njih
		lanacHotela.dodajHotel("Xabac", "Sloboda");
			lanacHotela.dodajRadnika("Milan", 2.50, 5, "Sloboda");
			lanacHotela.dodajRadnika("Dragan", 4.50, 15, "Sloboda");
			
		lanacHotela.dodajHotel("Beograd", "Hilton");
		lanacHotela.dodajHotel("Novi Sad", "Sheraton");
		lanacHotela.dodajHotel("Krusevac", "Golf");
			lanacHotela.dodajRadnika("Milanko", 2.50, 5, "Golf");
			lanacHotela.dodajRadnika("Djuradj", 4.50, 15, "Golf");
			
		lanacHotela.dodajHotel("Novi Sad", "Centar");
		lanacHotela.dodajHotel("Vrsac", "Srbija");
		lanacHotela.dodajHotel("Beograd", "Centar");
		lanacHotela.dodajHotel("Beograd", "Centar");
		
		lanacHotela.stampajLanac();
		System.out.println();
		
		// racunanje proseka plate
		lanacHotela.prosekPlata("Sloboda");
		lanacHotela.prosekPlata("Golf");
		lanacHotela.prosekPlata("Centar");
		System.out.println();
		
		// zatvaranje hotela Golf
		lanacHotela.zatvoriHotel("Golf");
		lanacHotela.stampajLanac();
		System.out.println();
		
		// zatvaranje hotela Sloboda i izbacivanje Hiltona
		lanacHotela.zatvoriHotel("Sloboda");
		lanacHotela.izbaciHotel("Hilton");
		lanacHotela.stampajLanac();
		System.out.println();
		
		System.out.println(lanacHotela + "\n");
		
	}
}

class LanacHotela {
	
	class Radnik {
		
		String ime;
		double plata;
		int staz;
		Radnik veza;
		
		
		public Radnik(String ime, double plata, int staz) {
			this.ime = ime;
			this.plata = plata;
			this.staz = staz;
		}
		
		
		public String toString() {
			return ime + "; plata: " + plata + "; " + staz + " godina radnog staza.";
		}
	}
	
	
	class Hotel {
		
		String naziv, grad;
		Radnik prviRadnik;
		Hotel veza;
		
		
		public Hotel(String grad, String naziv) {
			this.naziv = naziv;
			this.grad = grad;
			this.prviRadnik = null;
			this.veza = null;
		}
		
		
		public String toString() {
			return grad + ", Hotel " + naziv;
		}
	}
	
	
	Hotel prviHotel;
	
	
	public boolean dodajHotel(String grad, String naziv) {
		
		/**
		 * pre svega proveravamo da li postoji hotel sa istim nazivom u
		 * listi buduci da je to uslov zadatka; ukoliko ne postoji kre-
		 * iramo novi objekat i krecemo u provere; ako lanac nema hote-
		 * la dodajemo ga na pocetak; ukoliko to nije slucaj prvo gled-
		 * amo da li je zadati grad u leksikografskom poretku manji ne-
		 * go prvi grad u listi i ako jeste, dodajemo ga na pocetak li-
		 * ste, a ako nije krecemo u potragu za elementom od kog je ma-
		 * nji u listi; kada smo to nasli (koristeci prethodni i tekuci
		 * element liste) ubacujemo hotel na trazeno mesto
		 */
		
		if (nadjiHotel(naziv) != null) {
			
			return false;
		}
			
		
		Hotel noviHotel = new Hotel(grad, naziv);
		
		if (prviHotel == null) {
			
			noviHotel.veza = prviHotel;
			prviHotel = noviHotel;
			return true;
			
		} else {
				
			if (grad.compareTo(prviHotel.grad) <= 0) {
				
				noviHotel.veza = prviHotel;
				prviHotel = noviHotel;
				return true;
				
			} else {
				
				Hotel prethodniHotel = prviHotel;
				
				while (prethodniHotel.veza != null && grad.compareTo(prethodniHotel.veza.grad) > 0)
					prethodniHotel = prethodniHotel.veza;
					
				noviHotel.veza = prethodniHotel.veza;
				prethodniHotel.veza = noviHotel;
				return true;
			}
		}
	}
	
	
	public Hotel nadjiHotel(String naziv) {
		
		/**
		 * krecemo u potragu za hotelom od prvog hotela u listi i ukol-
		 * iko je pronadjen, menjamo logicki indikator, a ukoliko ga n-
		 * ismo pronasli idemo dalje u listi; na kraju vracamo pronadj-
		 * eni hotel (sto moze biti i null)
		 */
		
		Hotel tekuciHotel = prviHotel;
		
		while (tekuciHotel != null) {
			
			if (tekuciHotel.naziv.equals(naziv))
				return tekuciHotel;
			else
				tekuciHotel = tekuciHotel.veza;
		}
		
		return null;
	}
	
	
	public Hotel nadjiPoslednjiHotel() {
		
		/**
		 * nalazenje poslednjeg hotela u listi gledanjem jednog elemen-
		 * ta unapred
		 */
		
		if (prviHotel != null) {
			
			Hotel tekuciHotel = prviHotel;
			
			while (tekuciHotel.veza != null) {
				tekuciHotel = tekuciHotel.veza;
			}
			
			return tekuciHotel;
			
		} else {
			
			return null;
		}
	}
	
	
	public void izbaciHotel(String naziv) {
		
		/**
		 * hotel izbacujemo tako sto prvo proveravamo da li uopste pos-
		 * toje hoteli u listi; ukoliko postoje trazimo hotel sa zadat-
		 * im nazivom; kada ga nadjemo prespajamo prethodni oko nadjen-
		 * og hotela
		 */
		
		if (prviHotel != null) {
			
			if (prviHotel.naziv.equals(naziv))
				prviHotel = prviHotel.veza;
			
			Hotel prethodniHotel = prviHotel;
			boolean nadjen = false;
			
			while (prethodniHotel.veza != null && !nadjen) {
				if (prethodniHotel.veza.naziv.equals(naziv))
					nadjen = true;
				else
					prethodniHotel = prethodniHotel.veza;
			}
			
			if (prethodniHotel.veza != null)
				prethodniHotel.veza = prethodniHotel.veza.veza;
		}
	}
	
	
	public void zatvoriHotel(String naziv) {
		
		/**
		 * hotel zatvaramo tako sto trazimo ciljni i trazimo hotel koji
		 * je poslednji u listi buduci da cemo u njega da smestamo zap-
		 * oslene ukoliko zadovoljavaju uslov sa stazom; pa ukoliko smo
		 * pronasli ciljni hotel pocinjemo da gledamo njegove radnike i
		 * u skladu sa uslovom staza one koji zadovoljavaju dodajemo na
		 * poslednji hotel, a ostale ne diramo - kada se ovo zavrsi br-
		 * isemo ceo hotel a time i njih; ukoliko se ciljni i poslednji
		 * hotel poklapaju prosto brisemo hotel; ukoliko nismo pronasli
		 * ciljni ne radimo nista
		 */
		
		Hotel ciljniHotel = nadjiHotel(naziv);
		Hotel poslednjiHotel = nadjiPoslednjiHotel();
		
		if (ciljniHotel != null) {
			
			Radnik pomocniRadnik;
			
			while (ciljniHotel.prviRadnik.staz / 10 != 0) {
				
				/**
				 * prespajanje pomocu pomocnog radnika - zapamtiti
				 */
				
				pomocniRadnik = ciljniHotel.prviRadnik.veza;
				
				ciljniHotel.prviRadnik.veza = poslednjiHotel.prviRadnik;
				poslednjiHotel.prviRadnik = ciljniHotel.prviRadnik;
				
				ciljniHotel.prviRadnik = pomocniRadnik;
			}
			
			Radnik tekuciRadnik = ciljniHotel.prviRadnik;
			
			while (tekuciRadnik != null) {
				
				if (tekuciRadnik != null && tekuciRadnik.staz / 10 != 0) {
					
					pomocniRadnik = tekuciRadnik.veza;
					
					tekuciRadnik.veza = poslednjiHotel.prviRadnik;
					poslednjiHotel.prviRadnik = tekuciRadnik;
					
					tekuciRadnik = pomocniRadnik;
					
				} else { 
					
					tekuciRadnik = tekuciRadnik.veza;
				}
			}
			
			izbaciHotel(naziv);
			
		} else if (ciljniHotel == poslednjiHotel) {
			
			izbaciHotel(naziv);
		} else {
			
			System.out.println("Trazeni hotel ne postoji.");
		}
		
	}
	
	
	public double prosekPlata(String naziv) {
		
		/**
		 * prosek plata racunamo tako sto nalazimo zeljeni hotel i onda
		 * se krecemo kroz zaposlene i racunamo prosek plata
		 */
		
		Hotel ciljniHotel = nadjiHotel(naziv);
		
		if (ciljniHotel.prviRadnik != null) {
			
			Radnik tekuciRadnik = ciljniHotel.prviRadnik;
			double sum = 0.0;
			int count = 0;
			
			while (tekuciRadnik != null) {
				sum += tekuciRadnik.plata;
				count++;
				
				tekuciRadnik = tekuciRadnik.veza;
			}
			
			System.out.println("Prosek plata u hotelu " + naziv + ": " + sum / count);
			
			return sum / count;
			
		} else {
			
			System.out.println("Hotel nema zaposlenih.");
			return -1.0;
		}
		
	}
	
	
	public boolean dodajRadnika(String ime, double plata, int staz, String naziv) {
		
		/**
		 * kreira se radnik i pronalazi se hotel, radnik se dodaje hot-
		 * elu na pocetak
		 */
		
		Radnik noviRadnik = new Radnik(ime, plata, staz);
		Hotel ciljniHotel = nadjiHotel(naziv);
		
		if (ciljniHotel != null) {
			
			noviRadnik.veza = ciljniHotel.prviRadnik;
			ciljniHotel.prviRadnik = noviRadnik;
			return true;
			
		} else {
			
			System.out.println("Uneti hotel ne postoji.");
			return false;
		}
	}
	
	
	public void stampajLanac() {
		
		Hotel tekuciHotel = prviHotel;
		
		if (tekuciHotel != null) {
		
			System.out.println("Lanac hotela: ");
			
			while (tekuciHotel != null) {
				
				System.out.println(tekuciHotel);
				
				if (tekuciHotel.prviRadnik != null) {
					
					Radnik tekuciRadnik = tekuciHotel.prviRadnik;
					
					while (tekuciRadnik != null) {
						System.out.println("\t" + tekuciRadnik);
						tekuciRadnik = tekuciRadnik.veza;
					}
				}
				tekuciHotel = tekuciHotel.veza;
			}
			
		} else {
			System.out.println("Lanac hotela je prazan.");
		}
	}
	
	
	public String toString() {
		
		String output = "Lanac hotela [ ";
		
		Hotel tekuciHotel = prviHotel;
		
		while (tekuciHotel != null) {
			output += tekuciHotel.naziv + " ";
			tekuciHotel = tekuciHotel.veza;
		}
		
		return output + "]";
	}
}

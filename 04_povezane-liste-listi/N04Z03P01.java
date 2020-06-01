/**
 * Potrebno je napraviti klasu RacunarskeUcionice koja ce pamtiti poda-
 * tke o učionicama i opremi u njima.
 * 
 * Primarna lista elemenata su samo učionice. One se čuvaju u jednostr-
 * uko povezanoj listi. Za svaku učionicu se pamti boja zidova (što st-
 * varno i jeste najvažniji podatak za računarsku učionicu) i lista op-
 * reme u njoj. Brojevi učionica se ne pamte u okviru klase, već se od-
 * ređuju implicitno na osnovu njihove pozicije u glavnoj listi i mogu-
 * ce ih je menjati u zavisnosti od dodavanja i uklanjanj učionica. Vi-
 * še učionica u listi mogu imati istu boju. Nove učionice se dodaju na
 * kraj liste.
 * 
 * Komadi oprema se pamte u okviru pojedinačne učionice kao jednostruko
 * povezana lista. Može biti više istih komada opreme. Za svaki od kom-
 * ada opreme se pamti tip i kratak opis (recimo tip "monitor", opis "-
 * LG", tip "računar", opis "Dell2015", tip "stolica" opis "drvena", t-
 * ip "klima", opis "na zidu"). Komadi opreme nisu jedinstveni, odnosno
 * mogu se ponavljati u listi. Radi lakšeg traženja i ispisa, komadi o-
 * preme su u listi sortirani po tipu opreme.
 * 
 * Napravite metode za (i po potrebi pomoćne):
 * - dodavanje nove učionice u listu učionica, prosledjuje se boja uci-
 *   onice kao parametar.
 * - dodavanje komada opreme (zadati tip i opis) u učionicu sa prosleđ-
 *   enim brojem. Ako taj broj ne postoji, odustati od dodavanja. Vrat-
 *   iti podatak o uspešnosti dodavanja (boolean).
 * - na osnovu parametara (tip, opis, boja i n) dodati novi komad opre-
 *   me u n-tu učionicu sa zadatom bojom. Ako ne postoji takva učionica
 *   odustati
 * - ispisivanje svih komada opreme u učionicama zadate boje
 * - prebrojati koliko opreme nekog tipa ima u svim učionicama zajedno
 * - dodati komad opreme sa datim tipom i opisom u najprazniju učionicu
 * - izbaciti jedan komad opreme datog tipa iz učionice zadate brojem.
 * - izbaciti jedan komad opreme datog tipa i opisa iz bilo koje učion-
 *   ice učionice zadate bojom.
 * - izbaciti svu opremu zadatog tipa iz učionice sa datim brojem
 * - prebaciti svu opremu zatatog tipa iz učionice sa najviše opreme t-
 *   og tipa u učionicu sa najmanje opreme tog tipa.
 * 
 * Napomena: neki od ovih metoda su mnogo veće težine nego što se oček-
 * uje na testu i samim tim su odlični za samostalno vežbanje.
 */

class N04Z03 {
	
	public static void main(String[] args) {
		
		SpisakUcionica spisakUcionica = new SpisakUcionica();
		
		// dodavanje praznih ucionica
		spisakUcionica.dodajUcionicu("ljubicasta");
		spisakUcionica.dodajUcionicu("zelena");
		spisakUcionica.dodajUcionicu("zuta");
		spisakUcionica.stampajSpisak();
		
		// dodavanje jos praznih ucionica, demonstracija numerisanja
		spisakUcionica.dodajUcionicu("crvena");
		spisakUcionica.dodajUcionicu("crvena");
		spisakUcionica.dodajUcionicu("zuta");
		spisakUcionica.dodajUcionicu("crvena");
		spisakUcionica.dodajUcionicu("zuta");
		spisakUcionica.dodajUcionicu("zuta");
		spisakUcionica.stampajSpisak();
		
		// dodavanje opreme u ucionice i stampanje spiska
		spisakUcionica.dodajUredjaj("monitor", "Dell", 0);
		spisakUcionica.dodajUredjaj("monitor", "Asus", 0);
		spisakUcionica.dodajUredjaj("monitor", "Acer", 0);
		spisakUcionica.dodajUredjaj("kuciste", "Dell", 0);
		spisakUcionica.dodajUredjaj("skener", "HP", 0);
		spisakUcionica.dodajUredjaj("klima", "Midea", 0);
		spisakUcionica.stampajSpisak();
		
		// dodavanje uredjaja u n-tu ucionicu zadate boje
		spisakUcionica.dodajUredjajBoja("HDD", "Western Digital 3Tb", "zuta", 3);
		spisakUcionica.stampajSpisak();
		
		// stampanje spisak ucionica zadate boje
		spisakUcionica.stampajSpisakBoja("zuta");
		
		// prebrojavanje uredjaje zadatog tipa
		spisakUcionica.prebrojUredjaje("monitor");
		
		// dodavanje uredjaja u najprazniju ucionicu
		spisakUcionica.dodajUredjajNajpraznija("klima", "Beko");
		spisakUcionica.stampajSpisak();
		
		// izbacivanje uredjaja zadatog tipa iz zadate ucionice
		spisakUcionica.izbaciUredjajTip("monitor", 0);
		spisakUcionica.izbaciUredjajTip("monitor", 0);
		spisakUcionica.stampajSpisak();
		
		// izbacivanje uredjaja zadatog tipa i modela iz ucionice zadate boje
		spisakUcionica.izbaciUredjajBoja("monitor", "Dell", "zuta");
		spisakUcionica.stampajSpisak();
		
		// vracanje uredjaja zarad demonsracije
		spisakUcionica.dodajUredjaj("monitor", "Dell", 0);
		spisakUcionica.dodajUredjaj("monitor", "Asus", 0);
		spisakUcionica.dodajUredjaj("monitor", "Acer", 0);
		spisakUcionica.dodajUredjaj("kuciste", "Dell", 0);
		spisakUcionica.dodajUredjaj("skener", "HP", 0);
		spisakUcionica.dodajUredjaj("klima", "Midea", 0);
		spisakUcionica.stampajSpisak();
		
		// izbaciti sve uredjaje zadatog tipa iz ucionice
		spisakUcionica.izbaciUredjajTipSve("monitor", 0);
		spisakUcionica.stampajSpisak();
		
		// prebacivanje svih uredjaja iz ucionice sa najvise u onu sa najmanje komada
		spisakUcionica.dodajUredjaj("monitor", "Dell", 0);
		spisakUcionica.dodajUredjaj("monitor", "Asus", 0);
		spisakUcionica.dodajUredjaj("monitor", "Acer", 0);
		spisakUcionica.prebaciUredjajeSve("monitor");
		spisakUcionica.stampajSpisak();
	}
}

class SpisakUcionica {
	
	class Uredjaj {
		
		String tip, opis;
		Uredjaj veza;
		
		
		public Uredjaj(String tip, String opis) {
			this.tip = tip;
			this.opis = opis;
		}
		
		
		public String toString() {
			return tip + " : " + opis;
		}
	}
	
	
	public boolean dodajUredjaj(String tip, String opis, int broj) {
		
		Ucionica ciljnaUcionica = nadjiUcionicu(broj);
		
		if (ciljnaUcionica != null) {
			
			Uredjaj noviUredjaj = new Uredjaj(tip, opis);
			
			if (ciljnaUcionica.prviUredjaj == null) {
				
				noviUredjaj.veza = ciljnaUcionica.prviUredjaj;
				ciljnaUcionica.prviUredjaj = noviUredjaj;
				return true;
				
			} else {
				
				if (tip.compareTo(ciljnaUcionica.prviUredjaj.tip) <= 0) {
					
					noviUredjaj.veza = ciljnaUcionica.prviUredjaj;
					ciljnaUcionica.prviUredjaj = noviUredjaj;
					return true;
					
				} else {
					
					Uredjaj prethodniUredjaj = ciljnaUcionica.prviUredjaj;
					
					while (prethodniUredjaj.veza != null && tip.compareTo(prethodniUredjaj.veza.tip) > 0)
						prethodniUredjaj = prethodniUredjaj.veza;
					
					noviUredjaj.veza = prethodniUredjaj.veza;
					prethodniUredjaj.veza = noviUredjaj;
					return true;
				}
			}
			
		} else {
			
			System.out.println("Trazena ucionica ne postoji.");
			return false;
		}
	}
	
	
	public boolean dodajUredjajBoja(String tip, String opis, String boja, int n) {
		
		if (prvaUcionica != null) {
			
			Ucionica tekucaUcionica = prvaUcionica;
			int count = 0;
			
			while (tekucaUcionica != null && count != n + 1) {
				if (tekucaUcionica.boja.equals(boja))
					count++;
				
				tekucaUcionica = tekucaUcionica.veza;
			}
			
			if (tekucaUcionica != null) {
				
				dodajUredjaj(tip, opis, tekucaUcionica.broj - 1);
				return true;
				
			} else {
				
				return false;
			}
			
		} else {
			
			System.out.println("Lista je prazna.");
			return false;
		}
	}
	
	
	public void dodajUredjajNajpraznija(String tip, String opis) {
		
		Ucionica ciljnaUcionica = nadjiNajpraznijuUcionicu();
		
		dodajUredjaj(tip, opis, ciljnaUcionica.broj);
	}
	
	
	public int prebrojUredjaje(String tip) {
		
		if (prvaUcionica != null) {
			
			int count = 0;
			
			Ucionica tekucaUcionica = prvaUcionica;
			
			while (tekucaUcionica != null) {
				
				Uredjaj tekuciUredjaj = tekucaUcionica.prviUredjaj;
				
				while (tekuciUredjaj != null) {
					
					if (tekuciUredjaj.tip.equals(tip))
						count++;
					
					tekuciUredjaj = tekuciUredjaj.veza;
				}
				
				tekucaUcionica = tekucaUcionica.veza;
			}
			
			System.out.println("Ukupno ima: " + count + " (" + tip + ")");
			return count;
			
		} else {
			
			System.out.println("Lista je prazna.");
			return -1;
		}
	}
	
	
	public void prebaciUredjajeSve(String tip) {
		
		Ucionica pocetna = nadjiUcionicuSaNajviseTipa(tip);
		Ucionica krajnja = nadjiUcionicuSaNajmanjeTipa(tip);
		
		while (pocetna.prviUredjaj.tip.equals(tip)) {
			
			dodajUredjaj(pocetna.prviUredjaj.tip, pocetna.prviUredjaj.opis, krajnja.broj);
			pocetna.prviUredjaj = pocetna.prviUredjaj.veza;
		}
		
		Uredjaj prethodniUredjaj = null;
		Uredjaj tekuciUredjaj = pocetna.prviUredjaj;
		
		while (tekuciUredjaj != null) {
			
			prethodniUredjaj = tekuciUredjaj;
			tekuciUredjaj = tekuciUredjaj.veza;
			
			if (tekuciUredjaj != null && tekuciUredjaj.tip.equals(tip)) {
				dodajUredjaj(tekuciUredjaj.tip, tekuciUredjaj.opis, krajnja.broj);
				prethodniUredjaj.veza = tekuciUredjaj.veza;
				
				tekuciUredjaj = prethodniUredjaj;
			}
		}
	}
	
	
	public boolean izbaciUredjajTip(String tip, int n) {
		
		Ucionica ciljnaUcionica = nadjiUcionicu(n);
		
		if (ciljnaUcionica.prviUredjaj != null) {
			
			Uredjaj prethodniUredjaj = ciljnaUcionica.prviUredjaj;
			boolean nadjen = false;
			
			while (prethodniUredjaj.veza != null && !nadjen) {
				if (prethodniUredjaj.veza.tip.equals(tip))
					nadjen = true;
				else
					prethodniUredjaj = prethodniUredjaj.veza;
			}
			
			if (nadjen) {
				
				prethodniUredjaj.veza = prethodniUredjaj.veza.veza;
				return true;
				
			} else {
				
				System.out.println("Zadati tip opreme ne postoji u ucionici.");
				return false;
			}
			
		} else {
			
			System.out.println("Ucionica je prazna.");
			return false;
		}
	}
	
	
	public void izbaciUredjajTipSve(String tip, int n) {
		
		Ucionica ciljnaUcionica = nadjiUcionicu(n);
		
		if (ciljnaUcionica.prviUredjaj != null) {
			
			Uredjaj prethodniUredjaj = null;
			Uredjaj tekuciUredjaj = ciljnaUcionica.prviUredjaj;
			
			while (tekuciUredjaj.tip.equals(tip)) {
				
				tekuciUredjaj = tekuciUredjaj.veza;
			}
			
			while (tekuciUredjaj != null) {
				prethodniUredjaj = tekuciUredjaj;
				tekuciUredjaj = tekuciUredjaj.veza;
				
				if (tekuciUredjaj != null && tekuciUredjaj.tip.equals(tip)) {
					prethodniUredjaj.veza = tekuciUredjaj.veza;
					tekuciUredjaj = prethodniUredjaj;
				}
			}
			
		} else {
			
			System.out.println("Ucionica je prazna.");
		}
	}
	
	
	public boolean izbaciUredjajBoja(String tip, String opis, String boja) {
		
		if (prvaUcionica != null) {
			
			Ucionica tekucaUcionica = prvaUcionica;
			//~ boolean izbacen = false;
			
			while (tekucaUcionica != null) {
				if (tekucaUcionica.boja.equals(boja)) {
					
					Uredjaj tekuciUredjaj = tekucaUcionica.prviUredjaj;
					
					while (tekuciUredjaj != null) {
						
						if (tekuciUredjaj.tip.equals(tip) && tekuciUredjaj.opis.equals(opis)) {
							
							izbaciUredjajTip(tip, tekucaUcionica.broj);
							return true;
							
						} else {
							
							tekuciUredjaj = tekuciUredjaj.veza;
						}
					}
					
				} else {
					
					tekucaUcionica = tekucaUcionica.veza;
				}
			}
			
			return false;
			
		} else {
			
			System.out.println("Lista je prazna.");
			return false;
		}
	}
	
	
	class Ucionica {
		
		String boja;
		int broj;
		Uredjaj prviUredjaj;
		Ucionica veza;
		
		
		public Ucionica(String boja) {
			this.boja = boja;
			this.broj = -1;
			this.prviUredjaj = null;
		}
		
		
		public String toString() {
			return "Ucionica br. " + broj + " (" + boja + ")";
		}
	}
	
	
	Ucionica prvaUcionica;
	
	
	public void dodajUcionicu(String boja) {
		
		Ucionica novaUcionica = new Ucionica(boja);
		
		novaUcionica.veza = prvaUcionica;
		prvaUcionica = novaUcionica;
		
		numerisiUcionice();
	}
	
	
	private int numerisiUcionice() {
		
		if (prvaUcionica != null) {
			
			Ucionica tekucaUcionica = prvaUcionica;
			int count = 0;
			
			while (tekucaUcionica != null) {
				tekucaUcionica.broj = count;
				count++;
				tekucaUcionica = tekucaUcionica.veza;
			}
			
			return count;
			
		} else {
			
			return -1;
		}
	}
	
	
	public Ucionica nadjiUcionicu(int broj) {
		
		if (prvaUcionica != null) {
			
			Ucionica tekucaUcionica = prvaUcionica;
			boolean nadjen = false;
			
			while (tekucaUcionica != null && !nadjen) {
				if (tekucaUcionica.broj == broj)
					nadjen = true;
				else
					tekucaUcionica = tekucaUcionica.veza;
			}
			
			return tekucaUcionica;
			
		} else {
			
			System.out.println("Lista je prazna.");
			return null;
		}
	}
	
	
	public Ucionica nadjiUcionicu(String boja) {
		
		if (prvaUcionica != null) {
			
			Ucionica tekucaUcionica = prvaUcionica;
			boolean nadjen = false;
			
			while (tekucaUcionica != null && !nadjen) {
				if (tekucaUcionica.boja.equals(boja))
					nadjen = true;
				else
					tekucaUcionica = tekucaUcionica.veza;
			}
			
			return tekucaUcionica;
			
		} else {
			
			System.out.println("Lista je prazna.");
			return null;
		}
	}
	
	
	public Ucionica nadjiNajpraznijuUcionicu() {
		
		if (prvaUcionica != null) {
			
			int[] count = new int[numerisiUcionice()];
			
			Ucionica tekucaUcionica = prvaUcionica;
			
			while (tekucaUcionica != null) {
				
				Uredjaj tekuciUredjaj = tekucaUcionica.prviUredjaj;
				
				while (tekuciUredjaj != null) {
					
					count[tekucaUcionica.broj]++;
					tekuciUredjaj = tekuciUredjaj.veza;
				}
				
				tekucaUcionica = tekucaUcionica.veza;
			}
			
			int min = count[0];
			int ind = 0;
			
			for (int i = 1; i < count.length; i++) {
				if (count[i] < min) {
					min = count[i];
					ind = i;
				}
			}
			
			return nadjiUcionicu(ind);
			
		} else {
			
			System.out.println("Lista je prazna.");
			return null;
		}
	}
	
	
	public Ucionica nadjiUcionicuSaNajmanjeTipa(String tip) {
		
		if (prvaUcionica != null) {
			
			int[] count = new int[numerisiUcionice()];
			
			Ucionica tekucaUcionica = prvaUcionica;
			
			while (tekucaUcionica != null) {
				
				Uredjaj tekuciUredjaj = tekucaUcionica.prviUredjaj;
				
				while (tekuciUredjaj != null) {
					
					if (tekuciUredjaj.tip.equals(tip))
						count[tekucaUcionica.broj]++;
						
					tekuciUredjaj = tekuciUredjaj.veza;
				}
				
				tekucaUcionica = tekucaUcionica.veza;
			}
			
			int min = count[0];
			int ind = 0;
			
			for (int i = 1; i < count.length; i++) {
				if (count[i] < min) {
					min = count[i];
					ind = i;
				}
			}
			
			return nadjiUcionicu(ind);
			
		} else {
			
			System.out.println("Lista je prazna.");
			return null;
		}
	}
	
	
	public Ucionica nadjiUcionicuSaNajviseTipa(String tip) {
		
		if (prvaUcionica != null) {
			
			int[] count = new int[numerisiUcionice()];
			
			Ucionica tekucaUcionica = prvaUcionica;
			
			while (tekucaUcionica != null) {
				
				Uredjaj tekuciUredjaj = tekucaUcionica.prviUredjaj;
				
				while (tekuciUredjaj != null) {
					
					if (tekuciUredjaj.tip.equals(tip))
						count[tekucaUcionica.broj]++;
						
					tekuciUredjaj = tekuciUredjaj.veza;
				}
				
				tekucaUcionica = tekucaUcionica.veza;
			}
			
			int max = count[0];
			int ind = 0;
			
			for (int i = 1; i < count.length; i++) {
				if (count[i] > max) {
					max = count[i];
					ind = i;
				}
			}
			
			return nadjiUcionicu(ind);
			
		} else {
			
			System.out.println("Lista je prazna.");
			return null;
		}
	}
	
	
	public void stampajSpisakBoja(String boja) {
		
		if (prvaUcionica != null) {
			
			Ucionica tekucaUcionica = prvaUcionica;
			
			while (tekucaUcionica != null) {
				
				if (tekucaUcionica.boja.equals(boja)) {
					
					System.out.println(tekucaUcionica);
					
					if (tekucaUcionica.prviUredjaj != null) {
					
						Uredjaj tekuciUredjaj = tekucaUcionica.prviUredjaj;
						
						while (tekuciUredjaj != null) {
							
							System.out.println("\t" + tekuciUredjaj);
							tekuciUredjaj = tekuciUredjaj.veza;
						}
					}
				}
				tekucaUcionica = tekucaUcionica.veza;
			}
			
		} else {
			
			System.out.println("Lista je prazna.");
		}
		
		System.out.println();
	}
	
	
	public void stampajSpisak() {
		
		if (prvaUcionica != null) {
		
			System.out.println("Spisak ucionica: ");
			
			Ucionica tekucaUcionica = prvaUcionica;
			
			while (tekucaUcionica != null) {
				System.out.println(tekucaUcionica);
				
				if (tekucaUcionica.prviUredjaj != null) {
					
					Uredjaj tekuciUredjaj = tekucaUcionica.prviUredjaj;
					
					while (tekuciUredjaj != null) {
						
						System.out.println("\t" + tekuciUredjaj);
						tekuciUredjaj = tekuciUredjaj.veza;
					}
				}
				tekucaUcionica = tekucaUcionica.veza;
			}
			
		} else {
			
			System.out.println("Spisak ucionica je prazan.");
		}
		
		System.out.println();
	}
	
}

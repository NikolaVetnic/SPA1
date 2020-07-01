/**
 * Napisati program koji
 * a) učitava cele brojeve iz fajlova “p1.txt” i “p2.txt” na dva razli-
 *    čita steka celih brojeva
 * b) sa vrha prvog steka uklanja sve brojeve čija je poslednja cifra 6
 * c) sa vrha drugog steka uklanja sve brojeve koji su veći od svog sl-
 *    edbenika
 * d) spaja podatke sa dva steka u jedan novi, naizmeničnim ubacivanjem
 *    podataka (uzeti u obzir da stekovi ne moraju biti iste dužine)
 * e) ispisuje sadržaj tako dobijenog steka u fajl “pp.txt"
 **/

class N05Z03P05 {
	
	static Stek<Integer> stekIzFajla(String imeFajla) {
		
		Stek<Integer> output = new Stek<Integer>();
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String s = Svetovid.in(imeFajla).readToken();
				output.stavi(Integer.parseInt(s));
			}
			
			Svetovid.in(imeFajla).close();
		} else {
			
			System.out.println("Fajl ne sadrzi podatke - vracen je prazan stek!");
		}
		
		System.out.println();
		
		return output;
	}	// ucitavanje sadrzaja fajla ciji je naziv prosledjen u stek
	
	
	static void stekUFajl(Stek<Integer> stek, String imeFajla) {
		
		while (!stek.jePrazan())
			Svetovid.out(imeFajla).print(stek.skiniVrh() + " ");
		
		System.out.println("Stek je sacuvan u fajl: " + imeFajla);
		
		Svetovid.out(imeFajla).close();
		System.out.println();
	}	// cuvanje steka u fajl ciji je naziv prosledjen
	
	
	static void ukloniAkoJePoslednja6(Stek<Integer> stek) {
		
		while (stek.vrh() % 10 == 6)
			stek.skiniVrh();
	}	// uklanjanje elemenata sa vrha steka ako im je poslednja cifra 6
	
	
	static void ukloniAko(Stek<Integer> stek) {
		
		int vrh = stek.skiniVrh();
		int sledeci = stek.vrh();
		stek.stavi(vrh);
		
		while (vrh > sledeci) {
			
			vrh = stek.skiniVrh();
			sledeci = stek.vrh();
		}
		
		stek.stavi(vrh);
	}	// uklanjanje elemenata sa vrha ako su veci od sledbenika
	
	
	static Stek<Integer> spojStekove(Stek<Integer> stek1, Stek<Integer> stek2) {
		
		Stek<Integer> output = new Stek<Integer>();
		
		while(!stek1.jePrazan() || !stek2.jePrazan()) {
			
			if (!stek1.jePrazan())
				output.stavi(stek1.skiniVrh());
				
			if (!stek2.jePrazan())
				output.stavi(stek2.skiniVrh());
		}
		return output;
	}	// spajanje stekova prema zahtevima zadatka
	
	
	static Stek<Integer> stampaj(Stek<Integer> stek) {
		
		Stek<Integer> temp = new Stek<Integer>();
		
		System.out.print("[ ");
		
		while (!stek.jePrazan()) {
			
			temp.stavi(stek.vrh());
			System.out.print(stek.skiniVrh() + " ");
		}
		
		System.out.print("]\n");
		
		while (!temp.jePrazan())
			stek.stavi(temp.skiniVrh());
		
		return stek;
	}	// stampanje punog sadrzaja steka
	
	
	public static void main(String[] args) {
		
		String imeFajla;
		
		imeFajla = Svetovid.in.readLine("Ime prvog fajla: ");
		Stek<Integer> stek1 = stekIzFajla(imeFajla);
		stek1 = stampaj(stek1);
		
		imeFajla = Svetovid.in.readLine("Ime drugog fajla: ");
		Stek<Integer> stek2 = stekIzFajla(imeFajla);
		stek2 = stampaj(stek2);
		
		System.out.println("Uklanjanje elemenata sa poslednjom cifrom 6 iz prvog steka: ");
		ukloniAkoJePoslednja6(stek1);
		stek1 = stampaj(stek1);
		
		System.out.println("Uklanjanje elemenata vecih od sledbenika iz drugog steka: ");
		ukloniAko(stek2);
		stek2 = stampaj(stek2);
		
		System.out.println("Spajanje stekova naizmenicnim ubacivanjem: ");
		Stek<Integer> stek3 = spojStekove(stek1, stek2);
		stek3 = stampaj(stek3);
		
		imeFajla = Svetovid.in.readLine("Ime fajla za cuvanje: ");
		stekUFajl(stek3, imeFajla);
	}
}


/**
 * Tip podataka stek, koji omogućava skladištenje podataka u skladu sa principom
 * "poslednji unutra, prvi napolje".
 * 
 * <p>
 * Implementacija koristi niz, te je u skladu sa tim ograničena veličina steka
 * koji se koristi i moguće je da će operacija za dodavanje elemenata baciti
 * izuzetak ukoliko nema mesta.
 * </p>
 * 
 * @version v1.0.0
 * 
 * @param <T>
 *            Tip podataka koji će se čuvati u konkretnoj instanci steka.
 */
public class Stek<T> {
	/**
	 * Separator vrednosti u {@code toString} metodu: {@value} .
	 */
	public static final String SEPARATOR = ", ";

	// indeks prvog slobodnog elementa na steku
	private int popunjeno;

	// niz u kome se skladiste elementi
	private T[] elementi;

	/**
	 * Veličina stekova za koje nije prosledjen parametar o veličini ({@value}
	 * ).
	 */
	public static final int PODRAZUMEVANA_VELICINA = 100;

	/**
	 * Kreira novi Stek podrazumevane veličine {@value #PODRAZUMEVANA_VELICINA}.
	 */
	public Stek() {
		this(PODRAZUMEVANA_VELICINA);
	}

	/**
	 * Kreira nov Stek zadate velicine.
	 * 
	 * @param n
	 *            maksimalan broj elemenata koji će ovaj stek moći da primi.
	 */
	// pozeljno koristiti Suppress da kompajliranje ne prijavljuje upozorenja
	@SuppressWarnings("unchecked")
	public Stek(int n) {
		popunjeno = 0;
		elementi = (T[]) (new Object[n]);
	}

	/**
	 * Vraća da li je stek prazan.
	 * 
	 * @return da li je stek prazan
	 */
	public boolean jePrazan() {
		return popunjeno == 0;
	}

	/**
	 * Vraća da li je stek pun.
	 * 
	 * @return da li je stek pun
	 */
	public boolean jePun() {
		return popunjeno == elementi.length;
	}

	/**
	 * Vraća vrednost elementa na vrhu steka. Ukoliko je stek prazan baca
	 * izuzetak.
	 * 
	 * @return vrednost elementa na vrhu steka
	 */
	public T vrh() {
		if (jePrazan()) {
			throw new IllegalStateException("Stek je prazan");
		} else
			return elementi[popunjeno - 1];
	}

	/**
	 * Skida element sa vrha steka i vraća ga. Ukoliko je stek prazan baca se
	 * izuzetak.
	 * 
	 * @return vrednost elementa koji je bio na vrhu steka
	 */
	public T skiniVrh() {
		if (jePrazan()) {
			throw new IllegalStateException("Stek je prazan");
		} else
			popunjeno--;
		return elementi[popunjeno];
	}

	/**
	 * Ubacuje prosleđeni element na vrh steka. Ukoliko je stek već pun baca se
	 * izuzetak.
	 * 
	 * @param x
	 *            element koji će biti ubačen na vrh steka
	 */
	public void stavi(T x) {
		if (jePun()) {
			throw new IllegalStateException("Stek je pun");
		} else {
			elementi[popunjeno] = x;
			popunjeno++;
		}
	}

	/**
	 * Vraća String reprezentaciju ovog Steka. Reprezentacija će sadržati
	 * najviše 4 elementa sa steka, tačnije najviše prva dva i poslednja dva,
	 * razdvojenih sa {@value #SEPARATOR}, a ukoliko ima više od 4 elementa biće
	 * dodato i "..." između prvih i poslednjih elemenata.
	 */
	public String toString() {
		String rez = "Stek: ";
		if (jePrazan()) {
			rez += "prazan";
		} else {
			rez += elementi[popunjeno - 1];
			if (popunjeno > 1) {
				int sledeci = popunjeno - 2;
				rez += SEPARATOR + elementi[sledeci];
				if (popunjeno > 2) {
					if (popunjeno > 4) {
						rez += SEPARATOR + "...";
					}
					if (popunjeno > 3) {
						rez += SEPARATOR + elementi[1];
					}
					rez += SEPARATOR + elementi[0];
				}
			}
		}
		return rez;
	}
}

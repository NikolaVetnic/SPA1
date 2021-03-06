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
 
class N05Z03P02 {
	
	public static Stek<Integer> ucitajStek(String f) {
		
		Stek<Integer> stek = new Stek<>();
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				int e = Svetovid.in(f).readInt();
				stek.stavi(e);
			}
			
			Svetovid.in(f).close();
		}
		
		return stek;
	}
	
	
	public static void ukloni1(Stek<Integer> stek) {
		
		if (!stek.jePrazan()) {
			
			while (!stek.jePrazan() && stek.vrh() % 10 == 6)
				stek.skiniVrh();
		}
	}
	
	
	public static void ukloni2(Stek<Integer> stek) {
		
		if (!stek.jePrazan()) {
			
			int vrh = stek.skiniVrh();
			int sle = stek.vrh();
			stek.stavi(vrh);
			
			while (!stek.jePrazan() && vrh > sle) {
				
				vrh = stek.skiniVrh();
				sle = stek.vrh();
			}
			
			stek.stavi(vrh);
		}
	}
	
	
	public static Stek<Integer> spoji(Stek<Integer> s1, Stek<Integer> s2) {
		
		Stek<Integer> s3 = new Stek<>();
		
		while (!s1.jePrazan() || !s2.jePrazan()) {
			
			if (!s1.jePrazan())
				s3.stavi(s1.skiniVrh());
				
			if (!s2.jePrazan())
				s3.stavi(s2.skiniVrh());
		}
		
		return s3;
	}
	
	
	public static void sacuvajStek(String f, Stek<Integer> stek) {
		
		Stek<Integer> pom = stek;
		
		while (!stek.jePrazan())
			Svetovid.out(f).println(pom.skiniVrh());
			
		Svetovid.in(f).close();
	}
	
	
	public static void stampaj(Stek<Integer> stek) {
		
		Stek<Integer> pom = stek;
		
		System.out.println("Stek: ");
		
		while (!pom.jePrazan())
			System.out.println(pom.skiniVrh());
	}
	
	
	public static void main(String[] args) {
		
		Stek<Integer> s1 = ucitajStek("p1.txt");
		ukloni1(s1);
		System.out.println(s1);
		
		Stek<Integer> s2 = ucitajStek("p2.txt");
		ukloni2(s2);
		System.out.println(s2);
		
		Stek<Integer> s3 = spoji(s1, s2);
		
		sacuvajStek("pp.txt", s3);
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

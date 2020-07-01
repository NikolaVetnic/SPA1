class N01Z01P02 {
	
	public static void fajlUpis(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			char choice = Svetovid.in.readChar("Fajl vec postoji - obrisati? [d] / [n]");
			if (choice != 'd') {
				System.out.println("Izlaz.\t");
				Svetovid.in(imeFajla).close();
				return;
			} 
		}
		
		String unos;
		int count = 0;
		
		do {
			unos = Svetovid.in.readLine("Unos linije br. " + count + ": ");
			count++;
			
			if (!unos.equalsIgnoreCase("kraj"))
				Svetovid.out(imeFajla).println(unos);
				
		} while (!unos.equalsIgnoreCase("kraj"));
		
		Svetovid.in(imeFajla).close();
		System.out.println("Kraj unosa.\n");
	}
	
	public static void fajlStampa(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String red = Svetovid.in(imeFajla).readLine();
				System.out.println(red);
			}
			Svetovid.in(imeFajla).close();
		} else {
			
			System.out.println("U radnom direktorijumu ne postoji fajl sa tim nazivom.");
		}
		
		System.out.println("Kraj stampe.\n");
	}
	
	public static void fajlRacunanje(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			
			int count = 0;
			double sum = 0.0;
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				sum += Svetovid.in(imeFajla).readDouble();
				count++;
			}
			
			System.out.println("Suma:\t" + String.format("%1$.4f", sum));
			System.out.println("Prosek:\t" + String.format("%1$.4f", sum / count));
			}
	}
	
	public static void fajlGenerisanje(String imeFajla) {
		
		for (int i = 0; i < 10; i++) {
			
			double d = Math.random() * 100;
			Svetovid.out(imeFajla).println(d);
		}
		
		Svetovid.in(imeFajla).close();
	}
	
	public static void fajlString(String imeFajla, String unos) {
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				String red = Svetovid.in(imeFajla).readLine();
				if (red.equals(unos)) {
					System.out.println("Unos nadjen.");
				} else {
					System.out.println("Unos nije nadjen u trenutnom redu.");
				}
			}
			
			Svetovid.in(imeFajla).close();
		}
	}
	
	public static void main(String[] args) {
		
		String imeFajla;
		String unos;
		
		//imeFajla = Svetovid.in.readLine("Fajl za upisivanje: ");
		//fajlUpis(imeFajla);
		
		//imeFajla = Svetovid.in.readLine("Fajl za citanje: ");
		//fajlStampa(imeFajla);
		
		//imeFajla = Svetovid.in.readLine("Fajl za racunanje: ");
		//fajlRacunanje(imeFajla);
		
		imeFajla = Svetovid.in.readLine("Fajl za pretragu: ");
		unos = Svetovid.in.readLine("Unos za pretragu: ");
		fajlString(imeFajla, unos);
		
	}
}

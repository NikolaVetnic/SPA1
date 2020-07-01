/**
 * 1. Napisati program koji na disku kreira fajl imena fajl1.txt i u n-
 *    jega upisuje:
 * 		   Prvi red
 * 		   Drugi red
 * 
 * 		   Cetvrti red
 * 2. Napisati program koji ispisuje sadržaj fajla fajl1.txt na ekran.
 *    Prepraviti program da izlistava fajl čije je ime korisnik uneo.
 * 3. Iz fajla brojevi.txt učitati sve realne brojeve i racunati sumu i
 *    prosek brojeva.
 * 4. Fajl imena.txt sadrži česta imena. Svako ime nalazi se u posebnom
 *    redu u fajlu i nema duplikata. Napisati program koji proverava da
 *    li se string koji unosi korisnik nalazi u fajlu i ispisuje odgov-
 *    arajuću poruku. Ukoliko se string ne nalazi u fajlu program treba
 *    da dopiše string na kraj fajla.
 */

class N01Z01P01 {
	
	
	static void writeInFile(String filename, String[] strings) {
		
		for (String string : strings) {
			Svetovid.out(filename).println(string);
		}
		
		Svetovid.in(filename).close();
	}
	
	
	static void printFile(String filename) {
		
		if (Svetovid.testIn(filename)) {
			
			while (Svetovid.in(filename).hasMore()) {
				
				String line = Svetovid.in(filename).readLine();
				System.out.println(line);
			}
			Svetovid.in(filename).close();
		} else {
			
			System.out.println("File is empty.");
		}
	}
	
	
	static void sumAndAverage(String filename) {
		
		if (Svetovid.testIn(filename)) {
			
			double sum = 0.0;
			double avg = 0.0;
			int count = 0;
			
			while (Svetovid.in(filename).hasMore()) {
				sum += Svetovid.in(filename).readDouble();
				count++;
			}
			System.out.println("Sum: " + sum + "; Average: " + (sum / count));
			
			Svetovid.in(filename).close();
		} else {
			
			System.out.println("File is empty.");
		}
	}
	
	
	static void checkFile(String filename, String input) {
		
		if (Svetovid.testIn(filename)) {
			
			boolean found = false;
			
			while (Svetovid.in(filename).hasMore() && !found) {
				String name = Svetovid.in(filename).readToken();
				if (input.equals(name)) found = true;
			}
			
			if (!found)
				Svetovid.append(filename).println(input);
			else
				System.out.println("Input found in file.");
			
			Svetovid.in(filename).close();
			
		} else {
			System.out.println("File doesn't exist.");
		}
	}
	
	
	public static void main(String[] args) {
		
		String filename;
		
		String[] strings = {
			"Prvi red",
			"Drugi red",
			"",
			"Cetvrti red"
		};
		
		filename = Svetovid.in.readLine("Ime fajla za cuvanje: ");
		writeInFile(filename, strings);
		System.out.println();
		
		filename = Svetovid.in.readLine("Ime fajla za stampanje: ");
		printFile(filename);
		System.out.println();
		
		filename = Svetovid.in.readLine("Ime fajla za racunanje: ");
		sumAndAverage(filename);
		System.out.println();
		
		filename = Svetovid.in.readLine("Ime fajla sa imenima: ");
		String input = Svetovid.in.readLine("Ime: ");
		checkFile(filename, input);
	}
	
}

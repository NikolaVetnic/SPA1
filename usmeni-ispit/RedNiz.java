public RedNiz {
	
	public static final int PODRAZUMEVANA_VELICINA = 100;
	
	
	private int prvi, posl;
	private Object[] elementi;
	
	
	public Red() {
		this.prvi = -1;
		this.posl = -1;
		this.elementi = new Object[PODRAZUMEVANA_VELICINA];
	}
	
	
	private int dodajJedan(int i) {
		return (i + 1) % PODRAZUMEVANA_VELICINA;
	}
	
	
	public boolean jePrazan() {
		return prvi == -1;
	}
	
	
	public boolean jePun() {
		return dodajJedan(posl) == prvi;
	}
	
	
	public Object prvi() {
		
		if (jePrazan())
			return null;
			
		return elementi[prvi];
	}
	
	
	public void izbaciPrvi() {
		
		if (jePrazan())
			return;
		
		if (prvi == posl) {
			
			prvi = -1;
			posl = -1;
		} else {
			
			prvi = dodajJedan(prvi);
		}
	}
	
	
	public void naKraj(Object x) {
		
		if (jePun())
			return;
			
		if (prvi == posl) {
			
			prvi = 0;
			posl = 0;
		} else {
			
			posl = dodajJedan(posl);
		}
		
		elementi[posl] = x;
	}
}

class StekNiz {
	
	public static final int PODRAZUMEVANA_VELICINA = 100;
	
	
	private int topEl;
	private Object[] elementi;
	
	
	public Stek() {
		this.topEl = 0;
		this.elementi = new Object[PODRAZUMEVANA_VELICINA];
	}
	
	
	public boolean jePrazan() {
		return topEl == 0;
	}
	
	
	public boolean jePun() {
		return topEl == PODRAZUMEVANA_VELICINA;
	}
	
	
	public Object vrh() {
		
		if (jePrazan())
			return null;
			
		return elementi[topEl - 1];
	}
	
	
	public void skiniVrh() {
		
		if (jePrazan())
			return;
		
		topEl--;
	}
	
	
	public void stavi(Object x) {
		
		if (jePun())
			return;
			
		elementi[topEl] = x;
		topEl++;
	}
}

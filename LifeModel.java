package ConwayGameOfLife;

import java.util.Random;
import java.util.Observable;

public class LifeModel extends Observable {
	
	private final int RIJEN = 20; // aantal rijen in speelveld
	private final int KOLOMMEN = 30; // aantal kolommen in speelveld
	private final int LEVENSPERCENTAGE = 10; // kans op levende cel bij initialisatie
	
	private boolean[][] grid = new boolean[this.RIJEN][this.KOLOMMEN]; // initialiseer twee-dimensionaal speelveld
	
	private final Random RAND = new Random(); // instantie van Random klasse
	private final PatroonGenerator PATGEN = new PatroonGenerator(); // instantie van PatroonGenerator klasse
	
	public int getRijen() {
		return this.RIJEN;
	}
	
	public int getKolommen() {
		return this.KOLOMMEN;
	}
	
	public boolean[][] getGrid() { // getter voor speelveld
		return this.grid;
	}
	
	public LifeModel() {
		for (int i = 0; i < this.RIJEN; i++) { // itereer door alle rijen
			for (int j = 0; j < this.KOLOMMEN; j++) { // itereer door alle kolommen per rij
				if (i == 0 || i == this.RIJEN - 1 || j == 0 || j == this.KOLOMMEN - 1) {
					this.grid[i][j] = false; // laat de buitenste cellen (eerste en laatste rijen/kolommen) 'dood'
				} else {
					if (RAND.nextInt(100) + 1 <= this.LEVENSPERCENTAGE) {
						this.grid[i][j] = true; // levende cel gebaseerd op LEVENSPERCENTAGE
					} else {
						this.grid[i][j] = false;
					}
				}
			}
		}
	}
	
	public LifeModel(Patroon patroon) {
		this.grid = this.PATGEN.generate(this.grid, patroon); // initialiseer grid op basis van meegegeven patroon
	}
	
	public boolean isLevend(int rij, int kolom) {
		return this.grid[rij][kolom]; // retourneer cel-waarde ('levend' of 'dood')
	}
	
	private int telBuren(int rij, int kolom) {
		int buren = 0; // declareer buren variabele
		
		for (int i = -1; i <= 1; i++) { // itereer door aangrenzende rijen
			for (int j = -1; j <= 1; j++) { // itereer door aangrenzende kolommen per aangrenzende rij
				if (i == 0 && j == 0) continue; // sla middelste cel over (telt zichzelf niet mee)
				
				int celRij = rij + i; // bepaal rij in speelveld van cel in iteratie
				
				if (celRij < 0 || celRij >= this.RIJEN) continue; // sla cel buiten grid over
				
				int celKolom = kolom + j; // bepaal kolom in speelveld van cel in iteratie
				
				if (celKolom < 0 || celKolom >= this.KOLOMMEN) continue; // sla cel buiten grid over
				
				if (this.grid[celRij][celKolom]) buren++; // telt op als cel 'levend' is
			}
		}
		
		return buren; // retourneer aantal 'buren'
	}
	
	private boolean evolueer(int rij, int kolom) { // evolueert cel op basis van regel-string B3/S23
		boolean nieuweToestand = false; // declareer nieuwe toestand variabele
		
		if (!this.grid[rij][kolom]) {
			if (this.telBuren(rij, kolom) == 3) { // standard life
//			if (this.telBuren(rij, kolom) == 3 || this.telBuren(rij, kolom) == 6) { // high life
				nieuweToestand = true; // cel wordt 'geboren' met precies 3 'buren'
			}
		} else {
			if (this.telBuren(rij, kolom) == 2 || this.telBuren(rij, kolom) == 3) {
				nieuweToestand = true; // cel blijft leven met 2 of 3 'buren'
			}
		}
		
		return nieuweToestand; // retourneer nieuwe toestand van cel
	}
	
	public void volgendeGeneratie() {
		boolean[][] nieuwGrid = new boolean[this.RIJEN][this.KOLOMMEN]; // declareer variabele voor nieuw grid
		
		for (int i = 0; i < this.RIJEN; i++) { // itereer door alle rijen
			for (int j = 0; j < this.KOLOMMEN; j++) { // itereer door alle cellen (kolommen) per rij
				nieuwGrid[i][j] = this.evolueer(i, j); // evolueer cel
			}
		}
		
		this.grid = nieuwGrid; // vervang huidig grid met geëvolueerd grid
		
		this.setChanged(); // markeer het model als 'gewijzigd'
		this.notifyObservers(); // geef wijzigingen door aan observers
	}
	
	public void toggle(int rij, int kolom) {
		if (this.grid[rij][kolom]) { // wijzig waarde cel a.d.h.v. huidige waarde cel
			this.grid[rij][kolom] = false;
		} else {
			this.grid[rij][kolom] = true;
		}
		
		this.setChanged(); // markeer het model als 'gewijzigd'
		this.notifyObservers(new CelPositie(rij, kolom)); // geef wijzigingen door aan observers
	}
	
	public void test() { // test de telBuren() en evolueer() methodes en print de uitkomsten
		System.out.printf("rij: 1, kolom: 3, buren: %d\n", this.telBuren(1, 3));
		System.out.printf("oud: %s nieuw: %s\n\n", this.grid[1][3] ? "X" : ".", this.evolueer(1, 3) ? "X" : ".");
		System.out.printf("rij: 0, kolom: 7, buren: %d\n", this.telBuren(0, 7));
		System.out.printf("oud: %s nieuw: %s\n", this.grid[0][7] ? "X" : ".", this.evolueer(0, 7) ? "X" : ".");
	}
	
}
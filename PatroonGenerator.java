package ConwayGameOfLife;
public class PatroonGenerator {

	public boolean[][] generate(boolean[][] grid, Patroon patroon) {
		switch (patroon) { // initialiseer grid op basis van patroon
			case GLIDER:
				grid[0][1] = true;
				grid[1][2] = true;
				grid[2][0] = true;
				grid[2][1] = true;
				grid[2][2] = true;
				break;
			case SMALL_EXPLODER:
				grid[9][15] = true;
				grid[10][14] = true;
				grid[10][15] = true;
				grid[10][16] = true;
				grid[11][14] = true;
				grid[11][16] = true;
				grid[12][15] = true;
				break;
			case EXPLODER:
				grid[8][13] = true;
				grid[8][15] = true;
				grid[8][17] = true;
				grid[9][13] = true;
				grid[9][17] = true;
				grid[10][13] = true;
				grid[10][17] = true;
				grid[11][13] = true;
				grid[11][17] = true;
				grid[12][13] = true;
				grid[12][15] = true;
				grid[12][17] = true;
				break;
			case TEN_CELL_ROW:
				grid[10][11] = true;
				grid[10][12] = true;
				grid[10][13] = true;
				grid[10][14] = true;
				grid[10][15] = true;
				grid[10][16] = true;
				grid[10][17] = true;
				grid[10][18] = true;
				grid[10][19] = true;
				grid[10][20] = true;
				break;
			case LW_SPACESHIP:
				grid[9][1] = true;
				grid[9][2] = true;
				grid[9][3] = true;
				grid[9][4] = true;
				grid[10][0] = true;
				grid[10][4] = true;
				grid[11][4] = true;
				grid[12][0] = true;
				grid[12][3] = true;
				break;
			case LW_SPACESHIP_R:
				grid[9][25] = true;
				grid[9][26] = true;
				grid[9][27] = true;
				grid[9][28] = true;
				grid[10][25] = true;
				grid[10][29] = true;
				grid[11][25] = true;
				grid[12][26] = true;
				grid[12][28] = true;
				break;
			case CRASH:
				// spaceship links
				grid[9][2] = true;
				grid[9][3] = true;
				grid[9][4] = true;
				grid[9][5] = true;
				grid[10][1] = true;
				grid[10][5] = true;
				grid[11][5] = true;
				grid[12][1] = true;
				grid[12][4] = true;
				
				// spaceship rechts
				grid[9][24] = true;
				grid[9][25] = true;
				grid[9][26] = true;
				grid[9][27] = true;
				grid[10][24] = true;
				grid[10][28] = true;
				grid[11][24] = true;
				grid[12][25] = true;
				grid[12][27] = true;
		}
		
		return grid;
	}
	
}
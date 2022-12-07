package lucioles;

import outils.*;

// Étapes 2 et 3 : Définition de prairies, et simulation sans interaction

public class Prairie {

	// Seuil au delà duquel une luciole émet un flash.
	public static final double SEUIL = 100.0;

	// Indices nommés pour accéder aux données d'une luciole
	public static final int ENERGIE = 0;
	public static final int DELTA = 1;

	public static double[] creerLuciole(double lvlEnergie, double deltaEnergie){
		double[] luciole = new double[2];
		luciole[0] = lvlEnergie;
		luciole[1] = deltaEnergie;

		return luciole;
	}

	public static double[] incrementeLuciole(double[]luciole, int nbPas){
		
		for(int i=0;i<nbPas;i++){
			luciole[0]+=luciole[1];
		}

		return luciole;
	}

	public static double[][] creerPopulation(int nbLuciole){
		double[][] pop = new double[nbLuciole][2];

		for(int i=0;i<nbLuciole;i++){
			
			double lucioleEnergie = RandomGen.rGen.nextDouble()*100;
			double lucioleDeltaEnergie = RandomGen.rGen.nextDouble();
			double[] luciole = creerLuciole(lucioleEnergie, lucioleDeltaEnergie);
			
			for(int j=0;j<2;j++){
				pop[i][j]=luciole[j];
			}	
		}
		
		return pop;
	}

	public static int[][] prairieVide(int nblignes, int nbcolonnes){
		
		int[][] prairie = new int[nblignes][nbcolonnes];
		return prairie;
	}

	public static void affichePrairie(int[][]prairievide){
		
	}
	public static void main(String[] args) {
		// TODO À compléter
		
		double[][] a =creerPopulation(5);
		
		for(int i=0;i<a.length;i++){
			for(int j=0;j<2;j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}

}

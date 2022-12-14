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
			if(luciole[0]>100){
				luciole[0]=0;
			}
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
		for(int i=0;i<prairie.length;i++){
			for(int j=0;j<prairie[i].length;j++){
				prairie[i][j]=-1;
			}
		}
		return prairie;
	}

	public static void affichePrairie(int[][]prairie, double[][] population){
		
		for(int k=0;k<prairie[0].length+2;k++){
			System.out.print("# ");
		}
		
		System.out.println();
		
		for(int i=0;i<prairie.length;i++){
			
			System.out.print("#"+" ");
			
			for(int j=0;j<prairie[i].length;j++){

				if(prairie[i][j]==-1){
					System.out.print("  ");
				}
				else {
					if(population[prairie[i][j]][0]<100){
						System.out.print(". ");
					}
					else if(population[prairie[i][j]][0]>=100){
						System.out.print("* ");
					}
				}
			}
			System.out.print("#");
			System.out.println();
		}

		for(int k=0;k<prairie[0].length+2;k++){
			System.out.print("# ");
		}
		System.out.println();	
	}

	public static int[][] prairieLuciole(int nblignes,int nbcolonnes,double[][] population){
		
		int[][] prairie = prairieVide(nblignes, nbcolonnes);
		int ligne = 0;
		int colonne =0;
		
		
		for(int i =0;i<population.length;i++){
			
			for(int j=0;j<=population.length;j++){
				ligne = RandomGen.rGen.nextInt(nblignes);
				colonne = RandomGen.rGen.nextInt(nbcolonnes);
				if(prairie[ligne][colonne]== -1){
					prairie[ligne][colonne]=i;
					break;
				}
			}
		}
		
		
		
		return prairie;
	}

	public static void simulationprairie(int nbpas){
		
		double[][] b=creerPopulation(5);
		int[][] a =prairieLuciole(5, 5, b);
		affichePrairie(a, b);
		
		for(int i=0;i<nbpas-1;i++){
			for(int j=0;j<b.length;j++){
				b[j][0]+=b[j][1];
			}
			affichePrairie(a, b);

			for(int k=0;k<b.length;k++){
				if(b[k][0]>100){
					b[k][0]=0;
				}
			}
		}
	
	}
	
	
	public static void simulationPrairieGIF(int nbpas,double[][]population,int[][]prairie){
		String image ="img/prairie";
		String[] images = new String[nbpas];
		for(int i=0;i<nbpas;i++){
			for(int j=0;j<population.length;j++){
				population[j][0]+=population[j][1];
			}
			affichePrairie(prairie,population );
			BitMap.bmpEcritureFichier(image+i ,prairie, population,SEUIL);
			images[i]=image+i+".bmp";
			for(int k=0;k<population.length;k++){
				if(population[k][0]>100){
					population[k][0]=0;
				}
			}
		}

		GifCreator.construitGIF("simu/prairie.gif", images);
		
	}
	public static void main(String[] args) {
		double[][] population = creerPopulation(15);
		int[][] prairie = prairieLuciole(10, 10, population);
		simulationPrairieGIF(30, population,prairie);
	
	}

}

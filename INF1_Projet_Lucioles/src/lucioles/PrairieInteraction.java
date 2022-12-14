package lucioles;

import outils.*;

// Étape 4: Simulation d'une prairie avec interaction entre les lucioles

public class PrairieInteraction {

	// Seuil au delà duquel une luciole émet un flash.
	public static final double SEUIL = 100.0;

	// Indices nommés pour accéder aux données d'une luciole
	public static final int ENERGIE = 0;
	public static final int DELTA = 1;

	// Définition de l'apport d'énergie par flash, et du rayon de voisinage
	public static final double APPORT = 15.0;
	public static final int RAYON = 2;
	
	//cette fonction compte le nombre de lucioles dans la prairie
	public static int nbLucioles(int[][] prairie){
		
		int lucioles =0;
		
		
		for(int i =0;i<prairie.length;i++){
			for(int j=0;j<prairie[i].length;j++){
				if(prairie[i][j]>=0){
					lucioles ++;

				}
			}
		}
		return lucioles;
	}

	//cette fonction nous donne la position des lucioles dans la prairie
	public static int[][] XY(int nbluciole,int[][]prairie){
		int[][] XY=new int[nbluciole][2];
		//int luciole =0;
		for(int i =0;i<prairie.length;i++){
			for(int j=0;j<prairie[i].length;j++){
				if(prairie[i][j]!=-1){
					XY[prairie[i][j]][0] = i;
					XY[prairie[i][j]][1] = j;
				
				}
			}
		}
		return XY;
	}

	public static int[][] voisinage(int[][]prairie, int[][] XY, int nbluciole){
		int x=0;
		int y=0;
		int[][] voisins = new int[nbluciole][];

		for(int i =0 ;i<nbluciole;i++){
			
			x=XY[i][0]-RAYON;
			if(x<0){
				while(x<0){
					x++;
				}
			}

			y=XY[i][1]-RAYON;
			if(y<0){
				while(y<0){
					y++;
				}
			}

			int c = x+5;
			if(c>prairie.length){
				while(c>prairie.length){
					c--;
				}
			}

			int d = y+5;
			if(d>prairie[0].length){
				while(d>prairie[0].length){
					d--;
				}
			}

			int nbvoisin=0;
			for(int j=x;j<c;j++){
				for(int k=y;k<d;k++){
					if(prairie[j][k]!=-1 && prairie[j][k]!=i){
						nbvoisin++;
					}
				}
			}

			int[] voisin=new int[nbvoisin];
			int l=0;
			for(int j=x;j<c;j++){
				for(int k=y;k<d;k++){
					if(prairie[j][k]!=-1 && prairie[j][k]!=i){
						voisin[l]=prairie[j][k];
						l++;
					}
				}
			}

			voisins[i]= new int[nbvoisin];
			for(int j=0;j<voisin.length;j++){
				voisins[i][j]=voisin[j];
			}

		}
		
		return voisins;
	}

	public static void incrementeLuciole(double[][]population, int nbpas, int[][] voisinage,int[][] prairie){
		
		String image ="img/prairie";
		String[] images = new String[nbpas];
		for(int i=0;i<nbpas;i++){
			for(int j=0;j<population.length;j++){
				population[j][0]+=population[j][1];
			}
			
			Prairie.affichePrairie(prairie,population );
			BitMap.bmpEcritureFichier(image+i ,prairie, population,SEUIL);
			images[i]=image+i+".bmp";
			
			for(int k=0;k<population.length;k++){
				if(population[k][0]>100){
					for(int j=0;j<voisinage[k].length;j++){
						population[voisinage[k][j]][0]+=15;
					}
					population[k][0]=0;
				}
			}
		}

		GifCreator.construitGIF("simu/prairie.gif", images);
		
	}

	public static void main(String[] args) {
		// TODO À compléter
		double[][] popu = Prairie.creerPopulation(60);
		int[][] prairie = Prairie.prairieLuciole(40, 40, popu);
		int nbluciole = nbLucioles(prairie);
		int[][] XY = XY(nbluciole,prairie);
		int[][] c=voisinage(prairie, XY, nbluciole);
		incrementeLuciole(popu, 200, c, prairie);
		
	}

}

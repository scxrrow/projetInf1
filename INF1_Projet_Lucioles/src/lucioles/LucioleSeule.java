package lucioles;

// Étape 1 : Simulation d'une seule luciole


public class LucioleSeule {

	// Seuil au delà duquel une luciole émet un flash.
	public static final double SEUIL = 100.0;

	public static char symboliseEnergie(double lvlEnergie){
		char c;
		if(lvlEnergie<SEUIL){
			c='.';
		}
		else{
			c='*';
		}

		return c;
	}

	public static void afficheLuciole(double lvlEnergie,boolean verbeux){
		if(verbeux==false){
			System.out.println(symboliseEnergie(lvlEnergie));
		}
		else{
			System.out.println(symboliseEnergie(lvlEnergie)+" "+ lvlEnergie);
		}
	}

	public static double incrementeLuciole(double deltaEnergie, double lvlEnergie){
		double energie = deltaEnergie + lvlEnergie;
		
		return energie ;
	}

	public static void simuleLucioleNBpas(double lvlEnergie, double deltaEnergie, int nbPas){
		double luciole = incrementeLuciole(deltaEnergie, lvlEnergie);
		for(int i=0;i<nbPas;i++){
			
			if ( luciole>100){
				luciole = 0;
			}

			
			luciole =incrementeLuciole(deltaEnergie,luciole);
			afficheLuciole(luciole, true);
		}
	}

	public static void simuleLucioleNBflash(double lvlEnergie, double deltaEnergie){
		double luciole = incrementeLuciole(deltaEnergie, lvlEnergie);
		int nbFlash=0;
		while (nbFlash<3){
			
			if ( luciole>100){
				luciole = 0;
				nbFlash ++;
				if (nbFlash == 3){
					break;
				}
			}

			
			luciole =incrementeLuciole(deltaEnergie,luciole);
			afficheLuciole(luciole, true);
		}
	}
	
	public static void main(String[] args) {
		// TODO À compléter
		double lucioleEnergie = RandomGen.rGen.nextDouble()*100;
		double lucioleDeltaEnergie = RandomGen.rGen.nextDouble();
		afficheLuciole(lucioleEnergie, false);
		
		
		simuleLucioleNBflash(lucioleEnergie, lucioleDeltaEnergie);
		
		
	}

}

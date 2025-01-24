public class PruebaV{
	public static void main(String[] args){
	
		Verificador lambdaVerificador= (a) -> {
			if(a % 2  == 0) {
				System.out.println("El numero es par.");
				return true;
			}
			else {
				System.out.println("El numero es impar.");
				return false;
			}
		};
		printThing(lambdaVerificador);
	
	}
	
	static void printThing(Verificador thing){
		thing.verificar(4);
	}
}

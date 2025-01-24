public class PruebaOp{
	public static void main(String[] args){
	
		Operacion lambdaOperacion= (a,b) -> {
			System.out.println(a + b);
			return a+b;
		};
		printThing(lambdaOperacion);
	
	}
	
	static void printThing(Operacion thing){
		thing.realizarOperacion(5,4);
	}
}

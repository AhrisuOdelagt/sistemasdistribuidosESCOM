public class Lambdas {
	public static void main(String args[]) {
	
		// Printable lambdaPrintable = (p, s) -> System.out.println(p + "Meow" + s);
		Printable lambdaPrintable = (p, s) -> {
			return p + "Meow" + s;
		};
		
		/*printThing(
			() -> System.out.println("Meow")
		);*/
		String retorno = printThing(lambdaPrintable);
	}
	static String printThing(Printable thing) {
		return thing.print();
	}
}

public class EjerciciosSerie4{
    public static void main(String[] args) {
	int i = 0, serie[]= new int[20];
	serie[0] = 0;
	serie[1] = 1;
	serie[2] = 1;
        for(i = 0; i < 20; i++) {
            if(i < 3) {
                System.out.println("Termino " + (int)(i + 1) + ": " + serie[i]);
            }
            else {
                serie[i] = serie[i - 1] + serie[i - 2] + serie[i - 3];
                System.out.println("Termino " + (int)(i + 1) + ": " + serie[i]);
            }
        }
    }
}

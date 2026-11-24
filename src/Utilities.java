import java.util.ArrayList;

public class Utilities {
    public static int readValidInt(int min, int max, int[] invalid) {
        int read = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Insira valor de " + min + " a " + max + " ...: ");
            String input = Main.scan.nextLine();

            try {
                read = Integer.parseInt(input);

                if (read < min || read > max) {
                    System.out.println("Valor fora do intervalo permitido. Tente novamente.\n");
                    continue;
                }
                boolean isInvalid = false;
                for (int i : invalid) {
                    if (read == i) {
                        isInvalid = true;
                        break;
                    }
                }
                if (isInvalid) {
                    System.out.println("Este valor é proibido. Tente novamente.\n");
                } else {
                    isValid = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números inteiros.\n");
            }
        }

        System.out.println();
        return read;
    }

    public static double readValidDouble(double min, double max, double[] invalid) {
        double read = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Insira valor de " + min + " a " + max + " ...: ");
            String input = Main.scan.nextLine();

            try {
                read = Double.parseDouble(input);

                if (read < min || read > max) {
                    System.out.println("Valor fora do intervalo permitido. Tente novamente.\n");
                    continue;
                }
                boolean isInvalid = false;
                for (double i : invalid) {
                    if (read == i) {
                        isInvalid = true;
                        break;
                    }
                }
                if (isInvalid) {
                    System.out.println("Este valor é proibido. Tente novamente.\n");
                } else {
                    isValid = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.\n");
            }
        }

        System.out.println();
        return read;
    }

    public static char readValidSN(){
        char op = 'x';
        while (op != 's' && op != 'n') {
            System.out.println("..........: ");
            op = Main.scan.nextLine().charAt(0);
            op = Character.toLowerCase(op);
            if(op != 's' && op != 'n'){
                System.out.println("Opção inválida, insira apenas 'S' ou 'N'.");
                System.out.print("Tente novamente ");
            }
        }

        return op;
    }

    public static int readValidType(){
        System.out.println("== Escolha uma categoria ==");
        System.out.println(" 1. Índice na lista");
        System.out.println(" 2. País");
        System.out.println(" 3. Estado");
        System.out.println(" 4. Microrregião");
        System.out.println(" 5. Município");
        System.out.println(" 6. Extensão territorial");
        System.out.println(" 7. População");
        System.out.println(" 8. IDH");
        System.out.println(" 9. PIB");
        System.out.println("10. Atividade econômica");
        System.out.println("11. Renda média");
        System.out.println("12. Faixa etária");
        System.out.println("13. Quantidade de mulheres");

        return readValidInt(1, 13, null);
    }

    public static ArrayList<Integer> filterType(String[][][] cities, int type, String value){
        ArrayList<Integer> indexes = new ArrayList<>();

        boolean ok = false;
        int[] validTypes = {0, 1, 2, 3, 4, 9};
        for(int i : validTypes)
            if(type == i) 
                ok = true;
        if(!ok)
            return indexes;    
        
        for(int i=0; i<cities.length; i++) {
            if(cities[i][type][0].equalsIgnoreCase(value))
                indexes.add(i);
        }

        return indexes;
    }
}

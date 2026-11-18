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

    // Returns an array of indexes at which the city value
    // for the specified type is equal to "value".
    // Ex.: filterString(cities, 2, "RS")
    // -> returns an array of indexes at which the city's
    // UF is equal to "RS".
    public static ArrayList<Integer> filterString(String[][][] cities, int type, String value){
        ArrayList<Integer> indexes = new ArrayList<>();

        boolean ok = false;
        int[] validTypes = {1, 2, 3, 9};
        for(int i : validTypes)
            if(type == i) 
                ok = true;
        if(!ok)
            return indexes;    
        
        for(int i=0; i<cities.length; i++) {
            if(cities[i][type][0].equals(value))
                indexes.add(i);
        }

        return indexes;
    }
}

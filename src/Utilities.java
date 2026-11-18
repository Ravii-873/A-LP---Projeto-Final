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
}

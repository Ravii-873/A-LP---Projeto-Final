public class Report {
    public static void printCityReport(String[][][] cities, int index){
        String[][] city = cities[index];
        double area = Double.parseDouble(city[5][0]);
        int population = Integer.parseInt(city[6][0]);
        double gdp = Double.parseDouble(city[8][0]);
        int women = Integer.parseInt(city[12][0]);
        System.out.println("=== Relatório completo de " + city[4][0] + " ===");
        System.out.println("  Índice na lista ..........: " + city[0][0]);
        System.out.println("  País .....................: " + city[1][0]);
        System.out.println("  Estado ...................: " + city[2][0]);
        System.out.println("  Microrregião .............: " + city[3][0]);
        System.out.println("  Município ................: " + city[4][0]);
        System.out.printf("  Extensão territorial .....: %.2f\nkm²", area);
        System.out.println("  População ................: " + population);
        System.out.println("  IDH ......................: " + city[7][0]);
        System.out.printf("  PIB ......................: R$%.2f mi\n", gdp);
        System.out.println("  Atividade econômica ......: " + city[9][0]);
        System.out.println("  Renda média ..............: " + city[10][0]);
        System.out.println("  Faixas etárias (anos):");
        for(int i=0; i<city[11].length; i++){
            double band = Double.parseDouble(city[11][i]);
            System.out.printf("    %d a %d: %.2f%%\n", 2*i, 2*i+2, band);
        }
        System.out.println("  Quantidade de mulheres ...: " + women);

        System.out.println();
        System.out.printf("  PIB per capita ...........: R$%.2f\n", (double) 1e6*gdp/population);
        System.out.printf("  PIB por km² ..............: R$%.2f mil\n", 1000*gdp/area);
    }
}

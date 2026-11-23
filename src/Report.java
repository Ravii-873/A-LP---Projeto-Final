public class Report {
    public static void printCityReport(String[][][] cities, int index){
        String[][] city = cities[index];
        double area = Double.parseDouble(city[5][0]);
        int population = Integer.parseInt(city[6][0]);
        double hdi = Double.parseDouble(city[7][0]);
        double gdp = Double.parseDouble(city[8][0]);
        double avgIncome = Double.parseDouble(city[10][0]);
        int women = Integer.parseInt(city[12][0]);

        System.out.println("\n=== Relatório completo de " + city[4][0] + " ===\n");
        System.out.println("-- Dados obtidos --\n");
        System.out.println("  Índice na lista ..........: " + city[0][0]);
        System.out.println("  País .....................: " + city[1][0]);
        System.out.println("  Estado ...................: " + city[2][0]);
        System.out.println("  Microrregião .............: " + city[3][0]);
        System.out.println("  Município ................: " + city[4][0]);
        System.out.printf("  Extensão territorial .....: %.2fkm²\n", area);
        System.out.println("  População ................: " + population + " hab");
        System.out.printf("  IDH ......................: %.3f\n", hdi);
        System.out.printf("  PIB ......................: R$%.2f mi\n", gdp);
        System.out.println("  Atividade econômica ......: " + city[9][0]);
        System.out.printf("  Renda média ..............: R$%.2f\n", avgIncome);
        System.out.println("  Faixas etárias (anos):");
        int predominantAgeGroup = 0;
        for(int i=0; i<city[11].length; i++){
            double percentage = Double.parseDouble(city[11][i]);
            if(i < 50)
                System.out.printf("    %d a %d: %.2f%%\n", 2*i, 2*i+2, percentage);
            else
                System.out.printf("    %d ou mais: %.2f%%\n", 2*i, percentage);

            if(Double.parseDouble(city[11][predominantAgeGroup]) < percentage)
                predominantAgeGroup = i;
        }
        System.out.println("  Quantidade de mulheres ...: " + women);
        System.out.println();

        System.out.println("-- Análise dos dados --\n");
        System.out.printf("  Densidade demográfica ....: %.2f hab/km²\n", (double) population / area);
        System.out.printf("  PIB per capita ...........: R$%.2f\n", (double) 1e6*gdp/population);
        System.out.printf("  PIB por km² ..............: R$%.3f mi\n", gdp/area);
        System.out.printf("  %.2f%% Homens X Mulheres %.2f%%\n", 100.0*(population-women)/population, 100.0*women/population);
        System.out.printf("  Classificação do IDH (%.3f): ", hdi);
        if(hdi < 0.55) System.out.println("Baixo");
        else if(hdi < 0.7) System.out.println("Médio");
        else if(hdi < 0.8) System.out.println("Alto");
        else System.out.println("Muito alto");
        
        System.out.println("\n  Análise das faixas etárias:");
        double minor = 0;
        double adult = 0;
        double elderly = 0;
        for(int i=0; i<9; i++)
            minor += Double.parseDouble(city[11][i]);
        for(int i=9; i<30; i++)
            adult += Double.parseDouble(city[11][i]);
        for(int i=30; i<city[11].length; i++)
            elderly += Double.parseDouble(city[11][i]);
        System.out.printf("    Crianças e adolescentes ...: %.2f%%\n", minor);
        System.out.printf("    Adultos ...................: %.2f%%\n", adult);
        System.out.printf("    Idosos ....................: %.2f%%\n", elderly);
        String predominantAGBand = predominantAgeGroup*2 + " a " + (predominantAgeGroup*2+2);
        if(predominantAgeGroup == 50)
            predominantAGBand = "100 ou mais";
        System.out.println("    Faixa etária predominante: " + predominantAGBand + " anos\n");

        System.out.printf("  Massa salarial estimada...: R$%.3f mi\n", population * avgIncome / 1e6);
    }
}

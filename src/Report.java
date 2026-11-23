public class Report {
    public static void printCityReport(String[][][] cities, int index){
        String[][] city = cities[index];
        double area = Double.parseDouble(city[5][0]);
        int population = Integer.parseInt(city[6][0]);
        double HDI = Double.parseDouble(city[7][0]);
        double GDP = Double.parseDouble(city[8][0]);
        double avgIncome = Double.parseDouble(city[10][0]);
        int women = Integer.parseInt(city[12][0]);
        
        int predominantAgeGroup = 0;
        for(int i=0; i<city[11].length; i++){
            double percentage = Double.parseDouble(city[11][i]);
            if(Double.parseDouble(city[11][predominantAgeGroup]) < percentage)
                predominantAgeGroup = i;
        }
        String predominantAGBand = predominantAgeGroup*2 + " a " + (predominantAgeGroup*2+2);
        if(predominantAgeGroup == 50)
            predominantAGBand = "100 ou mais";

        String HDIClassfication = " --- ";
        if(HDI < 0.55) HDIClassfication = "Baixo";
        else if(HDI < 0.7) HDIClassfication = "Médio";
        else if(HDI < 0.8) HDIClassfication = "Alto";
        else HDIClassfication = "Muito alto";

        double density = (double) population / area;
        double GDPPerCapita = (double) 1e6*GDP/population;
        double GDPPerKm = GDP/area;
        double menPercentage = 100.0*(population-women)/population;
        double womenPercentage = 100.0*women/population;

        double minor = 0;
        double adult = 0;
        double elderly = 0;
        for(int i=0; i<9; i++)
            minor += Double.parseDouble(city[11][i]);
        for(int i=9; i<30; i++)
            adult += Double.parseDouble(city[11][i]);
        for(int i=30; i<city[11].length; i++)
            elderly += Double.parseDouble(city[11][i]);

        double totalPayroll = population * avgIncome / 1e6;

        System.out.println("\n=== Relatório completo de " + city[4][0] + " ===\n");
        System.out.println("-- Dados obtidos --\n");
        System.out.println("  Índice na lista ..........: " + city[0][0]);
        System.out.println("  País .....................: " + city[1][0]);
        System.out.println("  Estado ...................: " + city[2][0]);
        System.out.println("  Microrregião .............: " + city[3][0]);
        System.out.println("  Município ................: " + city[4][0]);
        System.out.printf("  Extensão territorial .....: %.2fkm²\n", area);
        System.out.println("  População ................: " + population + " hab");
        System.out.printf("  IDH ......................: %.3f\n", HDI);
        System.out.printf("  PIB ......................: R$%.2f mi\n", GDP);
        System.out.println("  Atividade econômica ......: " + city[9][0]);
        System.out.printf("  Renda média ..............: R$%.2f\n", avgIncome);
        System.out.println("  Faixas etárias (anos):");
        for(int i=0; i<city[11].length; i++){
            double percentage = Double.parseDouble(city[11][i]);
            if(i < 50)
                System.out.printf("    %d a %d: %.2f%%\n", 2*i, 2*i+2, percentage);
            else
                System.out.printf("    %d ou mais: %.2f%%\n", 2*i, percentage);
        }
        System.out.println("  Quantidade de mulheres ...: " + women);
        System.out.println();

        System.out.println("-- Análise dos dados --\n");
        System.out.printf("  Densidade demográfica ....: %.2f hab/km²\n", density);
        System.out.printf("  PIB per capita ...........: R$%.2f\n", GDPPerCapita);
        System.out.printf("  PIB por km² ..............: R$%.3f mi\n", GDPPerKm);
        System.out.printf("  %.2f%% Homens X Mulheres %.2f%%\n", menPercentage, womenPercentage);
        System.out.printf("  Classificação do IDH (%.3f): %s", HDI, HDIClassfication);
        
        System.out.println("\n  Análise das faixas etárias:");
        System.out.printf("    Crianças e adolescentes ...: %.2f%%\n", minor);
        System.out.printf("    Adultos ...................: %.2f%%\n", adult);
        System.out.printf("    Idosos ....................: %.2f%%\n", elderly);
        System.out.println("    Faixa etária predominante: " + predominantAGBand + " anos\n");

        System.out.printf("  Massa salarial estimada...: R$%.3f mi\n", totalPayroll);
    }
}

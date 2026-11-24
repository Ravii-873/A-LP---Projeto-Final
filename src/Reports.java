import java.util.ArrayList;

public class Reports {
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

        System.out.printf("\n=== Relatório completo de %s ===\n\n", city[4][0]);
        System.out.println("-- Dados obtidos --\n");
        System.out.printf("  Índice na lista ..........: %s\n", city[0][0]);
        System.out.printf("  País .....................: %s\n", city[1][0]);
        System.out.printf("  Estado ...................: %s\n", city[2][0]);
        System.out.printf("  Microrregião .............: %s\n", city[3][0]);
        System.out.printf("  Município ................: %s\n", city[4][0]);
        System.out.printf("  Extensão territorial .....: %,.2fkm²\n", area);
        System.out.printf("  População ................: %,d hab\n", population);
        System.out.printf("  IDH ......................: %.3f\n", HDI);
        System.out.printf("  PIB ......................: R$%,.2f mi\n", GDP);
        System.out.printf("  Atividade econômica ......: %s\n", city[9][0]);
        System.out.printf("  Renda média ..............: R$%,.2f\n", avgIncome);
        System.out.println("  Faixas etárias (anos):");
        for(int i=0; i<city[11].length; i++){
            double percentage = Double.parseDouble(city[11][i]);
            if(i < 50)
                System.out.printf("    %d a %d: %.2f%%\n", 2*i, 2*i+2, percentage);
            else
                System.out.printf("    %d ou mais: %.2f%%\n", 2*i, percentage);
        }
        System.out.printf("  Quantidade de mulheres ...: %,d\n", women);
        System.out.println();

        System.out.println("-- Análise dos dados --\n");
        System.out.printf("  Densidade demográfica ....: %,.2f hab/km²\n", density);
        System.out.printf("  PIB per capita ...........: R$%,.2f\n", GDPPerCapita);
        System.out.printf("  PIB por km² ..............: R$%,.3f mi\n", GDPPerKm);
        System.out.printf("  %.2f%% Homens X Mulheres %.2f%%\n", menPercentage, womenPercentage);
        System.out.printf("  Classificação do IDH (%.3f): %s\n", HDI, HDIClassfication);

        System.out.println("\n  Análise das faixas etárias:");
        System.out.printf("    Crianças e adolescentes ...: %.2f%%\n", minor);
        System.out.printf("    Adultos ...................: %.2f%%\n", adult);
        System.out.printf("    Idosos ....................: %.2f%%\n", elderly);
        System.out.printf("    Faixa etária predominante: %s anos\n\n", predominantAGBand);

        System.out.printf("  Massa salarial estimada...: R$%,.3f mi\n", totalPayroll);
    }

    public static void printRegionReport(String[][][] cities, ArrayList<Integer> arrRegionIndexes, int regionType){
        int[] indexes = new int[arrRegionIndexes.size()];
        int qttCities = 0;

        for(int i=0; i<arrRegionIndexes.size(); i++){
            int index = arrRegionIndexes.get(i);

            indexes[qttCities] = index;
            qttCities++;
        }

        double totalArea = 0;
        int totalPopulation = 0;
        double totalHDI = 0;
        double totalGDP = 0;
        double totalIncome = 0;
        double totalPayroll = 0;
        long totalWomen = 0;

        int extractionCount = 0;
        int industryCount = 0;
        int commerceCount = 0;

        double[] agePopulation = new double[51];

        for(int i=0; i<arrRegionIndexes.size(); i++){
            String[][] city = cities[indexes[i]];

            double area = Double.parseDouble(city[5][0]);
            int population = Integer.parseInt(city[6][0]);
            double HDI = Double.parseDouble(city[7][0]);
            double GDP = Double.parseDouble(city[8][0]);
            double income = Double.parseDouble(city[10][0]);
            int women = Integer.parseInt(city[12][0]);

            totalArea += area;
            totalPopulation += population;
            totalHDI += HDI;
            totalGDP += GDP;
            totalIncome += income;
            totalPayroll += population * income;
            totalWomen += women;

            if(city[9][0].equals("Extração"))
                extractionCount++;
            else if(city[9][0].equals("Indústria"))
                industryCount++;
            else if(city[9][0].equals("Comércio"))
                commerceCount++;

            for(int j=0; j<51; j++){
                double percentage = Double.parseDouble(city[11][j]);
                agePopulation[j] += population * percentage / 100.0;
            }
        }

        double avgPopulation = (double) totalPopulation / qttCities;
        double avgArea = totalArea / qttCities;
        double avgHDI = totalHDI / qttCities;
        double avgGDP = totalGDP / qttCities;
        double avgIncome = totalIncome / qttCities;
        double weightedIncome = totalPayroll / totalPopulation;

        double density = totalPopulation / totalArea;
        double GDPPerCapita = totalGDP * 1e6 / totalPopulation;
        double GDPPerKm = totalGDP / totalArea;

        long totalMen = totalPopulation - totalWomen;

        String microregionName = cities[indexes[0]][3][0];
        String stateName = cities[indexes[0]][2][0];
        String countryName = cities[indexes[0]][1][0];
        String regionName = cities[indexes[0]][regionType][0];

        if(regionType >= 1) System.out.printf("\n=== Relatório completo de %s ===\n\n", regionName);
        else System.out.println("\n=== Relatório Geral ===\n");

        System.out.println("-- Dados gerais --\n");

        if(regionType >= 1) System.out.printf("  País .....................: %s\n", countryName);
        if(regionType >= 2) System.out.printf("  Estado ...................: %s\n", stateName);
        if(regionType >= 3) System.out.printf("  Microrregião .............: %s\n", microregionName);
        System.out.printf("  Quantidade de municípios .: %,d\n", qttCities);
        System.out.printf("  Extensão territorial .....: %,.2f km²\n", totalArea);
        System.out.printf("  Ex. territorial média ....: %,.2f km²\n", avgArea);
        System.out.printf("  População total ..........: %,d hab\n", totalPopulation);
        System.out.printf("  População média ..........: %,.2f hab\n", avgPopulation);
        System.out.printf("  IDH médio ................: %.3f\n", avgHDI);
        System.out.printf("  PIB total ................: R$%,.2f mi\n", totalGDP);
        System.out.printf("  PIB médio municipal ......: R$%,.2f mi\n", avgGDP);
        System.out.printf("  Renda média municipal ....: R$%,.2f\n", avgIncome);
        System.out.printf("  Renda média ponderada ....: R$%,.2f\n", weightedIncome);
        System.out.printf("  Massa salarial estimada ..: R$%,.3f mi\n", totalPayroll / 1e6);

        System.out.println("\n-- Análise dos dados --\n");

        System.out.printf("  Densidade demográfica ....: %,.2f hab/km²\n", density);
        System.out.printf("  PIB per capita ...........: R$%,.2f\n", GDPPerCapita);
        System.out.printf("  PIB por km² ..............: R$%,.3f mi\n", GDPPerKm);

        System.out.printf("  Total de homens ..........: %,d\n", totalMen);
        System.out.printf("  Total de mulheres ........: %,d\n", totalWomen);

        System.out.printf("  %.2f%% Homens X Mulheres %.2f%%\n",
            100.0 * totalMen / totalPopulation,
            100.0 * totalWomen / totalPopulation);

        System.out.printf("  Classificação do IDH médio (%.3f): ",
            avgHDI);

        String HDIClassfication = " --- ";
        if(avgHDI < 0.55) HDIClassfication = "Baixo";
        else if(avgHDI < 0.7) HDIClassfication = "Médio";
        else if(avgHDI < 0.8) HDIClassfication = "Alto";
        else HDIClassfication = "Muito alto";
        System.out.printf("%s\n", HDIClassfication);

        System.out.println("\n  Distribuição das atividades econômicas:");

        System.out.printf("    Extração ...............: %,d municípios (%.2f%%)\n",
            extractionCount, 100.0 * extractionCount / qttCities);

        System.out.printf("    Indústria ..............: %,d municípios (%.2f%%)\n",
            industryCount, 100.0 * industryCount / qttCities);

        System.out.printf("    Comércio ...............: %,d municípios (%.2f%%)\n",
            commerceCount, 100.0 * commerceCount / qttCities);

        System.out.println("\n  Análise das faixas etárias:");

        double minor = 0;
        double adult = 0;
        double elderly = 0;
        int predominantAgeGroup = 0;

        for(int i=0; i<51; i++){
            double percentage = 100.0 * agePopulation[i] / totalPopulation;

            if(i < 9) minor += percentage;
            else if(i < 30) adult += percentage;
            else elderly += percentage;

            if(agePopulation[i] > agePopulation[predominantAgeGroup])
                predominantAgeGroup = i;

            if(i < 50)
                System.out.printf("    %d a %d: %.2f%%\n", 2*i, 2*i+2, percentage);
            else
                System.out.printf("    %d ou mais: %.2f%%\n", 2*i, percentage);
        }

        String predominantBand;

        if(predominantAgeGroup == 50)
            predominantBand = "100 ou mais";
        else
            predominantBand = predominantAgeGroup * 2 + " a " + (predominantAgeGroup * 2 + 2);

        System.out.printf("    Crianças e adolescentes ...: %,.2f%%\n", minor);
        System.out.printf("    Adultos ...................: %,.2f%%\n", adult);
        System.out.printf("    Idosos ....................: %,.2f%%\n", elderly);

        System.out.printf("    Faixa etária predominante: %s anos\n", predominantBand);
        
        final int limit = Math.min(5, qttCities);
        System.out.println("\n-- Rankings municipais --");

        System.out.printf("\n  TOP %d - População:\n", limit);
        printRanking(cities, indexes, qttCities, limit, 6, 0, " hab", true);

        System.out.printf("\n  BOTTOM %d - População:\n", limit);
        printRanking(cities, indexes, qttCities, limit, 6, 0, " hab", false);

        System.out.printf("\n  TOP %d - Extensão territorial:\n", limit);
        printRanking(cities, indexes, qttCities, limit, 5, 2, " km²", true);

        System.out.printf("\n  BOTTOM %d - Extensão territorial:\n", limit);
        printRanking(cities, indexes, qttCities, limit, 5, 2, " km²", false);

        System.out.printf("\n  TOP %d - IDH:\n", limit);
        printRanking(cities, indexes, qttCities, limit, 7, 3, "", true);

        System.out.printf("\n  BOTTOM %d - IDH:\n", limit);
        printRanking(cities, indexes, qttCities, limit, 7, 3, "", false);

        System.out.println();
    }

    public static void printRanking(
        String[][][] cities,
        int[] indexes,
        int qttCities,
        int limit,
        int field,
        int decimals,
        String unit,
        boolean descending
    ){
        boolean[] used = new boolean[qttCities];

        for(int i=0; i<limit; i++){
            int selected = -1;
            double selectedValue = 0;

            for(int j=0; j<qttCities; j++){
                if(used[j])
                    continue;

                double value = Double.parseDouble(
                    cities[indexes[j]][field][0]
                );

                if(selected == -1
                    || (descending && value > selectedValue)
                    || (!descending && value < selectedValue)){

                    selected = j;
                    selectedValue = value;
                }
            }

            used[selected] = true;

            String cityName = cities[indexes[selected]][4][0];

            System.out.printf("    %d. %-30s: %,." + decimals + "f%s\n",
                i+1, cityName, selectedValue, unit);
        }
    }
}
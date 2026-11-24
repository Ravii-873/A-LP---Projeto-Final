import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String file = "../database/brazilian_cities_data.csv"; 
    static Scanner scan = new Scanner(System.in);
    static int[] emptyIntArray = {};
    
    public static void exit(){
        System.out.println("\n=== Obrigado por utilizar! ===\n");
        scan.close();
    }

    public static int mainMenu(String DBName){
        System.out.println("\n=== Análises de \"" + DBName + "\" ===\n");
        System.out.println("Escolha uma ação: ");
        System.out.println("  1. Relatórios por município");
        System.out.println("  2. Relatórios por microrregião");
        System.out.println("  3. Relatórios por UF");
        System.out.println("  4. Relatórios gerais");
        System.out.println("  5. Encontrar municípios por dados");
        System.out.println("  6. Encontrar municípios por dados");
        System.out.println("  0. Sair");
        System.out.println();
        return Utilities.readValidInt(0, 6, emptyIntArray);
    }

    public static int multipleCitiesCase(String[][][] cities, ArrayList<Integer> citiesIndexes){
        for(int i=0; i<citiesIndexes.size(); i++){
            String[][] cityi = cities[citiesIndexes.get(i)];
            System.out.println("  " + (i+1) + ". " + cityi[4][0]);
            System.out.println("    " + cityi[1][0]);
            System.out.println("    " + cityi[2][0]);
            System.out.println("    " + cityi[3][0]);
            System.out.println();
        }

        System.out.println("  Abaixo, insira número do município desejado.\n");
        int op = Utilities.readValidInt(1, citiesIndexes.size(), emptyIntArray);
        return op-1;
    }
    
    public static void main(String[] args) {
        String DBName = null;
        int qttCities = 0;
        String[][][] cities = null;
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String firstLine = br.readLine();
            String parsedFirstLine[] = firstLine.split(";");
            DBName = parsedFirstLine[0];
            qttCities = Integer.parseInt(parsedFirstLine[1]);

            cities = new String[qttCities][13][];
            for(int i=0; i<cities.length; i++){
                String[] parsedLine = br.readLine().split(";");

                for(int j=0; j<cities[i].length; j++){
                    if(j != 11) {
                        cities[i][j] = new String[1];
                        cities[i][j][0] = parsedLine[j];
                    }
                    else {
                        String[] parsedAgeData = parsedLine[j].split("#");
                        cities[i][j] = new String[parsedAgeData.length];
                        for(int k=0; k<cities[i][j].length; k++)
                            cities[i][j][k] = parsedAgeData[k];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo de dados não foi encontrado!");
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo de dados!");
        }

        char anotherQuery = 's';
        while(anotherQuery == 's'){
            int op = mainMenu(DBName);
            switch (op) {
                case 1:{
                    System.out.println("\n=== Insira o nome do município ===");
                    String city = scan.nextLine();
                    ArrayList<Integer> arrCitiesIndexes = Utilities.filterType(cities, 4, city);
                    int wishedIndex = 0;
                    if(arrCitiesIndexes.size() == 0){
                        System.out.println("  Este município não foi encontrado na base de dados!");
                        break;
                    }
                    if(arrCitiesIndexes.size() > 1){
                        System.out.println("  Há mais de um município com este nome.");
                        System.out.println("  Sobre qual deles você deseja a análise?\n");
                        wishedIndex = multipleCitiesCase(cities, arrCitiesIndexes);
                    }
    
                    int cityIndex = arrCitiesIndexes.get(wishedIndex);
                    Report.printCityReport(cities, cityIndex);

                    break;
                }
                case 2:{
                    System.out.println("\n=== Insira o nome da microrregião ===");
                    String microregion = scan.nextLine();
                    ArrayList<Integer> arrMicroregionsIndexes = Utilities.filterType(cities, 3, microregion);
                    if(arrMicroregionsIndexes.size() == 0){
                        System.out.println("  Esta microrregião não foi encontrado na base de dados!");
                        break;
                    }
    
                    Report.printRegionReport(cities, arrMicroregionsIndexes, 3);

                    break;
                }
                case 3:{
                    System.out.println("\n=== Insira o nome da UF ===");
                    String state = scan.nextLine();
                    ArrayList<Integer> arrStatesIndexes = Utilities.filterType(cities, 2, state);
                    if(arrStatesIndexes.size() == 0){
                        System.out.println("  Esta UF não foi encontrada na base de dados!");
                        break;
                    }
    
                    Report.printRegionReport(cities, arrStatesIndexes, 2);

                    break;
                }
                case 4:{
                    ArrayList<Integer> arrIndexes = new ArrayList<>();
                    for(int i=0; i<qttCities; i++)
                        arrIndexes.add(i);
    
                    Report.printRegionReport(cities, arrIndexes, 0);

                    break;
                }
                case 0:{
                    exit();
                    break;
                }
                default:{
                    System.out.println("Houve um erro no processamento de sua escolha.");
                    exit();
                    break;
                }
            }

            System.out.print("\nVocê deseja realizar outra ação? (S/N) ");
            anotherQuery = Utilities.readValidSN();
        }

        exit();
    }
}
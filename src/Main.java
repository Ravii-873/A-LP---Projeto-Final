import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String file = "../database/brazilian_cities_data.csv"; 
    static Scanner scan = new Scanner(System.in);
    
    public static void exit(){
        System.out.println("\n=== Obrigado por utilizar! ===\n");
        scan.close();
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

        Report.printCityReport(cities, 0);

        exit();
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> wagiPoszczegolnychSloni, ustawieniePoczatowe, ustawienieDocelowe, odw;
    static BufferedReader bufferedReader;
    static FileReader fileReader;
    static String[] readTempTab; //tymczasowa tablica stringow przechowujaca wczytane linijki z pliku
    static int wynikKoncowy, iloscSloni;


    public static void main(String[] args) throws IOException {

        fileReader = new FileReader("D:\\slo4.in"); 
        bufferedReader = new BufferedReader(fileReader);
        //bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        iloscSloni = Integer.parseInt(bufferedReader.readLine());

        //wczytywane po kolei linijki, zmieniane w kolejne listy liczb calkowitych
        readTempTab = bufferedReader.readLine().trim().split("\\s+");
        wagiPoszczegolnychSloni = new ArrayList<>();
        for (int i = 0; i < iloscSloni; i++){
            wagiPoszczegolnychSloni.add(Integer.parseInt(readTempTab[i]));
        }

        readTempTab = bufferedReader.readLine().trim().split("\\s+");
        ustawieniePoczatowe = new ArrayList<>();
        for (int i = 0; i < iloscSloni; i++){
            ustawieniePoczatowe.add(Integer.parseInt(readTempTab[i])-1);
        }

        readTempTab = bufferedReader.readLine().trim().split("\\s+");
        ustawienieDocelowe = new ArrayList<>(ustawieniePoczatowe);
        for (int i = 0; i < iloscSloni; i++) {
            ustawienieDocelowe.set(Integer.parseInt(readTempTab[i])-1, ustawieniePoczatowe.get(i));
        }

        int min = 6500;  //waga najlzejszego slonia w zestawieniu, zmienna inicjalizowana najwieksza dopuszczalna waga
        for (int i = 0; i < iloscSloni; i++) {
            if (wagiPoszczegolnychSloni.get(i) <= min) {
                min = wagiPoszczegolnychSloni.get(i);
            }
        }

        odw = new ArrayList<>();
        for (int i = 0; i < iloscSloni; i++) {
            odw.add(0);
        }

        for (int i = 1; i < iloscSloni; i++) {
            if (odw.get(i) == 0) {
                int temp = i, minCykl = Integer.MAX_VALUE, sumaCykl = 0, lengthCykl = 0;
                do {
                    minCykl = wyliczMniejsza(minCykl, wagiPoszczegolnychSloni.get(temp));
                    sumaCykl = sumaCykl + wagiPoszczegolnychSloni.get(temp);
                    temp = ustawienieDocelowe.get(temp);
                    odw.set(temp, 1);
                    lengthCykl++;
                } while (!(temp == i));
                wynikKoncowy = wynikKoncowy + wyliczMniejsza(sumaCykl + (lengthCykl - 2) * minCykl,
                        sumaCykl + minCykl + (lengthCykl + 1) * minCykl);
            }

        }
        System.out.println(wynikKoncowy);
    }

    static int wyliczMniejsza(int a, int b) {
        if (a < b)
            return a;
        return b;
    }
}

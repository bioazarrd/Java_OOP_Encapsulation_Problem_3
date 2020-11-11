package D_JavaAdvancedOOP.Lecture3_Encapsulation.ExProblem3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        LinkedHashMap<String, Person> listOfPeople = new LinkedHashMap<>();
        String[] inputNames = reader.readLine().split(";");
        for (int i = 0; i < inputNames.length; i++) {
            String[] tokens = inputNames[i].split("=");
            String name = tokens[0];
            Person newPerson = new Person(name, Double.parseDouble(tokens[1]));
            listOfPeople.putIfAbsent(name, newPerson);
        }


        LinkedHashMap<String, Product> listOfProducts = new LinkedHashMap<>();
        String[] inputProducts = reader.readLine().split(";");
        for (int i = 0; i < inputProducts.length; i++) {
            String[] tokens = inputProducts[i].split("=");
            String name = tokens[0];
            Product product = new Product(name, Double.parseDouble(tokens[1]));
            listOfProducts.putIfAbsent(name, product);
        }



        String command = reader.readLine();
        while (!command.equals("END")) {
            String[] tokens = command.split("\\s+");
            String name = tokens[0];
            String product = tokens[1];

            try {
                listOfPeople.get(name).buyProduct(listOfProducts.get(product));
                System.out.printf("%s bought %s%n", name, product);
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
            command = reader.readLine();
        }



        for (Person value : listOfPeople.values()) {
            if (value.getProducts().size() > 0) {
                System.out.println(String.format("%s - %s",
                        value.getName(),
                        value.getProducts()
                                .stream()
                                .map(Product::getName)
                                .collect(Collectors.joining(", "))));
            } else {
                System.out.println(String.format("%s - Nothing bought", value.getName()));
            }
        }



    }
}

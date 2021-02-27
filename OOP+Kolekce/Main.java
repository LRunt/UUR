package uloha2;

import java.text.Collator;
import java.util.*;

public class Main {

    public static void main(String[] args) throws WrongParameterException {
        System.out.println("----------Uloha 2 - Lukas Runt - A20B0226P----------");
        ArrayList<Bird> ptaci = new ArrayList<Bird>();
        try{
            ptaci.add(new Eagle(10, 10, 50, "Bald eagle"));
            ptaci.add(new Eagle(0, 0, 20, "Golden eagle"));
            ptaci.add(new Eagle(50, 0, 30, "Booted eagle"));
            ptaci.add(new Eagle(10, 20, 100, "Desert eagle"));
            ptaci.add(new Pigeon(5, 15, 5, "Bohemian Cropper"));
            ptaci.add(new Pigeon(8,7,3,"Moravian Strasser"));
        }
        catch (WrongParameterException ex){
            System.err.println("Error while filling the list of birds");
        }
        System.out.println("-----------------------Birds------------------------");
        ptaci.forEach(ptak -> ptak.move(50,50));
        System.out.println("----------------------------------------------------");
        LinkedList<Animal> zvirata = new LinkedList<Animal>();
        zvirata.add(ptaci.get(0));
        zvirata.add(ptaci.get(1));
        zvirata.add(new Barracuda(25,10,20,"Fairey Barracuda"));
        zvirata.add(new Barracuda(30,20,10,"Great barracuda"));
        zvirata.add(new Sloth(1,2,3,"Pale-throated sloth"));
        zvirata.add(new Sloth(5,6,7,"Brown-throated sloth"));
        System.out.println("---------------------Animals------------------------");
        System.out.println("Class of the first object in collection: " + zvirata.getFirst().getClass().getCanonicalName());
        System.out.println("Energy of the first barracuda: " + zvirata.get(2).getEnergy());
        if(zvirata.get(0) instanceof Bird){
            System.out.println("The first element of list (" + zvirata.getFirst().getName() + ") is bird.");
        }
        else{
            System.out.println("The first element of list (" + zvirata.getFirst().getName() + ") is not bird.");
        }
        if(zvirata.get(1) instanceof Bird){
            System.out.println("The second element of list (" + zvirata.get(1).getName() + ") is bird.");
        }
        else{
            System.out.println("The second element of list (" + zvirata.get(1).getName() + ") is not bird.");
        }
        System.out.println("----------------------------------------------------");
        ArrayList<Mammal> savci = new ArrayList<Mammal>();
        try{
            savci.add(new Sloth(5,4,1,"ABC"));
            savci.add(new Sloth(9,8,7,"CBA"));
            savci.add(new Sloth(1,2,3,"EFG"));
            savci.add(new Rat(10,20,30,"BAC"));
            savci.add(new Rat(200,20,2,"CAB"));
            savci.add(new Rat(100,100,1,"XYZ"));
        }
        catch (WrongParameterException ex){
            System.err.println("Error while filling the list of mammal");
        }
        System.out.println("---------------------Mammals------------------------");
        System.out.println("Animals contains A, B, C in name:");
        savci.stream().filter(s -> s.getName().contains("A") && s.getName().contains("B") && s.getName().contains("C"))
                      .forEach(s -> System.out.println(s.toString()));
        System.out.println("Animals contains A, B, C in name and has more then 3 energy points:");
        savci.stream().filter(s -> s.getName().contains("A") && s.getName().contains("B") && s.getName().contains("C") && s.getEnergy()>3)
                      .forEach(s -> System.out.println(s.toString()));
        System.out.println("----------------------------------------------------");
        ArrayList<Bird> ptaci2 = ptaci;
        ptaci2.remove(3);
        ptaci.add(new Pigeon(25,25,10,"Dutch Beauty Homer"));
        System.out.println("-----------------------Birds------------------------");
        ptaci.forEach(p -> System.out.println(p.toString()));
        double averageEnergy = ptaci2.stream().mapToDouble(p -> p.getEnergy())
                                              .average().getAsDouble();
        System.out.printf("Average energy of birds: %.2f\n",averageEnergy);
        averageEnergy = ptaci2.stream().filter(p -> p instanceof Eagle)
                                       .mapToDouble(p -> p.getEnergy())
                                       .average().getAsDouble();
        System.out.printf("Average energy of eagles: %.2f\n",averageEnergy);
        System.out.println("----------------------------------------------------");
        ArrayList<String> jmena = new ArrayList<String>(); //puvodne s hackama, bohuzel kdyz jsem spustil jar, znaky vypadaly jako rozlitej caj
        jmena.add("Cenek");
        jmena.add("Bob");
        jmena.add("Hubert");
        jmena.add("Frantisek");
        jmena.add("Kazimir");
        jmena.add("Gertruda");
        System.out.println("-----------------------Names------------------------");
        jmena.forEach(j -> System.out.println(j));
        System.out.println("----------------Sorted by alphabet------------------");
        Collator c = Collator.getInstance(new Locale("cs"));
        Collections.sort(jmena, c);
        jmena.forEach(j -> System.out.println(j));
        Collections.shuffle(jmena);
        System.out.println("----------------Sorted ascending--------------------");
        Collections.sort(jmena, (j1, j2) -> j1.length() - j2.length());
        jmena.forEach(j -> System.out.println(j));
        System.out.println("----------------Sorted Descending-------------------");
        Collections.sort(jmena, (j1, j2) -> j2.length() - j1.length());
        jmena.forEach(j -> System.out.println(j));
        System.out.println("----------------------------------------------------");
        System.out.println("-----------------------Fish-------------------------");
        ArrayList<Fish> ryby = new ArrayList<Fish>();
        try{
            ryby.add(new Carp(15, 10, 6, "Silver carp"));
            ryby.add(new Carp(0, 5, 9, "Grass carp"));
            ryby.add(new Carp(50, 50, 15, "Magikarp"));
            ryby.add(new Barracuda(20, 20, 25, "Fairey Barracuda"));
            ryby.add(new Barracuda(55, 5, 17, "Great barracuda"));
            ryby.add(new Barracuda(68,69,36,"Gyarados"));
        }
        catch (WrongParameterException ex){
            System.err.println("Error while filling the list of fish");
        }
        ryby.forEach(r -> System.out.println(r.toString()));
        ArrayList<Fish> ryby2 = new ArrayList<Fish>(ryby);
        System.out.println("---------------------Changing-----------------------");
        ryby.forEach(r -> r.setX(1000));
        ryby.forEach(r -> r.setY(1000));
        ryby.forEach(r -> r.setEnergy(1000));
        ryby2.forEach(r2 -> System.out.println(r2));
        System.out.println("--------Adding and deleting all barracudas----------");
        ryby.add(2, new Carp(40,60,80, "Black carp"));
        ryby.removeIf(r -> r instanceof Barracuda);
        ryby.forEach(r -> System.out.println(r));
        System.out.println("----------------------------------------------------");

    }
}

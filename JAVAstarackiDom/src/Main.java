import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;

public class Main {

    //Ucitavanje pacijenti.txt
    public static void ucitajPacijente(ArrayList<Pacijent> pacijenti) {
        try {
            Scanner citac = new Scanner(new File("Pacijenti.txt"));

            while (citac.hasNextLine()) {
                String red = citac.nextLine();

                String[] podaci = red.split("\\|");

                int ID = Integer.parseInt(podaci[0]);
                String ime = podaci[1];
                String prezime = podaci[2];

                Pacijent p = new Pacijent(ID, ime, prezime);

                pacijenti.add(p);
            }

            citac.close();

        } catch (FileNotFoundException e) {
            System.out.println("Datoteka nije pronađena.");
        }
    }

    //Ucitavanje zaposlenici.txt
    public static void ucitajZaposlenike(ArrayList<Zaposlenik> zaposlenici) {
        try {
            Scanner citac = new Scanner(new File("Zaposlenici.txt"));

            while (citac.hasNextLine()) {
                String red = citac.nextLine();

                String[] podaci = red.split("\\|");

                int ID = Integer.parseInt(podaci[0]);
                String ime = podaci[1];
                String prezime = podaci[2];
                double plata = Double.parseDouble(podaci[3]);

                Zaposlenik p = new Zaposlenik(ID, ime, prezime, plata);

                zaposlenici.add(p);
            }

            citac.close();

        } catch (FileNotFoundException e) {
            System.out.println("Datoteka nije pronađena.");
        }
    }

    //Ucitavanje menadzeri.txt
    public static void ucitajMenadzere(ArrayList<Menadzer> menadzeri){
        try {
            Scanner citac = new Scanner(new File("Menadzeri.txt"));

            while (citac.hasNextLine()) {
                String red = citac.nextLine();

                String[] podaci = red.split("\\|");

                int ID = Integer.parseInt(podaci[0]);
                String ime = podaci[1];
                String prezime = podaci[2];
                double plata = Double.parseDouble(podaci[3]);
                double bonus = Double.parseDouble(podaci[4]);

                Menadzer p = new Menadzer(ID, ime, prezime, plata, bonus);

                menadzeri.add(p);
            }

            citac.close();

        } catch (FileNotFoundException e) {
            System.out.println("Datoteka nije pronađena.");
        }
    }

    //Cuvanje pacijenta u txt
    public static void sacuvajPacijente(ArrayList<Pacijent> pacijenti) {
        try {
            Collections.sort(pacijenti);

            FileWriter pisac = new FileWriter("Pacijenti.txt");

            for (Pacijent p : pacijenti) {
                pisac.write(p.getID() + "|" + p.getIme() + "|" + p.getPrezime() + "\n");
            }

            pisac.close();

        } catch (IOException e) {
            System.out.println("Greska prilikom zapisivanja.");
        }
    }

    //Cuvanje zapolenika u txt
    public static void sacuvajZaposlenike(ArrayList<Zaposlenik> zaposlenici) {
        try {
            Collections.sort(zaposlenici);

            FileWriter pisac = new FileWriter("Zaposlenici.txt");

            for (Zaposlenik p : zaposlenici) {
                pisac.write(p.getID() + "|" + p.getIme() + "|" + p.getPrezime() + "|" + p.getPlata() + "\n");
            }

            pisac.close();

        } catch (IOException e) {
            System.out.println("Greska prilikom zapisivanja.");
        }
    }

    //Cuvanje menadzera u txt
    public static void sacuvajMenadzere(ArrayList<Menadzer> menadzeri) {
        try {
            Collections.sort(menadzeri);

            FileWriter pisac = new FileWriter("Menadzeri.txt");

            for (Menadzer p : menadzeri) {
                pisac.write(p.getID() + "|" + p.getIme() + "|" + p.getPrezime() + "|" + p.getPlata() + "|" + p.getBonus() + "\n");
            }

            pisac.close();

        } catch (IOException e) {
            System.out.println("Greska prilikom zapisivanja.");
        }
    }


    //Unos pacijenta
    public static void unosPacijenta(ArrayList<Pacijent> pacijenti){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite ID pacijenta:");
        int ID = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<pacijenti.size();i++){
            if(pacijenti.get(i).getID()==ID){
                System.out.println("Pacijent sa unesenim ID-em već postoji u sistemu, ponovite vaš unos!");
                return;
            }
        }

        System.out.println("Unesite ime pacijenta:");
        String ime = scanner.nextLine();

        System.out.println("Unesite prezime pacijenta:");
        String prezime = scanner.nextLine();

        Pacijent p = new Pacijent(ID,ime,prezime);
        pacijenti.add(p);
        sacuvajPacijente(pacijenti);
        System.out.println("Pacijent uspješno dodan!");

    }

    //Brisanje pacijenta
    public static void brisanjePacijenta(ArrayList<Pacijent> pacijenti){
        if(pacijenti.isEmpty()){
            System.out.println("Niste unijeli niti jednog pacijenta!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite ID pacijenta kojeg želite obrisati:");
        int id = scanner.nextInt();
        boolean pronadjen = false;
        for(int i=0;i< pacijenti.size();i++){
            if(pacijenti.get(i).getID()==id){
                pacijenti.remove(i);
                pronadjen=true;
                break;
            }
        }
        if(pronadjen){
            sacuvajPacijente(pacijenti);
            System.out.println("Pacijent uspješno obrisan!");
        }else {
            System.out.println("Pacijent sa unesenim ID-em nije pronađen!");
        }

    }

    //Pretrazivanje pacijenta
    public static void pronadjiPacijenta(ArrayList<Pacijent> pacijenti){
        if(pacijenti.isEmpty()){
            System.out.println("Niste unijeli niti jednog pacijenta!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean pronadjen = false;
        System.out.println("Unesite ID pacijenta kojeg želite pronaći:");
        int id = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0;i< pacijenti.size();i++){
            if(pacijenti.get(i).getID()==id){
                pronadjen = true;
                System.out.println(pacijenti.get(i).ispis());
                break;
            }
        }
        if(!pronadjen){
            System.out.println("Pacijent sa unesenim ID-em ne postoji u sistemu!");
        }

    }

    //Izmjena pacijenta
    public static void izmijeniPacijenta(ArrayList<Pacijent> pacijenti){
        if(pacijenti.isEmpty()){
            System.out.println("Niste unijeli niti jednog pacijenta.");
            return;
        }
        boolean pronadjen=false;
        Scanner scanner = new Scanner(System.in);
        int id;
        System.out.println("Unesite ID pacijenta čije podatke želite izmijeniti:");
        id = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<pacijenti.size();i++){
            if(pacijenti.get(i).getID()==id){
                System.out.println("Pacijent uspješno pronađen!");
                pronadjen=true;
                String novoIme;
                String novoPrezime;
                System.out.println("Trenutni podaci o pacijentu:");
                System.out.println("ID:" + pacijenti.get(i).getID() + "|Ime:" + pacijenti.get(i).getIme() + "|Prezime:" + pacijenti.get(i).getPrezime());
                System.out.println("Unesite novo ime pacijenta:");
                novoIme = scanner.nextLine();
                System.out.println("Unesite novo prezime:");
                novoPrezime = scanner.nextLine();
                pacijenti.get(i).setIme(novoIme);
                pacijenti.get(i).setPrezime(novoPrezime);
                sacuvajPacijente(pacijenti);
                break;
            }
        }

        if(pronadjen){
            System.out.println("Uspješno ste updateovali pacijenta!");
        }else{
            System.out.println("Pacijent sa uensenim ID-em nije pronađen!");
        }

    }

    //Ispis svih pacijenata
    public static void ispisiPacijente(ArrayList<Pacijent> pacijenti){
        if(pacijenti.isEmpty()){
            System.out.println("Niste unijeli niti jednog pacijenta!");
            return;
        }
        for(int i=0;i< pacijenti.size();i++){
            System.out.println(pacijenti.get(i).ispis());
        }
    }

    //Unos zaposlenika
    public static void unosZaposlenika(ArrayList<Zaposlenik> zaposlenici){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite ID zaposlenika:");
        int ID = scanner.nextInt();
        for(int i=0;i<zaposlenici.size();i++){
            if(zaposlenici.get(i).getID()==ID){
                System.out.println("Zaposlenik sa uneseim ID-em već postoji!");
                return;
            }
        }
        scanner.nextLine();
        System.out.println("Unesite ime zaposlenika:");
        String ime = scanner.nextLine();
        System.out.println("Unesite prezime zaposlenika:");
        String prezime = scanner.nextLine();
        System.out.println("Unesite platu zaposlenika:");
        double plata = scanner.nextDouble();
        Zaposlenik p = new Zaposlenik(ID,ime,prezime,plata);
        zaposlenici.add(p);
        sacuvajZaposlenike(zaposlenici);
        System.out.println("Zaposlenik uspješno unesen!");

    }

    //Brisanje zaposlenika
    public static void brisanjeZaposlenika(ArrayList<Zaposlenik> zaposlenici){
        if(zaposlenici.isEmpty()){
            System.out.println("Niste unijeli niti jednog zaposlenika!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean pronadjen = false;
        System.out.println("Unesite ID zaposlenika kojeg želite obrisati:");
        int id = scanner.nextInt();
        for(int i=0;i<zaposlenici.size();i++){
            if(zaposlenici.get(i).getID()==id){
                zaposlenici.remove(i);
                sacuvajZaposlenike(zaposlenici);
                pronadjen = true;
                break;
            }
        }
        if(pronadjen){
            System.out.println("Zaposlenik uspješno obrisan!");
        }else {
            System.out.println("Zaposlenik sa unesenim ID-em nije pronađen u sistemu!");
        }

    }

    //Pretrazivanje zaposlenika
    public static void pronadjiZaposlenika(ArrayList<Zaposlenik> zaposlenici){
        if(zaposlenici.isEmpty()){
            System.out.println("Niste unijeli niti jednog zaposlenika!");
            return;
        }
        boolean pronadjen = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite ID zaposlenika kojeg želite pronaći!");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<zaposlenici.size();i++){
            if(zaposlenici.get(i).getID()==id){
                pronadjen = true;
                System.out.println("Zaposlenik je uspješno pronađen!");
                System.out.println("ID:" + zaposlenici.get(i).getID() + "| Ime:" + zaposlenici.get(i).getIme() + "| Prezime:" + zaposlenici.get(i).getPrezime() + "| Plata:" + zaposlenici.get(i).getPlata());
                break;
            }
        }
        if(!pronadjen){
            System.out.println("Zaposlenik sa unesenim ID-em nije pronađen u sistemu!");
        }
    }

    //Izmjena podataka o zaposlenicima
    public static void izmijeniZaposlenika(ArrayList<Zaposlenik> zaposlenici){
        if(zaposlenici.isEmpty()){
            System.out.println("Niste unijeli niti jednog zaposlenika!");
            return;
        }
        Scanner scanner = new Scanner (System.in);
        System.out.println("Unesite ID zaposlenika čije podatke želite izmijeniti:");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<zaposlenici.size();i++){
            if(zaposlenici.get(i).getID()==id){
                String novoIme;
                String novoPrezime;
                double novaPlata;
                System.out.println("Trenutni podaci zaposlenika kojeg želite izmijeniti.");
                System.out.println("ID:" + zaposlenici.get(i).getID() + "| Ime:" + zaposlenici.get(i).getIme() + "| Prezime:" + zaposlenici.get(i).getPrezime() + "| Plata:" + zaposlenici.get(i).getPlata());
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("Unesite novo ime zaposlenika:");
                novoIme = scanner.nextLine();
                System.out.println("Unesite novoPrezime zaposlenika:");
                novoPrezime = scanner.nextLine();
                System.out.println("Unesite novu platu zaposlenika:");
                novaPlata = scanner.nextDouble();
                zaposlenici.get(i).setIme(novoIme);
                zaposlenici.get(i).setPrezime(novoPrezime);
                zaposlenici.get(i).setPlata(novaPlata);
                sacuvajZaposlenike(zaposlenici);
                System.out.println("Podaci o zaposleniku uspješno izmijenjeni!");
                return;
            }
        }
        System.out.println("Zaposlenik sa unesenim ID-em nije pronađen u sistemu!");
    }

    //Ispisi zaposlenike
    public static void ispisiZaposlenike(ArrayList<Zaposlenik> zaposlenici){
        if(zaposlenici.isEmpty()){
            System.out.println("Niste unijeli niti jednog zaposlenika u sistem!");
        }
        for(int i=0;i< zaposlenici.size();i++){
            System.out.println(zaposlenici.get(i).ispis());
        }
    }

    //Unos menadzera
    public static void unosMenadzera(ArrayList<Menadzer> menadzeri){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Uneisite ID menadžera:");
        int ID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Unesite ime menadžera:");
        String ime = scanner.nextLine();
        System.out.println("Unesite prezime menadžera:");
        String prezime = scanner.nextLine();
        System.out.println("Unesite platu menadžera:");
        double plata = scanner.nextDouble();
        System.out.println("Unesite bonus menadžera:");
        double bonus = scanner.nextDouble();

        Menadzer p = new Menadzer(ID,ime,prezime,plata,bonus);
        menadzeri.add(p);
        sacuvajMenadzere(menadzeri);
        System.out.println("Menadžer uspjesno dodan!");
    }

    //Brisanje menadzera
    public static void brisanjeMenadzera(ArrayList<Menadzer> menadzeri){
        if(menadzeri.isEmpty()){
            System.out.println("Niste unijeli niti jednog menadžera!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean pronadjen = false;
        System.out.println("Unesite ID menadžera kojeg želite obrisati:");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i< menadzeri.size();i++){
            if(menadzeri.get(i).getID()==id){
                pronadjen=true;
                menadzeri.remove(i);
                sacuvajMenadzere(menadzeri);
                break;
            }
        }
        if(pronadjen){
            System.out.println("Menadžer uspjesno uklonjen!");
        }else {
            System.out.println("Menadžer sa unesenim ID-em nije pronađen u sistemu!");
        }

    }

    //Pretrazivanje menadzera
    public static void pretrazivanjeMenadzera(ArrayList<Menadzer> menadzeri){
        if(menadzeri.isEmpty()){
            System.out.println("Niste unijeli niti jednog menadžera u sistem!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean pronadjen = false;
        System.out.println("Unesite ID menadžera kojeg želite pretražiti:");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<menadzeri.size();i++){
            if(menadzeri.get(i).getID()==id){
                pronadjen = true;
                System.out.println("Podaci o pronađenom menadžeru:");
                System.out.println("ID:" + menadzeri.get(i).getID() + "| Ime:" + menadzeri.get(i).getIme() + "| Prezime:" + menadzeri.get(i).getPrezime() + "| Plata:" + menadzeri.get(i).getPlata() + "| Bonus:" + menadzeri.get(i).getBonus());
            }
        }

        if(!pronadjen){
            System.out.println("Menadžer sa unesenim ID-em nije pronađen u sistemu!");
        }
    }

    //Izmjena podataka o menadzeru
    public static void izmijeniMenadzera(ArrayList<Menadzer> menadzeri){
        if(menadzeri.isEmpty()){
            System.out.println("Niste unijeli niti jednog menadžera u sistem!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite ID menadžera čije podatke želite izmijeniti:");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<menadzeri.size();i++){
            if(menadzeri.get(i).getID()==id){
                System.out.println("Trenutni podaci menadžera kojeg želite izmijeniti.");
                System.out.println("ID:" + menadzeri.get(i).getID() + "| Ime:" + menadzeri.get(i).getIme() + "| Prezime:" + menadzeri.get(i).getPrezime() + "| Plata:" + menadzeri.get(i).getPlata() + "| Bonus:" + menadzeri.get(i).getBonus());
                System.out.println("Unesite novo ime menadžera:");
                String novoIme = scanner.nextLine();
                System.out.println("Unesite novo prezime menadžera:");
                String novoPrezime = scanner.nextLine();
                System.out.println("Unesite novu platu menadžera:");
                Double novaPlata = scanner.nextDouble();
                System.out.println("Unesite novi bonuus menadžera:");
                Double noviBonus = scanner.nextDouble();
                menadzeri.get(i).setIme(novoIme);
                menadzeri.get(i).setPrezime(novoPrezime);
                menadzeri.get(i).setPlata(novaPlata);
                menadzeri.get(i).setBonus(noviBonus);
                sacuvajMenadzere(menadzeri);
                return;
            }
        }
        System.out.println("Menadžer sa unesenim ID-em nije pronađen u sistemu!");
    }

    //Ispis menadzera
    public static void ispisMenadzera(ArrayList<Menadzer> menadzeri){
        if(menadzeri.isEmpty()){
            System.out.println("Niste unijeli niti jednog menadžera u sistem!");
            return;
        }
        for(int i=0;i<menadzeri.size();i++){
            System.out.println("ID:" + menadzeri.get(i).getID() + "| Ime:" + menadzeri.get(i).getIme() + "| Prezime:" + menadzeri.get(i).getPrezime() + "| Plata:" + menadzeri.get(i).getPlata() + "| Bonus:" + menadzeri.get(i).getBonus());
        }
    }



    public static void main(String[] args) {

        ArrayList <Pacijent> pacijenti = new ArrayList<>();
        ArrayList <Zaposlenik> zaposlenici = new ArrayList<>();
        ArrayList <Menadzer> menadzeri = new ArrayList<>();
        ucitajPacijente(pacijenti);
        ucitajZaposlenike(zaposlenici);
        ucitajMenadzere(menadzeri);
        int izbor;
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println("----------DOBRODOŠLI U SISTEM----------");
            System.out.println("Izaberite odgovarajuću opciju.");
            System.out.println("1.Rad sa pacijentima.");
            System.out.println("2.Rad sa zaposlenicima.");
            System.out.println("3.Rad sa menadžerima.");
            System.out.println("9.Izlaz.");
            System.out.println("Unesite izbor:");
            izbor = scanner.nextInt();
            scanner.nextLine();


            switch(izbor) {
                //Rad sa pacijentima
                case 1:
                    do{
                        System.out.println("-----Rad sa pacijentima-----");
                        System.out.println("1.Unos pacijenta.");
                        System.out.println("2.Brisanje pacijenta.");
                        System.out.println("3.Pretrazivanje pacijenta.");
                        System.out.println("4.Izmjena podataka o pacijentu.");
                        System.out.println("5.Ispis svih pacijenata.");
                        System.out.println("0.Izlaz.");
                        System.out.println("Unesite izbor:");

                        izbor = scanner.nextInt();
                        scanner.nextLine();
                        switch(izbor){
                            case 1:
                                unosPacijenta(pacijenti);
                                break;
                            case 2:
                                brisanjePacijenta(pacijenti);
                                break;
                            case 3:
                                pronadjiPacijenta(pacijenti);
                                break;
                            case 4:
                                izmijeniPacijenta(pacijenti);
                                break;
                            case 5:
                                ispisiPacijente(pacijenti);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Pogrešan unos.");
                                break;
                        }
                    }while(izbor != 0);
                    break;


                //Rad sa zaposlenicima
                case 2:
                    do{
                        System.out.println("-----Rad sa zaposlenicima-----");
                        System.out.println("1.Unos zaposlenika.");
                        System.out.println("2.Brisanje zaposlenika.");
                        System.out.println("3.Pretrazivanje zaposlenika.");
                        System.out.println("4.Izmjena podataka o zaposleniku.");
                        System.out.println("5.Ispis svih zaposlenika.");
                        System.out.println("0.Izlaz.");
                        System.out.println("Unesite izbor:");

                        izbor = scanner.nextInt();
                        scanner.nextLine();
                        switch(izbor){
                            case 1:
                                unosZaposlenika(zaposlenici);
                                break;
                            case 2:
                                brisanjeZaposlenika(zaposlenici);
                                break;
                            case 3:
                                pronadjiZaposlenika(zaposlenici);
                                break;
                            case 4:
                                izmijeniZaposlenika(zaposlenici);
                                break;
                            case 5:
                                ispisiZaposlenike(zaposlenici);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Pogrešan unos.");
                                break;
                        }
                    }while(izbor != 0);
                    break;


                //Rad sa menadzerima
                case 3:
                    do{
                        System.out.println("-----Rad sa menadžerima-----");
                        System.out.println("1.Unos menadžera.");
                        System.out.println("2.Brisanje mendžera.");
                        System.out.println("3.Pretrazivanje menadžera.");
                        System.out.println("4.Izmjena podataka o menadžeru.");
                        System.out.println("5.Ispis svih menadžera.");
                        System.out.println("0.Izlaz.");
                        System.out.println("Unesite izbor:");

                        izbor = scanner.nextInt();
                        scanner.nextLine();
                        switch(izbor){
                            case 1:
                                unosMenadzera(menadzeri);
                                break;
                            case 2:
                                brisanjeMenadzera(menadzeri);
                                break;
                            case 3:
                                pretrazivanjeMenadzera(menadzeri);
                                break;
                            case 4:
                                izmijeniMenadzera(menadzeri);
                                break;
                            case 5:
                                ispisMenadzera(menadzeri);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Pogrešan unos.");
                                break;
                        }
                    }while(izbor != 0);
                    break;

                case 9:
                    break;
                default:
                    System.out.println("Pogrešan unos.");
                    break;
            }






        }while(izbor != 9);




    scanner.close();
    }
}

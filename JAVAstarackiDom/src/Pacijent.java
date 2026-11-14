public class Pacijent implements Comparable<Pacijent>{
    private int ID;
    private String ime;
    private String prezime;

    public Pacijent(int ID, String ime, String prezime){
        this.ID = ID;
        this.ime = ime;
        this.prezime = prezime;
    }


    public int getID(){
        return ID;
    }
    public String getIme(){
        return ime;
    }
    public String getPrezime(){
        return prezime;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    public void setIme(String ime){
        this.ime = ime;
    }
    public void setPrezime(String prezime){
        this.prezime = prezime;
    }

    public void status(){
        System.out.println(ime + " je pacijent.");
    }

    public String ispis(){
        return "ID:" + ID + " Ime:" + ime + " Prezime:" + prezime;
    }

    @Override
    public int compareTo(Pacijent p) {
        return Integer.compare(this.ID, p.ID);
    }

}

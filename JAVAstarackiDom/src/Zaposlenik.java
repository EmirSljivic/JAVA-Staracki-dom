public class Zaposlenik extends Pacijent{
    private double plata;

    public Zaposlenik(int ID,String ime,String prezime,double plata){
        super(ID,ime,prezime);
        this.plata = plata;
    }

    public double getPlata(){
        return plata;
    }

    public void setPlata(double plata){
        this.plata = plata;
    }

    @Override
    public void status(){
        System.out.println(getIme() + " je zaposlenik.");
    }

    @Override
    public String ispis(){
        return super.ispis() + " Plata:" + plata;
    }



}

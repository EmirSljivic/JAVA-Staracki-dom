public class Menadzer extends Zaposlenik{
    private double bonus;

    public Menadzer(int ID,String ime,String prezime,double plata, double bonus){
        super(ID,ime,prezime,plata);
        this.bonus = bonus;
    }

    public double getBonus(){
        return bonus;
    }

    public void setBonus(double bonus){
        this.bonus = bonus;
    }

    @Override
    public void status(){
        System.out.println(getID() + " je menadzer.");
    }

    @Override
    public String ispis(){
        return super.ispis() + " Bonus:" + bonus;
    }
}

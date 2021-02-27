package uloha2;

/**
 * Instances of class {@code Carp} represents carps.
 * which can move, eat alga, lay 500 eggs.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-16)
 */
public class Carp extends Fish {

    public Carp (float x, float y, float energy, String name) throws WrongParameterException {
        super(x, y, energy, name);
    }

    /**
     * Writes how many energy animal has and which food eats.
     * Add energy points
     */
    public void eat(float algaEnergy){
        setEnergy(getEnergy()+algaEnergy);
        System.out.println("Carp (" + getName() + ") eat alga. Alga give " + algaEnergy + " energy, actual energy: " + getEnergy() + ".");
    }

    /**
     * Write how is animal moving and where it got to.
     * cost 1 energy point
     */
    public void move(float x, float y){
        if(getEnergy()>=1){
            setX(x);
            setY(y);
            setEnergy(getEnergy()-1);
            System.out.println("Carp (" + getName() + ") floats near the surface to:[" + x + " ," + y + "].");
        }
        else{
            System.out.println("Carp (" + getName() + ") can't move, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    /**
     * Write how many eggs were laid, cost 5 energy points.
     * If animal hasn't enough energy, then write that cannot lay eggs.
     */
    public void layEggs(){
        if(getEnergy()>=5){
            setEnergy(getEnergy()-5);
            System.out.println("Carp (" + getName() + ") lay 500 eggs.");
        }
        else{
            System.out.println("Carp (" + getName() + ") can't lay eggs, he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    @Override
    public String toString(){
        return "Carp (" + getName() + ") Position: [" + getX() + ";" + getY() + "] Energy: " + getEnergy();
    }
}

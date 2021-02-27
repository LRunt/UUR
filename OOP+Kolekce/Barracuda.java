package uloha2;

/**
 * Instances of class {@code Barracuda} represents barracudas.
 * which can move, eat meat, lay 1000 eggs.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-16)
 */
public class Barracuda extends Fish {

    public Barracuda (float x, float y, float energy, String name) throws WrongParameterException {
        super(x, y, energy, name);
    }

    /**
     * Writes how many energy animal has and which food eats.
     * Add energy points
     */
    public void eat(float meatEnergy){
        setEnergy(getEnergy()+meatEnergy);
        System.out.println("Barracuda (" + getName() + ") eat meat. Meat give " + meatEnergy + " energy, actual energy: " + getEnergy() + ".");
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
            System.out.println("Barracuda (" + getName() + ") floats deep to:[" + x + " ," + y + "].");
        }
        else{
            System.out.println("Barracuda (" + getName() + ") can't move, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    /**
     * Write how many eggs were laid, cost 5 energy points.
     * If animal hasn't enough energy, then write that cannot lay eggs.
     */
    public void layEggs(){
        if(getEnergy()>=5){
            setEnergy(getEnergy()-5);
            System.out.println("Barracuda (" + getName() + ") lay 1000 eggs.");
        }
        else{
            System.out.println("Barracuda (" + getName() + ") can't lay eggs, he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    @Override
    public String toString(){
        return "Barracuda (" + getName() + ") Position: [" + getX() + ";" + getY() + "] Energy: " + getEnergy();
    }
}

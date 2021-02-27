package uloha2;

/**
 * Instances of class {@code Rat} represents rats.
 * which can move, eat everything, squeaks.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-16)
 */
public class Rat extends Mammal {

    public Rat(float x, float y, float energy, String name) throws WrongParameterException {
        super(x, y, energy, name);
    }

    /**
     * Writes how many energy animal has and which food eats.
     * Add energy points
     */
    public void eat(float foodEnergy){
        setEnergy(getEnergy() + foodEnergy);
        System.out.println("Rat (" + getName() + ") eat everything. Everything give " + foodEnergy + " energy, actual energy: " + getEnergy() + ".");
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
            System.out.println("Rat (" + getName() + ") run to:[" + x + " ," + y + "].");
        }
        else{
            System.out.println("Rat (" + getName() + ") can't move, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    /**
     * Writes sound of animal, cost 1 energy point.
     * If animal hasn't enough energy, then write that it cannot cry.
     */
    public void cry(){
        if(getEnergy()>=1){
            setEnergy(getEnergy()-1);
            System.out.println("Rat (" + getName() + ") is squeaking.");
        }
        else{
            System.out.println("Rat (" + getName() + ") can't cry, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    @Override
    public String toString(){
        return "Rat (" + getName() + ") Position: [" + getX() + ";" + getY() + "] Energy: " + getEnergy();
    }
}

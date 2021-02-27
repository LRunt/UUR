package uloha2;

/**
 * Instances of class {@code Sloth} represents sloths.
 * which can move, eat leaves, growls.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-16)
 */
public class Sloth extends Mammal {

    public Sloth (float x, float y, float energy, String name) throws WrongParameterException {
        super(x, y, energy, name);
    }

    /**
     * Writes how many energy animal has and which food eats.
     * Add energy points
     */
    public void eat(float leavesEnergy){
        setEnergy(getEnergy()+leavesEnergy);
        System.out.println("Sloth (" + getName() + ") eat leaves. Leaves give " + leavesEnergy + " energy, actual energy: " + getEnergy() + ".");
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
            System.out.println("Sloth (" + getName() + ") is crawling to:[" + x + " ," + y + "].");
        }
        else{
            System.out.println("Sloth (" + getName() + ") can't move, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    /**
     * Writes sound of animal, cost 1 energy point.
     * If animal hasn't enough energy, then write that it cannot cry.
     */
    public void cry(){
        if(getEnergy()>=1){
            setEnergy(getEnergy()-1);
            System.out.println("Sloth (" + getName() + ") is growling.");
        }
        else{
            System.out.println("Sloth (" + getName() + ") can't cry, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    @Override
    public String toString(){
        return "Sloth (" + getName() + ") Position: [" + getX() + ";" + getY() + "] Energy: " + getEnergy();
    }

}

package uloha2;

/**
 * Instances of class {@code Pigeon} represents pigeons.
 * which can move, eat grain, goes beep, lay 2 eggs.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-16)
 */
public class Pigeon extends Bird {

    public Pigeon (float x, float y, float energy, String name) throws WrongParameterException {
        super(x , y, energy, name);
    }

    /**
     * Writes how many energy animal has and which food eats.
     * Add energy points
     */
    public void eat(float grainEnergy){
        setEnergy(getEnergy()+grainEnergy);
        System.out.println("Pigeon (" + getName() + ") eat grain. Grain give " + grainEnergy + " energy, actual energy: " + getEnergy() + ".");
    }

    /**
     * Write how is animal moving and where it got to.
     * cost 1 energy point.
     */
    public void move(float x, float y){
        if(getEnergy()>=1){
            setX(x);
            setY(y);
            setEnergy(getEnergy()-1);
            System.out.println("Pigeon (" + getName() + ") fly low to:[" + x + " ," + y + "].");
        }
        else{
            System.out.println("Pigeon (" + getName() + ") can't move, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    /**
     * Writes sound of animal, cost 1 energy point.
     * If animal hasn't enough energy, then write that it cannot cry.
     */
    public void cry(){
        if(getEnergy()>=1){
            setEnergy(getEnergy()-1);
            System.out.println("Pigeon (" + getName() + ") goes beep.");
        }
        else{
            System.out.println("Pigeon (" + getName() + ") can't cry, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    /**
     * Write how many eggs were laid, cost 5 energy points.
     * If animal hasn't enough energy, then write that cannot lay eggs.
     */
    public void layEggs(){
        if(getEnergy()>=5){
            setEnergy(getEnergy()-5);
            System.out.println("Pigeon (" + getName() + ") lay 2 eggs.");
        }
        else{
            System.out.println("Pigeon (" + getName() + ") can't lay eggs, he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    @Override
    public String toString(){
        return "Pigeon (" + getName() + ") Position: [" + getX() + ";" + getY() + "] Energy: " + getEnergy();
    }
}

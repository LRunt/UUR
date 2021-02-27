package uloha2;

/**
 * Instances of class {@code Eagle} represents eagles.
 * which can move, eat meat, cry loudly, lay 3 eggs.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-16)
 */
public class Eagle extends Bird {

    public Eagle (float x, float y, float energy, String name) throws WrongParameterException {
        super(x, y, energy, name);
    }

    /**
     * Writes how many energy animal has and which food eats.
     * Add energy points
     */
    public void eat(float meatEnergy){
        setEnergy(getEnergy()+meatEnergy);
        System.out.println("Eagle (" + getName() + ") eat meat. Meat give " + meatEnergy + " energy, actual energy: " + getEnergy() + ".");
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
            System.out.println("Eagle (" + getName() + ") fly high to:[" + x + " ," + y + "].");
        }
        else{
            System.out.println("Eagle (" + getName() + ") can't move, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    /**
     * Writes sound of animal, cost 1 energy point.
     * If animal hasn't enough energy, then write that it cannot cry.
     */
    public void cry(){
        if(getEnergy()>=1){
            setEnergy(getEnergy()-1);
            System.out.println("Eagle (" + getName() + ") cry loudly.");
        }
        else{
            System.out.println("Eagle (" + getName() + ") can't cry, because he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    /**
     * Write how many eggs were laid, cost 5 energy points.
     * If animal hasn't enough energy, then write that cannot lay eggs.
     */
    public void layEggs(){
        if(getEnergy()>=5){
            setEnergy(getEnergy()-5);
            System.out.println("Eagle (" + getName() + ") lay 3 eggs.");
        }
        else{
            System.out.println("Eagle (" + getName() + ") can't lay eggs, he hasn't got enough energy, actual energy: " + getEnergy() + ".");
        }
    }

    @Override
    public String toString(){
        return "Eagle (" + getName() + ") Position: [" + getX() + ";" + getY() + "] Energy: " + getEnergy();
    }
}

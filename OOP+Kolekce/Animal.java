package uloha2;

/**
 * Instances of class {@code Animal} represents animals, which can move and eat.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-08)
 */
public abstract class Animal implements IEatable, IMoveable {
    private float x;
    private float y;
    private float energy;
    private String name;

    /**
     * Creates animal on certain coordinates, with definated energy and name
     * @param x x coordinate of animal
     * @param y y coordinate of animal
     * @param energy energy of animal
     * @param name name of animal
     */
    public Animal(float x, float y, float energy, String name) throws WrongParameterException {
        this.x = x;
        this.y = y;
        if(energy < 0){
            throw new WrongParameterException("Energy must be a positive number, value " + energy +  " was used!");
        }
        else {
            this.energy = energy;
        }
        this.name = name;
    }

    /**
     * @return x
     */
    public float getX(){
        return x;
    }

    /**
     * @return y
     */
    public float getY(){
        return y;
    }

    /**
     * @return energy
     */
    public float getEnergy(){
        return energy;
    }

    /**
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * @param x
     */
    public void setX(float x){
        this.x = x;
    }

    /**
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @param energy
     */
    public void setEnergy(float energy){
        if(energy<0){
            System.err.printf("ERROR! \nEnergy can't be negative.\n");
        }
        else{
            this.energy = energy;
        }
    }
}

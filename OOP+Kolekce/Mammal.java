package uloha2;

/**
 * Instances of class {@code Mammal} represents mammal,
 * which can move, eat, cry.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-08)
 */
public abstract class Mammal extends Animal implements ICryable {

    public Mammal (float x, float y, float energy, String name) throws WrongParameterException {
        super(x, y, energy, name);
    }
}

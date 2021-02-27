package uloha2;

/**
 * Instances of class {@code Bird} represents birds,
 * which can move, eat, cry, lay eggs.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-08)
 */
public abstract class Bird extends Animal implements ICryable, ILayable {

    public Bird(float x, float y, float energy, String name) throws WrongParameterException {
        super(x, y,energy, name);
    }

}

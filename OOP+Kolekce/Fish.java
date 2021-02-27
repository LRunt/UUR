package uloha2;

/**
 * Instances of class {@code Fish} represent fish,
 * which can move, eat and lay eggs.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-08)
 */
public abstract class Fish extends Animal implements ILayable {

    public Fish(float x, float y, float energy, String name) throws WrongParameterException {
        super(x, y, energy, name);
    }
}

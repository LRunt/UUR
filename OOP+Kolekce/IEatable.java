package uloha2;

/**
 * Instances of interface {@code IEatable} represents animals,
 * which can eat.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-08)
 */
public interface IEatable {

    /**
     * Writes how many energy animal has and which food eats.
     * Add energy points.
     */
    public void eat(float energy);
}

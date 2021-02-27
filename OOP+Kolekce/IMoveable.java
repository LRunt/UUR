package uloha2;

/**
 * Instances of interface {@code IMoveable} represents animals,
 * which can move.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-08)
 */
public interface IMoveable {

    /**
     * Write how is animal moving and where it got to.
     * cost 1 energy point
     */
    public void move(float x, float y);
}

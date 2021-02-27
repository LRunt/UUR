package uloha2;

/**
 * Instance of interface {@code ILayable} represents animals,
 * which can lay eggs.
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-08)
 */
public interface ILayable {

    /**
     * Write how many eggs were laid, cost 5 energy points.
     * If animal hasn't enough energy, then write that cannot lay eggs.
     */
    public void layEggs();
}

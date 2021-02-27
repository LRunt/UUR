package uloha2;

/**
 * Instances of interface {@code ICryable} represents animals,
 * which can cry
 *
 * @author Lukas Runt
 * @version 1.0 (2020-02-08)
 */public interface ICryable {

    /**
     * Writes sound of animal, cost 1 energy point.
     * If animal hasn't enough energy, then write that it cannot cry.
     */
     public void cry();
}

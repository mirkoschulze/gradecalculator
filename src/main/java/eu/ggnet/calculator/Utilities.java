package eu.ggnet.calculator;

import eu.ggnet.calculator.model.Grade.Mark;

/**
 * Static class with useful methods for multiple situations.
 *
 * @author Mirko Schulze
 */
public class Utilities {

    private Utilities() {

    }

    /**
     * Converts an integer value into a respective {@link Mark}.
     *
     * @param value integer
     * @return Mark
     */
    public static Mark createMark(int value) {
        Mark mark = Mark.ZERO;
        for (Mark m : Mark.values()) {
            if (m.getHighschoolMark() == value) {
                mark = m;
            }
        }
        return mark;
    }

}

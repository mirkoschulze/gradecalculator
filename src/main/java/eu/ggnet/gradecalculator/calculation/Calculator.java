package eu.ggnet.gradecalculator.calculation;

import eu.ggnet.gradecalculator.model.Classbook;
import eu.ggnet.gradecalculator.model.Grade;
import eu.ggnet.gradecalculator.model.Grade.Mark;
import eu.ggnet.gradecalculator.model.Grade.Subject;
import eu.ggnet.gradecalculator.model.Pupil;

/**
 * Class with static methods to generate new instances of
 * {@link CalculatedGrade}.
 *
 * @author Mirko Schulze
 */
public class Calculator {

    /**
     * Private constructor to prevent an instantiation.
     */
    private Calculator() {

    }

    /**
     * Counts how often each individual {@link Mark} is given for the submitted
     * {@link Subject} in the submitted {@link Classbook}. Saves the first Mark
     * with the most occurencies.
     * <p>
     * Generates and returns a new {@link CalculatedGrade} with the calculated
     * {@link Grade} and its occurencies as suffix.
     *
     * @param classbook {@link Classbook} for which the
     * {@link Grade} shall be calculated
     * @param subject {@link Subject} for which the {@link Grade}
     * shall be calculated
     * @return CalculatedGrade - the wrapped {@link Grade}
     */
    public static CalculatedGrade calculateAccumulatedGrade(Classbook classbook, Subject subject) {
        String calculation = "Äufiste Note in der " + classbook.toString() + " = ";
        int mostOccurencies = 0;
        int iterationStep = 0;
        if (classbook.getPupils() != null && (!classbook.getPupils().isEmpty())) {
            for (int i = 0; i < Mark.values().length; i++) {
                int occurencies = 0;
                for (Pupil pupil : classbook.getPupils()) {
                    if (pupil.getCertification() != null) {
                        if (Mark.values()[i].getHighschoolMark() == pupil.getCertification()
                                .getGrades().get(subject.ordinal()).getMark().getHighschoolMark()) {
                            occurencies++;
                        }
                    }
                    if (occurencies > mostOccurencies) {
                        mostOccurencies = occurencies;
                        iterationStep = i;
                    }
                }
            }
        }
        String suffix = "(" + mostOccurencies + " mal)";
        return new CalculatedGrade(calculation, new Grade(subject, Mark.values()[iterationStep]), suffix);
    }

    /**
     * Collects each {@link Mark} for the submitted {@link Subject} in the
     * submitted {@link Classbook}, adds the value of each mark to a number and
     * divides that number by the amount of {@link Pupil} in that classbook.
     * <p>
     * Generates and returns a new {@link CalculatedGrade} with the calculated
     * number as mark.
     *
     * @param classbook {@link Classbook} for which the
     * {@link Grade} shall be calculated
     * @param subject {@link Subject} for which the {@link Grade}
     * shall be calculated
     * @return CalculatedGrade - the wrapped {@link Grade}
     */
    public static CalculatedGrade calculateAverageGrade(Classbook classbook, Subject subject) {
        String calculation = "Durchschnittliche Note in der " + classbook.getClassbookTitle() + " = ";
        int total = 0;
        int average = 0;
        if (classbook.getPupils() != null && (!classbook.getPupils().isEmpty())) {
            for (Pupil pupil : classbook.getPupils()) {
                if (pupil.getCertification() != null) {
                    total += pupil.getCertification().getGrades().get(subject.ordinal()).getMark().getHighschoolMark();
                }
            }
            average = total / classbook.getPupils().size();
        }
        return new CalculatedGrade(calculation, new Grade(subject, Mark.values()[average]));
    }
}

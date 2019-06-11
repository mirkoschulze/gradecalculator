/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.calculation;

import eu.ggnet.calculatorui.model.Pupil;
import eu.ggnet.calculatorui.model.Classbook;
import eu.ggnet.calculatorui.model.Grade;
import eu.ggnet.calculatorui.model.Grade.Note;
import eu.ggnet.calculatorui.model.Grade.Subject;
import static eu.ggnet.calculatorui.model.Grade.Subject.AE;
import static eu.ggnet.calculatorui.model.Grade.Subject.ENGLISH;
import static eu.ggnet.calculatorui.model.Grade.Subject.IT_SYSTEMS;
import static eu.ggnet.calculatorui.model.Grade.Subject.IT_WORKSHOP;
import static eu.ggnet.calculatorui.model.Grade.Subject.WUG;

/**
 * Static class to generate new instances of {@link CalculatedGrade}.
 *
 * @author Administrator
 */
public class Calculator {

    /**
     * Counts how often each individual {@link Note} is given to the
     * {@link Pupil} mentioned in a {@link Classbook}.
     * <p>
     * Saves the note with the most occurencies.
     * <p>
     * Generates and returns a new {@link CalculatedGrade} with the calculated
     * note and its occurencies as suffix.
     *
     * @param classbook Counts all notes from the submitted classbook.
     * @param subject Counts all notes for the submitted subject.
     * @return Returns the calculated grade.
     */
    public static CalculatedGrade calculateAccumulatedGrade(Classbook classbook, Subject subject) {
        String calculation = "Häufigste Note";
        int mostOccurencies = 0;
        int iterationStep = 0;
        if (classbook.getPupils() != null) {
            for (int i = 0; i < Note.values().length; i++) {
                int occurencies = 0;
                for (Pupil pupil : classbook.getPupils()) {
                    if (pupil.getCertification() != null) {
                        if (Note.values()[i].getHighschoolNote() == pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote()) {
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
        return new CalculatedGrade(calculation, new Grade(subject, Note.values()[iterationStep]), suffix);
    }

    /**
     * Collects each {@link Note} for the submitted {@link Subject} in the
     * submitted {@link Classbook}, adds the value of each note to a number and
     * divides that number by the amount of {@link Pupil} in that classbook.
     * <p>
     * Generates and returns a new {@link CalculatedGrade} with the calculated
     * number as note.
     *
     * @param classbook Collects all notes from the submitted classbook.
     * @param subject Collects all notes for the submitted subject.
     * @return Returns the calculated grade.
     */
    public static CalculatedGrade calculateAverageGrade(Classbook classbook, Subject subject) {
        String calculation = "Durchschnittsnote";
        int total = 0;
        int average = 0;
        if (classbook.getPupils() != null) {
            if (!classbook.getPupils().isEmpty()) {
                for (Pupil pupil : classbook.getPupils()) {
                    if (pupil.getCertification() != null) {
                        switch (subject) {
                            case ENGLISH:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            case IT_SYSTEMS:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            case IT_WORKSHOP:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            case WUG:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            case AE:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            default:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                        }
                    }
                }
                average = total / classbook.getPupils().size();
            }
        }
        return new CalculatedGrade(calculation, new Grade(subject, Note.values()[average]));
    }
}

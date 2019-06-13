package eu.ggnet.calculator.calculation;

import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Grade;
import eu.ggnet.calculator.model.Grade.Note;
import eu.ggnet.calculator.model.Grade.Subject;
import static eu.ggnet.calculator.model.Grade.Subject.DEV;
import static eu.ggnet.calculator.model.Grade.Subject.SOC;
import static eu.ggnet.calculator.model.Grade.Subject.ENG;
import static eu.ggnet.calculator.model.Grade.Subject.IT_W;
import static eu.ggnet.calculator.model.Grade.Subject.IT_S;

/**
 * Static class to generate new instances of {@link CalculatedGrade}.
 *
 * @author Mirko Schulze
 */
public class Calculator {

    /**
     * Counts how often each individual {@link Note} is given to the
     * {@link Pupil} mentioned in a {@link Classbook}. Saves the note with the
     * most occurencies.
     * <p>
     * Generates and returns a new {@link CalculatedGrade} with the calculated
     * note and its occurencies as suffix.
     *
     * @param classbook Classbook
     * @param subject Subject
     * @return CalculatedGrade
     */
    public static CalculatedGrade calculateAccumulatedGrade(Classbook classbook, Subject subject) {
        String calculation = "Accumulated grade in " + subject.getTheme() + " for " + classbook.toString() + " = ";
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
        String suffix = "(" + mostOccurencies + " times)";
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
     * @param classbook Classbook
     * @param subject Subject
     * @return CalculatedGrade
     */
    public static CalculatedGrade calculateAverageGrade(Classbook classbook, Subject subject) {
        String calculation = "Average note in " + subject.getTheme() + " for " + classbook.getClassbookTitle() + " = ";
        int total = 0;
        int average = 0;
        if (classbook.getPupils() != null) {
            if (!classbook.getPupils().isEmpty()) {
                for (Pupil pupil : classbook.getPupils()) {
                    if (pupil.getCertification() != null) {
                        switch (subject) {
                            case ENG:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            case IT_S:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            case IT_W:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            case SOC:
                                total += pupil.getCertification().getGrades().get(subject.ordinal()).getNote().getHighschoolNote();
                                break;
                            case DEV:
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

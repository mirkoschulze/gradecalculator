/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui;

import eu.ggnet.calculatorui.model.Certification;
import eu.ggnet.calculatorui.model.Pupil;
import eu.ggnet.calculatorui.model.Classbook;
import eu.ggnet.calculatorui.model.Grade;
import eu.ggnet.calculatorui.model.Grade.Note;
import eu.ggnet.calculatorui.model.Grade.Subject;
import eu.ggnet.calculatorui.model.Pupil.Sex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Static class to generate random instances of
 * {@link Certification}, {@link Pupil} and {@link Classbook}.
 * <p>
 * Contains methods to generate random Strings for names or select a random sex.
 *
 * @author mirko.schulze
 */
public class Generator {

    private static final Random R = new Random();

    /**
     * Generates a {@link Certification} with a random {@link Note} for each
     * {@link Subject} for the submitted {@link Pupil}.
     *
     * @param pupil Sets pupils certification.
     * @return Returns the generated certification.
     */
    public static Certification generateCertification(Pupil pupil) {
        List<Grade> listOfGrades = new ArrayList<>();
        Arrays.asList(Grade.Subject.values()).forEach(s -> {
            listOfGrades.add(new Grade(s, Grade.Note.values()[R.nextInt(16)]));
        });
        return new Certification(pupil, listOfGrades);
    }

    /**
     * Generates an instance of {@link Pupil} with random values.
     *
     * @return Returns the generated pupil.
     */
    public static Pupil generatePupil() {
        Sex g = generateSex();
        return new Pupil(g, R.nextInt(10) + 18, generateForename(g), generateSurname());
    }

    /**
     * Generates an instance of {@link Schoolclass} with random values.
     *
     * @return Returns the generated schoolclass.
     */
    public static Classbook generateClassbook() {
        List<Pupil> pupils = new ArrayList<>();
        for (int i = 0; i < R.nextInt(20) + 10; i++) {
            pupils.add(generatePupil());
        }
        return new Classbook(generateSchoolclassName(), pupils);
    }

    /**
     * Selects a random {@link Sex} for an instance of {@link Pupil}.
     *
     * @return Returns the selected gender.
     */
    public static Pupil.Sex generateSex() {
        if (R.nextInt(2) < 1) {
            return Pupil.Sex.MALE;
        } else {
            return Pupil.Sex.FEMALE;
        }
    }

    /**
     * Selects a random forename for an instance of {@link Pupil}.
     *
     * @param gender The selected name depends on the submitted {@link Sex}.
     * @return Returns a String with the selected forename.
     */
    public static String generateForename(Sex gender) {
        if (gender == Pupil.Sex.FEMALE) {
            switch (R.nextInt(16)) {
                case 0:
                    return "Anna";
                case 1:
                    return "Charlotte";
                case 3:
                    return "Doris";
                case 4:
                    return "Elena";
                case 6:
                    return "Franziska";
                case 7:
                    return "Hannelore";
                case 8:
                    return "Maria";
                case 9:
                    return "Brigitte";
                case 10:
                    return "Magdalena";
                case 11:
                    return "Gertrud";
                case 12:
                    return "Vanessa";
                case 13:
                    return "Yvonne";
                case 14:
                    return "Sabine";
                default:
                    return "Ursula";
            }
        } else {
            switch (R.nextInt(16)) {
                case 0:
                    return "Albert";
                case 1:
                    return "Ernst";
                case 2:
                    return "Ferdinand";
                case 3:
                    return "Günther";
                case 4:
                    return "Heinrich";
                case 5:
                    return "Jürgen";
                case 6:
                    return "Kunibert";
                case 7:
                    return "Siegfried";
                case 8:
                    return "Sönke";
                case 9:
                    return "Gottlob";
                case 10:
                    return "Klaas";
                case 11:
                    return "Ahrend";
                case 12:
                    return "Bodo";
                case 13:
                    return "Friedhold ";
                case 14:
                    return "Arne";
                default:
                    return "Wilhelm";
            }
        }
    }

    /**
     * Selects a random surname for an instance of {@link Pupil}.
     *
     * @return Returns a String with the selected surname.
     */
    public static String generateSurname() {
        switch (R.nextInt(10)) {
            case 0:
                return "Burkhard";
            case 1:
                return "Fischer";
            case 2:
                return "Koch";
            case 3:
                return "Lopez";
            case 4:
                return "Müller";
            case 6:
                return "Richter";
            case 7:
                return "Schwarzenegger";
            case 8:
                return "Harstromberg";
            case 9:
                return "Kloetzer";
            case 10:
                return "Meisenheimer";
            case 11:
                return "Gersmann";
            case 12:
                return "Winnenbrock";
            case 13:
                return "Freyning";
            case 14:
                return "Klingelhus";
            case 15:
                return "Noltemeyer";
            case 16:
                return "Holtgreve";
            case 17:
                return "Bolnbach";
            case 18:
                return "Steinberger";
            default:
                return "Zimmermann";
        }
    }

    /**
     * Selects a random name for an instance of {@link Schoolclass}.
     *
     * @return Returns a String with the selected name.
     */
    public static String generateSchoolclassName() {
        switch (R.nextInt(6)) {
            case 0:
                return "KP " + R.nextInt(25);
            case 1:
                return "IT " + R.nextInt(25);
            case 2:
                return "OUG " + R.nextInt(25);
            case 3:
                return "SUK " + R.nextInt(25);
            case 4:
                return "WUG " + R.nextInt(25);
            default:
                return "AE " + R.nextInt(25);
        }
    }

}

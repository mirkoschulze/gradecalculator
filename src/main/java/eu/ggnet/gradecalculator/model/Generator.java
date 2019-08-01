package eu.ggnet.gradecalculator.model;

import eu.ggnet.gradecalculator.model.Grade.Mark;
import eu.ggnet.gradecalculator.model.Grade.Subject;
import eu.ggnet.gradecalculator.model.Pupil.Sex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class with static methods to generate random instances of
 * {@link Certification}, {@link Pupil} and {@link Classbook}.
 * <p>
 * Contains supporting methods to generate random attributes for instances.
 *
 * @author Mirko Schulze
 */
public class Generator {

    private static final Random R = new Random();

    /**
     * Private constructor to prevent an instantiation.
     */
    private Generator() {

    }

    /**
     * Generates a {@link Pupil} with a random {@link Sex}, a random age and a
     * random name.
     *
     * @return Pupil - {@link Pupil} with random values
     */
    public static Pupil generatePupil() {
        Sex s = Generator.generateSex();
        return new Pupil(s, R.nextInt(10) + 18, generateForename(s), generateSurname());
    }

    /**
     * Generates a {@link Classbook} with a random title and a random amount of
     * random {@link Pupil}.
     *
     * @return Classbook - {@link Classbook} with random values
     */
    public static Classbook generateClassbook() {
        List<Pupil> pupils = new ArrayList<>();
        for (int i = 0; i < R.nextInt(20) + 10; i++) {
            pupils.add(Generator.generatePupil());
        }
        return new Classbook(Generator.generateClassbookName(), pupils);
    }

    /**
     * Generates a {@link Certification} with a random {@link Mark} for each
     * {@link Subject} for the submitted {@link Pupil}.
     *
     * @param pupil Pupil - the {@link Pupil} that shall get the
     * {@link Certification}
     * @return Certification - random {@link Certification} for the submitted
     * {@link Pupil}
     */
    public static Certification generateCertification(Pupil pupil) {
        List<Grade> listOfGrades = new ArrayList<>();
        Arrays.asList(Grade.Subject.values()).forEach(s -> {
            listOfGrades.add(new Grade(s, Grade.Mark.values()[R.nextInt(16)]));
        });
        return new Certification(pupil, listOfGrades);
    }

    /**
     * Returns a random {@link Sex} for a {@link Pupil}.
     *
     * @return Sex - random {@link Sex} for a {@link Pupil}
     */
    private static Pupil.Sex generateSex() {
        if (R.nextInt(2) < 1) {
            return Pupil.Sex.MALE;
        } else {
            return Pupil.Sex.FEMALE;
        }
    }

    /**
     * Returns a random forename for a {@link Pupil}, depending on the submitted
     * {@link Sex}.
     *
     * @param sex Sex - {@link Sex} of the {@link Pupil}
     * @return String - random forename for a {@link Pupil}
     */
    private static String generateForename(Sex sex) {
        if (sex == Pupil.Sex.FEMALE) {
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
     * Returns a random surname for a {@link Pupil}.
     *
     * @return String - random surname for a {@link Pupil}
     */
    private static String generateSurname() {
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
     * Joins and returns a random title for a {@link Classbook}, consisting of
     * "IT" followed by a numver between 0 and 12, followed by a random vocal
     * character.
     *
     * @return String - random title for a {@link Classbook}
     */
    private static String generateClassbookName() {
        return "IT " + R.nextInt(13) + ClassbookCharacter.values()[R.nextInt(ClassbookCharacter.values().length)].character;
    }

    /**
     * Enum with vocal characters for titles of a {@link Classbook}.
     */
    public enum ClassbookCharacter {
        A('a'), E('e'), I('i'), O('o'), U('u');

        private char character;

        private ClassbookCharacter(char character) {
            this.character = character;
        }
    }

}

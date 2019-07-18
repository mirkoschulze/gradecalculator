package eu.ggnet.gradecalculator.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the evaluation for a finished course or module.
 * <p>
 * Combines a {@link Mark} with a {@link Subject} and adds the {@link LocalDate}
 * at which it was created.
 * <p>
 * Contains methods to present data in a human-readable format. Overrides
 * {@link Object#toString()} for presentation purposes.
 * <p>
 * Contains enums for the different subjects and values of a mark.
 *
 * @author Mirko Schulze
 */
@Getter
@AllArgsConstructor
public class Grade {

    private final Subject subject;
    private final Mark mark;
    private final LocalDate localDate;

    public Grade(Subject subject, Mark mark) {
        this.subject = subject;
        this.mark = mark;
        this.localDate = LocalDate.now();
    }

    /**
     * {@link ComboBox} uses {@link Object#toString()} to display data.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.toSimpleLine();
    }

    /**
     * Joins and returns a human-readable String with most data of this
     * instance.
     *
     * @return String
     */
    public String toSimpleLine() {
        return this.subject.getTheme() + ": " + this.mark.getMiddleschoolMark();
    }

    /**
     * Joins and returns a human-readable String with the data of this instance.
     *
     * @return String
     */
    public String toEnhancedLine() {
        return this.subject.getTheme() + ": " + this.mark.getMiddleschoolMark()
                + " [ " + this.localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "]";
    }

    /**
     * Enumeration of courses given at a school. Overrides
     * {@link Object#toString()} for presentation purposes.
     */
    @Getter
    public enum Subject {

        ENG("business english"), IT_S("it systems"),
        IT_W("it workshop"), SOC("economics and society"),
        DEV("software development"), ORGA("organization and business processes");

        private final String theme;

        private Subject(String description) {
            this.theme = description;
        }

        /**
         * {@link ComboBox} uses {@link Object#toString()} to display data.
         *
         * @return String
         */
        @Override
        public String toString() {
            return this.theme;
        }

    }

    /**
     * Enumeration of ratings for a {@link Subject}, from 0 to 15.
     */
    @Getter
    public enum Mark {

        ZERO(0, "6"), ONE(1, "5-"), TWO(2, "5"), THREE(3, "5+"), FOUR(4, "4-"), FIVE(5, "4"),
        SIX(6, "4+"), SEVEN(7, "3-"), EIGHT(8, "3"), NINE(9, "3+"), TEN(10, "2-"),
        ELEVEN(11, "2"), TWELVE(12, "2+"), THIRTEEN(13, "1-"), FORTEEN(14, "1"), FIFTEEN(15, "1+");

        private final int highschoolMark;
        private final String middleschoolMark;

        private Mark(int hsMark, String msMark) {
            this.highschoolMark = hsMark;
            this.middleschoolMark = msMark;
        }
    }

}

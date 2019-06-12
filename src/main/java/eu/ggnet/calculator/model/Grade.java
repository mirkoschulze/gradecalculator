/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculator.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;
import lombok.Getter;

/**
 * Represents the evaluation for a finished course or module.
 * <p>
 * Combines a {@link Note} with a {@link Subject} and adds the {@link LocalDate}
 * at which it was created.
 * <p>
 * Contains a method to present data in a human-readable format. Overrides
 * {@link Object#toString()} for presentation purposes.
 *
 * @author Administrator
 */
@Getter
public class Grade {

    private final Subject subject;
    private final Note note;
    private final LocalDate localDate;

    public Grade(Subject subject, Note note) {
        this.subject = subject;
        this.note = note;
        this.localDate = LocalDate.now();
    }

    /**
     * {@link ComboBox} uses {@link Object#toString()} to display data.
     *
     * @return Returns the grades subject and miidleschoolNote.
     */
    @Override
    public String toString() {
        return this.subject.theme + ": " + this.note.middleschoolNote;
    }

    /**
     * Joins and returns a human-readable String with the data of this instance.
     *
     * @return Returns the created String.
     */
    public String toSimpleLine() {
        return this.subject.getTheme() + ": " + this.note.getMiddleschoolNote()
                + " [ " + this.localDate.format(DateTimeFormatter.ofPattern("EEEE dd.MM.yyyy")) + "]";
    }

    /**
     * Enumeration of courses given at a school.Overrides
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
         * @return Returns a Subjects theme.
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
    public enum Note {

        ZERO(0, "6"), ONE(1, "5-"), TWO(2, "5"), THREE(3, "5+"), FOUR(4, "4-"), FIVE(5, "4"),
        SIX(6, "4+"), SEVEN(7, "3-"), EIGHT(8, "3"), NINE(9, "3+"), TEN(10, "2-"),
        ELEVEN(11, "2"), TWELVE(12, "2+"), THIRTEEN(13, "1-"), FORTEEN(14, "1"), FIFTEEN(15, "1+");

        private final int highschoolNote;
        private final String middleschoolNote;

        private Note(int hsNote, String msNote) {
            this.highschoolNote = hsNote;
            this.middleschoolNote = msNote;
        }
    }

}
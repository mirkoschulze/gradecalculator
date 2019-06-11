/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui;

import eu.ggnet.calculatorui.calculation.CalculatedGrade;
import eu.ggnet.calculatorui.model.Certification;
import eu.ggnet.calculatorui.model.Pupil;
import eu.ggnet.calculatorui.model.Classbook;
import eu.ggnet.calculatorui.model.Grade;
import java.io.PrintStream;

/**
 * Class to take care of output purposes via
 * {@link PrintStream#println(java.lang.String)} on {@link System#out}.
 *
 * @author Administrator
 */
public class CliOut {

    public void toCli(Certification certification) {
        System.out.println(certification.toEnhancedLine() + "\n");
    }

    public void toCli(Classbook classbook) {
        System.out.println(classbook.toEnhancedLine() + "\n");
    }

    public void toCli(Pupil pupil) {
        System.out.println(pupil.toSimpleLine() + "\n");
    }

    public void toCli(Grade grade) {
        System.out.println(grade.toSimpleLine() + "\n");
    }

    public void toCli(CalculatedGrade calculatedGrade) {
        System.out.println(calculatedGrade.toEnhancedLine() + "\n");
    }

    public void toCli(Message message) {
        System.out.println("\n\n" + message.getMessage() + "\n\n");
    }

}

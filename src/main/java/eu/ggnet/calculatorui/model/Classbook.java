/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.model;

import java.util.List;

/**
 * Represents the classbook of a schoolclass.
 * <p>
 * Contains a String to hold the name of a {@link Schoolclass} and a list with
 * instances of [@link Pupil}.
 * <p>
 * Contains a method to present data in a human-readable format.
 *
 * @author mirko.schulze
 */
public class Classbook {

    private String classbookName;
    private List<Pupil> pupils;

    public Classbook(String className, List<Pupil> pupils) {
        this.classbookName = className;
        this.pupils = pupils;
    }

    public String getClassbookName() {
        return classbookName;
    }

    public List<Pupil> getPupils() {
        return pupils;
    }

    @Override
    public String toString(){
        return this.classbookName;
    }
//    public String toString() {
//        return "Classbook{" + "name=" + classbookName + ", pupils=" + pupils + '}';
//    }

    /**
     * Joins and returns a human-readable String with all data in this object.
     *
     * @return Returns the created String.
     */
    public String toEnhancedLine() {
        StringBuilder sb = new StringBuilder();
        if (this.classbookName != null) {
            sb.append("Klasse: ").append(this.classbookName).append("\n");
            sb.append("\n");
        }
        if (this.pupils != null) {
            this.pupils.forEach(p -> {
                sb.append(p.getForename()).append(" ").append(p.getSurname()).append(": ").append("\n");
                if (p.getCertification() != null) {
                    p.getCertification().getGrades().forEach(g -> sb.append(g.toSimpleLine()).append("\n"));
                }
                sb.append("\n");
            });
        }
        return sb.toString();
    }

}

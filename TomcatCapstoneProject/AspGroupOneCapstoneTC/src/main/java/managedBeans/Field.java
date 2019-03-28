
package managedBeans;

import java.io.Serializable;

/**
 *
 * @author Jonathan Anders
 */
public class Field implements Serializable {

    private String label;
    private String value;

    public Field() {
        //
    }

    public Field(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
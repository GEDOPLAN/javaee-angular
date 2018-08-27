
package de.gedoplan.javaee.model;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
public class ListValue {

    private Integer id;
    
    private String value;

    public ListValue(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public ListValue() {
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}

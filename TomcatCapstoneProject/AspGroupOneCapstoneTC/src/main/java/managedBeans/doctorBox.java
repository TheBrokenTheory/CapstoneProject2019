package managedBeans;

import java.util.List;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIData;
import javax.faces.component.UINamingContainer;

@FacesComponent(value="dynamicFieldList") // To be specified in componentType attribute.
@SuppressWarnings({"rawtypes", "unchecked"}) // We don't care about the actual model item type anyway.
public class doctorBox extends UINamingContainer {

    private UIData table;

    public void add() {
        ((List) getAttributes().get("value")).add(new Field("somelabel"));
    }

    public void remove() {
        ((List) getAttributes().get("value")).remove(table.getRowData());
    }

    public UIData getTable() {
        return table;
    }

    public void setTable(UIData table) {
        this.table = table;
    }

}
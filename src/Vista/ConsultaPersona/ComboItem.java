package Vista.ConsultaPersona;

import java.util.Objects;

public class ComboItem {

	private Object value;
	private Object label;
	
	public ComboItem(Object value,Object label){
		this.value = value;
		this.label = label;
		
	}
	
	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return label.toString();
	}
}

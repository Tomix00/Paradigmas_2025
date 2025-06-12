package namedEntity.category;

import namedEntity.NamedEntity;

public class Other extends NamedEntity implements java.io.Serializable {

    public Other(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    @Override
	public String toString() {
		return "[Nombre:" + getName() + " | Frecuencia:" + getFrequency() + " | Tipo: Otros | Tema:" + getCategory() + "]";
	}
}

package ua.org.cofriends.gem.entity;

public class Gem {
	
	public enum Preciousness {
		PRECIOUS("Precious"), HALF_PRECIOUS("HalfPrecious");
		
		private final String name;
		
		private Preciousness(String name) {
			this.name = name;
		}
		
		public static Preciousness of(String name) {
			for (Preciousness value : values()) {
				if (value.getName().equals(name)) {
					return value;
				}
			}
			return null;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private String id;
	private String name;
	private Preciousness preciousness;
	private String origin;
	private VisualParams visualParams;
	private int value;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the preciousness
	 */
	public Preciousness getPreciousness() {
		return preciousness;
	}

	/**
	 * @param preciousness the preciousness to set
	 */
	public void setPreciousness(Preciousness preciousness) {
		this.preciousness = preciousness;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the visualParams
	 */
	public VisualParams getVisualParams() {
		return visualParams;
	}

	/**
	 * @param visualParams the visualParams to set
	 */
	public void setVisualParams(VisualParams visualParams) {
		this.visualParams = visualParams;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Gem [id=" + id + ", name=" + name + ", preciousness="
				+ preciousness + ", origin=" + origin + ", visualParams="
				+ visualParams + ", value=" + value + "]";
	}

	public static class VisualParams {
		
		private String color;
		private int transparency;
		private int corners;
		
		/**
		 * @return the color
		 */
		public String getColor() {
			return color;
		}

		/**
		 * @param color the color to set
		 */
		public void setColor(String color) {
			this.color = color;
		}

		/**
		 * @return the transparency
		 */
		public int getTransparency() {
			return transparency;
		}

		/**
		 * @param transparency the transparency to set
		 */
		public void setTransparency(int transparency) {
			this.transparency = transparency;
		}

		/**
		 * @return the corners
		 */
		public int getCorners() {
			return corners;
		}

		/**
		 * @param corners the corners to set
		 */
		public void setCorners(int corners) {
			this.corners = corners;
		}

		@Override
		public String toString() {
			return "VisualParams [color=" + color + ", transparency="
					+ transparency + ", corners=" + corners + "]";
		}
	}
}
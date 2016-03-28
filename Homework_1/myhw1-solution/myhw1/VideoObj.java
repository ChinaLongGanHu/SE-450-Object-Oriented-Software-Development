package myhw1;

/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * <p><b>Class Type:</b> Immutable Data Class</p>
 * <p><b>Object Invariant:</b></p>
 *   Title is non-null, no leading or final spaces, not empty string.
 * <p><b>Object Invariant:</b></p>
 *   Year is greater than 1800, less than 5000.
 * <p><b>Object Invariant:</b></p>
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable<VideoObj> {
	/** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
	private final String title;
	/** <p><b>Invariant:</b> greater than 1800, less than 5000 </p>*/
	private final int    year;
	/** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
	private final String director;

	/**
	 * Initialize all object attributes.
	 * Title and director are "trimmed" to remove leading and final space.
	 * @throws IllegalArgumentException if any object invariant is violated.
	 */
	VideoObj(String title, int year, String director) {
		if (  (title == null)
				|| (director == null)
				|| (year <= 1800)
				|| (year >= 5000)) {
			throw new IllegalArgumentException();
		}
		this.title = title.trim();
		this.director = director.trim();
		this.year = year;
		if (  ("".equals(title))
				|| ("".equals(director))) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Return the value of the attribute.
	 */
	public String director() {
		return director;
	}

	/**
	 * Return the value of the attribute.
	 */
	public String title() {
		return title;
	}

	/**
	 * Return the value of the attribute.
	 */
	public int year() {
		return year;
	}

	/**
	 * Compare the attributes of this object with those of thatObject.
	 * @param thatObject the Object to be compared.
	 * @return deep equality test between this and thatObject.
	 */
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof VideoObj)) {
			return false;
		}
		VideoObj that = (VideoObj) thatObject;
//		return title.equals(that.title())
//				&& (year == that.year())
//				&& director.equals(that.director());
		if (!title.equals(that.title())) return false;
		if (year != that.year()) return false;
		if (director.equals(that.director())) return false;
		return true;
	}

	/**
	 * Return a hash code value for this object using the algorithm from Bloch:
	 * fields are added in the following order: title, year, director.
	 */
	public int hashCode() {
		int result = 17;
		result = 37*result + title.hashCode();
		result = 37*result + year;
		result = 37*result + director.hashCode();
		return result;
	}

	/**
	 * Compares the attributes of this object with those of thatObject, in
	 * the following order: title, year, director.
	 * @param that the VideoObj to be compared.
	 * @return a negative integer, zero, or a positive integer as this
	 *  object is less than, equal to, or greater than that object.
	 */
	public int compareTo(VideoObj that) {
		int titleDiff = title.compareTo(that.title());
		if (titleDiff != 0) {
			return titleDiff;
		}
		int yearDiff = Integer.compare (year, that.year());
		if (yearDiff != 0) {
			return yearDiff;
		}
		int directorDiff = director.compareTo(that.director());
		if (directorDiff != 0) {
			return directorDiff;
		}
		return 0;
	}

	/**
	 * Return a string representation of the object in the following format:
	 * <code>"title (year) : director"</code>.
	 */
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(title);
		buffer.append(" (");
		buffer.append(year);
		buffer.append(") : ");
		buffer.append(director);
		return buffer.toString();
	}
}

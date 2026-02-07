package structural.decorator.httpPipeline.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Represents HTTP headers as a case-insensitive map of header names to their
 * values. Provides methods for retrieving header values, checking for the
 * presence of headers, and creating new Headers instances with modified values.
 */
public final class Headers {

	private final Map<String, List<String>> values;

	/**
	 * Constructs a Headers instance with the given map of header values. The map is
	 * copied to ensure immutability.
	 *
	 * @param values A map of header names to their corresponding list of values.
	 */
	private Headers(Map<String, List<String>> values) {
		this.values = Map.copyOf(values);
	}

	/**
	 * Creates an empty Headers instance with no header values.
	 *
	 * @return An empty Headers instance.
	 */
	public static Headers empty() {
		return new Headers(Map.of());
	}

	/**
	 * Creates a Headers instance from the given map of header values. The header
	 * names are normalized to lower case to ensure case-insensitivity.
	 *
	 * @param values A map of header names to their corresponding list of values.
	 * @return A Headers instance containing the provided header values.
	 */
	public static Headers of(Map<String, List<String>> values) {
		return new Headers(normalize(values));
	}

	/**
	 * Retrieves the list of values associated with the specified header name. The
	 * header name is normalized to lower case to ensure case-insensitivity.
	 *
	 * @param name The name of the header to retrieve.
	 * @return An Optional containing the list of values for the specified header,
	 *         or an empty Optional if the header is not present.
	 */
	public Optional<List<String>> get(String name) {
		return Optional.ofNullable(values.get(normalize(name)));
	}

	/**
	 * Checks if the specified header name is present in the headers. The header
	 * name is normalized to lower case to ensure case-insensitivity.
	 *
	 * @param name The name of the header to check for presence.
	 * @return true if the header is present, false otherwise.
	 */
	public boolean contains(String name) {
		return values.containsKey(normalize(name));
	}

	/**
	 * Returns an unmodifiable view of the headers as a map of header names to their
	 * corresponding list of values.
	 *
	 * @return An unmodifiable map representing the headers.
	 */
	public Map<String, List<String>> asMap() {
		return values;
	}

	/**
	 * Creates a new Headers instance with the specified header name and value added
	 * to the existing headers. The header name is normalized to lower case to
	 * ensure case-insensitivity.
	 *
	 * @param name  The name of the header to add.
	 * @param value The value of the header to add.
	 * @return A new Headers instance containing the existing headers plus the new
	 *         header.
	 */
	public Headers with(String name, String value) {
		Map<String, List<String>> copy = new HashMap<>(values);
		copy.put(normalize(name), List.of(value));
		return new Headers(copy);
	}

	/**
	 * Normalizes the header names in the given map to lower case to ensure
	 * case-insensitivity. The values are copied to maintain immutability.
	 *
	 * @param input A map of header names to their corresponding list of values.
	 * @return A new map with normalized header names and copied values.
	 */
	private static Map<String, List<String>> normalize(Map<String, List<String>> input) {
		Map<String, List<String>> normalized = new HashMap<>();
		input.forEach((k, v) -> normalized.put(normalize(k), List.copyOf(v)));
		return normalized;
	}

	/**
	 * Normalizes the given header name to lower case to ensure case-insensitivity.
	 *
	 * @param name The header name to normalize.
	 * @return The normalized header name in lower case.
	 */
	private static String normalize(String name) {
		return name.toLowerCase(Locale.ROOT);
	}

}

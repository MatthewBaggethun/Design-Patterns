package behavioral.command.textEditor.receivers;

import java.util.HashMap;
import java.util.Map;

/**
 * The Editor singleton contains methods to perform text editing. Editing can be
 * performed on Page objects. To edit a text, the editor must have a current
 * page.
 * 
 * @see Page
 */
public enum Editor {

	INSTANCE;

	private Page currentPage;
	private Map<String, Page> pages = new HashMap<>();

	public void insertText(String newText) {
		currentPage.getPageStringBuilder().insert(currentPage.getCursorPosition(), newText);
		currentPage.setCursorPosition(currentPage.getPageStringBuilder().length());
	}

	public void insertText(int textStartIndex, String newText) {
		currentPage.getPageStringBuilder().insert(textStartIndex, newText);
		currentPage.setCursorPosition(newText.length() + textStartIndex);
	}

	public String deleteText(int textStartIndex, int deletionLength) {
		String deleted = currentPage.getPageStringBuilder().substring(textStartIndex, textStartIndex + deletionLength);

		currentPage.getPageStringBuilder().delete(textStartIndex, textStartIndex + deletionLength);
		currentPage.setCursorPosition(textStartIndex);

		return deleted;
	}

	public String replaceText(int textStartIndex, int textEndIndex, String newText) {
		if (textStartIndex > textEndIndex || textStartIndex == textEndIndex || textStartIndex < 0 || textEndIndex < 0)
			throw new IllegalArgumentException("Invalid index inputs.");

		String replaced = currentPage.getPageStringBuilder().substring(textStartIndex, textEndIndex);
		currentPage.getPageStringBuilder().replace(textStartIndex, textEndIndex, newText);
		currentPage.setCursorPosition(textStartIndex + newText.length());

		return replaced;
	}

	public void moveCursor(int newIndex) {
		if (newIndex >= 0 && newIndex <= currentPage.getPageStringBuilder().length()) {
			currentPage.setCursorPosition(newIndex);
		}
	}

	public boolean addPage(Page page) {
		if (!pages.containsKey(page.getPageName())) {
			pages.put(page.getPageName(), page);
			return true;
		} else
			throw new IllegalArgumentException("A page with that name already exists.");
	}

	public void removePage(Page page) {
		pages.remove(page.getPageName());
	}

	public Page getCurrentPage() {
		return currentPage;
	}

	public Map<String, Page> getPages() {
		return pages;
	}

	public void setCurrentPage(Page page) {
		if (!pages.containsKey(page.getPageName()))
			throw new IllegalArgumentException("That page does not exist.");

		currentPage = pages.get(page.getPageName());
	}

	public void setPages(Map<String, Page> pages) {
		Map<String, Page> newPageMap = new HashMap<>();
		pages.forEach(newPageMap::put);
		this.pages = newPageMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(currentPage.getPageName() + "\n" + currentPage.getPageStringBuilder());
		return builder.toString();
	}

	public String toStringAllPages() {
		StringBuilder builder = new StringBuilder();
		pages.forEach((name, page) -> builder.append(name + "\n" + page.getPageStringBuilder() + "\n"));
		return builder.toString();
	}

}

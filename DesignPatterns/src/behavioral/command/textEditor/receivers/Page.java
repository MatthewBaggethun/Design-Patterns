package behavioral.command.textEditor.receivers;

/**
 * A Page contains the state of a document.
 */
public class Page {

	Editor editor = Editor.INSTANCE;
	private String pageName;
	private StringBuilder pageStringBuilder;
	private int cursorPosition;

	public Page(String pageName) {
		this.pageName = pageName;
		this.pageStringBuilder = new StringBuilder();
		this.cursorPosition = 0;
	}

	public String getPageName() {
		return pageName;
	}

	public StringBuilder getPageStringBuilder() {
		return pageStringBuilder;
	}

	public int getCursorPosition() {
		return cursorPosition;
	}

	public void setPageName(String newPageName) {
		Page newPage = new Page(newPageName);
		newPage.pageStringBuilder = this.pageStringBuilder;

		if (editor.addPage(newPage))
			editor.removePage(this);
	}

	public void setCursorPosition(int newCursorPosition) {
		cursorPosition = newCursorPosition;
	}

}

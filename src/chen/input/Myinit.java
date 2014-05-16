package chen.input;
import javax.swing.JButton;
import java.awt.TextArea;
import javax.swing.JTextArea;
import java.awt.Label;
import javax.swing.JScrollPane;

public final class Myinit {
	/**
	 * @wbp.factory
	 */
	public static JButton createJButton() {
		JButton button = new JButton("\u8BCD\u6CD5\u5206\u6790");
		return button;
	}
	/**
	 * @wbp.factory
	 */
	
	/**
	 * @wbp.factory
	 */
	public static JTextArea createJTextArea() {
		JTextArea textArea = new JTextArea();
		return textArea;
	}
	/**
	 * @wbp.factory
	 */
	public static Label createLabel() {
		Label label = new Label("input");
		return label;
	}
	/**
	 * @wbp.factory
	 */
	public static JScrollPane createJScrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		return scrollPane;
	}
}
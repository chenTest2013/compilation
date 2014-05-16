package chen.calculator;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import chen.compile.Grammaranalyze;

public class UI extends JFrame implements KeyListener {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    boolean mark;
    StringBuilder sbu;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JButton button;
    private JButton button_1;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JButton btnNewButton_5;
    private JButton btnNewButton_9;
    private JButton btnx;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    private JButton btnNewButton_10;
    private JButton button_7;
    private JButton button_6;
    private JButton btnNewButton_11;
    private JTextArea textArea;
    private JButton btnNewButton_12;
    private JButton button_8;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    UI frame = new UI();
		    frame.setResizable(false);
		    frame.setVisible(true);

		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */

    public UI() {

	sbu = new StringBuilder();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 328, 389);
	contentPane = new JPanel();

	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	btnNewButton = Myfactory.createJButton();
	setLister(btnNewButton);
	btnNewButton.setText("7");
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton);
	    }
	});
	btnNewButton.setBounds(20, 129, 47, 23);
	contentPane.add(btnNewButton);

	btnNewButton_1 = Myfactory.createJButton();
	setLister(btnNewButton_1);
	btnNewButton_1.setText("8");
	btnNewButton_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton_1);
	    }
	});
	btnNewButton_1.setBounds(77, 129, 47, 23);
	contentPane.add(btnNewButton_1);

	btnNewButton_2 = Myfactory.createJButton();
	setLister(btnNewButton_2);
	btnNewButton_2.setText("9");
	btnNewButton_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton_2);
	    }
	});
	btnNewButton_2.setBounds(134, 129, 47, 23);
	contentPane.add(btnNewButton_2);

	btnNewButton_3 = Myfactory.createJButton();
	setLister(btnNewButton_3);
	btnNewButton_3.setText("4");
	btnNewButton_3.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton_3);
	    }
	});
	btnNewButton_3.setBounds(20, 181, 47, 23);
	contentPane.add(btnNewButton_3);

	btnNewButton_4 = Myfactory.createJButton();
	setLister(btnNewButton_4);
	btnNewButton_4.setText("5");
	btnNewButton_4.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton_4);
	    }
	});
	btnNewButton_4.setBounds(77, 181, 47, 23);
	contentPane.add(btnNewButton_4);

	btnNewButton_5 = Myfactory.createJButton();
	setLister(btnNewButton_5);
	btnNewButton_5.setText("6");
	btnNewButton_5.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton_5);
	    }
	});
	btnNewButton_5.setBounds(134, 181, 47, 23);
	contentPane.add(btnNewButton_5);

	JButton btnNewButton_6 = Myfactory.createJButton();
	setLister(btnNewButton_6);
	btnNewButton_6.setText("\u67E5\u770B");
	btnNewButton_6.setBounds(20, 10, 93, 23);
	contentPane.add(btnNewButton_6);

	JButton btnNewButton_7 = Myfactory.createJButton();
	setLister(btnNewButton_7);
	btnNewButton_7.setText("\u7F16\u8F91");
	btnNewButton_7.setBounds(114, 10, 93, 23);
	contentPane.add(btnNewButton_7);

	JButton btnNewButton_8 = Myfactory.createJButton();
	setLister(btnNewButton_8);
	btnNewButton_8.setText("\u5E2E\u52A9");
	btnNewButton_8.setBounds(209, 10, 93, 23);
	contentPane.add(btnNewButton_8);

	JScrollPane scrollPane = new JScrollPane();

	scrollPane.setBounds(20, 42, 282, 64);
	contentPane.add(scrollPane);

	textArea = new JTextArea();

	setLister(textArea);
	textArea.setFont(new Font("Monospaced", Font.BOLD, 18));
	setLister(textArea);
	textArea.setEditable(false);
	scrollPane.setViewportView(textArea);

	button = Myfactory.createJButton();
	setLister(button);
	button.setText("/");
	button.setFont(new Font("ËÎÌå", Font.BOLD, 12));
	button.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button);
	    }
	});
	button.setBounds(191, 129, 47, 23);
	contentPane.add(button);

	button_1 = Myfactory.createJButton();
	setLister(button_1);
	button_1.setText("%");
	button_1.setFont(new Font("ËÎÌå", Font.BOLD, 12));
	button_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button_1);
	    }
	});
	button_1.setBounds(248, 129, 54, 23);
	contentPane.add(button_1);

	btnNewButton_9 = Myfactory.createJButton();
	setLister(btnNewButton_9);
	btnNewButton_9.setText("*");
	btnNewButton_9.setFont(new Font("ËÎÌå", Font.BOLD, 12));
	btnNewButton_9.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton_9);
	    }
	});
	btnNewButton_9.setBounds(191, 181, 47, 23);
	contentPane.add(btnNewButton_9);

	btnx = Myfactory.createJButton();
	setLister(btnx);
	btnx.setText("Back");
	btnx.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String str = textArea.getText();
		if (mark) {
		    textArea.setText("");
		    mark = false;
		} else if (str.length() > 0) {
		    textArea.setText(str.substring(0, str.length() - 1));
		} else if (str.length() == 0) {
		    textArea.setText("ÇëÊäÈë");
		}
	    }
	});
	btnx.setVerticalAlignment(SwingConstants.BOTTOM);
	btnx.setFont(new Font("ËÎÌå", Font.PLAIN, 10));
	btnx.setBounds(248, 181, 54, 23);
	contentPane.add(btnx);

	button_2 = Myfactory.createJButton();
	setLister(button_2);
	button_2.setText("1");
	button_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button_2);
	    }
	});
	button_2.setBounds(20, 230, 47, 23);
	contentPane.add(button_2);

	button_3 = Myfactory.createJButton();
	setLister(button_3);
	button_3.setText("2");
	button_3.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button_3);
	    }
	});
	button_3.setBounds(77, 230, 47, 23);
	contentPane.add(button_3);

	button_4 = Myfactory.createJButton();
	setLister(button_4);
	button_4.setText("3");
	button_4.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button_4);
	    }
	});
	button_4.setBounds(134, 230, 47, 23);
	contentPane.add(button_4);

	button_5 = Myfactory.createJButton();
	setLister(button_5);
	button_5.setText("-");
	button_5.setFont(new Font("ËÎÌå", Font.BOLD, 12));
	button_5.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button_5);
	    }
	});
	button_5.setBounds(191, 230, 47, 23);
	contentPane.add(button_5);

	button_6 = Myfactory.createJButton();
	setLister(button_6);
	button_6.setText("+");
	button_6.setFont(new Font("ËÎÌå", Font.BOLD, 12));
	button_6.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button_6);
	    }
	});
	button_6.setBounds(191, 281, 47, 23);
	contentPane.add(button_6);

	button_7 = Myfactory.createJButton();
	setLister(button_7);
	button_7.setText(".");
	button_7.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button_7);
	    }
	});
	button_7.setFont(new Font("ËÎÌå", Font.PLAIN, 16));
	button_7.setBounds(134, 280, 47, 23);
	contentPane.add(button_7);

	btnNewButton_10 = Myfactory.createJButton();
	setLister(btnNewButton_10);
	btnNewButton_10.setText("0");
	btnNewButton_10.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton_10);
	    }
	});
	btnNewButton_10.setBounds(20, 281, 103, 23);
	contentPane.add(btnNewButton_10);

	btnNewButton_11 = Myfactory.createJButton();
	setLister(btnNewButton_11);
	btnNewButton_11.setText("=");
	btnNewButton_11.setFont(new Font("ËÎÌå", Font.BOLD, 12));
	btnNewButton_11.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		count();
	    }
	});
	btnNewButton_11.setBounds(248, 230, 54, 74);
	contentPane.add(btnNewButton_11);

	btnNewButton_12 = new JButton("(");
	setLister(btnNewButton_12);
	btnNewButton_12.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(btnNewButton_12);
	    }
	});
	btnNewButton_12.setBounds(20, 317, 93, 23);
	contentPane.add(btnNewButton_12);

	button_8 = new JButton(")");
	setLister(button_8);
	button_8.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		inputNum(button_8);
	    }
	});
	button_8.setBounds(209, 317, 93, 23);
	contentPane.add(button_8);

	JButton btnTest = new JButton("Test");

	setLister(btnTest);
	btnTest.setBounds(110, 317, 99, 23);
	contentPane.add(btnTest);
    }

    public void inputNum(JButton jb) {

	String str = textArea.getText();
	if (str.startsWith("ÇëÊäÈë") || str.startsWith("ERROR") || mark) {
	    str = "";
	    mark = false;
	}
	sbu.append(str + jb.getText());
	textArea.setText(sbu.toString());
	sbu.delete(0, sbu.length());
    }

    public void keyTyped(KeyEvent e) {// ÅÐ¶Ï¼üÅÌÊÇ·ñÎªÔËËã·û
	// System.out.println("keyTyped");
	if (e.getKeyChar() == '+' || e.getKeyChar() == '-'
		|| e.getKeyChar() == '*' || e.getKeyChar() == '/') {
	    String str = textArea.getText();
	    if (str.startsWith("ÇëÊäÈë") || str.startsWith("ERROR") || mark) {
		str = "";
		mark = false;
	    }
	    String temp = str + e.getKeyChar();
	    // System.out.println("keyTyped="+temp);
	    textArea.setText(temp);
	}

    }

    public void keyPressed(KeyEvent e) {// ÅÐ¶ÏÊý×Ö
	// System.out.println("keyPressed" + e.getKeyCode());
	if ((e.getKeyChar() + "").matches("[0-9]")) {
	    String str = textArea.getText();
	    if (str.startsWith("ÇëÊäÈë") || str.startsWith("ERROR") || mark) {
		str = "";
		mark = false;
	    }// else if(str.length()==20){
	     // str=str+"\n";
	     // }
	    String temp = str + e.getKeyChar();
	    // System.out.println("keyPressed="+temp);
	    textArea.setText(temp);
	    // System.out.println(mark);
	}

    }

    public void keyReleased(KeyEvent e) {// ÅÐ¶ÏÍË¸ñºÍEnter¼ü
	// System.out.println("keyReleased");
	if (e.getKeyCode() == 8) {
	    String str = textArea.getText();
	    if (mark) {
		textArea.setText("");
		mark = false;
	    } else if (str.length() > 0) {
		textArea.setText(str.substring(0, str.length() - 1));
	    } else if (str.length() == 0) {
		textArea.setText("ÇëÊäÈë");
	    }
	} else if (e.getKeyCode() == 10) {
	    count();
	}

    }

    public void setLister(Object obj) {
	if (obj instanceof JButton) {
	    ((JButton) obj).addKeyListener(this);
	} else if (obj instanceof JTextArea) {
	    ((JTextArea) obj).addKeyListener(this);
	}
    }

    public int count() {
	if (!mark) {
	    String str = textArea.getText();
	    if ("".equals(str) || "ÇëÊäÈë".equals(str)) {
		textArea.setText("ÇëÊäÈë");
	    } else {
		Grammaranalyze gra = new Grammaranalyze();
		gra.analyze("", str);
		if (gra.getError().length() > 0) {
		    textArea.setText("ERROR");
		    mark = true;
		    return 0;
		}
		sbu.append(str + "=\t");
		textArea.setText(sbu.toString());
		sbu.delete(0, sbu.length());
		System.out.println("AAAAAAAAAAA");
	    }
	}
	mark = true;
	return 0;

    }
}

package chen.input;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import chen.compile.Grammaranalyze;
import chen.compile.Wordanalyze;

public class MainUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3482546070945339926L;
    private JPanel contentPane;
    private JButton button;
    private JButton button_2;
   
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JTextArea textArea;
    private JTextArea textArea_2;
    private JTextArea textArea_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    MainUI frame = new MainUI();
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
    public MainUI() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 691, 446);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	Label label = Myinit.createLabel();
	label.setBounds(-1, 54, 41, 23);
	contentPane.add(label);

	Label label_1 = Myinit.createLabel();
	label_1.setText("show");
	label_1.setBounds(-1, 241, 44, 23);
	contentPane.add(label_1);

	button = Myinit.createJButton();
	button.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String str = textArea_2.getText();
		String input = textArea.getText();
		if (str == null || "".equals(str)) {
		    textArea_2.setText("请输入文法");
		} else if (input == null || "".equals(input)||"请输入".equals(input)) {
		   
		    textArea.setText("请输入");
		} else {
		   
		    Grammaranalyze gra = new Grammaranalyze();
		    gra.analyze(str, input);
		    textArea_1.setText(gra.getShow().toString());
		  
		}
	    }
	});
	button.setText("\u8BED\u6CD5\u5206\u6790");

	button.setBounds(511, 327, 93, 23);
	contentPane.add(button);

	button_2 = Myinit.createJButton();
	button_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		textArea.setText("");
		textArea_1.setText("");
	    }
	});
	button_2.setText("\u6E05\u9664");
	button_2.setBounds(281, 327, 93, 23);
	contentPane.add(button_2);

	JScrollPane scrollPane = Myinit.createJScrollPane();
	scrollPane.setBounds(75, 170, 228, 121);
	contentPane.add(scrollPane);

	textArea_1 = new JTextArea();
	scrollPane.setViewportView(textArea_1);

	scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(75, 10, 529, 148);
	contentPane.add(scrollPane_1);

	textArea = Myinit.createJTextArea();
	textArea.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if(textArea.getText().trim().startsWith("请输入")){
			textArea.setText("");
		    }
		}
	});
	scrollPane_1.setViewportView(textArea);

	scrollPane_2 = new JScrollPane();
	scrollPane_2.setBounds(341, 170, 263, 121);
	contentPane.add(scrollPane_2);

	textArea_2 = new JTextArea();
	textArea_2.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		textArea_2
			.setText("E→TE';E'→+TE'|-TE'|ε;T→FT';T'→ *FT' | /FT'|ε;F→ (E) | i");
		this.getClass();
	    }
	});
	scrollPane_2.setViewportView(textArea_2);

	JLabel lblNewLabel = new JLabel("<html>请<br>输<br>入<br>文<br>法");
	lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblNewLabel.setAutoscrolls(true);
	lblNewLabel.setBounds(316, 175, 15, 116);
	contentPane.add(lblNewLabel);
	
	JButton button_1 = Myinit.createJButton();
	
	button_1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    String str=textArea.getText();
		    if("请输入".equals(str)||"".equals(str)){
			textArea.setText("请输入");
		    }else{
			 Wordanalyze wd=new Wordanalyze();
			 wd.analyze(str);
			 textArea_1.setText(wd.getSto().getSbuder().toString());
		    }
		   
		    
		}
	});
	button_1.setBounds(75, 327, 93, 23);
	contentPane.add(button_1);
    }
}

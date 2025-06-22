 
import java.awt.*; 
import java.awt.event.*; 
 
public class NewspaperSelector extends Frame { 
    NewspaperSelector() { 
        setSize(300, 200); 
        setLayout(new FlowLayout()); 
 
        List list = new List(5, true); 
        list.add("The Times of India"); 
        list.add("The Hindu"); 
        list.add("Indian Express"); 
        list.add("Deccan Chronicle"); 
        list.add("Hindustan Times"); 
        add(list); 
 
        Button button = new Button("Show"); 
        add(button); 
 
        TextArea area = new TextArea(5, 30); 
        add(area); 
 
        button.addActionListener(e -> { 
            String[] items = list.getSelectedItems(); 
            area.setText(String.join("\n", items)); 
        }); 
 
        addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
                dispose(); 
            } 
        }); 
 
        setVisible(true); 
    } 
 
    public static void main(String[] args) { 
        new NewspaperSelector(); 
    } 
} 
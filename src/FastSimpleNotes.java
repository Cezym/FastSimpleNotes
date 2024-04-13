import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FastSimpleNotes extends AbstractListModel<String> {
    static ArrayList<String> notesList = new ArrayList<>();

    public static void main(String[] args) {
        FastSimpleNotes fastSimpleNotes = new FastSimpleNotes();

        JList<String> list = new JList<>(fastSimpleNotes);

        JFrame jf = new JFrame("Notes");

        jf.setLayout(new BorderLayout());

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane jScrollPane = new JScrollPane(list);

        jf.add(jScrollPane, BorderLayout.CENTER);

        JTextField jTextField = new JTextField();

        jTextField.addActionListener(e -> {
            String text = jTextField.getText();
            if (!notesList.contains(text)) {
                notesList.add(text);
                notesList.sort(Comparator.comparing(String::toLowerCase));
                list.updateUI();
                list.setSelectedIndex(notesList.indexOf(text));
            } else
                System.out.println("Text:" + text + " zostal juz wprowadzony");

            jTextField.setText("");
        });


        list.addMouseListener(new MouseListener() {

                                  @Override
                                  public void mouseClicked(MouseEvent e) {
                                      if (e.getClickCount() == 2 && !e.isConsumed()) {
                                          e.consume();
                                          notesList.remove(list.getSelectedIndex());
                                          list.updateUI();
                                      }
                                  }

                                  @Override
                                  public void mousePressed(MouseEvent e) {

                                  }

                                  @Override
                                  public void mouseReleased(MouseEvent e) {

                                  }

                                  @Override
                                  public void mouseEntered(MouseEvent e) {

                                  }

                                  @Override
                                  public void mouseExited(MouseEvent e) {

                                  }
                              }
        );

        list.addListSelectionListener(new ListSelectionListener() {
            List<String> lastSelectedList = list.getSelectedValuesList();

            @Override
            public void valueChanged(ListSelectionEvent e) {
                List<String> actualSelectedList = list.getSelectedValuesList();
                if (!actualSelectedList.equals(lastSelectedList)) {
                    lastSelectedList = actualSelectedList;
                    for (String text :
                            actualSelectedList) {
                        System.out.println(text);
                    }
                }
            }


        });

        jf.add(jTextField, BorderLayout.NORTH);

        jf.setResizable(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        jf.setSize(250, 600);

        jf.setLocation(width / 2 - jf.getWidth() / 2, height / 2 - jf.getHeight() / 2);

        jf.setVisible(true);
    }

    @Override
    public int getSize() {
        return notesList.size();
    }

    @Override
    public String getElementAt(int index) {
        return notesList.get(index);
    }
}

package org.example;

import javax.swing.*;
import java.io.*;

public class TextEditor extends JFrame {
    JTextArea textArea;

    public TextEditor() {
        setTitle("Текстовий Редактор");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Меню
        JMenuBar menuBar = new JMenuBar();

        // Меню "Файл"
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem openItem = new JMenuItem("Відкрити");
        JMenuItem saveItem = new JMenuItem("Зберегти");
        JMenuItem restartItem = new JMenuItem("Перезапустити програму");
        JMenuItem exitItem = new JMenuItem("Вихід");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(restartItem);
        fileMenu.add(exitItem);

        // меню "Довідка"
        JMenu helpMenu = new JMenu("Довідка");
        JMenuItem helpItem = new JMenuItem("Довідка");
        JMenuItem aboutItem = new JMenuItem("Про програму");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // картинка
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("logo.png");
        imageLabel.setIcon(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //поле редагування
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        Box mainBox = Box.createVerticalBox();
        mainBox.add(imageLabel);
        mainBox.add(scrollPane);

        setContentPane(mainBox);

        //------------------------------------------//
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        restartItem.addActionListener(e -> restart());
        exitItem.addActionListener(e -> System.exit(0));

        helpItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Це просто текстовий редактор. Це все.",
                "Довідка", JOptionPane.INFORMATION_MESSAGE));

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "текстовий редактор v1.0\nРозробник: Непеляк Дмитро\n2025 рік",
                "Про програму", JOptionPane.INFORMATION_MESSAGE));

        setVisible(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Помилка відкриття файлу", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Помилка збереження файлу", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void restart() {
        dispose();
        SwingUtilities.invokeLater(TextEditor::new);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditor::new);
    }
}

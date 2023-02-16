import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class  POSPanel extends JPanel {
    JButton[] MBtn = new JButton[20];
    String[] menu = {
            "짜장면", "짬뽕", "탕수육", "볶음밥",
            "모듬초밥", "가츠동", "메밀소바", "해물우동",
            "피자", "파스타", "수제햄버거", "치킨",
            "돌솥비빔밥", "순두부찌개", "김치찌개","제육볶음",
            "콜라","사이다","소주","맥주"};
    int[] price = new int[]{
            4000, 5000, 7000, 6000,
            10000, 7500, 6000, 6500,
            9000, 5000, 6000, 9500,
            6500, 5000, 7000,8000,
            1500,1500,4000,4000};

    JTextField tf = new JTextField(30);
    JButton[] SBtn = new JButton[3];
    String[] Str = {"선택취소", "전체취소", "결제"};
    String[] ColName = {"메뉴", "수량", "가격"};
    String[][] Data;
    int count = 1;
    DefaultTableModel model = new DefaultTableModel(Data, ColName);
    JTable table = new JTable(model);

    class Screen extends JPanel {
        Screen() {
            setBackground(Color.WHITE);
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            table.setRowHeight(50);
            table.getTableHeader().setFont(new Font("고딕", Font.BOLD, 15));
            add(new JScrollPane(table));
        }
    }

    class MenuBtn extends JPanel {
        MenuBtn() {
            setLayout(new GridLayout(6, 3, 3, 3));
            setBackground(Color.WHITE);
            for (int i = 0; i < MBtn.length; i++) {
                MBtn[i] = new JButton(menu[i]);
                add(MBtn[i]);
            }
        }
    }

    class StrBtn extends JPanel {
        StrBtn() {
            setBackground(Color.WHITE);
            setLayout(new GridLayout(1, 4, 3, 3));

            for (int i = 0; i < Str.length; i++) {
                SBtn[i] = new JButton(Str[i]);
                add(SBtn[i]);
            }
        }
    }

    public POSPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        MenuBtn mbtn = new MenuBtn();
        StrBtn sbtn = new StrBtn();
        Screen sc = new Screen();

        //금액란
        tf.setSize(450, 70);
        tf.setLocation(50, 480);
        add(tf);

        sc.setSize(500, 500);
        sc.setLocation(25, 20);
        add(sc);

        mbtn.setSize(400, 430);
        mbtn.setLocation(530, 23);
        add(mbtn);

        sbtn.setSize(400, 70);
        sbtn.setLocation(530, 480);
        add(sbtn);

        //메뉴추가
        for (int i = 0; i < MBtn.length; i++) {
            final int index = i;
            MBtn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton MBtn = (JButton) e.getSource();
                    DefaultTableModel m = (DefaultTableModel) table.getModel();
                    m.addRow(new Object[]{menu[index], count, price[index]});
                }
            });
        }

        //선택취소
        SBtn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton MBtn = (JButton) e.getSource();
                DefaultTableModel m = (DefaultTableModel) table.getModel();

                m.removeRow(table.getSelectedRow());
            }
        });


        //전체취소
        SBtn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton MBtn = (JButton) e.getSource();
                DefaultTableModel m = (DefaultTableModel) table.getModel();

                m.setRowCount(0);
                tf.setText(String.valueOf(""));
            }
        });

        //결제버튼
        SBtn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton MBtn = (JButton) e.getSource();
                int rowCont = table.getRowCount();
                int sum = 0;
                for (int i = 0; i < rowCont; i++) {
                    sum += (int) table.getValueAt(i, 2);
                }
                tf.setText(String.valueOf(" 금액은 : " + sum + "원입니다."));
                tf.setFont(new Font("고딕", Font.BOLD, 30));
            }
        });
    }
}

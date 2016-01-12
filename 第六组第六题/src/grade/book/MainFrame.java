package grade.book;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gallen
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() throws SQLException{
        initComponents();
        initialShowTable();
        Connection conn = MySQLConn.MySQLConn();
        String ssql = "select distinct cname from classes";
        PreparedStatement pstm = conn.prepareStatement(ssql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next())
        {
            cb_crsNam.addItem(rs.getString(1));
        }
    }
    
    private void initialShowTable()throws SQLException
    {
        Connection conn = MySQLConn.MySQLConn();
        String sql = "select lastname,firstname,ranking,total,grade,hw1,hw2,hw3,hw4,midterm1,midterm2,final from Students right join Grades on Students.SID = Grades.SID "
                + "right join classes on classes.cid = grades.cid "
                + "where classes.year = " + cb_year.getSelectedItem() + " and "
                + " classes.section = " + cb_sec.getSelectedItem() + " and "
                + " classes.quarter = '" + cb_quarter.getSelectedItem() + "'";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int count = metaData.getColumnCount();
        Vector<String[]> vec = new Vector<String[]>();
        if(rs.next() == false)
        {
            String[] str = new String[]{""};
            vec.add(str);
        }
        while(rs.next())
        {
            String[] str = new String[count];
            for(int i = 1; i <= count; i++)
            {
                str[i-1] = rs.getString(i);
            }
            vec.addElement(str);
        }
        if(vec.size() != 0)
        {
            TableModel tm = new TableModel(vec);
            shwTable.setModel(tm);
        }
        
        conn.close();
    }
    
    class TableModel extends AbstractTableModel{
        Vector<String[]> vec;
        public TableModel(Vector<String[]> v )
        {
            vec = v;
        }
        @Override
        public int getRowCount() {
            return vec.size();
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getColumnCount() {
            return vec.get(0).length;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return vec.get(rowIndex)[columnIndex];
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("gradeBook?zeroDateTimeBehavior=convertToNullPU").createEntityManager();
        toolBar = new javax.swing.JToolBar();
        bt_newStu = new javax.swing.JButton();
        bt_rmvStu = new javax.swing.JButton();
        bt_shwStu = new javax.swing.JButton();
        sprt1 = new javax.swing.JToolBar.Separator();
        bt_newClass = new javax.swing.JButton();
        bt_shwGrades = new javax.swing.JButton();
        sprt2 = new javax.swing.JToolBar.Separator();
        bt_saveAll = new javax.swing.JButton();
        bt_prntRprt = new javax.swing.JButton();
        pnl_select = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_year = new javax.swing.JComboBox<>();
        cb_quarter = new javax.swing.JComboBox<>();
        cb_crsNam = new javax.swing.JComboBox<>();
        cb_sec = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_filtering = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pnl_comAssgmtExm = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        srp_cae = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        pnl_policy = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tf_noHWK = new javax.swing.JTextField();
        tf_maxHWK = new javax.swing.JTextField();
        tf_HWKPC = new javax.swing.JTextField();
        tf_HWKDrops = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tf_maxMid1 = new javax.swing.JTextField();
        tf_midtermPC1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf_maxMid2 = new javax.swing.JTextField();
        tf_midtermPC2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tf_maxFinal = new javax.swing.JTextField();
        tf_final = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cb_editableField = new javax.swing.JComboBox<>();
        if_shwTable = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        shwTable = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        mn_file = new javax.swing.JMenu();
        mni_exit = new javax.swing.JMenuItem();
        mn_student = new javax.swing.JMenu();
        mni_addNewStu = new javax.swing.JMenuItem();
        mni_chgStuID = new javax.swing.JMenuItem();
        mni_rmvStu = new javax.swing.JMenuItem();
        mn_class = new javax.swing.JMenu();
        mni_addNewClass = new javax.swing.JMenuItem();
        mn_report = new javax.swing.JMenu();
        mn_system = new javax.swing.JMenu();
        mn_help = new javax.swing.JMenu();
        mni_about = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Grade Book");

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        bt_newStu.setText("New Student");
        bt_newStu.setFocusable(false);
        bt_newStu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_newStu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_newStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_newStuActionPerformed(evt);
            }
        });
        toolBar.add(bt_newStu);

        bt_rmvStu.setText("Remove Student");
        bt_rmvStu.setFocusable(false);
        bt_rmvStu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_rmvStu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(bt_rmvStu);

        bt_shwStu.setText("Show Students");
        bt_shwStu.setFocusable(false);
        bt_shwStu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_shwStu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(bt_shwStu);
        toolBar.add(sprt1);

        bt_newClass.setText("New Class");
        bt_newClass.setFocusable(false);
        bt_newClass.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_newClass.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_newClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_newClassActionPerformed(evt);
            }
        });
        toolBar.add(bt_newClass);

        bt_shwGrades.setText("Show Grades");
        bt_shwGrades.setFocusable(false);
        bt_shwGrades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_shwGrades.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_shwGrades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_shwGradesActionPerformed(evt);
            }
        });
        toolBar.add(bt_shwGrades);
        toolBar.add(sprt2);

        bt_saveAll.setText("Save All");
        bt_saveAll.setFocusable(false);
        bt_saveAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_saveAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_saveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_saveAllActionPerformed(evt);
            }
        });
        toolBar.add(bt_saveAll);

        bt_prntRprt.setText("Print Report");
        bt_prntRprt.setFocusable(false);
        bt_prntRprt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_prntRprt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(bt_prntRprt);

        jLabel1.setText("Year");

        cb_year.setEditable(true);
        cb_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));
        cb_year.setPreferredSize(new java.awt.Dimension(50, 21));
        cb_year.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_yearItemStateChanged(evt);
            }
        });
        cb_year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_yearActionPerformed(evt);
            }
        });

        cb_quarter.setEditable(true);
        cb_quarter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FALL", "WINTER", "SPRING", "SUMMER 1", "SUMMER 2" }));
        cb_quarter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_quarterItemStateChanged(evt);
            }
        });

        cb_crsNam.setEditable(true);
        cb_crsNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_crsNamItemStateChanged(evt);
            }
        });

        cb_sec.setEditable(true);
        cb_sec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
        cb_sec.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_secItemStateChanged(evt);
            }
        });

        jLabel2.setText("Quarter");

        jLabel3.setText("Course Name");

        jLabel4.setText("Sec.");

        jLabel5.setText("Filtering");

        javax.swing.GroupLayout pnl_selectLayout = new javax.swing.GroupLayout(pnl_select);
        pnl_select.setLayout(pnl_selectLayout);
        pnl_selectLayout.setHorizontalGroup(
            pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_selectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cb_year, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_quarter, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cb_crsNam, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_selectLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_sec, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_selectLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cb_filtering, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        pnl_selectLayout.setVerticalGroup(
            pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_selectLayout.createSequentialGroup()
                .addGroup(pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_selectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_quarter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_crsNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_sec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_filtering, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel6.setText("Comments on Assignments and Exams");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        srp_cae.setViewportView(jTextArea1);

        javax.swing.GroupLayout pnl_comAssgmtExmLayout = new javax.swing.GroupLayout(pnl_comAssgmtExm);
        pnl_comAssgmtExm.setLayout(pnl_comAssgmtExmLayout);
        pnl_comAssgmtExmLayout.setHorizontalGroup(
            pnl_comAssgmtExmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_comAssgmtExmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(srp_cae)
                .addContainerGap())
            .addGroup(pnl_comAssgmtExmLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel6)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        pnl_comAssgmtExmLayout.setVerticalGroup(
            pnl_comAssgmtExmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_comAssgmtExmLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srp_cae, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel7.setText("Policy:");

        jLabel9.setText("No. of HWK");

        jLabel10.setText("Max. HWK");

        jLabel11.setText("HWK %");

        jLabel12.setText("HWK Drops");

        tf_HWKPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_HWKPCActionPerformed(evt);
            }
        });

        jLabel13.setText("Max. Mid 1");

        jLabel14.setText("Midterm 1 %");

        jLabel15.setText("Max. Mid 2");

        jLabel16.setText("Midterm 2 %");

        jLabel17.setText("Max. Final");

        jLabel18.setText("Final %");

        jLabel19.setText("Editable Field");

        cb_editableField.setEditable(true);
        cb_editableField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none" }));

        javax.swing.GroupLayout pnl_policyLayout = new javax.swing.GroupLayout(pnl_policy);
        pnl_policy.setLayout(pnl_policyLayout);
        pnl_policyLayout.setHorizontalGroup(
            pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_policyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_policyLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnl_policyLayout.createSequentialGroup()
                        .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_policyLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_maxHWK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_policyLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_HWKPC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_policyLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_HWKDrops, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_policyLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_noHWK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnl_policyLayout.createSequentialGroup()
                                .addComponent(tf_midtermPC1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_final, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_policyLayout.createSequentialGroup()
                                .addComponent(tf_maxMid2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19))
                            .addGroup(pnl_policyLayout.createSequentialGroup()
                                .addComponent(tf_midtermPC2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cb_editableField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnl_policyLayout.createSequentialGroup()
                                .addComponent(tf_maxMid1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_maxFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_policyLayout.setVerticalGroup(
            pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_policyLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_noHWK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_maxHWK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tf_HWKPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tf_HWKDrops, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnl_policyLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_maxMid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(tf_maxFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(tf_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_midtermPC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_maxMid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_policyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_midtermPC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(cb_editableField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        if_shwTable.setVisible(true);

        shwTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "LastName", "FirstName", "Ranking", "Total", "Grade", "HW1", "HW2", "HW3", "HW4", "Midterm1", "Midterm2", "Final"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        shwTable.setCellSelectionEnabled(true);
        shwTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        shwTable.setTableHeader(shwTable.getTableHeader());
        jScrollPane1.setViewportView(shwTable);

        javax.swing.GroupLayout if_shwTableLayout = new javax.swing.GroupLayout(if_shwTable.getContentPane());
        if_shwTable.getContentPane().setLayout(if_shwTableLayout);
        if_shwTableLayout.setHorizontalGroup(
            if_shwTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        if_shwTableLayout.setVerticalGroup(
            if_shwTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        mn_file.setText("File");

        mni_exit.setText("Exit");
        mni_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_exitActionPerformed(evt);
            }
        });
        mn_file.add(mni_exit);

        menuBar.add(mn_file);

        mn_student.setText("Student");

        mni_addNewStu.setText("Add New Student");
        mni_addNewStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_addNewStuActionPerformed(evt);
            }
        });
        mn_student.add(mni_addNewStu);

        mni_chgStuID.setText("Change Student ID");
        mni_chgStuID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_chgStuIDActionPerformed(evt);
            }
        });
        mn_student.add(mni_chgStuID);

        mni_rmvStu.setText("Remove Student");
        mn_student.add(mni_rmvStu);

        menuBar.add(mn_student);

        mn_class.setText("Class");

        mni_addNewClass.setText("Add New Class");
        mni_addNewClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_addNewClassActionPerformed(evt);
            }
        });
        mn_class.add(mni_addNewClass);

        menuBar.add(mn_class);

        mn_report.setText("Report");
        menuBar.add(mn_report);

        mn_system.setText("System");
        menuBar.add(mn_system);

        mn_help.setText("Help");

        mni_about.setText("About");
        mn_help.add(mni_about);

        menuBar.add(mn_help);

        setJMenuBar(menuBar);
        menuBar.getAccessibleContext().setAccessibleName("");
        menuBar.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(if_shwTable)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnl_comAssgmtExm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnl_select, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_policy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnl_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnl_comAssgmtExm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnl_policy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(if_shwTable)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void bt_newStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newStuActionPerformed
        // TODO add your handling code here:
        NewStudent ns = new NewStudent();
        ns.setVisible(true);
    }//GEN-LAST:event_bt_newStuActionPerformed

    private void bt_saveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_saveAllActionPerformed

    private void bt_shwGradesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_shwGradesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_shwGradesActionPerformed

    private void mni_addNewStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_addNewStuActionPerformed
        // TODO add your handling code here:
        NewStudent ns = new NewStudent();
        ns.setVisible(true);
    }//GEN-LAST:event_mni_addNewStuActionPerformed

    private void mni_chgStuIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_chgStuIDActionPerformed
        // TODO add your handling code here:
        ChangeStudentID csi = new ChangeStudentID();
        csi.setVisible(true);
    }//GEN-LAST:event_mni_chgStuIDActionPerformed

    private void mni_addNewClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_addNewClassActionPerformed
        // TODO add your handling code here:
        NewClass nc = new NewClass();
        nc.setVisible(true);
    }//GEN-LAST:event_mni_addNewClassActionPerformed

    private void bt_newClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newClassActionPerformed
        // TODO add your handling code here:
        NewClass nc = new NewClass();
        nc.setVisible(true);
    }//GEN-LAST:event_bt_newClassActionPerformed

    private void tf_HWKPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_HWKPCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_HWKPCActionPerformed

    private void mni_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_exitActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_mni_exitActionPerformed

    private void cb_yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_yearActionPerformed

    private void cb_yearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_yearItemStateChanged
        try {
            initialShowTable();
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection conn = MySQLConn.MySQLConn();
            String[] str = new String[]{"NumHWK", "MaxHWK","PCTHWK","HWKDrops","MaxMidterm1", "PCTMidterm1","MaxMidterm2","PCTMidterm2","MaxFinal","PCTFinal"};
            for(int i = 0; i < 10; i++){
                String noh_string = "select policy."+str[i]+" from policy right join Classes on Classes.CID = policy.CID where classes.year = " + cb_year.getSelectedItem();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(noh_string);
                ResultSet rs = pstm.executeQuery();
                if(rs.next()){
                    if(str[i].equals("NumHWK"))
                    {
                        tf_noHWK.setText(rs.getString(1));
                    }else if(str[i].equals("MaxHWK"))
                    {
                        tf_maxHWK.setText(rs.getString(1));
                    }else if(str[i].equals("PCTHWK"))
                    {
                        tf_HWKPC.setText(rs.getString(1));
                    }else if(str[i].equals("HWKDrops"))
                    {
                        tf_HWKDrops.setText(rs.getString(1));
                    }else if(str[i].equals("MaxMidterm1"))
                    {
                        tf_maxMid1.setText(rs.getString(1));
                    }else if(str[i].equals("PCTMidterm1"))
                    {
                        tf_midtermPC1.setText(rs.getString(1));
                    }else if(str[i].equals("MaxMidterm2"))
                    {
                        tf_maxMid2.setText(rs.getString(1));
                    }else if(str[i].equals("PCTMidterm2"))
                    {
                        tf_midtermPC2.setText(rs.getString(1));
                    }else if(str[i].equals("MaxFinal"))
                    {
                        tf_maxFinal.setText(rs.getString(1));
                    }else if(str[i].equals("PCTFinal"))
                    {
                        tf_final.setText(rs.getString(1));
                    }                    
                }
                else
                {
                    tf_noHWK.setText(null);
                    tf_maxHWK.setText(null);
                    tf_HWKPC.setText(null);
                    tf_HWKDrops.setText(null);
                    tf_maxMid1.setText(null);
                    tf_midtermPC1.setText(null);
                    tf_maxMid2.setText(null);
                    tf_midtermPC2.setText(null);
                    tf_maxFinal.setText(null);
                    tf_final.setText(null);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_yearItemStateChanged

    private void cb_quarterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_quarterItemStateChanged
        // TODO add your handling code here:
        try {
            initialShowTable();
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_quarterItemStateChanged

    private void cb_crsNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_crsNamItemStateChanged
        // TODO add your handling code here:
        try {
            initialShowTable();
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_crsNamItemStateChanged

    private void cb_secItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_secItemStateChanged
        // TODO add your handling code here:
        try {
            initialShowTable();
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_secItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_newClass;
    private javax.swing.JButton bt_newStu;
    private javax.swing.JButton bt_prntRprt;
    private javax.swing.JButton bt_rmvStu;
    private javax.swing.JButton bt_saveAll;
    private javax.swing.JButton bt_shwGrades;
    private javax.swing.JButton bt_shwStu;
    private javax.swing.JComboBox<String> cb_crsNam;
    private javax.swing.JComboBox<String> cb_editableField;
    private javax.swing.JTextField cb_filtering;
    private javax.swing.JComboBox<String> cb_quarter;
    private javax.swing.JComboBox<String> cb_sec;
    private javax.swing.JComboBox<String> cb_year;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JInternalFrame if_shwTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mn_class;
    private javax.swing.JMenu mn_file;
    private javax.swing.JMenu mn_help;
    private javax.swing.JMenu mn_report;
    private javax.swing.JMenu mn_student;
    private javax.swing.JMenu mn_system;
    private javax.swing.JMenuItem mni_about;
    private javax.swing.JMenuItem mni_addNewClass;
    private javax.swing.JMenuItem mni_addNewStu;
    private javax.swing.JMenuItem mni_chgStuID;
    private javax.swing.JMenuItem mni_exit;
    private javax.swing.JMenuItem mni_rmvStu;
    private javax.swing.JPanel pnl_comAssgmtExm;
    private javax.swing.JPanel pnl_policy;
    private javax.swing.JPanel pnl_select;
    private javax.swing.JTable shwTable;
    private javax.swing.JToolBar.Separator sprt1;
    private javax.swing.JToolBar.Separator sprt2;
    private javax.swing.JScrollPane srp_cae;
    private javax.swing.JTextField tf_HWKDrops;
    private javax.swing.JTextField tf_HWKPC;
    private javax.swing.JTextField tf_final;
    private javax.swing.JTextField tf_maxFinal;
    private javax.swing.JTextField tf_maxHWK;
    private javax.swing.JTextField tf_maxMid1;
    private javax.swing.JTextField tf_maxMid2;
    private javax.swing.JTextField tf_midtermPC1;
    private javax.swing.JTextField tf_midtermPC2;
    private javax.swing.JTextField tf_noHWK;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}

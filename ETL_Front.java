/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etl;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.*;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DJSCE.Student
 */
public class ETL_Front extends javax.swing.JFrame {
     int no[]=new int[5];
            String name[]=new String[5];
            int sal[]=new int[5];
            String add[]=new String[5];
            String gender[]=new String[5];

    /**
     * Creates new form ETL_Front
     */
    public ETL_Front() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        extract = new javax.swing.JButton();
        load = new javax.swing.JButton();
        transform = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        extract.setText("Extract");
        extract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extractActionPerformed(evt);
            }
        });

        load.setText("Load");

        transform.setText("Transform");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(extract)
                .addGap(29, 29, 29)
                .addComponent(load)
                .addGap(27, 27, 27)
                .addComponent(transform)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extract)
                    .addComponent(load)
                    .addComponent(transform))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    
    private void extractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extractActionPerformed
        try {
            // TODO add your handling code here:
            Oracle();
        } catch (Exception ex) {
            Logger.getLogger(ETL_Front.class.getName()).log(Level.SEVERE, null, ex);
        }
        File();   
        try {
            Access();
        } catch (Exception ex) {
            Logger.getLogger(ETL_Front.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_extractActionPerformed

     public void Access() throws Exception {

      Connection conn=DriverManager.getConnection("jdbc:ucanaccess://D:\\Database16.accdb");
      Connection conn2= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SCOTT2","tiger");
            Statement stmt2=conn2.createStatement();  
        String sql="select * from employee8";
        try{
             Statement st=null;
                ResultSet rs= null;
            //pst=conn.prepareStatement(sql);
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                int no=rs.getInt("no");
                String name=rs.getString("name");
                int sal=rs.getInt("sal");
                String add=rs.getString("addr");
                String gender=rs.getString("gender");
                //java.util.Date utilDate = affiliate.getDate();
                //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                sql="insert into result(no,name,sal,addr,gender) values("+no+",'"+name+"',"+sal+",'"+add+"','"+gender+"');";
                stmt2.executeUpdate(sql);


            }
        }
        catch (Exception e){
         
        }

}
    
    public void Oracle() throws Exception {
                   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SCOTT2","tiger");
            Statement stmt=conn.createStatement();
        try{


           
            ResultSet rset=stmt.executeQuery("select * from employee8");
           
            int i=0;
            
            while(rset.next())
            {
                
               // System.out.println("i");
                no[i]=rset.getInt("no");
                name[i]=rset.getString("name");
                sal[i]=rset.getInt("sal");
                add[i]=rset.getString("addr");
                gender[i]=rset.getString("gender");
               // System.out.println(no+" "+name);      
                   i++;
                
                //String query="insert into result values("+no+",'"+name+"',"+sal+",'"+add+"','"+gender+"')";
                
                //stmt.executeUpdate(query);

                
            }
            
        }
        catch(Exception e){
		e.printStackTrace();
        }
        finally
        {
            for(int k=0;k<5;k++)
            {
                  System.out.println(no[k]+" "+name[k]); 
                
                 String query="insert into result values("+no[k]+",'"+name[k]+"',"+sal[k]+",'"+add[k]+"','"+gender[k]+"')";
                
                stmt.executeUpdate(query);  
            }
        }
        
        
    }
    
    public void File()
    {
        try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        
			
			
        Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SCOTT2","tiger");			
			//Query insert to table product with 5 value
			String query = "INSERT INTO result VALUES(?, ?, ?, ?, ?)";
			//Create prepare statement 
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			
			//Get list product from file text
			ArrayList<Product> listProduct = getListProductFromTextFile("D:\\60003140039\\text.txt");
			//Insert list to db
			for(int i = 0; i < listProduct.size(); i ++) {
				preparedStatement.setInt(1, listProduct.get(i).getId());
				preparedStatement.setString(2, listProduct.get(i).getName());
				preparedStatement.setInt(3, listProduct.get(i).getSal());
				preparedStatement.setString(4, listProduct.get(i).getAdd());
                                preparedStatement.setString(5, listProduct.get(i).getGender());
                                
                                
				
				preparedStatement.executeUpdate();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }    
        public static ArrayList<Product> getListProductFromTextFile(String filePath) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		ArrayList<Product> listResult = new ArrayList<Product>();
		try {
			fis = new FileInputStream(filePath);
			isr = new InputStreamReader(fis);
			bReader = new BufferedReader(isr);
			//String save line get from text file
			String line = null;
			//Array save product
			String[]strProduct = null;
			
			//Loop and get all data in text file
			while(true) {
				//Get 1 line
				line = bReader.readLine();
				//Check line get empty, exit loop 
				if(line == null) {
					break;
				} else {
					strProduct = line.split(",");
					listResult.add(new Product(Integer.parseInt(strProduct[0]), strProduct[1], Integer.parseInt(strProduct[2]), strProduct[3] , strProduct[4]));
				}
			}
			
		} catch (Exception e) {
			System.out.println("Read file error");
			e.printStackTrace();
		} finally {
			//close file
			try {
				bReader.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listResult;
	}
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
            java.util.logging.Logger.getLogger(ETL_Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ETL_Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ETL_Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ETL_Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ETL_Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ETL_Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ETL_Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ETL_Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ETL_Front().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton extract;
    private javax.swing.JButton load;
    private javax.swing.JButton transform;
    // End of variables declaration//GEN-END:variables
}



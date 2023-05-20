package services;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import interfaces.Exporter;
import interfaces.Template;

public class ExporterService {
    private Exporter exporter = new Exporter();
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCell cell;

    public ExporterService() {
        exporter.getExporterBtn().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exporter();
            }
        });

    }

    public void exporter() {
        int rowIndex = 1;
        int n=1;
        //--------show File chooser----------------

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Choisir le dossier ");
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = jfc.showDialog(null, "enregistrer");

        // -------EXPORT -------------------
       try (HSSFWorkbook workbook=new HSSFWorkbook()) {

         //--------cell style----------------------
         CellStyle headerStyle = workbook.createCellStyle();
         HSSFFont font= workbook.createFont();
         font.setBold(true);
         headerStyle.setFont(font);
         headerStyle.setAlignment(HorizontalAlignment.CENTER);
           

        sheet = workbook.createSheet("Personnel");
        row = sheet.createRow(0);

        cell = row.createCell(0);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("N°");

        cell = row.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Nom");

        cell = row.createCell(2);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Prénom");

        cell = row.createCell(3);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Poste");

        cell = row.createCell(4);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Heures");

        try {
            ResultSet res = Template.db_tables.getTablePersonnel().select();
            while (res.next()) {
                row = sheet.createRow(rowIndex++);
                cell = row.createCell(0);
                cell.setCellValue(n++);

                cell = row.createCell(1);
                cell.setCellValue(res.getString("Nom"));

                cell = row.createCell(2);
                cell.setCellValue(res.getString("Prenom"));

                cell = row.createCell(3);
                cell.setCellValue(res.getString("Poste"));

                cell = row.createCell(4);
                cell.setCellValue(res.getInt("Heures"));


            }

            //--------cell autosizing------------------------
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            //--------Après la selection de dossier----------------------------------------
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                workbook.write(new FileOutputStream(jfc.getSelectedFile()+File.separator+"ListPersonnel.xls"));
                System.out.println("Exportation terminé");
                JOptionPane.showMessageDialog(null, "Exportation terminé");
                rowIndex=0;
                n=0;
                workbook.close();
            }
        
       } catch (Exception e) {
             e.printStackTrace();
       }

       }catch (Exception e) {
        e.printStackTrace();
  }
    }

    public Exporter getExporter() {
        return exporter;
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
    }

}

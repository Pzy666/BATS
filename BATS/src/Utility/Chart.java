/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entities.Expense;
import Entities.Project;
import SQLconnect.SQLconnect;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Ziyin
 */
public class Chart {
      public  CategoryDataset getRealDataSet(){
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i =Expense.getAllBillCodeNum();
        String [][] arr=SQLconnect.importAll(i);
        int b=Project.getproNum();
        String [] pc=Project.getAllProjectName(b);
        for(int t=0;t<b;t++){
               double total=0;
            for(int z=0;z<i;z++){
        if(pc[t].equals(arr[z][0])){
        for(int j=3;j<7;j++){
        total+=Double.parseDouble(arr[t][j]);
        }
        }
        }
        dataset.addValue(total,Project.getpidbyName(pc[t]),pc[t]);
            
        }
        return dataset;     
    }

    public  ChartPanel BarC(){
	CategoryDataset dataset = getRealDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("Full Expense Report","Project ID","Expense($)", dataset, PlotOrientation.VERTICAL, true,false,false );
        ChartPanel fram1=new ChartPanel(chart,true);   
         return fram1;       
   }
    
      public  JFreeChart chartC(){
	CategoryDataset dataset = getRealDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("Full Expense Report","Project ID","Expense($)", dataset, PlotOrientation.VERTICAL, true,false,false );
        ChartPanel fram1=new ChartPanel(chart,true);  
         
         return chart;
         
   }

   public  DefaultPieDataset getDataSet(String proname) {
    DefaultPieDataset dataset = new DefaultPieDataset();
    int t=Expense.getAllBillCodeNum();
    String [][] arr=SQLconnect.importAll(t);
    double flight = 0, hotel = 0, trans = 0, dining = 0, others = 0;
         for(int i =0;i<t;i++){
             if(arr[i][0].equals(proname)){
                 flight += Double.parseDouble(arr[i][3]);
                 hotel  += Double.parseDouble(arr[i][4]);
                 trans  += Double.parseDouble(arr[i][5]);
                 dining += Double.parseDouble(arr[i][6]);
                 others += Double.parseDouble(arr[i][7]);
             }
         }
         dataset.setValue("Flight Expense",flight);
         dataset.setValue("Hotel Expense",hotel);
         dataset.setValue("Ground Transportation",trans);
         dataset.setValue("Dining Expense",dining);
         dataset.setValue("Others",others);
         return dataset;
       }
   
    public  ChartPanel PieC(String proname){
        DefaultPieDataset data = getDataSet(proname);
        JFreeChart chart = ChartFactory.createPieChart3D("Project "+proname+" Expense Distribution",data,true,false,false);
        PiePlot pieplot = (PiePlot) chart.getPlot();
	DecimalFormat df = new DecimalFormat("0.00%");
	NumberFormat nf = NumberFormat.getNumberInstance();
        NumberFormat n1=NumberFormat.getCurrencyInstance();
	StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);
	pieplot.setLabelGenerator(sp1);
        pieplot.setNoDataMessage("The Project you choose has not data currently, check it later.");
        pieplot.setForegroundAlpha(1.0f);
	pieplot.setCircular(false);
	pieplot.setLabelGap(0.02D);
        pieplot.setIgnoreNullValues(true);
	pieplot.setIgnoreZeroValues(true);
        ChartPanel frame1=new ChartPanel (chart,true);
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
	PiePlot piePlot= (PiePlot) chart.getPlot();
	piePlot.setLabelFont(new Font("宋体",Font.BOLD,10));
	chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));
        return frame1;
   
   }
    public  JFreeChart PieChart(String proname){
        DefaultPieDataset data = getDataSet(proname);
        JFreeChart chart = ChartFactory.createPieChart3D("Project "+proname+" Expense Distribution",data,true,false,false);
        PiePlot pieplot = (PiePlot) chart.getPlot();
	DecimalFormat df = new DecimalFormat("0.00%");
	NumberFormat nf = NumberFormat.getNumberInstance();
	StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);
	pieplot.setLabelGenerator(sp1);
        pieplot.setNoDataMessage("The Project you choose has not data currently, check it later.");
        pieplot.setForegroundAlpha(1.0f);
	pieplot.setCircular(false);
	pieplot.setLabelGap(0.02D);
        pieplot.setIgnoreNullValues(true);
	pieplot.setIgnoreZeroValues(true);
        ChartPanel frame1=new ChartPanel (chart,true);
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
	PiePlot piePlot= (PiePlot) chart.getPlot();
	piePlot.setLabelFont(new Font("宋体",Font.BOLD,10));
	chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));
        return chart;
   
   }
}

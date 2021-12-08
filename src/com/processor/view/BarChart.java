package com.processor.view;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.Vector;

public class BarChart extends JDialog{
        ChartPanel chartPanel;
        public BarChart(MainView mainView) {
            Vector<Integer> vector = mainView.getQuarterData();
            DefaultCategoryDataset data = (DefaultCategoryDataset) getDataSet(vector);
            JFreeChart chart = ChartFactory.createBarChart3D(
                     "Quarterly Statistics - " + mainView.user_name,
                    "Quarter",
                    "Quantity",
                    data,
                    PlotOrientation.VERTICAL,
                    true,
                    false,
                    false
            );

            CategoryPlot plot = chart.getCategoryPlot();
            CategoryAxis domain = plot.getDomainAxis();
            domain.setTickLabelFont(new Font("Cooper Black", Font.PLAIN, 12));
            domain.setLabelFont(new Font("Cooper Black", Font.BOLD, 20));
            ValueAxis rangeAxis = plot.getRangeAxis();
            rangeAxis.setLabelFont(new Font("Cooper Black", Font.BOLD, 16));
            chart.getLegend().setItemFont(new Font("Cooper Black", Font.BOLD, 16));
            chart.getTitle().setFont(new Font("Cooper Black", Font.BOLD, 16));

            chartPanel = new ChartPanel(chart);
            this.add(chartPanel);

            setSize(900,860);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);
            setVisible(true);
        }

        public static CategoryDataset getDataSet(Vector<Integer> vector) {
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            data.setValue(vector.get(0), "Total business volume", "1st");
            data.setValue(vector.get(1), "Total business volume", "2nd");
            data.setValue(vector.get(2), "Total business volume", "3rd");
            data.setValue(vector.get(3), "Total business volume", "4th");
            return data;
        }

        public static void main(String[] args) {
        }
}

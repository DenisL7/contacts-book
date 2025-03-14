package org.example.contactsbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.contacts.book.dao.contacts.ContactsDAO;
import org.contacts.book.dao.contacts.ContactsDAOImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "StatisticServlet",value = "/statistic")
public class StatisticServlet extends HttpServlet {
    private ContactsDAO contactsDAO;
    public void init() {
        try {
            InitialContext ctx=new InitialContext();
            DataSource dataSource=(DataSource) ctx.lookup("java:comp/env/myContacts");
            contactsDAO=new ContactsDAOImpl(dataSource);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Java", 45);
        dataset.setValue("Python", 30);
        dataset.setValue("JavaScript", 25);
        JFreeChart chart = ChartFactory.createPieChart("Programming Languages Usage",
                dataset,                       // dataset
                true,                          // include legend
                true,                          // tooltips
                false                          // URLs
        );
        resp.setContentType("image/png");
        OutputStream out = resp.getOutputStream();
        ChartUtilities.writeChartAsPNG(out, chart, 500, 400);
        out.close();*/
        Map<String,Integer> contacts=contactsDAO.createdContactsByMonth(req.getRemoteUser());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String key:contacts.keySet()){
            int value=contacts.get(key);
            dataset.setValue(value,key,key);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "statistics",
                "month",
                "amount of contacts",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        resp.setContentType("image/png");
        OutputStream out = resp.getOutputStream();
        ChartUtilities.writeChartAsPNG(out, chart, 500, 400);
        out.close();



    }
}

package dk.itu.gsd.lms.ui;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(MainView.NAME)
@Theme("runo")
public class MainView extends Panel implements View
{
    public static final String NAME = "profile";

    @Autowired
    private transient ApplicationContext applicationContext;


    @PostConstruct
    public void PostConstruct()
    {
        Layout mainLayout = new VerticalLayout();
        mainLayout.addComponent( new Label("This is the building ui"));
        
       // mainLayout.addComponent( getChart());
        mainLayout.addComponent(new Label("This is the LMS web client"));
        
        setContent(mainLayout);
    }
//    protected com.vaadin.ui.Component getChart() {
//        Chart chart = new Chart(ChartType.COLUMN);
//
//        Configuration conf = chart.getConfiguration();
//
//        conf.setTitle("Total fruit consumtion, grouped by gender");
//        conf.setSubTitle("Source: WorldClimate.com");
//
//        XAxis x = new XAxis();
//        x.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
//                "Sep", "Oct", "Nov", "Dec");
//        conf.addxAxis(x);
//
//        YAxis y = new YAxis();
//        y.setMin(0);
//        y.setTitle("Rainfall (mm)");
//        conf.addyAxis(y);
//
//        Legend legend = new Legend();
//        legend.setLayout(LayoutDirection.VERTICAL);
//        legend.setBackgroundColor("#FFFFFF");
//        legend.setHorizontalAlign(HorizontalAlign.LEFT);
//        legend.setVerticalAlign(VerticalAlign.TOP);
//        legend.setX(100);
//        legend.setY(70);
//        legend.setFloating(true);
//        legend.setShadow(true);
//        conf.setLegend(legend);
//
//        Tooltip tooltip = new Tooltip();
//        tooltip.setFormatter("this.x +': '+ this.y +' mm'");
//        conf.setTooltip(tooltip);
//
//        PlotOptionsColumn plot = new PlotOptionsColumn();
//        plot.setPointPadding(0.2);
//        plot.setBorderWidth(0);
//
//        conf.addSeries(new ListSeries("Tokyo", 49.9, 71.5, 106.4, 129.2, 144.0,
//                176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4));
//        conf.addSeries(new ListSeries("New York", 83.6, 78.8, 98.5, 93.4,
//                106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3));
//        conf.addSeries(new ListSeries("London", 48.9, 38.8, 39.3, 41.4, 47.0,
//                48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2));
//        conf.addSeries(new ListSeries("Berlin", 42.4, 33.2, 34.5, 39.7, 52.6,
//                75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1));
//
//        chart.drawChart(conf);
//        return chart;
//    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
    }
    
}

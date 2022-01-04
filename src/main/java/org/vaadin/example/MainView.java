package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.HashMap;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin", shortName = "Project Base", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    public MainView() {
        add(createBarWithNegativeStack());
    }

    private Chart createBarWithNegativeStack () {
        Chart chart = new Chart(ChartType.BAR);
        chart.setWidth("800px");
        Configuration conf = chart.getConfiguration();

        conf.setTitle("Population pyramid for Germany, midyear 2010");
        conf.setSubTitle("Source: www.census.gov");

        final String[] categories = new String[] { "0-4", "5-9", "10-14",
                "15-19", "20-24", "25-29", "30-34", "35-39", "40-44", "45-49",
                "50-54", "55-59", "60-64", "65-69", "70-74", "75-79", "80-84",
                "85-89", "90-94", "95-99", "100 +" };

        XAxis x1 = new XAxis();
        conf.addxAxis(x1);
        x1.setCategories(categories);
        x1.setReversed(false);

        XAxis x2 = new XAxis();
        conf.addxAxis(x2);
        x2.setCategories(categories);
        x2.setOpposite(true);
        x2.setReversed(false);
        x2.setLinkedTo(x1);

        YAxis y = new YAxis();
        y.setMin(-4000000);
        y.setMax(4000000);
        y.setTitle(new AxisTitle(""));
        conf.addyAxis(y);

        PlotOptionsSeries plot = new PlotOptionsSeries();
        plot.setStacking(Stacking.NORMAL);
        conf.setPlotOptions(plot);
        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter(
                "function() {return '<b>'+ this.series.name +', age '+ this.point.category +'</b><br/>'+ 'Population: '+ Highcharts.numberFormat(Math.abs(this.point.y), 0)}");
        conf.setTooltip(tooltip);

        conf.addSeries(new ListSeries("Male", -1746181, -1884428, -2089758,
                -2222362, -2537431, -2507081, -2443179, -2664537, -3556505,
                -3680231, -3143062, -2721122, -2229181, -2227768, -2176300,
                -1329968, -836804, -354784, -90569, -28367, -3878));
        conf.addSeries(new ListSeries("Female", 1656154, 1787564, 1981671,
                2108575, 2403438, 2366003, 2301402, 2519874, 3360596, 3493473,
                3050775, 2759560, 2304444, 2426504, 2568938, 1785638, 1447162,
                1005011, 330870, 130632, 21208));
        return chart;
    }
}

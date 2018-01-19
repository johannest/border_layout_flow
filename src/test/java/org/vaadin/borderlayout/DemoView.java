package org.vaadin.borderlayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("")
@HtmlImport("styles.html")
public class DemoView extends Div {

    private String[] texts = { "NORTH", "SOUTH", "CENTER", "EAST", "WEST" };
    private String[] widths = {"100%","100%","100%","100%","100%"};
    private String[] heights = {"100px","100px","300px","300px","300px"};

    public DemoView() {
        Component[] components = new Component[5];
        for (int i = 0; i < 5; i++) {
            components[i] = new TextArea();
            ((TextArea) components[i]).setValue(texts[i]);
            ((TextArea) components[i]).setWidth(widths[i]);
            ((TextArea) components[i]).setHeight(heights[i]);
        }

        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setWidth("800px");
        borderLayout.addComponent(components[0], BorderLayout.Constraint.NORTH);
        borderLayout.addComponent(components[1], BorderLayout.Constraint.SOUTH);
        borderLayout.addComponent(components[2], BorderLayout.Constraint.CENTER);
        borderLayout.addComponent(components[3], BorderLayout.Constraint.EAST);
        borderLayout.addComponent(components[4], BorderLayout.Constraint.WEST);

        add(borderLayout);
    }
}

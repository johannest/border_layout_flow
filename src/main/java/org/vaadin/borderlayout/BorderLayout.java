package org.vaadin.borderlayout;

import java.io.Serializable;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;

import elemental.json.JsonObject;

@Tag("border-layout")
@HtmlImport("bower_components/border-layout/border-layout.html")
public class BorderLayout extends Component implements HasStyle, HasComponents, HasSize {

    public enum Constraint {
        NORTH, WEST, CENTER, EAST, SOUTH
    }

    protected Component defaultNorth = new Label(" ");
    protected Component defaultWest = new Label(" ");
    protected Component defaultCenter = new Label(" ");
    protected Component defaultEast = new Label(" ");
    protected Component defaultSouth = new Label(" ");

    protected Component north = defaultNorth;
    protected Component west = defaultWest;
    protected Component center = defaultCenter;
    protected Component east = defaultEast;
    protected Component south = defaultSouth;

    public BorderLayout(Component... components) {
        this.add(components);
    }

    public BorderLayout() {
    }

    /**
     * Adds components into layout in the default order: 1. NORTH, 2. WEST, 3.
     * CENTER, 4. EAST, 5. SOUTH until all slots are filled
     *
     * @throws IllegalArgumentException
     *             if layout is "full"
     */
    public BorderLayout addComponent(Component c) {
        if (getComponent(Constraint.NORTH).equals(defaultNorth)) {
            addComponent(c, Constraint.NORTH);
            return this;
        } else if (getComponent(Constraint.WEST).equals(defaultWest)) {
            addComponent(c, Constraint.WEST);
            return this;
        } else if (getComponent(Constraint.CENTER).equals(defaultCenter)) {
            addComponent(c, Constraint.CENTER);
            return this;
        } else if (getComponent(Constraint.EAST).equals(defaultEast)) {
            addComponent(c, Constraint.EAST);
            return this;
        }  else if (getComponent(Constraint.SOUTH).equals(defaultSouth)) {
            addComponent(c, Constraint.SOUTH);
            return this;
        }
        throw new IllegalArgumentException(
                "All layout places are filled, please use addComponent(Component c, Constraint constraint) for force fill given place");
    }

    public BorderLayout addComponent(Component component, Constraint constraint) {
        if (constraint == Constraint.NORTH) {
            north = component;
            component.getElement().setAttribute("slot", "NORTH");
            getElement().appendChild(component.getElement());
        } else if (constraint == Constraint.WEST) {
            west = component;
            component.getElement().setAttribute("slot", "WEST");
            getElement().appendChild(component.getElement());
        } else if (constraint == Constraint.CENTER) {
            center = component;
            component.getElement().setAttribute("slot", "CENTER");
            getElement().appendChild(component.getElement());
        } else if (constraint == Constraint.EAST) {
            east = component;
            component.getElement().setAttribute("slot", "EAST");
            getElement().appendChild(component.getElement());
        } else if (constraint == Constraint.SOUTH) {
            south = component;
            component.getElement().setAttribute("slot", "SOUTH");
            getElement().appendChild(component.getElement());
        } else {
            throw new IllegalArgumentException(
                    "Invalid BorderLayout constraint.");
        }
        return this;
    }

    /**
     * Return component from specific position
     *
     * @param position
     * @return
     */
    public Component getComponent(Constraint position) {
        if (position == Constraint.NORTH) {
            return north;
        } else if (position == Constraint.WEST) {
            return west;
        } else if (position == Constraint.CENTER) {
            return center;
        } else if (position == Constraint.EAST) {
            return east;
        } else if (position == Constraint.SOUTH) {
            return south;
        } else {
            throw new IllegalArgumentException(
                    "Invalid BorderLayout constraint.");
        }
    }


    private Component getDefault(Constraint position) {
        if (position == Constraint.NORTH) {
            return defaultNorth;
        } else if (position == Constraint.WEST) {
            return defaultWest;
        } else if (position == Constraint.CENTER) {
            return defaultCenter;
        } else if (position == Constraint.EAST) {
            return defaultEast;
        } else if (position == Constraint.SOUTH) {
            return defaultSouth;
        } else {
            throw new IllegalArgumentException(
                    "Invalid BorderLayout constraint.");
        }
    }

    /**
     * Returns position of given component or null if the layout doesn't contain it
     * @param component
     * @return
     */
    public Constraint getConstraint(Component component) {
        if (north.equals(component)) {
            return Constraint.NORTH;
        } else if (west.equals(component)) {
            return Constraint.WEST;
        } else if (center.equals(component)) {
            return Constraint.CENTER;
        } else if (east.equals(component)) {
            return Constraint.EAST;
        } else if (south.equals(component)) {
            return Constraint.SOUTH;
        } else {
            return null;
        }
    }

    /**
     * Removes the given child components from this component.
     *
     * @param components
     *            The components to remove.
     * @throws IllegalArgumentException
     *             if any of the components is not a child of this component.
     */
    public void remove(Component... components) {
        for (Component component : components) {
            if (getElement().equals(component.getElement().getParent())) {
                component.getElement().removeAttribute("slot");
                getElement().removeChild(component.getElement());
            } else {
                throw new IllegalArgumentException("The given component ("
                        + component + ") is not a child of this component");
            }
        }
    }

    /**
     * Removes all contents from this component, this includes child components,
     * text content as well as child elements that have been added directly to
     * this component using the {@link Element} API.
     */
    public void removeAll() {
        getElement().getChildren()
                .forEach(child -> child.removeAttribute("slot"));
        getElement().removeAllChildren();
    }

    protected void updateStyles(JsonObject _Args) {
        this.getElement().callFunction("updateStyles", new Serializable[]{_Args});
    }

}

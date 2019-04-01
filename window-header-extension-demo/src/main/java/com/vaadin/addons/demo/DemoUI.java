package com.vaadin.addons.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.windowheaderextension.WindowHeaderExtension;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Theme("demo")
@Title("WindowHeaderExtension Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();

        Button button = new Button("Open window", e -> {
            Window w = new Window("Window Header", new Label("Window Content"));
            w.setHeight("25%");
            w.setWidth("25%");
            w.center();
            WindowHeaderExtension.extend(w, FontAwesome.GITHUB, "GitHub button",
                    () -> {
                        final String url = "https://github.com/KatriHaapalinna/window-header-extension";
                        getUI().getPage().open(url, "_blank");
                    }, "GitHub opening button");
            WindowHeaderExtension.extend(w, VaadinIcons.ACCESSIBILITY, () -> {
                Notification.show("You clicked a custom button!");
            }, "accessibility button");
            UI.getCurrent().addWindow(w);
        });
        layout.addComponents(button);
        setContent(layout);
    }

}

package org.vaadin.addons.windowheaderextension;

import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Add many of these with different configurations, combine with different
 * components, for regressions and also make them dynamic if needed.
 */
public class BasicMyComponentUsageUI extends AbstractTest {

    @Override
    public Component getTestComponent() {
        VerticalLayout v = new VerticalLayout();
        Button b = new Button("Open window");
        v.addComponent(b);
        b.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                // create window
                Window w = new Window();
                w.setHeight("200");
                w.setWidth("200");
                // additional buttons to window
                WindowHeaderExtension.extend(w, FontAwesome.AMBULANCE,
                        "I say hello", new WindowButtonClickListener() {

                            @Override
                            public void buttonClicked() {
                                JavaScript js = JavaScript.getCurrent();
                                js.execute("window.alert('hello!');");

                            }

                        });
                WindowHeaderExtension.extend(w, FontAwesome.APPLE,
                        "I do other things", new WindowButtonClickListener() {

                            @Override
                            public void buttonClicked() {
                                JavaScript js = JavaScript.getCurrent();
                                js.execute("window.alert('another click!');");

                            }

                        });
                UI.getCurrent().addWindow(w);
            }
        });

        return v;
    }

}

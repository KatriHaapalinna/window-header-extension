package org.vaadin.addons.windowheaderextension;

import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
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
        final UI ui = UI.getCurrent();
        VerticalLayout v = new VerticalLayout();
        Button b = new Button("Open window");
        v.addComponent(b);
        b.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                // create window
                final Window w = new Window();
                final VerticalLayout vl = new VerticalLayout();
                vl.addComponent(
                        new Label("Demo Window Demo Window Demo Window"));

                w.setContent(vl);
                w.setWidth("400px");
                // additional buttons to window
                WindowHeaderExtension.extend(w, FontAwesome.AMBULANCE,
                        "  I say hello", new WindowButtonClickListener() {

                            @Override
                            public void buttonClicked() {
                                JavaScript js = JavaScript.getCurrent();
                                js.execute("window.alert('hello!');");
                            }
                        }, "hello button");
                WindowHeaderExtension.extend(w, FontAwesome.GITHUB,
                        "Source code", new WindowButtonClickListener() {

                            @Override
                            public void buttonClicked() {
                                if (vl.getComponentCount() > 1) {
                                    return;
                                }
                                final String url = "https://github.com/KatriHaapalinna/window-header-extension";
                                Button b = new Button("Go to source");
                                b.setIcon(FontAwesome.GITHUB);
                                b.addClickListener(new ClickListener() {

                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        ui.getPage().open(url, "");
                                    }
                                });
                                vl.addComponent(b);
                            }
                        }, "GitHub button");
                WindowHeaderExtension.extend(w, FontAwesome.CAMERA_RETRO,
                        new WindowButtonClickListener() {

                            @Override
                            public void buttonClicked() {
                                JavaScript js = JavaScript.getCurrent();
                                js.execute("window.alert('without tooltip!');");
                            }
                        });
                UI.getCurrent().addWindow(w);
            }
        });

        return v;
    }

}

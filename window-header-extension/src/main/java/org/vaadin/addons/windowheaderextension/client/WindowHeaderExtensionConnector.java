package org.vaadin.addons.windowheaderextension.client;

import org.vaadin.addons.windowheaderextension.WindowHeaderExtension;

import com.google.gwt.aria.client.Roles;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VWindow;
import com.vaadin.client.ui.window.WindowConnector;
import com.vaadin.shared.ui.Connect;

@Connect(WindowHeaderExtension.class)
public class WindowHeaderExtensionConnector extends AbstractExtensionConnector {
    public static final String CLASSNAME = "windowheader";
    VWindow window;
    Element buttonDiv;

    private final WindowHeaderExtensionServerRpc rpc = RpcProxy
            .create(WindowHeaderExtensionServerRpc.class, this);

    public WindowHeaderExtensionConnector() {
    }

    @Override
    public WindowHeaderExtensionState getState() {
        return (WindowHeaderExtensionState) super.getState();
    }

    @Override
    protected void extend(ServerConnector target) {
        window = ((WindowConnector) target).getWidget();

        buttonDiv = DOM.createDiv();
        buttonDiv.addClassName(CLASSNAME + "-button");
        buttonDiv.setTabIndex(0);

        buttonDiv.setInnerHTML(getState().iconHtml);

        // if tooltip is not null or empty, add to div
        if (getState().tooltipText != null
                && !getState().tooltipText.trim().isEmpty()) {
            buttonDiv.addClassName("tooltip");

            Element tooltip = DOM.createSpan();
            tooltip.addClassName("tooltiptext");
            tooltip.setInnerText(getState().tooltipText.trim());

            buttonDiv.appendChild(tooltip);
        }

        Roles.getButtonRole().set(buttonDiv);

        if (getState().ariaLabel != null && !getState().ariaLabel.isEmpty()) {
            Roles.getButtonRole().setAriaLabelProperty(buttonDiv,
                    getState().ariaLabel);
        }

        Style s = buttonDiv.getStyle();
        double visibleChildren = window.header.getChildCount() - 1;
        s.setRight(visibleChildren * 33.0, Style.Unit.PX);
        window.header.getFirstChildElement().getStyle()
                .setProperty("borderRadius", 0.0, Style.Unit.PX);

        window.header.insertFirst(buttonDiv);
        addButtonClickListener(buttonDiv);
        addKeyboardListener(buttonDiv);

        Element caption = (Element) window.header.getLastChild();
        caption.getStyle().setHeight(36, Style.Unit.PX);
    }

    public native void addButtonClickListener(Element el)
    /*-{
        var self = this;
        el.onclick = $entry(function () {
            self.@org.vaadin.addons.windowheaderextension.client.WindowHeaderExtensionConnector::buttonClicked()();
        });
    
    }-*/;

    public native void addKeyboardListener(Element el)
    /*-{
    var self = this;
        el.onkeyup = $entry(function (event) {
            var key = 0;
           if ($wnd.event) {
             key = $wnd.event.keyCode;
           } else if (event) {
             key = event.keyCode;
           }
           // ENTER or SPACE key
           if (key == 13 || key == 32) {
             self.@org.vaadin.addons.windowheaderextension.client.WindowHeaderExtensionConnector::buttonClicked()();
           }
           return true;
        });

    }-*/;

    private void buttonClicked() {
        rpc.buttonClick();
    }

}

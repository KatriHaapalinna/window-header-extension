package org.vaadin.addons.windowheaderextension;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.windowheaderextension.client.WindowHeaderExtensionServerRpc;
import org.vaadin.addons.windowheaderextension.client.WindowHeaderExtensionState;

import com.vaadin.server.AbstractExtension;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Window;

public class WindowHeaderExtension extends AbstractExtension {

    private final List<WindowButtonClickListener> listeners = new ArrayList<WindowButtonClickListener>();

    private WindowHeaderExtensionServerRpc rpc = new WindowHeaderExtensionServerRpc() {

        public void buttonClick() {
            for (WindowButtonClickListener listener : listeners) {
                listener.buttonClicked();
            }
        }
    };

    private WindowHeaderExtension() {
        registerRpc(rpc);
    }

    @Override
    public WindowHeaderExtensionState getState() {
        return (WindowHeaderExtensionState) super.getState();
    }

    /**
     * Adds additional header button to Window
     *
     * @param w
     *            Window
     * @param icon
     *            FontAwesome Icon
     * @param tooltipText
     *            text for button tooltip
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window w, FontAwesome icon, String tooltipText,
            WindowButtonClickListener clickListener) {
        WindowHeaderExtension ex = new WindowHeaderExtension();
        ex.getState().iconHtml = icon.getHtml();
        ex.getState().tooltipText = tooltipText;
        ex.listeners.add(clickListener);
        ex.extend(w);
    }

}

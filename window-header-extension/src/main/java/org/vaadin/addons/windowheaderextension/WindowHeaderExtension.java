package org.vaadin.addons.windowheaderextension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.windowheaderextension.client.WindowHeaderExtensionServerRpc;
import org.vaadin.addons.windowheaderextension.client.WindowHeaderExtensionState;

import com.vaadin.icons.VaadinIcons;
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
     * Add header button (icon) to Window
     *
     * @param win
     *            Window
     * @param icon
     *            FontAwesome icon
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window win, FontAwesome icon,
            WindowButtonClickListener clickListener) {
        extend(win, icon, "", clickListener, "");
    }

    /**
     * Add header button (icon) to Window
     *
     * @param win
     *            Window
     * @param icon
     *            VaadinIcons icon
     * @param ariaLabel
     *            accessibility description
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window win, FontAwesome icon,
            WindowButtonClickListener clickListener, String ariaLabel) {
        extend(win, icon, "", clickListener, ariaLabel);
    }

    /**
     * Add header button (icon) with tooltip to Window
     *
     * @param win
     *            Window
     * @param icon
     *            FontAwesome icon
     * @param tooltipText
     *            text for button tooltip
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window win, FontAwesome icon, String tooltipText,
            WindowButtonClickListener clickListener, String ariaLabel) {
        WindowHeaderExtension ex = new WindowHeaderExtension();
        ex.getState().iconHtml = icon.getHtml();
        ex.getState().tooltipText = tooltipText;
        ex.getState().ariaLabel = ariaLabel;
        ex.listeners.add(clickListener);
        ex.extend(win);
    }

    /**
     * Add header button (icon) to Window
     *
     * @param win
     *            Window
     * @param icon
     *            VaadinIcons icon
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window win, VaadinIcons icon,
            WindowButtonClickListener clickListener) {
        extend(win, icon, clickListener, "");
    }

    /**
     * Add header button (icon) to Window
     *
     * @param win
     *            Window
     * @param icon
     *            VaadinIcons icon
     * @param ariaLabel
     *            accessibility description
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window win, VaadinIcons icon,
            WindowButtonClickListener clickListener, String ariaLabel) {
        extend(win, icon, "", clickListener, ariaLabel);
    }

    /**
     * Add header button (icon) with tooltip to Window
     *
     * @param win
     *            Window
     * @param icon
     *            VaadinIcons icon
     * @param ariaLabel
     *            accessibility description
     * @param tooltipText
     *            text for button tooltip
     * @param clickListener
     *            WindowButtonClicklistener
     */
    public static void extend(Window win, VaadinIcons icon, String tooltipText,
            WindowButtonClickListener clickListener, String ariaLabel) {
        WindowHeaderExtension ex = new WindowHeaderExtension();
        ex.getState().iconHtml = icon.getHtml();
        ex.getState().ariaLabel = ariaLabel;
        ex.getState().tooltipText = tooltipText;
        ex.listeners.add(clickListener);
        ex.extend(win);
    }

    public interface WindowButtonClickListener extends Serializable {

        public void buttonClicked();

    }

}

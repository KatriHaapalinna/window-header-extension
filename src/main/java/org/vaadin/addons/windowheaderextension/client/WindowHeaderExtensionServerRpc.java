package org.vaadin.addons.windowheaderextension.client;

import com.vaadin.shared.communication.ServerRpc;

// ServerRpc is used to pass events from client to server
public interface WindowHeaderExtensionServerRpc extends ServerRpc {

	// Example API: Widget click is clicked
	public void buttonClick();

}

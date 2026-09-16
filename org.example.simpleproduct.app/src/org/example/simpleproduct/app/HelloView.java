package org.example.simpleproduct.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class HelloView extends ViewPart {

	public static final String ID = "org.example.simpleproduct.app.helloView"; //$NON-NLS-1$

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Label label = new Label(parent, SWT.CENTER);
		label.setText("Hello from Simple Product!");
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
	}

	@Override
	public void setFocus() {
		// nothing to focus
	}
}

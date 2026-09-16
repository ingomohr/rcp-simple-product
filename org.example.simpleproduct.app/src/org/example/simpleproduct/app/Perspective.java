package org.example.simpleproduct.app;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public static final String ID = "org.example.simpleproduct.app.perspective"; //$NON-NLS-1$

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		IFolderLayout folder = layout.createFolder("main", IPageLayout.LEFT, 1.0f, editorArea); //$NON-NLS-1$
		folder.addView(HelloView.ID);
	}
}

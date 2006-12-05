/*******************************************************************************
 * Copyright (c) 2004 - 2006 University Of British Columbia and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     University Of British Columbia - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylar.internal.java.ui.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.internal.ui.actions.SelectionConverter;
import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.mylar.context.ui.InterestFilter;
import org.eclipse.mylar.internal.context.ui.actions.AbstractAutoFocusViewAction;
import org.eclipse.mylar.internal.java.ui.JavaDeclarationsFilter;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;

/**
 * @author Mik Kersten
 */
public class FocusPackageExplorerAction extends AbstractAutoFocusViewAction {

	public FocusPackageExplorerAction() {
		super(new InterestFilter(), true, true, true);
	}

	@Override
	protected ISelection resolveSelection(IEditorPart part, ITextSelection changedSelection, StructuredViewer viewer)
			throws CoreException {
		Object elementToSelect = null;
		if (changedSelection instanceof TextSelection && part instanceof JavaEditor) {
			TextSelection textSelection = (TextSelection) changedSelection;
			IJavaElement javaElement = SelectionConverter.resolveEnclosingElement((JavaEditor) part, textSelection);
			if (javaElement != null) {
				elementToSelect = javaElement;
			}
		}

		if (elementToSelect != null) {
			StructuredSelection currentSelection = (StructuredSelection) viewer.getSelection();
			if (currentSelection.size() <= 1) {
				for (ViewerFilter filter : Arrays.asList(viewer.getFilters())) {
					if (filter instanceof JavaDeclarationsFilter && elementToSelect instanceof IMember) {
						elementToSelect = ((IMember) elementToSelect).getCompilationUnit();
					}
				} 
			}
		}
		return new StructuredSelection(elementToSelect);
	}

	@Override
	protected void setDefaultLinkingEnabled(boolean on) {
		IViewPart part = super.getPartForAction();
		if (part instanceof PackageExplorerPart) {
			((PackageExplorerPart)part).setLinkingEnabled(on);
		}
	}

	@Override
	protected boolean isDefaultLinkingEnabled() {
		IViewPart part = super.getPartForAction();
		if (part instanceof PackageExplorerPart) {
			return ((PackageExplorerPart)part).isLinkingEnabled();
		}
		return false;
	}
	
	// @Override
	// public void run(IAction action) {
	// super.run(action);
	// List<StructuredViewer> viewers = getViewers();
	// if (viewers.size() == 1) {
	// StructuredViewer viewer = getViewers().get(0);
	// // bug 159333: work-around for viewer's auto-collapse
	// Object element = null;
	// IEditorPart activeEditor =
	// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
	// .getActiveEditor();
	// if (activeEditor != null) {
	// Object input = getElementOfInput(activeEditor.getEditorInput());
	// if (input instanceof IFile) {
	// element = JavaCore.create((IFile) input);
	// }
	//
	// if (element == null) { // try a non Java resource
	// element = input;
	// }
	//
	// if (element != null) {
	// ISelection newSelection = new StructuredSelection(element);
	// if (viewer.getSelection().equals(newSelection)) {
	// viewer.reveal(element);
	// } else {
	// viewer.setSelection(newSelection, true);
	// }
	// }
	// }
	// }
	// }

	// /**
	// * Copied from PackageExplorerPart
	// */
	// private Object getElementOfInput(IEditorInput input) {
	// if (input instanceof IClassFileEditorInput)
	// return ((IClassFileEditorInput) input).getClassFile();
	// else if (input instanceof IFileEditorInput)
	// return ((IFileEditorInput) input).getFile();
	// else if (input instanceof JarEntryEditorInput)
	// return ((JarEntryEditorInput) input).getStorage();
	// return null;
	// }

	@Override
	public List<StructuredViewer> getViewers() {
		List<StructuredViewer> viewers = new ArrayList<StructuredViewer>();
		// TODO: get from super
		IViewPart part = super.getPartForAction();
		if (part instanceof PackageExplorerPart) {
			viewers.add(((PackageExplorerPart) part).getTreeViewer());
		}
		return viewers;
	}
}
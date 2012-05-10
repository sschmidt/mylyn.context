/*******************************************************************************
 * Copyright (c) 2012 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.context.ui.state;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPage;

/**
 * Provides access to E4 API using reflection to avoid compile time dependencies.
 * 
 * @author Steffen Pingel
 */
// TODO e4.2 remove reflection
public class E4EditorReflector {

	private static final String MODEL_ELEMENT_ID = "org.eclipse.e4.ui.compatibility.editor"; //$NON-NLS-1$

	Class<?> eModelServiceClazz;

	Class<?> mUiElementClazz;

	Class<?> mPartClazz;

	private Class<?> ePartServiceClazz;

	private Class<?> partStateClazz;

	private Class<?> mApplicationElementClazz;

	public E4EditorReflector() {
		try {
			eModelServiceClazz = Class.forName("org.eclipse.e4.ui.workbench.modeling.EModelService"); //$NON-NLS-1$
			ePartServiceClazz = Class.forName("org.eclipse.e4.ui.workbench.modeling.EPartService"); //$NON-NLS-1$
			partStateClazz = Class.forName("org.eclipse.e4.ui.workbench.modeling.EPartService$PartState"); //$NON-NLS-1$
			mUiElementClazz = Class.forName("org.eclipse.e4.ui.model.application.ui.MUIElement"); //$NON-NLS-1$
			mPartClazz = Class.forName("org.eclipse.e4.ui.model.application.ui.basic.MPart"); //$NON-NLS-1$
			mApplicationElementClazz = Class.forName("org.eclipse.e4.ui.model.application.MApplicationElement");
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	private RuntimeException handleException(Exception e) {
		return new RuntimeException(e);
	}

	public List<?> findElements(IWorkbenchPage page) {
		try {
			// page.getWindowModel()
			Method getWindowModel = page.getClass().getDeclaredMethod("getWindowModel"); //$NON-NLS-1$
			Object windowModel = getWindowModel.invoke(page);

			// EModelService modelService = PlatformUI.getWorkbench().getService(EModelService.class);
			Object modelService = getModelService();
			Method findElements = eModelServiceClazz.getDeclaredMethod("findElements", mUiElementClazz, String.class, //$NON-NLS-1$
					Class.class, List.class, int.class);

			//List<MPart> editors = modelService.findElements(page.getWindowModel(), CompatibilityEditor.MODEL_ELEMENT_ID, MPart.class, null, EModelService.IN_SHARED_AREA);
			List<?> editors = (List<?>) findElements.invoke(modelService, windowModel, MODEL_ELEMENT_ID, mPartClazz,
					null, 0x08);
			return editors;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	private Object getModelService() {
		return PlatformUI.getWorkbench().getService(eModelServiceClazz);
	}

	private Object getPartService(IWorkbenchWindow window) {
		return window.getService(ePartServiceClazz);
	}

	public Map<String, String> getPersistedState(Object editor) {
		try {
			//String state = editor.getPersistedState().get("memento");
			Method getPersistedStateMethod = mApplicationElementClazz.getDeclaredMethod("getPersistedState");
			Map<String, String> persistedState = (Map<String, String>) getPersistedStateMethod.invoke(editor);
			return persistedState;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public Object createPart(IWorkbenchWindow window) {
		try {
			Object partService = getPartService(window);
			// MPart editor = partService.createPart(CompatibilityEditor.MODEL_ELEMENT_ID);
			Method method = ePartServiceClazz.getDeclaredMethod("createPart", String.class); //$NON-NLS-1$
			return method.invoke(partService, MODEL_ELEMENT_ID);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public void showPart(WorkbenchPage page, Object editor) {
		try {
			//EditorReference reference = page.createEditorReferenceForPart(editor, null, editor.getElementId(), null);
			Method method = mApplicationElementClazz.getDeclaredMethod("getElementId");
			String elementId = (String) method.invoke(editor);

			method = WorkbenchPage.class.getDeclaredMethod("createEditorReferenceForPart", mPartClazz,
					IEditorInput.class, String.class, IMemento.class);
			method.invoke(page, editor, null, elementId, null);

			Object partService = getPartService(page.getWorkbenchWindow());
			// PartState.ACTIVATE
			Object partStateActivate = partStateClazz.getEnumConstants()[0];
			//partService.showPart(editor, PartState.ACTIVATE);
			method = ePartServiceClazz.getDeclaredMethod("showPart", mPartClazz, partStateClazz);
			method.invoke(partService, editor, partStateActivate);
		} catch (Exception e) {
			throw handleException(e);
		}

	}

}

/*******************************************************************************
 * Copyright (c) 2012 Technische Universitaet Muenchen and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Chair for Applied Software Engineering (TUM) - initial API / implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.context.ui.content;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.mylyn.context.core.ContextCore;
import org.eclipse.mylyn.context.core.IContextContributor;

/**
 * @author Sebastian Schmidt
 */
public class ContributorContentProvider implements ITreeContentProvider {

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public Object[] getElements(Object inputElement) {
		Object[] elements = new Object[0];
		for (IContextContributor contributor : ContextCore.getContextContributor()) {
			Object[] currentElements = contributor.getElements(inputElement);
			if (currentElements != null) {
				elements = ArrayUtils.addAll(elements, currentElements);
			}
		}

		return elements;
	}

	public Object[] getChildren(Object parentElement) {
		Object[] elements = new Object[0];
		for (IContextContributor contributor : ContextCore.getContextContributor()) {
			Object[] children = contributor.getChildren(parentElement);
			if (children != null) {
				elements = ArrayUtils.addAll(elements, children);
			}
		}

		return elements;
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		return true;
	}

	public void dispose() {
		return;
	}
}
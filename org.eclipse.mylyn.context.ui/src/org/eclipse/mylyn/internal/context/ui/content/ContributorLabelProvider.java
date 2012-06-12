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

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.mylyn.context.core.IContributedInteractionElement;

/**
 * @author Sebastian Schmidt
 */
public class ContributorLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof IContributedInteractionElement) {
			return ((IContributedInteractionElement) element).getHandleIdentifier();
		}
		return null;
	}
}

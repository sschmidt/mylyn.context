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

package org.eclipse.mylyn.context.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.mylyn.internal.context.ui.actions.InterestDecrementAction;

/**
 * @author Sebastian Schmidt
 * @since 3.8
 */
public class RemoveFromContextAction extends Action {

	public static final String ACTION_ID = "org.eclipse.mylyn.context.ui.actions.RemoveFromContextAction"; //$NON-NLS-1$

	private final Viewer viewer;

	public RemoveFromContextAction(Viewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void run() {
		InterestDecrementAction interestDecrementAction = new InterestDecrementAction();
		interestDecrementAction.selectionChanged(null, viewer.getSelection());
		interestDecrementAction.run(null);
	}

	@Override
	public String getText() {
		return "Remove From Context"; //$NON-NLS-1$
	}

	@Override
	public String getId() {
		return ACTION_ID;
	}
}

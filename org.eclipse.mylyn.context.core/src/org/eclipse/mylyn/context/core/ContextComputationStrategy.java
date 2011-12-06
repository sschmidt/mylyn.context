/*******************************************************************************
 * Copyright (c) 2011 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.context.core;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A strategy for computing context of a task. Implementations take a task data and from it derive a list of objects
 * that can be added to the context.
 * 
 * @author David Green
 * @since 3.6
 */
public abstract class ContextComputationStrategy {

	/**
	 * Computes a list of objects that should be added to the context based on the task data.
	 */
	public abstract List<Object> computeContext(IInteractionContext context, IAdaptable input, IProgressMonitor monitor);

}

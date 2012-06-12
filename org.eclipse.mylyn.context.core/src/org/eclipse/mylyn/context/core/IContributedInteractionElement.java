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

package org.eclipse.mylyn.context.core;

/**
 * @author Sebastian Schmidt
 * @since 3.8
 */
public interface IContributedInteractionElement extends IInteractionElement {

	public IContextContributor getContextContributor();

	public Object getReference();
}

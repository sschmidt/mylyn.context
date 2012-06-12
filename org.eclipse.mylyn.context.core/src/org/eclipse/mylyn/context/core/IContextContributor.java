/*******************************************************************************
 * Copyright (c) 2012 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sebastian Schmidt - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.context.core;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author Sebastian Schmidt
 * @since 3.8
 */
public interface IContextContributor {

	public InputStream getSerializedContextInformation();

	public String getIdentifier();

	public boolean isInteresting(IInteractionElement contributorElement, double threshold);

	public Object[] getElements(Object inputElement);

	public Object[] getChildren(Object parentElement);

	public Collection<IContributedInteractionElement> getAllElements();

	public void removeElements(List<IInteractionElement> removedElements);

	public IContributedInteractionElement getInteractionElement(Object visibleObject);

	public boolean isLandmark(IContributedInteractionElement interactionElement);

}

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

package org.eclipse.mylyn.internal.context.core;

import java.util.List;

import org.eclipse.mylyn.context.core.IContextContributor;
import org.eclipse.mylyn.context.core.IContributedInteractionElement;
import org.eclipse.mylyn.context.core.IDegreeOfInterest;
import org.eclipse.mylyn.monitor.core.InteractionEvent;

/**
 * @author Sebastian Schmidt
 */
public class SimpleDegreeOfInterest implements IDegreeOfInterest {

	private final IContributedInteractionElement interactionElement;

	private final IContextContributor contributor;

	public SimpleDegreeOfInterest(IContextContributor contributor, IContributedInteractionElement interactionElement) {
		this.contributor = contributor;
		this.interactionElement = interactionElement;
	}

	public boolean isPropagated() {
		return true;
	}

	public boolean isPredicted() {
		return false;
	}

	public boolean isLandmark() {
		return contributor.isLandmark(interactionElement);
	}

	public boolean isInteresting() {
		return contributor.isInteresting(interactionElement, 0.0);
	}

	public float getEncodedValue() {
		return 0;
	}

	public float getDecayValue() {
		return 0;
	}

	public float getValue() {
		return 0;
	}

	public List<InteractionEvent> getEvents() {
		return null;
	}

}

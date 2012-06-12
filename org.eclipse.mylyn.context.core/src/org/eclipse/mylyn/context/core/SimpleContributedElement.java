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

import java.util.Collection;

import org.eclipse.mylyn.internal.context.core.InteractionContextRelation;
import org.eclipse.mylyn.internal.context.core.SimpleDegreeOfInterest;

/**
 * @author Sebastian Schmidt
 * @since 3.8
 */
public class SimpleContributedElement implements IContributedInteractionElement {

	private final IContextContributor contributor;

	private final Object referencedObject;

	private String handleIdentifier;

	private final String contentType;

	private final IInteractionContext context;

	private final IDegreeOfInterest simpleDegreeOfInterest;

	public SimpleContributedElement(IContextContributor contributor, Object referencedObject, String handleIdentifier,
			String contentType) {
		this.contributor = contributor;
		this.referencedObject = referencedObject;
		this.handleIdentifier = handleIdentifier;
		this.contentType = contentType;
		this.context = ContextCore.getContextManager().getActiveContext();
		this.simpleDegreeOfInterest = new SimpleDegreeOfInterest(contributor, this);
	}

	public String getHandleIdentifier() {
		return handleIdentifier;
	}

	public void setHandleIdentifier(String handle) {
		this.handleIdentifier = handle;
	}

	public IInteractionContext getContext() {
		return context;
	}

	public Collection<InteractionContextRelation> getRelations() {
		// ignore
		return null;
	}

	public IInteractionRelation getRelation(String targetHandle) {
		// ignore
		return null;
	}

	public IDegreeOfInterest getInterest() {
		return simpleDegreeOfInterest;
	}

	public String getContentType() {
		return contentType;
	}

	public IContextContributor getContextContributor() {
		return contributor;
	}

	public Object getReference() {
		return referencedObject;
	}

	public void clearRelations() {
	}

}

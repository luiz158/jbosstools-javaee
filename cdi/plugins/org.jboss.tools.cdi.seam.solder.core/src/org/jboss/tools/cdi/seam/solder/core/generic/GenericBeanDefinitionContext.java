/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/
package org.jboss.tools.cdi.seam.solder.core.generic;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.IType;
import org.jboss.tools.cdi.core.extension.AbstractDefinitionContextExtension;
import org.jboss.tools.cdi.internal.core.impl.definition.AnnotationDefinition;
import org.jboss.tools.cdi.seam.solder.core.CDISeamSolderConstants;

/**
 * 
 * @author Viacheslav Kabanovich
 *
 */
public class GenericBeanDefinitionContext extends AbstractDefinitionContextExtension {
	Map<String, GenericConfiguration> genericConfiguartions = new HashMap<String, GenericConfiguration>();

	protected AbstractDefinitionContextExtension copy(boolean clean) {
		GenericBeanDefinitionContext copy = new GenericBeanDefinitionContext();
		copy.root = root;
		if(!clean) {
			copy.genericConfiguartions.putAll(genericConfiguartions);
			//
		}
		return copy;
	}

	@Override
	protected void doApplyWorkingCopy() {
		genericConfiguartions = ((GenericBeanDefinitionContext)workingCopy).genericConfiguartions;
		//
	}

	@Override
	public void clean() {
		genericConfiguartions.clear();
		//
	}

	@Override
	public void clean(String typeName) {
		genericConfiguartions.remove(typeName);
		for (GenericConfiguration c: genericConfiguartions.values()) {
			c.clear(typeName);
		}
		//
	}

	@Override
	public void computeAnnotationKind(AnnotationDefinition annotation) {
		if(annotation.isAnnotationPresent(CDISeamSolderConstants.GENERIC_TYPE_ANNOTATION_TYPE_NAME)) {
			annotation.setExtendedKind(CDISeamSolderConstants.GENERIC_ANNOTATION_KIND);
			String qn = annotation.getType().getFullyQualifiedName();
			getGenericConfiguration(qn).setGenericTypeDefinition(annotation);
		}
	}

	public boolean isGenericTypeAnnotation(IType type) {
		return (genericConfiguartions.containsKey(type.getFullyQualifiedName()));
	}

	public Map<String, GenericConfiguration> getGenericConfigurations() {
		return genericConfiguartions;
	}

	public GenericConfiguration getGenericConfiguration(String typeName) {
		GenericConfiguration result = genericConfiguartions.get(typeName);
		if(result == null) {
			result = new GenericConfiguration(typeName);
			genericConfiguartions.put(typeName, result);
		}
		return result;
	}
}
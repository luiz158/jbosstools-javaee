/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.jsf.model.impl;

import org.jboss.tools.common.model.XModelException;
import org.jboss.tools.common.model.impl.RegularObjectImpl;

public class EntryObjectImpl extends RegularObjectImpl {
    private static final long serialVersionUID = 535265808617651721L;
	
	public boolean isNullValue() {
		return "true".equals(getAttributeValue("null-value"));
	}
	
	public boolean isAttributeEditable(String name) {
		if(!super.isAttributeEditable(name)) return false;
		if("value".equals(name)) {
			return !isNullValue();
		}
		return true;
	}
	
	String cachedValue = null;
	
	protected void onAttributeValueEdit(String name, String oldValue, String newValue) throws XModelException {
		if("null-value".equals(name)) {
			if("true".equals(newValue)) {
				cachedValue = getAttributeValue("value");
				setAttributeValue("value", "");
			} else if(cachedValue != null) {
				setAttributeValue("value", cachedValue);
			}
		}
	}

}

/******************************************************************************* 
 * Copyright (c) 2010 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.cdi.text.ext.hyperlink;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.jboss.tools.cdi.core.IInjectionPoint;
import org.jboss.tools.cdi.core.IInjectionPointField;
import org.jboss.tools.cdi.core.IInjectionPointMethod;
import org.jboss.tools.cdi.core.IObserverMethod;
import org.jboss.tools.cdi.text.ext.CDIExtensionsMessages;
import org.jboss.tools.cdi.text.ext.CDIExtensionsPlugin;
import org.jboss.tools.common.text.ext.hyperlink.AbstractHyperlink;

public class EventHyperlink extends AbstractHyperlink{
	IInjectionPoint event;
	IRegion region;
	
	public EventHyperlink(IRegion region, IInjectionPoint event, IDocument document){
		this.event = event;
		this.region = region;
		setDocument(document);
	}
	

	@Override
	protected IRegion doGetHyperlinkRegion(int offset) {
		return region;
	}

	protected void doHyperlink(IRegion region) {
		IEditorPart part = null;
		
		if(event != null && event.getClassBean() != null){
			try{
				part = JavaUI.openInEditor(event.getClassBean().getBeanClass());
			}catch(JavaModelException ex){
				CDIExtensionsPlugin.log(ex);
			}catch(PartInitException ex){
				CDIExtensionsPlugin.log(ex);
			}
			
			IJavaElement element = event.getClassBean().getBeanClass();
			if(event instanceof IInjectionPointField)
				element = ((IInjectionPointField)event).getField();
			else if(event instanceof IInjectionPointMethod)
				element = ((IInjectionPointMethod)event).getMethod();
			
			if (part != null) {
				JavaUI.revealInEditor(part, element);
			} 
		}
		if (part == null)
			openFileFailed();
	}

	@Override
	public String getHyperlinkText() {
		String text = CDIExtensionsMessages.CDI_EVENT_HYPERLINK_OPEN_EVENT+" "+event.getClassBean().getBeanClass().getElementName();
		
		if(event instanceof IInjectionPointField)
			text += "."+((IInjectionPointField)event).getField().getElementName();
		else if(event instanceof IInjectionPointMethod)
			text += "."+((IInjectionPointMethod)event).getMethod().getElementName();
		
		return text;
	}

}
package org.jboss.jsr299.tck.tests.jbt.builtin;

import javax.enterprise.context.Conversation;
import javax.inject.Named;

@Named("javax.enterprise.context.conversation")
public class ConversationImpl implements Conversation {

	public ConversationImpl(String s) {}

	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void begin(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTransient() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTimeout(long arg0) {
		// TODO Auto-generated method stub
		
	}

}

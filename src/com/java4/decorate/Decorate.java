package com.java4.decorate;

public abstract class Decorate extends Component{
	protected Component component;
	
	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	@Override
	public void operation() {
		// TODO Auto-generated method stub
		if(component!=null)
			component.operation();
		
	}
	
}

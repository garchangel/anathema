package net.sf.anathema.lib.workflow.booleanvalue;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public interface IBooleanValueView {

  public void setSelected(boolean selected);

  public void addChangeListener(IBooleanValueChangedListener listener);

}

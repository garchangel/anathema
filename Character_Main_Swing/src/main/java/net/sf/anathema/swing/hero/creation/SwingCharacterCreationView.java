package net.sf.anathema.swing.hero.creation;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.framework.swing.selection.ListObjectSelectionView;
import net.sf.anathema.hero.creation.CharacterCreationView;
import net.sf.anathema.hero.creation.ToggleButtonPanel;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SwingCharacterCreationView implements CharacterCreationView {

  private final JPanel component;

  public SwingCharacterCreationView() {
    this.component = new JPanel(new MigLayout(LayoutUtils.withoutInsets().gridGapX("10")));
  }

  @Override
  public ToggleButtonPanel addToggleButtonPanel() {
    SwingToggleButtonPanel panel = new SwingToggleButtonPanel();
    component.add(panel.getComponent(), new CC().grow().pushY());
    return panel;
  }

  public JComponent getContent() {
    return component;
  }

  @Override
  public VetoableObjectSelectionView<HeroTemplate> addObjectSelectionList() {
    ListObjectSelectionView<HeroTemplate> view = new ListObjectSelectionView<>(HeroTemplate.class);
    JScrollPane scrollPane = new JScrollPane(view.getComponent());
    component.add(scrollPane, new CC().grow().push());
    return view;
  }
}
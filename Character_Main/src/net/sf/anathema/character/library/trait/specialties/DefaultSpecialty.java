package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.DefaultTraitType;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.hero.model.Hero;

public class DefaultSpecialty extends DefaultTrait implements Specialty {

  private final String subTraitName;
  private final AbstractSubTraitContainer container;
  private final TraitType type;

  private static TraitRules createSpecialtyRules(Hero hero) {
    return new TraitRules(new DefaultTraitType("Specialty"), SimpleTraitTemplate.createStaticLimitedTemplate(0, 3), hero);
  }

  public DefaultSpecialty(Hero hero, AbstractSubTraitContainer container, TraitType type, String specialtyName) {
    super(hero, createSpecialtyRules(hero), new FriendlyValueChangeChecker());
    this.container = container;
    this.type = type;
    this.subTraitName = specialtyName;
  }

  @Override
  public String getName() {
    return subTraitName;
  }

  @Override
  public TraitType getBasicTraitType() {
    return type;
  }

  @Override
  public void setCurrentValue(int value) {
    int increment = value - getCurrentValue();
    if (container.getCurrentDotTotal() + increment <= SpecialtiesContainer.ALLOWED_SPECIALTY_COUNT) {
      super.setCurrentValue(value);
    } else {
      super.resetCurrentValue();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DefaultSpecialty)) {
      return false;
    }
    DefaultSpecialty other = (DefaultSpecialty) obj;
    return super.equals(obj) && other.getName().equals(getName()) && other.getBasicTraitType() == getBasicTraitType();
  }

  @Override
  public int hashCode() {
    return getName().hashCode() + getBasicTraitType().hashCode();
  }
}
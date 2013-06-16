package net.sf.anathema.character.main.attributes.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.attributes.template.AttributeGroup;
import net.sf.anathema.character.main.attributes.template.AttributeTemplate;
import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.List;

public class AttributesModel implements AttributesList, CharacterModel {

  public static final SimpleIdentifier MODEL_ID = new SimpleIdentifier("attributes");
  private AttributeTemplate template;

  public AttributesModel(AttributeTemplate template) {
    this.template = template;
  }

  @Override
  public void initialize(ChangeAnnouncer announcer, Hero hero) {
    // nothing to do until now
  }

  @Override
  public Identifier getId() {
    return MODEL_ID;
  }

  public int getTraitMaximum(IGenericCharacter character) {
    ICharacterTemplate characterTemplate = character.getTemplate();
    TraitType traitType = characterTemplate.getAttributeGroups()[0].getTraitType();
    ITraitTemplate template = characterTemplate.getTraitTemplateCollection().getTraitTemplate(traitType);
    return template.getLimitation().getAbsoluteLimit(character);
  }

  public void iterate(AttributesIterator iterator) {
    for (AttributeGroup group : template.groups) {
      iterator.nextGroup(new SimpleIdentifier(group.id));
      iterateGroup(iterator, group.attributes);
    }
  }

  private void iterateGroup(AttributesIterator iterator, List<String> attributeIds) {
    for (String traitId : attributeIds) {
      iterateTrait(iterator, new SimpleIdentifier(traitId));
    }
  }

  private void iterateTrait(AttributesIterator iterator, Identifier traitId) {
    iterator.nextTrait(traitId);
  }
}
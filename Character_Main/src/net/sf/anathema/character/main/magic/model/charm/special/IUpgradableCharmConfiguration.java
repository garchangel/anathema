package net.sf.anathema.character.main.magic.model.charm.special;

public interface IUpgradableCharmConfiguration extends IMultipleEffectCharmConfiguration {
  int getUpgradeBPCost();

  int getUpgradeXPCost();
}